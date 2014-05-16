package immibis.modjam4;

public class TileWatermill extends TileShaft {
	@Override
	public void updateEntity() {
		super.updateEntity();
		shaftNode.getNetwork().angvel += ShaftUtils.fromDegreesPerSecond(3);
	}
}
