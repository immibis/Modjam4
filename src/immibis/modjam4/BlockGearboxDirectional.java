package immibis.modjam4;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGearboxDirectional extends BlockMachineBase {
	public BlockGearboxDirectional(Material m) {
		super(m);
		
		setCreativeTab(CreativeTabs.tabAllSearch);
		setHardness(2);
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileGearboxDirectional();
	}
	
	@Override
	public int getRenderType() {
		return 0;
	}
	
	@Override
	public void renderInvBlock(RenderBlocks rb) {
		throw new UnsupportedOperationException();
	}
}
