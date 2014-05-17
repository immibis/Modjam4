package immibis.modjam4.shaftnet;

import immibis.modjam4.CableNetwork;
import immibis.modjam4.ICable;

import java.util.ArrayList;
import java.util.List;


/**
 * A network is a bunch of shaft machines rotating at the same speed.
 */
public class ShaftNetwork {
	private List<ShaftNode> nodes = new ArrayList<ShaftNode>();
	private List<SpeedTorqueCurve> machineCurves = new ArrayList<SpeedTorqueCurve>();
	private List<NetworkLink> linkedNetworks = new ArrayList<NetworkLink>();
	
	private NetworkGroup group = new NetworkGroup();
	{group.add(this);}
	
	public int angle;
	public long angvel;
	
	long lastUpdate;
	
	public void mergeInto(ShaftNetwork network) {
		if(network == this)
			return;
		
		network.angle += (int)(((double)angle - network.angle) * nodes.size() / (network.nodes.size() + nodes.size()));

		network.angvel = (angvel*nodes.size() + network.angvel*network.nodes.size()) / (network.nodes.size() + nodes.size());
		
		group.networks.remove(this);
		
		for(ShaftNode c : nodes) {
			c.network = network;
			network.add(c);
		}
	}

	public void add(ShaftNode node) {
		nodes.add(node);
		SpeedTorqueCurve curve = node.getSpeedTorqueCurve();
		if(curve != null)
			machineCurves.add(curve);
		NetworkLink link = node.getNetworkLink();
	}
	
	void tick() {
		angle += angvel;
		
		//angvel *= 0.95;
		
		long sumtorque = 0;
		for(SpeedTorqueCurve stc : machineCurves)
			sumtorque += stc.getTorqueAtSpeed(angvel);
		
		int inertia = nodes.size(); // temporary
		
		//System.out.println("angvel "+angvel+", sumtorque "+sumtorque+", new "+(angvel+sumtorque/inertia));
		
		angvel += sumtorque / inertia;
	}

	public ShaftNetwork createSplitNetwork() {
		ShaftNetwork n = new ShaftNetwork();
		n.angle = angle;
		n.angvel = angvel;
		return n;
	}
}
