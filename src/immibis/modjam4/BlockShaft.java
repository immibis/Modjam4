package immibis.modjam4;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

// metadata is direction - 0 (Y), 2 (Z) or 4 (X)
public class BlockShaft extends BlockMachineBase {
	public BlockShaft(Material m) {
		super(m);
		
		setCreativeTab(CreativeTabs.tabAllSearch);
		setHardness(2.0F);
        setStepSound(soundTypeWood);
        setBlockTextureName("log_oak");
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
	
	@Override
	@SideOnly(Side.CLIENT)
	public void renderInvBlock(RenderBlocks rb) {
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		Tessellator.instance.startDrawingQuads();
		new RenderTileShaft().renderShaft(true);
		Tessellator.instance.draw();
	}
	
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z,
			EntityPlayer pl, int p_149727_6_, float p_149727_7_,
			float p_149727_8_, float p_149727_9_) {
		if(getClass() == BlockShaft.class)
			return ((TileShaft)w.getTileEntity(x, y, z)).debug(pl);
		else
			return super.onBlockActivated(w, x, y, z, pl, 0, 0, 0, 0);
	}
}
