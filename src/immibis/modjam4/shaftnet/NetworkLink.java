package immibis.modjam4.shaftnet;

public class NetworkLink {
	public ShaftNetwork netA;
	public ShaftNetwork netB;
	public double velocityMultiplier;
	
	public NetworkLink(ShaftNetwork a, ShaftNetwork b, double m) {
		netA = a;
		netB = b;
		velocityMultiplier = m;
	}

	public void unlink() {
		netA.removeLink(this);
		netB.removeLink(this);
		netA.propagateNewGroup();
	}

	public void link() {
		netA.addLink(this);
		netB.addLink(this);
	}
	
	@Override
	public String toString() {
		return "Link("+netA+", "+netB+")";
	}
}
