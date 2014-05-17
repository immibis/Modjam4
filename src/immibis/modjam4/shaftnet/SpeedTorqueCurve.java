package immibis.modjam4.shaftnet;

import immibis.modjam4.ShaftUtils;

public class SpeedTorqueCurve {
	public long getTorqueAtSpeed(long speed) {
		return ShaftUtils.fromDegreesPerSecond(100) - speed;
	}
}
