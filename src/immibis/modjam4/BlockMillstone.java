package immibis.modjam4;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMillstone extends BlockMachineBase {

	public BlockMillstone() {
		super(Material.rock);
		
		setCreativeTab(CreativeTabs.tabAllSearch);
		setHardness(5);
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileMillstone();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void renderInvBlock(RenderBlocks rb) {
		
	}

}
