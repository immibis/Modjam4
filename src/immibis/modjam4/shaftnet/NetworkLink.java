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
		System.out.println("unlinking "+netA+" and "+netB);
		netA.removeLink(this);
		netB.removeLink(this);
		NetworkGroup oldBG = netB.group;
		netA.propagateNewGroup();
		if(netB.group == oldBG)
			netB.propagateNewGroup();
		System.out.println("unlinked "+netA+" and "+netB);
	}

	public void link() {
		System.out.println("linking "+netA+" and "+netB);
		netA.addLink(this);
		netB.addLink(this);
		System.out.println("linked "+netA+" and "+netB);
	}
	
	@Override
	public String toString() {
		return "Link("+netA+", "+netB+")";
	}
}
