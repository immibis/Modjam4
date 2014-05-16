package immibis.modjam4;

import immibis.modjam4.shaftnet.ShaftNode;
import net.minecraft.tileentity.TileEntity;

public class TileCreativeEngine extends TileMachine {

	{shaftNode.setSideMask(63);}
	
	@Override
	public ShaftNode getShaftNode(int side) {
		return shaftNode;
	}
	
	@Override
	public void updateEntity() {
		shaftNode.getNetwork().angvel = 3 << 24;
		shaftNode.tick();
	}
}
