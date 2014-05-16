package immibis.modjam4;

import immibis.modjam4.shaftnet.ShaftNode;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.util.ForgeDirection;

public class TileGearboxDirectional extends TileMachine {
	
	private ShaftNode shaftNode = new ShaftNode(this);
	{shaftNode.setSideMask(63);}
	
	@Override
	public ShaftNode getShaftNode(int side) {
		return shaftNode;
	}
	
	@Override
	protected void updateNeighbourConnections() {
		shaftNode.updateNeighbours();
	}
}
