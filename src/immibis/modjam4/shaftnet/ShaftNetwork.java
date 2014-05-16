package immibis.modjam4.shaftnet;

import immibis.modjam4.CableNetwork;
import immibis.modjam4.ICable;

import java.util.ArrayList;
import java.util.List;


public class ShaftNetwork {
	private List<ShaftNode> nodes = new ArrayList<ShaftNode>();
	
	public double generatedPower; // W
	public double consumedPower; // W
	public int frequency; // angle units/tick (!)
	public int angle;
	
	public double generatedPowerAcc, consumedPowerAcc;

	public void mergeInto(ShaftNetwork network) {
		if(network == this)
			return;
		
		for(ShaftNode c : nodes) {
			c.network = network;
			network.add(c);
		}
	}

	public void add(ShaftNode cable) {
		nodes.add(cable);
	}
}
