package immibis.modjam4;

import immibis.modjam4.shaftnet.ShaftNode;
import immibis.modjam4.shaftnet.SpeedTorqueCurve;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

/**
 * Windmill is affected by non-air blocks up to 9 blocks in front of the windmill centre block, 4 blocks down, 4 blocks left and right,
 * and 4 blocks up. If a column cannot see the sky, all blocks in that column also count as blocked.
 */
public class TileWindmill extends TileShaft implements SpeedTorqueCurve {
	
	private int maxSpeed = 1;
	private int maxTorque;
	private boolean obstructed; // true if there is a block overlapping the actual windmill
	
	// power = torque * speed
	// torque = max torque * (1 - speed / max speed)
	// power = max torque * speed * (1 - speed / max speed)
	// power = max torque * speed - max torque * speed * speed / max_speed
	// scale so max torque = max speed = 1
	// power = speed - speed * speed -> max power at speed = 0.5
	// actual max power at speed = max speed / 2
	// max power = max torque * (max speed / 2) * (1 - (max speed / 2) / max spee)
	// max power = max torque * (max speed / 2) * (1/2)
	// max power = 1/4 * max torque * max speed
	
	// note: the water wheel CONSUMES power if speed > max speed
	
	@Override
	public long getTorqueAtSpeed(long speed) {
		if(obstructed || speed >= Integer.MAX_VALUE || speed <= Integer.MIN_VALUE)
			return -speed;
		if(maxTorque == 0)
			return 0;
		return maxTorque - maxTorque * speed / (maxTorque < 0 ? -maxSpeed : maxSpeed);
	}
	
	public static int LL_OBSTRUCTION = -1;
	
	@Override
	protected ShaftNode createShaftNode() {
		return new ShaftNode(this) {
			@Override
			public SpeedTorqueCurve getSpeedTorqueCurve() {
				return TileWindmill.this;
			}
		};
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		int meta = getBlockMetadata();
		
		obstructed = false;
		maxTorque = 0;
		
		if(false) {
			// windmill is obstructed
			obstructed = true;
			
			getBlockType().dropBlockAsItem(worldObj, xCoord, yCoord, zCoord, 0, 0);
			worldObj.setBlockToAir(xCoord, yCoord, zCoord);
			
			return;
		}
		
		{
			int NORMAL_TORQUE = ShaftUtils.fromDegreesPerSecond(60) / 10;
			maxTorque = NORMAL_TORQUE;
			maxSpeed = ShaftUtils.fromDegreesPerSecond(45);
		}
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getAABBPool().getAABB(xCoord-3, yCoord-3, zCoord-3, xCoord+3, yCoord+3, zCoord+3);
	}
}
