package immibis.modjam4.shaftnet;

import java.util.ArrayList;
import java.util.List;

public class NetworkGroup {
	List<ShaftNetwork> networks = new ArrayList<ShaftNetwork>();
	boolean needVelocityRecalc = false;
	
	void recalcVelocity() {
		
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
