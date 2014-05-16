package immibis.modjam4;

import net.minecraft.tileentity.TileEntity;

public class TileCreativeEngine extends TileEntity implements IShaft {

	public int angle; // INT_MIN to INT_MAX
	public int angvel = 3 << 24; // angle units per tick
	
	@Override
	public int getAngle(int side) {
		return angle;
	}
	
	@Override
	public int getAngVel(int side) {
		return angvel;
	}
	
	@Override
	public boolean doesShaftConnect(int side) {
		return true;
	}
	
	@Override
	public void updateEntity() {
		angvel = 5 << 25;
		angle += angvel;
	}
	
	@Override
	public double getMomentOfInertia(int side) {
		return 100000000;
	}

}
