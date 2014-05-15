package immibis.modjam4;

import net.minecraft.tileentity.TileEntity;

public class TileShaft extends TileEntity {
	public int angle; // INT_MIN to INT_MAX
	
	@Override
	public void updateEntity() {
		angle += (3 << 24);
	}
}
