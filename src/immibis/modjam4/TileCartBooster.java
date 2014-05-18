package immibis.modjam4;

import java.util.List;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.AxisAlignedBB;
import immibis.modjam4.shaftnet.ShaftNode;
import immibis.modjam4.shaftnet.SpeedTorqueCurve;

public class TileCartBooster extends TileShaft implements SpeedTorqueCurve {
	
	private int usePowerTimer = 0;
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if(usePowerTimer > 0)
			usePowerTimer--;
		
		// 360 deg/s = 2.0 blocks/tick (max speed enforced by EntityMinecart)
		double speed = shaftNode.getNetwork().angvel / (double)ShaftUtils.fromDegreesPerSecond(360);
		//speed *= speed;
		speed *= 2.0;
		speed = Math.abs(speed);
		
		AxisAlignedBB bb = AxisAlignedBB.getAABBPool().getAABB(xCoord, yCoord+1, zCoord, xCoord+1, yCoord+2, zCoord+1);
		for(EntityMinecart mc : (List<EntityMinecart>)worldObj.getEntitiesWithinAABB(EntityMinecart.class, bb)) {
			double curSpeed = Math.sqrt(mc.motionX*mc.motionX+mc.motionZ*mc.motionZ);
			if(curSpeed < 0.01 || curSpeed >= speed*0.9)
				continue;
			
			double mult = speed / curSpeed;
			mc.motionX *= mult;
			mc.motionZ *= mult;
			usePowerTimer += 5;
			//if(!worldObj.isRemote) System.out.println("*"+mult+" -> "+speed);
		}
	}
	
	
	
	@Override
	protected ShaftNode createShaftNode() {
		return new ShaftNode(this) {
			@Override
			public SpeedTorqueCurve getSpeedTorqueCurve() {
				return TileCartBooster.this;
			}
		};
	}



	@Override
	public long getTorqueAtSpeed(long speed) {
		return usePowerTimer > 0 ? -speed : -speed/50;
	}
}
