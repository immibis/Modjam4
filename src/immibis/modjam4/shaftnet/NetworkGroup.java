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
