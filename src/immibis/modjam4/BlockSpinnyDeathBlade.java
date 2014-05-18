package immibis.modjam4;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSpinnyDeathBlade extends BlockShaft {
	public BlockSpinnyDeathBlade() {
		super(Material.iron);
		
		setCreativeTab(CreativeTabs.tabAllSearch);
		setHardness(5);
		setBlockName("immibis_modjam4.deathBlade");
	}
	
	@Override
	public int onBlockPlaced(World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		return side^1;
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileSpinnyDeathBlade();
	}
	
	@Override
	public void renderInvBlock(RenderBlocks rb) {
		GL11.glPushMatrix();
		GL11.glScalef(0.2f, 0.2f, 0.2f);
		GL11.glRotatef(-22.5f, 0, 1, 0);
		GL11.glRotatef(90, 0, 0, 1);
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		Tessellator.instance.startDrawingQuads();
		new RenderTileSpinnyDeathBlade().renderShaft(true);
		Tessellator.instance.draw();
		
		GL11.glDisable(GL11.GL_LIGHTING);
		Tessellator.instance.startDrawingQuads();
		new RenderTileSpinnyDeathBlade().renderAttachment();
		Tessellator.instance.draw();
		GL11.glPopMatrix();
		GL11.glEnable(GL11.GL_LIGHTING);
	}
}
