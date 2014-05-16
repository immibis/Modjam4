package immibis.modjam4;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

public class RenderTileMillstone extends RenderTileShaft {
	public void renderAttachment() {
		final double MINY = -1/16f, MAXY = 0.51;
		final int NUM_SLICES = 16;
		Tessellator t = Tessellator.instance;
		IIcon i = Blocks.stone.getIcon(0, 0);
		
		final double RADIUS = 24/16f;
		
		final double SPOKESIZE = 4/16f;
		
		for(int slice = 0; slice < NUM_SLICES; slice += 2) {
			
			double x = Math.sin(slice * (Math.PI * 2 / NUM_SLICES));
			double z = Math.cos(slice * (Math.PI * 2 / NUM_SLICES));
			
			double px = Math.sin((slice-1) * (Math.PI * 2 / NUM_SLICES));
			double pz = Math.cos((slice-1) * (Math.PI * 2 / NUM_SLICES));
			
			double nx = Math.sin((slice+1) * (Math.PI * 2 / NUM_SLICES));
			double nz = Math.cos((slice+1) * (Math.PI * 2 / NUM_SLICES));
			
			t.addVertexWithUV(0, MAXY, 0, i.getInterpolatedU(8), i.getInterpolatedV(8));
			t.addVertexWithUV(px*RADIUS, MAXY, pz*RADIUS, i.getInterpolatedU(px*8+8), i.getInterpolatedV(pz*8+8));
			t.addVertexWithUV(x*RADIUS, MAXY, z*RADIUS, i.getInterpolatedU(x*8+8), i.getInterpolatedV(z*8+8));
			t.addVertexWithUV(nx*RADIUS, MAXY, nz*RADIUS, i.getInterpolatedU(nx*8+8), i.getInterpolatedV(nz*8+8));
			
			t.addVertexWithUV(nx*RADIUS, MINY, nz*RADIUS, i.getInterpolatedU(nx*8+8), i.getInterpolatedV(nz*8+8));
			t.addVertexWithUV(x*RADIUS, MINY, z*RADIUS, i.getInterpolatedU(x*8+8), i.getInterpolatedV(z*8+8));
			t.addVertexWithUV(px*RADIUS, MINY, pz*RADIUS, i.getInterpolatedU(px*8+8), i.getInterpolatedV(pz*8+8));
			t.addVertexWithUV(0, MINY, 0, i.getInterpolatedU(8), i.getInterpolatedV(8));
			
			t.addVertexWithUV(nx*RADIUS, MAXY, nz*RADIUS, i.getMinU(), i.getMaxV());
			t.addVertexWithUV( x*RADIUS, MAXY,  z*RADIUS, i.getMaxU(), i.getMaxV());
			t.addVertexWithUV( x*RADIUS, MINY,  z*RADIUS, i.getMaxU(), i.getMinV());
			t.addVertexWithUV(nx*RADIUS, MINY, nz*RADIUS, i.getMinU(), i.getMinV());
			
			t.addVertexWithUV( x*RADIUS, MAXY,  z*RADIUS, i.getMinU(), i.getMaxV());
			t.addVertexWithUV(px*RADIUS, MAXY, pz*RADIUS, i.getMaxU(), i.getMaxV());
			t.addVertexWithUV(px*RADIUS, MINY, pz*RADIUS, i.getMaxU(), i.getMinV());
			t.addVertexWithUV( x*RADIUS, MINY,  z*RADIUS, i.getMinU(), i.getMinV());
			
		}
	}
}
