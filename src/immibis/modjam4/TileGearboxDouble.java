package immibis.modjam4;

import net.minecraft.tileentity.TileEntity;

public class TileGearboxDouble extends TileEntity implements IShaft {
	
	public int angle;
	public int angvel;

	@Override
	public int getAngle(int side) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAngVel(int side) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean doesShaftConnect(int side) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getMomentOfInertia(int side) {
		// TODO Auto-generated method stub
		return 0;
	}

}
