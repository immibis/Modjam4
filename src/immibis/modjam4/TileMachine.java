package immibis.modjam4;

import immibis.modjam4.shaftnet.ShaftNode;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileMachine extends TileEntity implements IShaft {

	@Override
	public ShaftNode getShaftNode(int side) {
		return null;
	}
	
	protected void updateNeighbourConnections() {}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		updateNeighbourConnections();
	}
	
	/*public CableNetwork getConnectedCable() {
		ForgeDirection fd = ForgeDirection.VALID_DIRECTIONS[shaftSide^1];
		int x = xCoord+fd.offsetX, y = yCoord+fd.offsetY, z = zCoord+fd.offsetZ;
		if(!worldObj.blockExists(x, y, z))
			return null;
		
		TileEntity te = worldObj.getTileEntity(x, y, z);
		if(!(te instanceof ICable))
			return null;
		
		return ((ICable)te).getNetwork();
	}*/
	
	@Override
	public Packet getDescriptionPacket() {
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, null);
	}

	public void onBlockUpdate() {
		updateNeighbourConnections();
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
}
