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
			shaftNode.setSideMask(getSideMask());
			updateNeighbourConnections();
		}
		super.updateEntity();
		shaftNode.tick();
	}
	
	protected int getSideMask() {
		return 3 << (getBlockMetadata() & 6);
	}

	@Override
	protected void updateNeighbourConnections() {
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		shaftNode.updateNeighbours();
	}

	public boolean debug(EntityPlayer pl) {
		
		if(!worldObj.isRemote)
			return false;
		
		pl.addChatComponentMessage(new ChatComponentText("network: "+shaftNode.getNetwork()));
		
		return false;
	}
}
