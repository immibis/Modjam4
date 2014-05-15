package immibis.modjam4;

import net.minecraft.tileentity.TileEntity;

public class TileCreativeEngine extends TileEntity implements IShaft {

	@Override
	public int getAngle(int side) {
		return angle;
	}

}
