package immibis.modjam4;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

// metadata is high-speed direction
public class BlockGearboxDouble extends BlockMachineBase {
	public BlockGearboxDouble(Material m) {
		super(m);
		
		setCreativeTab(CreativeTabs.tabAllSearch);
		setHardness(2);
	}
	
	IIcon iEnd, iSide;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_) {
		iSide = p_149651_1_.registerIcon("immibis_modjam4:dgearbox");
		iEnd = p_149651_1_.registerIcon("furnace_top");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if((meta & 6) == 0)
			return (side & 6) == 0 ? iEnd : iSide;
		return ((side & 6) == 0) ? iSide : iEnd;
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileGearboxDouble();
	}
	
	@Override
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase pl, ItemStack p_149689_6_) {
		int meta = BlockPistonBase.determineOrientation(w, x, y, z, pl);
		w.setBlockMetadataWithNotify(x, y, z, meta, 3);
	}

	@Override
	public void renderInvBlock(RenderBlocks rb) {}
	
	@Override
	public int getRenderType() {
		return Modjam4Mod.DOUBLE_GEARBOX_RENDER_ID;
	}
}
