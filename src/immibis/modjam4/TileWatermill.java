package immibis.modjam4;

import net.minecraft.util.AxisAlignedBB;

public class TileWatermill extends TileShaft {
	@Override
	public void updateEntity() {
		super.updateEntity();
		shaftNode.getNetwork().angvel += ShaftUtils.fromDegreesPerSecond(3);
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getAABBPool().getAABB(xCoord-3, yCoord-3, zCoord-3, xCoord+3, yCoord+3, zCoord+3);
	}
}
