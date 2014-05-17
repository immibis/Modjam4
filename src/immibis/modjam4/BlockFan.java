package immibis.modjam4;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFan extends BlockShaft {
	public BlockFan(Material m) {
		super(m);
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileFan();
	}
	
	@Override
	public int onBlockPlaced(World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		return side;
	}
}
