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
		
		double speed = angvel_dps / 160 * 0.01;
		
		final double OVERHANG = 2/16f;
		
		switch(getBlockMetadata()) {
		case 5: // -X
			for(Entity e : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getAABBPool().getAABB(xCoord-range, yCoord-OVERHANG, zCoord-OVERHANG, xCoord, yCoord+1+OVERHANG, zCoord+1+OVERHANG))) {
				if(e instanceof EntityPlayer)
					continue;
				e.motionX -= speed;
			}
			break;
		case 4: // +X
			for(Entity e : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getAABBPool().getAABB(xCoord+1, yCoord-OVERHANG, zCoord-OVERHANG, xCoord+1+range, yCoord+1+OVERHANG, zCoord+1+OVERHANG))) {
				if(e instanceof EntityPlayer)
					continue;
				e.motionX += speed;
			}
			break;
		case 3: // -Z
			for(Entity e : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getAABBPool().getAABB(xCoord-OVERHANG, yCoord-OVERHANG, zCoord-range, xCoord+1+OVERHANG, yCoord+1+OVERHANG, zCoord))) {
				if(e instanceof EntityPlayer)
					continue;
				e.motionZ -= speed;
			}
			break;
		case 2: // +Z
			for(Entity e : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getAABBPool().getAABB(xCoord-OVERHANG, yCoord-OVERHANG, zCoord+1, xCoord+1+OVERHANG, yCoord+1+OVERHANG, zCoord+1+range))) {
				if(e instanceof EntityPlayer)
					continue;
				e.motionZ += speed;
			}
			break;
		case 1: // -Y
			for(Entity e : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getAABBPool().getAABB(xCoord-OVERHANG, yCoord-range, zCoord-OVERHANG, xCoord+1+OVERHANG, yCoord, zCoord+1+OVERHANG))) {
				if(e instanceof EntityPlayer)
					continue;
				e.motionY -= speed;
				//e.motionX += (xCoord + 0.5 - e.posX) * 0.01;
				//e.motionZ += (zCoord + 0.5 - e.posZ) * 0.01;
			}
			break;
		case 0: // +Y
			for(Entity e : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getAABBPool().getAABB(xCoord-OVERHANG, yCoord+1, zCoord-OVERHANG, xCoord+1+OVERHANG, yCoord+1+range, zCoord+1+OVERHANG))) {
				if(e instanceof EntityPlayer)
					continue;
				e.motionY += speed * 2;
				//e.motionX += (xCoord + 0.5 - e.posX) * 0.01;
				//e.motionZ += (zCoord + 0.5 - e.posZ) * 0.01;
			}
			break;
		}
	}
	
	@Override
	public long getTorqueAtSpeed(long speed) {
		return -speed / 50;
	}
	
	@Override
	protected int getSideMask() {
		return 1 << getBlockMetadata();
	}
	
	@Override
	public ShaftNode getShaftNode(int side) {
		return side == getBlockMetadata() ? shaftNode : null;
	}
}
