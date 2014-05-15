package immibis.modjam4;

import java.util.ArrayList;
import java.util.List;

public class CableNetwork {
	
	private List<ICable> cables = new ArrayList<ICable>();
	
	public double generatedPower; // W
	public double frequency; // Hz
	
	public double generatedPowerAcc;

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
		return cables.size()+" cables "+hashCode();
	}
	
	

	public void tick() {
		generatedPower = generatedPowerAcc;
		generatedPowerAcc = 0;
	}

}
