package immibis.modjam4;

import immibis.modjam4.shaftnet.ShaftNode;
import net.minecraft.tileentity.TileEntity;

public class TileCreativeEngine extends TileEntity implements IShaft {

	public ShaftNode shaftNode;
	
	@Override
	public ShaftNode getShaftNode(int side) {
		return shaftNode;
	}
	
	@Override
	public void updateEntity() {
		//shaftNode.angvel = 3 << 24;
		shaftNode.tick();
	}
}
