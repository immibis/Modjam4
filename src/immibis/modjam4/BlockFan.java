package immibis.modjam4;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFan extends BlockShaft {
	public BlockFan(Material m) {
		super(m);
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileFan();
	}
	
	@Override
	public int onBlockPlaced(World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		return side^1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void renderInvBlock(RenderBlocks rb) {
		GL11.glPushMatrix();
		GL11.glScalef(1.3f, 1.3f, 1.3f);
		GL11.glRotatef(-22.5f, 0, 1, 0);
		GL11.glRotatef(-45f, 0, 0, 1);
		//GL11.glRotatef(-90, 0, 0, 1);
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		Tessellator.instance.startDrawingQuads();
		new RenderTileFan().renderShaft(true, false);
		Tessellator.instance.draw();
		GL11.glPopMatrix();
	}
}
