package immibis.modjam4;

import net.minecraft.tileentity.TileEntity;

public class TileShaft extends TileEntity {
	public int angle; // INT_MIN to INT_MAX
	public int angvel = 3 << 24; // angle units per tick
	
	@Override
	public void updateEntity() {
		angle += angvel;
	}
}
