package immibis.modjam4;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
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
		RenderTileMillstone rtm = new RenderTileMillstone();
		
		GL11.glPushMatrix();
		GL11.glScalef(0.8f, 0.8f, 0.8f);
		//GL11.glRotatef(-22.5f, 0, 1, 0);
		//GL11.glRotatef(-45f, 0, 0, 1);
		//GL11.glRotatef(-90, 0, 0, 1);
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		Tessellator.instance.startDrawingQuads();
		rtm.renderShaft(true);
		Tessellator.instance.draw();
		
		
		GL11.glDisable(GL11.GL_LIGHTING);
		rtm.renderStatic();
		Tessellator.instance.startDrawingQuads();
		rtm.renderAttachment();
		Tessellator.instance.draw();
		GL11.glPopMatrix();
	}

}
