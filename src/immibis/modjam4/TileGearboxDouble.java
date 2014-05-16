package immibis.modjam4;

import net.minecraft.tileentity.TileEntity;

public class TileGearboxDouble extends TileMachineBase /*implements IShaft*/ {
	
	/*
	public static final double MOMENT_OF_INERTIA = TileShaft.MOMENT_OF_INERTIA * 30;
	
	// angle/angvel of low-speed side
	public int angle;
	public int angvel;
	
	@Override
	public void updateEntity() {
		int meta = getBlockMetadata();
		IShaft hsShaft = getConnectedShaft(meta);
		IShaft lsShaft = getConnectedShaft(meta^1);
		if(hsShaft == null || lsShaft == null)
			return;
		
		int hs_angvel = hsShaft.getAngVel(meta^1);
		int hs_angle = hsShaft.getAngle(meta^1);
		double hs_moi = hsShaft.getMomentOfInertia(meta^1);
		int ls_angvel = lsShaft.getAngVel(meta);
		int ls_angle = lsShaft.getAngle(meta);
		double ls_moi = lsShaft.getMomentOfInertia(meta);
		
		double totEnergy = 0.5 * (ls_moi*ls_angvel*ls_angvel + hs_moi*hs_angvel*hs_angvel + MOMENT_OF_INERTIA*angvel*angvel);
		
		// find angvel such that
		//   totEnergy = 0.5 * (ls_moi + MOMENT_OF_INERTIA + hs_moi*4)*angvel*angvel
		angvel = (int)Math.sqrt(totEnergy * 2 / (ls_moi + MOMENT_OF_INERTIA + hs_moi*4));
		
		//System.out.println(totEnergy+" "+0.5 * (ls_moi*angvel*angvel + hs_moi*angvel*angvel*4 + MOMENT_OF_INERTIA*angvel*angvel));
		
		angle += angvel;
	}
	

	@Override
	public int getAngle(int side) {
		if(side == getBlockMetadata())
			return angle * 2;
		return angle;
	}

	@Override
	public int getAngVel(int side) {
		if(side == getBlockMetadata())
			return angvel * 2;
		return angvel;
	}

	@Override
	public boolean doesShaftConnect(int side) {
		return (side & 6) == (getBlockMetadata() & 6);
	}

	@Override
	public double getMomentOfInertia(int side) {
		// TODO Auto-generated method stub
		return 0;
	}*/

}
