package immibis.modjam4;

public class ShaftUtils {
	/** Returns a - b with wraparound */
	public static int angdiff(int a, int b) {
		
		/*
		if(a > b) {
			int diff = a - b;
			if(diff < 0) diff = -diff;
			return diff;
		
		} else {
			int diff = b - a;
			if(diff < 0) diff = -diff;
			return -diff;
		}*/
		
		return a - b; // d'oh!
	}

	public static int bisectAngle(int a, int b) {
		return b + angdiff(a, b) / 2;
	}

	public static double toDegreesPerSecond(int angvel) {
		return angvel * (360.0 * 20.0 / 4294967296.0);
	}
	
	public static double toDegrees(int angle) {
		return angle * (360.0 / 4294967296.0);
	}
	
	public static double toRadians(int angle) {
		return angle * (Math.PI * 2.0 / 4294967296.0);
	}

	public static int fromRadians(double angle) {
		return (int)(angle * (4294967296.0 / Math.PI / 2.0));
	}
	public static int averageAngle(int[] angles, int n) {
		double totSin = 0, totCos = 0;
		for(int k = 0; k < n; k++) {
			double r = toRadians(angles[k]);
			totSin += Math.sin(r);
			totCos += Math.cos(r);
		}
		return fromRadians(Math.atan2(totSin / n, totCos / n));
	}
}
