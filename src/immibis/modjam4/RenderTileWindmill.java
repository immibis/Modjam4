package immibis.modjam4;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

public class RenderTileWindmill extends RenderTileShaft {
	public void renderAttachment() {
		final double MINY = -2/16f, MAXY = -MINY;
		final double CMINY = -1/16f, CMAXY = -CMINY;
		Tessellator t = Tessellator.instance;
		IIcon i = Blocks.planks.getIcon(0, 0);
		
		final double R = 2.5;
		
		t.draw();
		
		for(int k = 0; k < 4; k++) {
			GL11.glRotatef(90, 0, 1, 0);
			
			t.startDrawingQuads();
			
			renderStem(t, MINY, MAXY, 0, R, -0.1, 0, false, i);
			renderStem(t, MINY, MAXY, R*0.25, R*0.25+0.1, -1.15, -0.1, true, i);
			renderStem(t, MINY, MAXY, R*0.50, R*0.50+0.1, -1, -0.1, true, i);
			renderStem(t, MINY, MAXY, R*0.75, R*0.75+0.1, -0.85, -0.1, true, i);
			
			renderStem(t, CMINY, CMAXY, 0, R, -1, -0.05, true, Blocks.wool.getIcon(0, 0));
			
			t.draw();
		}
		
		t.startDrawingQuads();
	}
	
	private void renderStem(Tessellator t, double MINY, double MAXY, double minx, double maxx, double minz, double maxz, boolean swap, IIcon i) {
		t.addVertexWithUV(minx, MAXY, maxz, i.getMinU(), i.getMinV());
		t.addVertexWithUV(maxx, MAXY, maxz, i.getMinU(), i.getMaxV());
		t.addVertexWithUV(maxx, MAXY, minz, i.getMaxU(), i.getMaxV());
		t.addVertexWithUV(minx, MAXY, minz, i.getMaxU(), i.getMinV());
		
		t.addVertexWithUV(minx, MINY, minz, i.getMaxU(), i.getMinV());
		t.addVertexWithUV(maxx, MINY, minz, i.getMaxU(), i.getMaxV());
		t.addVertexWithUV(maxx, MINY, maxz, i.getMinU(), i.getMaxV());
		t.addVertexWithUV(minx, MINY, maxz, i.getMinU(), i.getMinV());
		
		t.addVertexWithUV(minx, MINY, maxz, i.getMinU(), i.getMinV());
		t.addVertexWithUV(maxx, MINY, maxz, i.getMinU(), i.getMaxV());
		t.addVertexWithUV(maxx, MAXY, maxz, i.getMaxU(), i.getMaxV());
		t.addVertexWithUV(minx, MAXY, maxz, i.getMaxU(), i.getMinV());
		
		t.addVertexWithUV(minx, MAXY, minz, i.getMinU(), i.getMinV());
		t.addVertexWithUV(maxx, MAXY, minz, i.getMinU(), i.getMaxV());
		t.addVertexWithUV(maxx, MINY, minz, i.getMaxU(), i.getMaxV());
		t.addVertexWithUV(minx, MINY, minz, i.getMaxU(), i.getMinV());
		
		if(swap) {
			t.addVertexWithUV(minx, MINY, maxz, i.getMaxU(), i.getMinV());
			t.addVertexWithUV(minx, MAXY, maxz, i.getMaxU(), i.getMaxV());
			t.addVertexWithUV(minx, MAXY, minz, i.getMinU(), i.getMaxV());
			t.addVertexWithUV(minx, MINY, minz, i.getMinU(), i.getMinV());
			
		}
		
		t.addVertexWithUV(maxx, MINY, minz, i.getMinU(), i.getMinV());
		t.addVertexWithUV(maxx, MAXY, minz, i.getMinU(), i.getMaxV());
		t.addVertexWithUV(maxx, MAXY, maxz, i.getMaxU(), i.getMaxV());
		t.addVertexWithUV(maxx, MINY, maxz, i.getMaxU(), i.getMinV());
	}
}
