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
}
