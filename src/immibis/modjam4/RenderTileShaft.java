package immibis.modjam4;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

@SideOnly(Side.CLIENT)
public class RenderTileShaft extends TileEntitySpecialRenderer  {

	@Override
	public void renderTileEntityAt(TileEntity te_, double renderX, double renderY, double renderZ, float partialTick) {
		int meta = te_.getBlockMetadata();
		
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		Tessellator t = Tessellator.instance;
		
		IIcon icon = Blocks.log.getIcon(2, 0);
		
		t.startDrawingQuads();
		t.setTranslation(renderX, renderY, renderZ);
		t.setBrightness(0x00F000F0);
		switch(meta) {
		case 0:
			t.addVertexWithUV(0.25, 0, 0.25, icon.getMinU(), icon.getMinV());
			t.addVertexWithUV(0.25, 0, 0.75, icon.getMaxU(), icon.getMinV());
			t.addVertexWithUV(0.25, 1, 0.75, icon.getMaxU(), icon.getMaxV());
			t.addVertexWithUV(0.25, 1, 0.25, icon.getMinU(), icon.getMaxV());
			break;
		}
		t.draw();
		t.setTranslation(0, 0, 0);
	}

}
