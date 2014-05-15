package immibis.modjam4;

import net.minecraft.tileentity.TileEntity;

public class TileInductionGenerator extends TileOneShaftMachine implements IShaft {
	
	public static final double MOMENT_OF_INERTIA = 5000; // kgm^2
	
	@Override
	public void updateEntity() {
		
		angvel = ShaftUtils.fromDegreesPerSecond(45);
		angle += angvel;

		CableNetwork cable = getConnectedCable();
		IShaft conn = getConnectedShaft();
		if(conn != null && cable != null) {
			int s_angvel = conn.getAngVel(shaftSide^1);
			int s_angle = conn.getAngle(shaftSide^1);
			
			int slip = conn.getAngle(shaftSide^1) - angle;
			
			
			// torque = dspeed/dt * moment of inertia
			// input power = input torque * input speed (rad/s)
			
			//double genpower = powerFreqCurve(cable.frequency);
			//cable.generatedPowerAcc += genpower;
			
			// J*d_freq/dt = T_electric - T_mechanical
			// J is inertia
			// applies to whole system
			
			angle += ShaftUtils.angdiff(s_angle, angle)/2;
		}
	}

	@Override
	public double getMomentOfInertia(int side) {
		return MOMENT_OF_INERTIA;
	}
	
}
