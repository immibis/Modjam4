package immibis.modjam4.shaftnet;

import immibis.modjam4.shaftnet.MatrixMath.SingularMatrixException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NetworkGroup {
	List<ShaftNetwork> networks = new ArrayList<ShaftNetwork>();
	boolean needVelocityRecalc = false;
	
	/**
	 * Set if the network must be stationary (all velocities 0)
	 * This happens for example if you connect both sides of a non-1:1 gearbox together.
	 */
	boolean noValidVelocities = false;
	
	long groupAngVel;
	
	void recalcVelocity() {
		List<NetworkLink> links = new ArrayList<NetworkLink>();
		for(ShaftNetwork n : networks)
			for(NetworkLink l : n.links)
				if(l.netA == n)
					links.add(l);
		
		if(links.size() < networks.size() - 1) {
			System.out.println("[ImmibisMJ4 Debug/Warning] "+links.size()+" links for "+networks.size()+" networks! Shaft network group is corrupted?");
			noValidVelocities = true;
			return;
		}
		
		if(networks.size() == 1) {
			groupAngVel *= networks.get(0).relativeVelocity;
			networks.get(0).relativeVelocity = 1;
			return;
		}
		
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
		
		boolean previousNVV = noValidVelocities;
		double previousLastNetworkRelativeVelocity = networks.get(networks.size()-1).relativeVelocity;
		
		try {
			MatrixMath.toReducedRowEchelonForm(matrix);
		} catch (SingularMatrixException e) {
			noValidVelocities = true;
			return;
		}
		
		for(int net = 0; net < networks.size() - 1; net++) {
			if(matrix[net][net] != 1) {
				noValidVelocities = true;
				return;
			}
		}
		
		for(int net = 0; net < networks.size() - 1; net++) {
			networks.get(net).relativeVelocity = -matrix[net][networks.size()-1];
		}
		networks.get(networks.size()-1).relativeVelocity = 1;
		noValidVelocities = false;
		
		if(previousNVV) {
			groupAngVel = 0;
		} else {
			groupAngVel *= previousLastNetworkRelativeVelocity / networks.get(networks.size()-1).relativeVelocity;
		}
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

	public double calcInertia() {
		double inertia = 0;
		for(ShaftNetwork sn : networks)
			inertia += 
	}
}
