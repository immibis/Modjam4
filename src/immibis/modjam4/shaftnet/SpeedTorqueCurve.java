package immibis.modjam4.shaftnet;

import immibis.modjam4.ShaftUtils;

public interface SpeedTorqueCurve {
	public long getTorqueAtSpeed(long speed);
}
