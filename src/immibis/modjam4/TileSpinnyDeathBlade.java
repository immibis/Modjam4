package immibis.modjam4;

import java.util.List;

import immibis.modjam4.shaftnet.ShaftNode;
import immibis.modjam4.shaftnet.SpeedTorqueCurve;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;

public class TileSpinnyDeathBlade extends TileShaft {
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if(!worldObj.isRemote) {
			
			long angvel = shaftNode.getNetwork().angvel;
			int lastAngle = angle;
			angle += (int)(angvel*4);
			if(Math.abs(angvel) >= (1L << 30) || (angvel > 0 ? angle < lastAngle : angle > lastAngle)) {
				
				int damage = (int)Math.min(5000, Math.abs(angvel) / ShaftUtils.fromDegreesPerSecond(10));
				
				AxisAlignedBB bb = null;
				switch(getBlockMetadata()) {
				case 0: case 1:
					bb = AxisAlignedBB.getAABBPool().getAABB(xCoord-4, yCoord, zCoord-4, xCoord+5, yCoord+1, zCoord+5);
					break;
				case 2: case 3:
					bb = AxisAlignedBB.getAABBPool().getAABB(xCoord-4, yCoord-4, zCoord, xCoord+5, yCoord+5, zCoord+1);
					break;
				case 4: case 5:
					bb = AxisAlignedBB.getAABBPool().getAABB(xCoord, yCoord-4, zCoord-4, xCoord+1, yCoord+5, zCoord+5);
					break;
				}
				
				if(bb != null) {
					for(EntityLivingBase e : (List<EntityLivingBase>)worldObj.getEntitiesWithinAABB(EntityLivingBase.class, bb)) {
						double dx = e.posX - xCoord - 0.5;
						double dy = e.posY - yCoord - 0.5;
						double dz = e.posZ - zCoord - 0.5;
						if(dx*dx + dy*dy + dz*dz > 4.5*4.5)
							continue;
						e.attackEntityFrom(Modjam4Mod.damageSourceSpinnyBlade, damage);
					}
				}
				
			}
		}
	}
	
	private int angle;
	
	@Override
	protected ShaftNode createShaftNode() {
		return new ShaftNode(this) {
			@Override
			public SpeedTorqueCurve getSpeedTorqueCurve() {
				return new SpeedTorqueCurve() {
					@Override
					public long getTorqueAtSpeed(long speed) {
						return -speed;
					}
				};
			}
		};
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getAABBPool().getAABB(xCoord-4, yCoord-4, zCoord-4, xCoord+5, yCoord+5, zCoord+5);
	}
}
