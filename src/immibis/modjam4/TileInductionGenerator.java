package immibis.modjam4;

import net.minecraft.tileentity.TileEntity;

public class TileInductionGenerator extends TileOneShaftMachine implements IShaft {
	
	@Override
	public void updateEntity() {

		IShaft conn = getConnectedShaft();
		if(conn != null) {
			int s_angvel = conn.getAngVel(shaftSide^1);
			int s_angle = conn.getAngle(shaftSide^1);
			
			int slip = conn.getAngle(shaftSide^1) - angle;
			
			angle += ShaftUtils.angdiff(s_angle, angle)/2;
		}
	}
	
}
