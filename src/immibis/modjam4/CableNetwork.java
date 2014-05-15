package immibis.modjam4;

import java.util.ArrayList;
import java.util.List;

public class CableNetwork {
	
	private List<ICable> cables = new ArrayList<ICable>();

	public void mergeInto(CableNetwork network) {
		for(ICable c : cables)
			c.setNetwork(network);
	}

	public void add(ICable cable) {
		cables.add(cable);
	}
	
	@Override
	public String toString() {
		return cables.size()+" cables";
	}

}
