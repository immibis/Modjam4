package immibis.modjam4.shaftnet;

import immibis.modjam4.CableNetwork;
import immibis.modjam4.ICable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


/**
 * A network is a bunch of shaft machines rotating at the same speed.
 */
public class ShaftNetwork {
	private List<ShaftNode> nodes = new ArrayList<ShaftNode>();
	private List<SpeedTorqueCurve> machineCurves = new ArrayList<SpeedTorqueCurve>();
	private Collection<NetworkLink> links = new HashSet<NetworkLink>();
	
	double relativeVelocity = 1; // relative to other networks in group
	
	NetworkGroup group = new NetworkGroup();
	{group.add(this);}
	
	public int angle;
	public long angvel;
	
	long lastUpdate;
	
	public void mergeInto(ShaftNetwork network) {
		if(network == this)
			return;
		
		network.angle += (int)(((double)angle - network.angle) * nodes.size() / (network.nodes.size() + nodes.size()));

		network.angvel = (angvel*nodes.size() + network.angvel*network.nodes.size()) / (network.nodes.size() + nodes.size());
		
		/*System.out.println("merging "+this+" into "+network);
		for(NetworkLink link : new ArrayList<NetworkLink>(links)) {
			if(link.netA == this) {
				link.unlink();
				link.netA = network;
				link.link();
				
			} else if(link.netB == this) {
				link.unlink();
				link.netB = network;
				link.link();
				
			} else
				throw new AssertionError();
		}
		links.clear();*/
		
		for(ShaftNode c : nodes) {
			c.network = network;
			network.add(c);
		}
		nodes.clear();
	}

	public void add(ShaftNode node) {
		nodes.add(node);
		SpeedTorqueCurve curve = node.getSpeedTorqueCurve();
		if(curve != null)
			machineCurves.add(curve);
	}
	
	void addLink(NetworkLink link) {
		if(this == link.netA)
			if(this == link.netB)
				throw new AssertionError("invalid link");
			else
				addLink(link, link.netB);
		else if(this == link.netB)
			addLink(link, link.netA);
		else
			throw new AssertionError("invalid link");
	}

	private void addLink(NetworkLink link, ShaftNetwork other) {
		if(link.netA != this && link.netB != this)
			throw new AssertionError();
		group.mergeInto(other.group);
		links.add(link);
	}

	void tick() {
		if(group.needVelocityRecalc) {
			group.needVelocityRecalc = false;
			group.recalcVelocity();
		}
		
		angle += angvel;
		
		//angvel *= 0.95;
		
		long sumtorque = 0;
		for(SpeedTorqueCurve stc : machineCurves)
			sumtorque += stc.getTorqueAtSpeed(angvel);
		
		int inertia = nodes.size(); // temporary
		
		//System.out.println("angvel "+angvel+", sumtorque "+sumtorque+", new "+(angvel+sumtorque/inertia));
		
		angvel += sumtorque / inertia;
	}

	ShaftNetwork createSplitNetwork() {
		ShaftNetwork n = new ShaftNetwork();
		n.angle = angle;
		n.angvel = angvel;
		return n;
	}

	void propagateNewGroup() {
		propagateGroup(new NetworkGroup());
	}
	
	private void propagateGroup(NetworkGroup g) {
		if(group == g)
			return;
		
		group = g;
		g.add(this);
		
		for(NetworkLink l : links) {
			l.netA.propagateGroup(g);
			l.netB.propagateGroup(g);
		}
	}
	
	@Override
	public String toString() {
		return Integer.toHexString(hashCode())+", group="+Integer.toHexString(group.hashCode());
	}

	void removeLink(NetworkLink link) {
		links.remove(link);
	}
}
