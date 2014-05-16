package immibis.modjam4.shaftnet;

import immibis.modjam4.CableNetwork;
import immibis.modjam4.ICable;

import java.util.ArrayList;
import java.util.List;


public class ShaftNetwork {
	private List<ShaftNode> nodes = new ArrayList<ShaftNode>();
	
	public int angle, angvel;
	
	long lastUpdate;
	
	public void mergeInto(ShaftNetwork network) {
		if(network == this)
			return;
		
		network.angle += (angle - network.angle) / 2;

		boolean sign = network.angvel < 0;
		network.angvel = (int)Math.sqrt((((double)angvel)*angvel*nodes.size() + ((double)network.angvel)*network.angvel*network.nodes.size())/(nodes.size() + network.nodes.size()));
		if(sign)
			network.angvel = -network.angvel;
		
		for(ShaftNode c : nodes) {
			c.network = network;
			network.add(c);
		}
	}

	public void add(ShaftNode cable) {
		nodes.add(cable);
	}
	
	void tick() {
		angle += angvel;
		angvel *= 0.95;
	}

	public ShaftNetwork createSplitNetwork() {
		ShaftNetwork n = new ShaftNetwork();
		n.angle = angle;
		n.angvel = angvel;
		return n;
	}
}
