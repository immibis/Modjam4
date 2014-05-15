package immibis.modjam4;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCreativeEngine extends BlockContainer {
	public BlockCreativeEngine() {
		super(Material.iron);
		
		setCreativeTab(CreativeTabs.tabAllSearch);
		setHardness(2.0F);
        setStepSound(soundTypeMetal);
        setBlockName("immibis_modjam4.creativeEngine");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileCreativeEngine();
	}
}
