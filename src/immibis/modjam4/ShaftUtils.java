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
		return angvel / 4294967296.0 * 360 * 20;
	}
	
	public static double toDegrees(int angle) {
		return angle / 4294967296.0 * 360;
	}
}
