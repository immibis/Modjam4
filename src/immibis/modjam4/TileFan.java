package immibis.modjam4;

import java.util.List;

import immibis.modjam4.shaftnet.ShaftNode;
import immibis.modjam4.shaftnet.SpeedTorqueCurve;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileFan extends TileShaft implements SpeedTorqueCurve {
	
	@Override
	protected ShaftNode createShaftNode() {
		return new ShaftNode(this) {
			@Override
			public SpeedTorqueCurve getSpeedTorqueCurve() {
				return TileFan.this;
			}
		};
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		double angvel_dps = Math.abs(ShaftUtils.toDegreesPerSecond((int)shaftNode.getNetwork().angvel));
		
		int range = (int)angvel_dps/32;
		
		switch(getBlockMetadata()) {
		case 5: // -X
			for(Entity e : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getAABBPool().getAABB(xCoord-0.5-range, yCoord-0.5, zCoord-0.5, xCoord-0.5, yCoord+1.5, zCoord+1.5))) {
				if(e instanceof EntityPlayer)
					continue;
				e.motionX = e.motionX*0.8 - 0.05;
				e.motionY += 0.04;
				e.motionZ *= 0.8;
				e.fallDistance = 0;
				e.setPosition(e.posX, e.posY+(yCoord+0.5-e.posY)*0.1, e.posZ+(zCoord+0.5-e.posZ)*0.1);
			}
			break;
		case 1: // -Y
			for(Entity e : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getAABBPool().getAABB(xCoord-0.5, yCoord-0.5, zCoord-0.5, xCoord+1.5, yCoord-0.5-range, zCoord+1.5))) {
				if(e instanceof EntityPlayer)
					continue;
				e.motionX *= 0.8;
				e.motionY -= 0.1;
				e.motionZ *= 0.8;
				e.fallDistance = 0;
				e.setPosition(e.posX+(xCoord+0.5-e.posX)*0.1, e.posY, e.posZ+(zCoord+0.5-e.posZ)*0.1);
			}
		}
	}
	
	@Override
	public long getTorqueAtSpeed(long speed) {
		return 0;
	}
}
