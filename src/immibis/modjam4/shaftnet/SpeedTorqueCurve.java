package immibis.modjam4.shaftnet;

import immibis.modjam4.ShaftUtils;

/**
 * SPEED UNITS: angle units per tick
 *   so Integer.MAX_VALUE speed is 20 revs/sec = 7200 deg/sec
 * 
 * TORQUE UNITS: momentum units per tick
 *   so Integer.MAX_VALUE is max speed/tick if inertia is 1  
 * 
 * MOMENTUM UNITS: speed units * inertia
 *   shafts have inertia 1
 *
 */
public interface SpeedTorqueCurve {
	public long getTorqueAtSpeed(long speed);
}
