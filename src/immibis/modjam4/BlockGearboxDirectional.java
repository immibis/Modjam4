package immibis.modjam4;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGearboxDirectional extends BlockContainer {
	public BlockGearboxDirectional(Material m) {
		super(m);
		
		setCreativeTab(CreativeTabs.tabAllSearch);
		setBlockName("immibis_modjam4.directionalGearbox");
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileGearboxDirectional();
	}
}
