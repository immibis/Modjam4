package immibis.modjam4;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMillstone extends BlockShaft {

	public BlockMillstone() {
		super(Material.rock);
		
		setCreativeTab(CreativeTabs.tabAllSearch);
		setHardness(5);
		setBlockName("immibis_modjam4.millstone");
	}
	
	@Override
	public int getRenderType() {
		return Modjam4Mod.NULL_RENDER_ID;
	}
	
	@Override
	public int onBlockPlaced(World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		return 0;
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
