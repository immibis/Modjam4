package immibis.modjam4;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.World;

public class CableNetwork {
	
	private List<ICable> cables = new ArrayList<ICable>();
	
	public double generatedPower; // W
	public double consumedPower; // W
	public int frequency; // angle units/tick (!)
	
	public double generatedPowerAcc, consumedPowerAcc;

	public void mergeInto(CableNetwork network) {
		if(network == this)
			return;
		
		//System.out.println(this+" mergeInto "+network);
		for(ICable c : cables) {
			c.setNetwork(network);
			network.add(c);
		}
	}

	public void add(ICable cable) {
		cables.add(cable);
	}
	
	@Override
	public String toString() {
		return cables.size()+" cables "+hashCode()+" angvel="+ShaftUtils.toDegreesPerSecond(frequency);
	}
	
	
	long lastUpdate;

	public void tick(World world) {
		if(lastUpdate == world.getTotalWorldTime())
			return;
		lastUpdate = world.getTotalWorldTime();
		
		generatedPower = generatedPowerAcc;
		consumedPower = consumedPowerAcc;
		generatedPowerAcc = consumedPowerAcc = 0;
		
		double excessPower = generatedPower - consumedPower;
		
		// 1kW excess = 0.01 rad/s/tick increase in frequency, for a network with 10 cables
		frequency += ShaftUtils.fromRadiansPerSecond(excessPower / 100000.0 / cables.size());
		
		// temporary; induction generators can't supply power to a dead network 
		if(frequency < ShaftUtils.fromRadiansPerSecond(0.0001))
			frequency = ShaftUtils.fromRadiansPerSecond(0.0001);
	}

}
