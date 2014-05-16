package immibis.modjam4;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

// metadata is axis (0=Y 2=Z 4=X)
public class BlockWatermill extends BlockShaft {
	public BlockWatermill(Material m) {
		super(m);
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileWatermill();
	}
}
