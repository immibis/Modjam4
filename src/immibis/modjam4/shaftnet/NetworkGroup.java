package immibis.modjam4.shaftnet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NetworkGroup {
	List<ShaftNetwork> networks = new ArrayList<ShaftNetwork>();
	boolean needVelocityRecalc = false;
	
	void recalcVelocity() {
		List<NetworkLink> links = new ArrayList<NetworkLink>();
		for(ShaftNetwork n : networks)
			for(NetworkLink l : n.links)
				if(l.netA == n)
					links.add(l);
		
		// construct a matrix for a system of linear equations
		// one equation for each link, one variable for each network's relativeVelocity
		double[][] matrix = new double[links.size()][networks.size()]; // [row][col]
		int linkIndex = 0;
		for(NetworkLink l : links) {
			// netAspeed*vm = netBspeed
			// -> netAspeed*vm - netBspeed = 0
			matrix[linkIndex][networks.indexOf(l.netA)] = l.velocityMultiplier;
			matrix[linkIndex][networks.indexOf(l.netB)] = -1;
			linkIndex++;
		}
		
		MatrixMath.toReducedRowEchelonForm(matrix);
		
		
	}

	void add(ShaftNetwork net) {
		networks.add(net);
		needVelocityRecalc = true;
	}

	void mergeInto(NetworkGroup group) {
		if(this == group)
			return;
		
		for(ShaftNetwork n : networks) {
			n.group = group;
			group.add(n);
		}
	}
}
