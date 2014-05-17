package immibis.modjam4;

import immibis.modjam4.shaftnet.ShaftNode;
import immibis.modjam4.shaftnet.SpeedTorqueCurve;
import net.minecraft.tileentity.TileEntity;

public class TileFan extends TileShaft implements SpeedTorqueCurve {
	
	@Override
	protected ShaftNode createShaftNode() {
		return new ShaftNode(this) {
			@Override
			public SpeedTorqueCurve getSpeedTorqueCurve() {
				return TileFan.this;
			}
		};
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		double angvel_dps = ShaftUtils.toDegreesPerSecond((int)shaftNode.getNetwork().angvel);
		
		switch(getBlockMetadata()) {
		case 4: // -X
		case 5: // +X
		}
	}
	
	@Override
	public long getTorqueAtSpeed(long speed) {
		return 0;
	}
}
