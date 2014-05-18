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
		
		double r = 4.5;
		
		double y1 = 0.45, y2 = 0.35;
		
		t.draw();
		
		GL11.glDisable(GL11.GL_CULL_FACE);
		
		for(int k = 0; k < 4; k++) {
			GL11.glRotatef(90, 0, 1, 0);
			
			t.startDrawingQuads();
			
			double prevAngle = 0, prevDAngle = 0, prevradius = 0;
			int numslices = 8;
			for(int slice = 0; slice < numslices; slice++) {
				double radius = r * (slice + 1) / numslices + 0.125;
				double angle = Math.atan(-0.25/radius) + (radius-0.25)/8 - 0.2;
				double dangle = Math.PI / 4 / radius;// * (Math.sin(slice*Math.PI/2/numslices + Math.PI/2));
				
				t.addVertexWithUV(radius*Math.sin(angle), y1, radius*Math.cos(angle), i.getMaxU(), i.getMinV());
				t.addVertexWithUV(radius*Math.sin(angle+dangle), y1, radius*Math.cos(angle+dangle), i.getMaxU(), i.getMaxV());
				t.addVertexWithUV(prevradius*Math.sin(prevAngle+prevDAngle), y1, prevradius*Math.cos(prevAngle+prevDAngle), i.getMinU(), i.getMaxV());
				t.addVertexWithUV(prevradius*Math.sin(prevAngle), y1, prevradius*Math.cos(prevAngle), i.getMinU(), i.getMinV());
				
				prevAngle = angle;
				prevDAngle = dangle;
				prevradius = radius;
			}
			
			
			//t.addVertexWithUV(r, y2, w, i.getMaxU(), i.getMinV());
			//t.addVertexWithUV(r, y1,-w, i.getMaxU(), i.getMaxV());
			//t.addVertexWithUV(m, y1,-w+d, i.getMinU(), i.getMaxV());
			//t.addVertexWithUV(m, y2, w+d, i.getMinU(), i.getMinV());
			t.draw();
		}
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		
		t.startDrawingQuads();
	}

}
