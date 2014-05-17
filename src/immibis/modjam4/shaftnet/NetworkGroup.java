package immibis.modjam4.shaftnet;

import java.util.ArrayList;
import java.util.List;

public class NetworkGroup {
	List<ShaftNetwork> networks = new ArrayList<ShaftNetwork>();

	void add(ShaftNetwork net) {
		networks.add(net);
	}
}
