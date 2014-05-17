package immibis.modjam4;

import immibis.modjam4.shaftnet.ShaftNode;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.util.ForgeDirection;

public class TileShaft extends TileMachine {
	
	ShaftNode shaftNode = createShaftNode();
	
	protected ShaftNode createShaftNode() {
		return new ShaftNode(this);
	}
	
	@Override
	public ShaftNode getShaftNode(int side) {
		return (side & 6) == (getBlockMetadata() & 6) ? shaftNode : null;
	}
	
	@Override
	public String toString() {
		return xCoord+"/"+yCoord+"/"+zCoord;
	}
	
	private boolean firstTick = true;
	@Override
	public void updateEntity() {
		if(firstTick) {
			firstTick = false;
			shaftNode.setSideMask(3 << getBlockMetadata());
		}
		super.updateEntity();
		shaftNode.tick();
	}
	
	@Override
	protected void updateNeighbourConnections() {
		shaftNode.updateNeighbours();
	}
}
