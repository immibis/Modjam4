package immibis.modjam4;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileCable extends TileEntity implements ICable {
	@Override
	public void onChunkUnload() {
		super.onChunkUnload();
		
		for(ForgeDirection fd : ForgeDirection.VALID_DIRECTIONS) {
			int x = xCoord+fd.offsetX, y = yCoord+fd.offsetY, z = zCoord+fd.offsetZ;
			if(!worldObj.blockExists(x, y, z))
				continue;
			
			TileEntity te = worldObj.getTileEntity(x, y, z);
			if(te instanceof ICable)
				((ICable)te).onNeighbourCableUnload(fd.ordinal()^1, this, x, y, z);
		}
	}
	
	private ICable[] neighbours = new TileCable[6];
	
	@Override
	public void onNeighbourCableUnload(int dir, ICable obj, int x, int y, int z) {
		if(neighbours[dir] == obj)
			neighbours[dir] = null;
	}
	
	private void updateNeighbour(int dir) {
		ForgeDirection fd = ForgeDirection.VALID_DIRECTIONS[dir];
		int x = xCoord+fd.offsetX, y = yCoord+fd.offsetY, z = zCoord+fd.offsetZ;
		
		if(worldObj.blockExists(x, y, z)) {
			TileEntity te = worldObj.getTileEntity(x, y, z);
			if(te instanceof ICable)
				setNeighbour(dir, (ICable)te);
			else
				setNeighbour(dir, null);
		}
		else
			setNeighbour(dir, null);
	}
	
	private void setNeighbour(int dir, ICable te) {
		if(neighbours[dir] != te) {
			if(neighbours[dir] != null) {
				te.propagateNetwork(new CableNetwork());
			}
			neighbours[dir] = te;
		}
	}

	private boolean firstUpdate = true;
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if(firstUpdate) {
			firstUpdate = false;
			for(int k = 0; k < 6; k++)
				updateNeighbour(k);
		}
	}

	public void onBlockUpdate() {
		for(int k = 0; k < 6; k++)
			updateNeighbour(k);
	}
}
