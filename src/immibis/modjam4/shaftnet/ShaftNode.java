package immibis.modjam4.shaftnet;

import net.minecraft.tileentity.TileEntity;


public class ShaftNode {
	
	private TileEntity te;
	private int sideMask;
	
	ShaftNetwork network = new ShaftNetwork();
	{network.add(this);}
	
	public ShaftNode(TileEntity te, int sideMask) {
		this.te = te;
		this.sideMask = sideMask;
	}
	
	public void updateNeighbours() {
		
	}

	public void tick() {
		
	}

}
