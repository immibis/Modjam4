package immibis.modjam4;

import net.minecraft.tileentity.TileEntity;

public class TileInductionGenerator extends TileOneShaftMachine implements IShaft {
	
	public static final double MOMENT_OF_INERTIA = 5000; // kgm^2
	
	@Override
	public void updateEntity() {
		
		if(worldObj.isRemote)
			initSide(getBlockMetadata());
		
		angle += angvel;
		// angle = cable.currentPhaseAngle;

		CableNetwork cable = getConnectedCable();
		IShaft conn = getConnectedShaft();
		if(conn != null && cable != null) {
			int s_angvel = conn.getAngVel(shaftSide^1);
			int s_angle = conn.getAngle(shaftSide^1);
			
			int slip = angle - conn.getAngle(shaftSide^1);
			
			// torque is proportional to slip
			// input power (W) = input torque (kgm^2s^-2) * input speed (rad/s)
			
			// negative slip = power generated; positive slip = power consumed
			
			double torque = ShaftUtils.toDegrees(slip) * 1000;
			double genPower = -torque * ShaftUtils.toRadiansPerSecond(angvel);
			
			if(genPower > 0)
				cable.generatedPowerAcc += genPower;
			else
				cable.consumedPowerAcc -= genPower;
			
			// J*d_freq/dt = T_electric - T_mechanical
			// J is inertia
			// applies to whole system
			
			//angle += ShaftUtils.angdiff(s_angle, angle)/16;
			
			angvel = cable.frequency;
			angle = s_angle + angvel;
		}
	}

	@Override
	public double getMomentOfInertia(int side) {
		return MOMENT_OF_INERTIA;
	}
	
}
