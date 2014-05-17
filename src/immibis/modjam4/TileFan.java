package immibis.modjam4;

import java.util.List;

import immibis.modjam4.shaftnet.ShaftNode;
import immibis.modjam4.shaftnet.SpeedTorqueCurve;
import net.minecraft.entity.Entity;
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
		
		double angvel_dps = ShaftUtils.toDegreesPerSecond((int)shaftNode.getNetwork().angvel);
		
		int range = 7;
		
		switch(getBlockMetadata()) {
		case 5: // -X
			for(Entity e : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getAABBPool().getAABB(xCoord-0.5-range, yCoord-0.5, zCoord-0.5, xCoord-0.5, yCoord+0.5, zCoord+0.5))) {
				e.motionX = -0.05;
				e.posZ = (e.posZ * 0.9) + (zCoord + 0.5) * 0.1;
				e.posZ = zCoord + 0.5;
				e.posY = yCoord + 0.5;
				e.lastTickPosY = e.posY;
				e.prevPosY = e.posY;
				e.motionY = 0.0001;
				e.prevPosZ = e.lastTickPosZ = e.posZ;
			}
			break;
		}
	}
	
	@Override
	public long getTorqueAtSpeed(long speed) {
		return 0;
	}
}
