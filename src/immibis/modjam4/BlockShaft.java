package immibis.modjam4;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

// metadata is direction - 0 (Y), 2 (Z) or 4 (X)
public class BlockShaft extends Block {
	public BlockShaft(Material m) {
		super(m);
		
		setCreativeTab(CreativeTabs.tabAllSearch);
	}
	
	@Override
	public int onBlockPlaced(World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		return side & 6;
	}
}
