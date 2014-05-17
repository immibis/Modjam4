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
		
		//int range = (int)angvel_dps/32;
		int range = 8;
		
		double speed = angvel_dps / 160 * 0.05;
		
		switch(getBlockMetadata()) {
		case 5: // -X
			for(Entity e : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getAABBPool().getAABB(xCoord-0.5-range, yCoord-0.5, zCoord-0.5, xCoord-0.5, yCoord+1.5, zCoord+1.5))) {
				if(e instanceof EntityPlayer)
					continue;
				e.motionX -= speed;
			}
			break;
		case 4: // +X
			for(Entity e : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getAABBPool().getAABB(xCoord+0.5, yCoord-0.5, zCoord-0.5, xCoord+0.5+range, yCoord+1.5, zCoord+1.5))) {
				if(e instanceof EntityPlayer)
					continue;
				e.motionX += speed;
			}
			break;
		case 3: // -Z
			for(Entity e : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getAABBPool().getAABB(xCoord-0.5, yCoord-0.5, zCoord-0.5-range, xCoord+1.5, yCoord+1.5, zCoord-0.5))) {
				if(e instanceof EntityPlayer)
					continue;
				e.motionZ -= speed;
			}
			break;
		case 2: // +Z
			for(Entity e : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getAABBPool().getAABB(xCoord-0.5, yCoord-0.5, zCoord+0.5, xCoord+0.5, yCoord+1.5, zCoord+0.5+range))) {
				if(e instanceof EntityPlayer)
					continue;
				e.motionZ += speed;
			}
			break;
		case 1: // -Y
			for(Entity e : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getAABBPool().getAABB(xCoord-0.5, yCoord-0.5, zCoord-0.5, xCoord+1.5, yCoord-0.5-range, zCoord+1.5))) {
				if(e instanceof EntityPlayer)
					continue;
				e.motionY -= speed;
			}
			break;
		case 0: // +Y
			for(Entity e : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getAABBPool().getAABB(xCoord-0.5, yCoord+0.5, zCoord-0.5, xCoord+1.5, yCoord+0.5+range, zCoord+1.5))) {
				if(e instanceof EntityPlayer)
					continue;
				e.motionY += speed*3;
			}
			break;
		}
	}
	
	@Override
	public long getTorqueAtSpeed(long speed) {
		return -speed / 50;
	}
}
