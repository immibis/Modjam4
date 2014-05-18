package immibis.modjam4;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

public class RenderTileSpinnyDeathBlade extends RenderTileShaft {

	@Override
	public void renderAttachment() {
		IIcon i = Blocks.iron_block.getIcon(0, 0);
		Tessellator t = Tessellator.instance;
		
		double r = 3.5;
		
		double y1 = 0.5, y2 = 0.35;
		
		t.draw();
		
		for(int k = 0; k < 4; k++) {
			GL11.glRotatef(90, 0, 1, 0);
			
			t.startDrawingQuads();
			
			double prevAngle = 0;
			int numslices = 16;
			for(int slice = 0; slice < numslices; slice++) {
				double angle = 0;
				double radius = (r + 1) * slice / numslices;
				double prevradius = r * slice / numslices;
				double dangle = 0.1;
				t.addVertexWithUV(radius*Math.sin(angle), y2, radius*Math.sin(angle), i.getMaxU(), i.getMinV());
				t.addVertexWithUV(radius*Math.sin(angle+dangle), y1, radius*Math.sin(angle+dangle), i.getMaxU(), i.getMaxV());
				t.addVertexWithUV(w, y1,-w, i.getMinU(), i.getMaxV());
				t.addVertexWithUV(w, y2, w, i.getMinU(), i.getMinV());
				prevAngle = angle;
			}
			
			
			//t.addVertexWithUV(r, y2, w, i.getMaxU(), i.getMinV());
			//t.addVertexWithUV(r, y1,-w, i.getMaxU(), i.getMaxV());
			//t.addVertexWithUV(m, y1,-w+d, i.getMinU(), i.getMaxV());
			//t.addVertexWithUV(m, y2, w+d, i.getMinU(), i.getMinV());
			t.draw();
		}
		
		t.startDrawingQuads();
	}

}
