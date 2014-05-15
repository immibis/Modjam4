package immibis.modjam4;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

// metadata = input face
public class BlockInductionGenerator extends BlockContainer {
	public BlockInductionGenerator() {
		super(Material.iron);
		
		setCreativeTab(CreativeTabs.tabAllSearch);
		setHardness(5);
		setBlockName("immibis_modjam4.inductionMotor");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if(side == meta)
			return Blocks.log.getIcon(0, 0);
		else
			return super.getIcon(side, meta);
	}
	
	@Override
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase pl, ItemStack stack) {
		w.setBlockMetadataWithNotify(x, y, z, BlockPistonBase.determineOrientation(w, x, y, z, pl), 3);
		((TileInductionGenerator)w.getTileEntity(x, y, z)).initSide(w.getBlockMetadata(x, y, z));
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileInductionGenerator();
	}
}
