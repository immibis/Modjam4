package immibis.modjam4;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fluids.BlockFluidClassic;

public class TileWatermill extends TileShaft {
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		int level1, level2, level3;
		switch(getBlockMetadata()) {
		case 0: // shaft on Y axis; ??????
		default:
			return;
			
		case 2: // shaft on Z axis; water on X axis
			level1 = getLiquidLevel(-1, -2, 0);
			level2 = getLiquidLevel(0, -2, 0);
			level3 = getLiquidLevel(1, -2, 0);
			break;
			
		case 4: // shaft on X axis; water on Z axis
			level1 = getLiquidLevel(0, -2, -1);
			level2 = getLiquidLevel(0, -2, 0);
			level3 = getLiquidLevel(0, -2, 1);
			break;
		}
		
		shaftNode.getNetwork().angvel += ShaftUtils.fromDegreesPerSecond(0.5 * (level3 - level1));
	}
	
	private int getLiquidLevel(int dx, int dy, int dz) {
		dx+=xCoord; dy+=yCoord; dz+=zCoord;
		if(!worldObj.blockExists(dx,dy,dz))
			return 0;
		Block b = worldObj.getBlock(dx, dy, dz);
		if(!(b instanceof BlockLiquid) && !(b instanceof BlockFluidClassic))
			return 0;
		
		return worldObj.getBlockMetadata(dx, dy, dz) & 7;
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getAABBPool().getAABB(xCoord-3, yCoord-3, zCoord-3, xCoord+3, yCoord+3, zCoord+3);
	}
}
