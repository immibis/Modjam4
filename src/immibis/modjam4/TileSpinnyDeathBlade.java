package immibis.modjam4;

public class TileSpinnyDeathBlade extends TileShaft {
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if(!worldObj.isRemote) {
			// 3 hearts/hit at 120 deg/s
			double damage = shaftNode.getNetwork().angvel / (double)ShaftUtils.fromDegreesPerSecond(120);
		}
	}
}
