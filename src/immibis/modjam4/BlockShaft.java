package immibis.modjam4;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

// metadata is direction - 0 (Y), 2 (Z) or 4 (X)
public class BlockShaft extends BlockContainer {
	public BlockShaft(Material m) {
		super(m);
		
		setCreativeTab(CreativeTabs.tabAllSearch);
		setHardness(2.0F);
        setStepSound(soundTypeWood);
	}
	
	@Override
	public int onBlockPlaced(World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		return side & 6;
	}
	
	@Override
	public int getRenderType() {
		return Modjam4Mod.NULL_RENDER_ID;
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileShaft();
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
}
