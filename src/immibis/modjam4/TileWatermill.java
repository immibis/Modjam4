package immibis.modjam4;

import immibis.modjam4.shaftnet.ShaftNode;
import immibis.modjam4.shaftnet.SpeedTorqueCurve;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

public class TileWatermill extends TileShaft implements SpeedTorqueCurve {
	
	private static final int maxSpeed = ShaftUtils.fromDegreesPerSecond(45);
	private int maxTorque;
	private boolean obstructed;
	
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
				return TileWatermill.this;
			}
		};
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		int meta = getBlockMetadata();
		
		obstructed = false;
		maxTorque = 0;
		
		int level1, level2, level3, numfalls;
		switch(getBlockMetadata()) {
		case 0: // shaft on Y axis; ??????
		default:
			return;
			
		case 2: // shaft on Z axis; water on X axis
			level1 = getLiquidLevel(-1, -2, 0);
			level2 = getLiquidLevel(0, -2, 0);
			level3 = getLiquidLevel(1, -2, 0);
			numfalls = 0;
			for(int k = -2; k < 3; k++)
				numfalls += isWaterfall(-2, k, 0) - isWaterfall(2, k, 0);
			break;
			
		case 4: // shaft on X axis; water on Z axis
			level1 = getLiquidLevel(0, -2, -1);
			level2 = getLiquidLevel(0, -2, 0);
			level3 = getLiquidLevel(0, -2, 1);
			numfalls = 0;
			for(int k = -2; k < 3; k++)
				numfalls += isWaterfall(0, k, -2) - isWaterfall(0, k, 2);
			break;
		}
		
		
		if(level1 == LL_OBSTRUCTION || level2 == LL_OBSTRUCTION || level3 == LL_OBSTRUCTION || numfalls < -1000 || numfalls > 1000) {
			// water wheel is obstructed
			obstructed = true;
			
			getBlockType().dropBlockAsItem(worldObj, xCoord, yCoord, zCoord, 0, 0);
			worldObj.setBlockToAir(xCoord, yCoord, zCoord);
			
			return;
		}
		
		{
			int TORQUE_PER_LEVEL_DIFF = ShaftUtils.fromDegreesPerSecond(13) / 10;
			int TORQUE_PER_WATERFALL = ShaftUtils.fromDegreesPerSecond(4) / 10;
			maxTorque = TORQUE_PER_WATERFALL * numfalls;
			if((level3 >= level2 && level2 >= level1) || (level1 >= level2 && level2 >= level3))
				maxTorque += TORQUE_PER_LEVEL_DIFF * (level1 - level3);
			//int diff = targetAngvel - shaftNode.getNetwork().angvel;
			//shaftNode.getNetwork().angvel += diff * 0.5;
		}
	}
	
	
	private int getLiquidLevel(int dx, int dy, int dz) {
		return getLiquidLevel(worldObj, dx+xCoord, dy+yCoord, dz+zCoord);
	}
	
	public static int getLiquidLevel(World worldObj, int x, int y, int z) {
		if(!worldObj.blockExists(x,y,z))
			return 0;
		Block b = worldObj.getBlock(x, y, z);
		if(!(b instanceof BlockLiquid))
			if(worldObj.isAirBlock(x, y, z))
				return 0;
			else
				return LL_OBSTRUCTION;
		
		return 7 - (worldObj.getBlockMetadata(x, y, z) & 7);
	}
	
	private int isWaterfall(int dx, int dy, int dz) {
		dx+=xCoord; dy+=yCoord; dz+=zCoord;
		if(!worldObj.blockExists(dx,dy,dz))
			return 0;
		Block b = worldObj.getBlock(dx, dy, dz);
		if(!(b instanceof BlockLiquid))
			if(worldObj.isAirBlock(dx, dy, dz))
				return 0;
			else
				return -10000000;
		
		return (worldObj.getBlockMetadata(dx, dy, dz) & 8) >> 3;
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getAABBPool().getAABB(xCoord-3, yCoord-3, zCoord-3, xCoord+3, yCoord+3, zCoord+3);
	}
}
