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
}
