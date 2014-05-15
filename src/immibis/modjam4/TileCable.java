package immibis.modjam4;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileCable extends TileEntity {
	@Override
	public void onChunkUnload() {
		super.onChunkUnload();
		
		for(ForgeDirection fd : ForgeDirection.VALID_DIRECTIONS) {
			int x = xCoord+fd.offsetX, y = yCoord+fd.offsetY, z = zCoord+fd.offsetZ;
			
		}
	}
}
