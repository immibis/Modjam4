package immibis.modjam4;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

public class RenderTileWatermill extends RenderTileShaft {
	protected void renderAttachment() {
		final double MINY = -7.5/16f, MAXY = -MINY;
		final int NUM_SLICES = 8;
		Tessellator t = Tessellator.instance;
		IIcon i = Blocks.planks.getIcon(0, 0);
		
		final double R_MIN = 24/16f;
		final double R_MAX = 26/16f;
		
		final double SPOKESIZE = 2/16f;
		
		for(int slice = 0; slice < NUM_SLICES; slice++) {
			double x = Math.sin(slice * (Math.PI * 2 / NUM_SLICES));
			double z = Math.cos(slice * (Math.PI * 2 / NUM_SLICES));
			
			double nx = Math.sin((slice+1) * (Math.PI * 2 / NUM_SLICES));
			double nz = Math.cos((slice+1) * (Math.PI * 2 / NUM_SLICES));
			
			t.addVertexWithUV(x*R_MIN, MINY, z*R_MIN, i.getMinU(), i.getMinV());
			t.addVertexWithUV(x*R_MIN, MAXY, z*R_MIN, i.getMaxU(), i.getMinV());
			t.addVertexWithUV(nx*R_MIN, MAXY, nz*R_MIN, i.getMaxU(), i.getMaxV());
			t.addVertexWithUV(nx*R_MIN, MINY, nz*R_MIN, i.getMinU(), i.getMaxV());
			
			t.addVertexWithUV(nx*R_MAX, MINY, nz*R_MAX, i.getMinU(), i.getMaxV());
			t.addVertexWithUV(nx*R_MAX, MAXY, nz*R_MAX, i.getMaxU(), i.getMaxV());
			t.addVertexWithUV(x*R_MAX, MAXY, z*R_MAX, i.getMaxU(), i.getMinV());
			t.addVertexWithUV(x*R_MAX, MINY, z*R_MAX, i.getMinU(), i.getMinV());
			
			t.addVertexWithUV(x*R_MAX, MINY, z*R_MAX, i.getMinU(), i.getMinV());
			t.addVertexWithUV(x*R_MIN, MINY, z*R_MIN, i.getMaxU(), i.getMinV());
			t.addVertexWithUV(nx*R_MIN, MINY, nz*R_MIN, i.getMaxU(), i.getMaxV());
			t.addVertexWithUV(nx*R_MAX, MINY, nz*R_MAX, i.getMinU(), i.getMaxV());
			
			t.addVertexWithUV(nx*R_MAX, MAXY, nz*R_MAX, i.getMinU(), i.getMaxV());
			t.addVertexWithUV(nx*R_MIN, MAXY, nz*R_MIN, i.getMaxU(), i.getMaxV());
			t.addVertexWithUV(x*R_MIN, MAXY, z*R_MIN, i.getMaxU(), i.getMinV());
			t.addVertexWithUV(x*R_MAX, MAXY, z*R_MAX, i.getMinU(), i.getMinV());
			
			
			// render spoke
			double spokemult = Math.sqrt((nx-x)*(nx-x)+(nz-z)*(nz-z));
			double dx = (nx-x)*spokemult, dz=(nz-z)*spokemult;
			x = (x+nx)/2; z = (z+nz)/2;
			
			t.addVertexWithUV(x+dx,  SPOKESIZE, z+dz, i.getMinU(), i.getMaxV());
			t.addVertexWithUV(0,     SPOKESIZE, 0,    i.getMaxU(), i.getMaxV());
			t.addVertexWithUV(0,    -SPOKESIZE, 0,    i.getMaxU(), i.getMinV());
			t.addVertexWithUV(x+dx, -SPOKESIZE, z+dz, i.getMinU(), i.getMinV());
			
			t.addVertexWithUV(x-dx,  SPOKESIZE, z-dz, i.getMinU(), i.getMaxV());
			t.addVertexWithUV( -dx,  SPOKESIZE,  -dz,    i.getMaxU(), i.getMaxV());
			t.addVertexWithUV( +dx,  SPOKESIZE,  +dz,    i.getMaxU(), i.getMinV());
			t.addVertexWithUV(x+dx,  SPOKESIZE, z+dz, i.getMinU(), i.getMinV());
			
			/*t.addVertexWithUV( SPOKESIZE, -SPOKESIZE, -R_MAX, i.getMinU(), i.getMinV());
			t.addVertexWithUV( SPOKESIZE, -SPOKESIZE,  R_MAX, i.getMaxU(), i.getMinV());
			t.addVertexWithUV( SPOKESIZE,  SPOKESIZE,  R_MAX, i.getMaxU(), i.getMaxV());
			t.addVertexWithUV( SPOKESIZE,  SPOKESIZE, -R_MAX, i.getMinU(), i.getMaxV());
			
			t.addVertexWithUV(-SPOKESIZE, -SPOKESIZE, -R_MAX, i.getMinU(), i.getMaxV());
			t.addVertexWithUV(-SPOKESIZE, -SPOKESIZE,  R_MAX, i.getMaxU(), i.getMaxV());
			t.addVertexWithUV( SPOKESIZE, -SPOKESIZE,  R_MAX, i.getMaxU(), i.getMinV());
			t.addVertexWithUV( SPOKESIZE, -SPOKESIZE, -R_MAX, i.getMinU(), i.getMinV());
			
			t.addVertexWithUV( SPOKESIZE,  SPOKESIZE, -R_MAX, i.getMinU(), i.getMinV());
			t.addVertexWithUV( SPOKESIZE,  SPOKESIZE,  R_MAX, i.getMaxU(), i.getMinV());
			t.addVertexWithUV(-SPOKESIZE,  SPOKESIZE,  R_MAX, i.getMaxU(), i.getMaxV());
			t.addVertexWithUV(-SPOKESIZE,  SPOKESIZE, -R_MAX, i.getMinU(), i.getMaxV());*/
		}
		
		/*t.addVertexWithUV(-R_MAX,  SPOKESIZE, -SPOKESIZE, i.getMinU(), i.getMaxV());
		t.addVertexWithUV( R_MAX,  SPOKESIZE, -SPOKESIZE, i.getMaxU(), i.getMaxV());
		t.addVertexWithUV( R_MAX, -SPOKESIZE, -SPOKESIZE, i.getMaxU(), i.getMinV());
		t.addVertexWithUV(-R_MAX, -SPOKESIZE, -SPOKESIZE, i.getMinU(), i.getMinV());
		
		t.addVertexWithUV(-R_MAX, -SPOKESIZE,  SPOKESIZE, i.getMinU(), i.getMinV());
		t.addVertexWithUV( R_MAX, -SPOKESIZE,  SPOKESIZE, i.getMaxU(), i.getMinV());
		t.addVertexWithUV( R_MAX,  SPOKESIZE,  SPOKESIZE, i.getMaxU(), i.getMaxV());
		t.addVertexWithUV(-R_MAX,  SPOKESIZE,  SPOKESIZE, i.getMinU(), i.getMaxV());
		
		t.addVertexWithUV(-R_MAX, -SPOKESIZE, -SPOKESIZE, i.getMinU(), i.getMaxV());
		t.addVertexWithUV( R_MAX, -SPOKESIZE, -SPOKESIZE, i.getMaxU(), i.getMaxV());
		t.addVertexWithUV( R_MAX, -SPOKESIZE,  SPOKESIZE, i.getMaxU(), i.getMinV());
		t.addVertexWithUV(-R_MAX, -SPOKESIZE,  SPOKESIZE, i.getMinU(), i.getMinV());
		
		t.addVertexWithUV(-R_MAX,  SPOKESIZE,  SPOKESIZE, i.getMinU(), i.getMinV());
		t.addVertexWithUV( R_MAX,  SPOKESIZE,  SPOKESIZE, i.getMaxU(), i.getMinV());
		t.addVertexWithUV( R_MAX,  SPOKESIZE, -SPOKESIZE, i.getMaxU(), i.getMaxV());
		t.addVertexWithUV(-R_MAX,  SPOKESIZE, -SPOKESIZE, i.getMinU(), i.getMaxV());
		
		
		t.addVertexWithUV(-SPOKESIZE,  SPOKESIZE, -R_MAX, i.getMinU(), i.getMaxV());
		t.addVertexWithUV(-SPOKESIZE,  SPOKESIZE,  R_MAX, i.getMaxU(), i.getMaxV());
		t.addVertexWithUV(-SPOKESIZE, -SPOKESIZE,  R_MAX, i.getMaxU(), i.getMinV());
		t.addVertexWithUV(-SPOKESIZE, -SPOKESIZE, -R_MAX, i.getMinU(), i.getMinV());
		
		t.addVertexWithUV( SPOKESIZE, -SPOKESIZE, -R_MAX, i.getMinU(), i.getMinV());
		t.addVertexWithUV( SPOKESIZE, -SPOKESIZE,  R_MAX, i.getMaxU(), i.getMinV());
		t.addVertexWithUV( SPOKESIZE,  SPOKESIZE,  R_MAX, i.getMaxU(), i.getMaxV());
		t.addVertexWithUV( SPOKESIZE,  SPOKESIZE, -R_MAX, i.getMinU(), i.getMaxV());
		
		t.addVertexWithUV(-SPOKESIZE, -SPOKESIZE, -R_MAX, i.getMinU(), i.getMaxV());
		t.addVertexWithUV(-SPOKESIZE, -SPOKESIZE,  R_MAX, i.getMaxU(), i.getMaxV());
		t.addVertexWithUV( SPOKESIZE, -SPOKESIZE,  R_MAX, i.getMaxU(), i.getMinV());
		t.addVertexWithUV( SPOKESIZE, -SPOKESIZE, -R_MAX, i.getMinU(), i.getMinV());
		
		t.addVertexWithUV( SPOKESIZE,  SPOKESIZE, -R_MAX, i.getMinU(), i.getMinV());
		t.addVertexWithUV( SPOKESIZE,  SPOKESIZE,  R_MAX, i.getMaxU(), i.getMinV());
		t.addVertexWithUV(-SPOKESIZE,  SPOKESIZE,  R_MAX, i.getMaxU(), i.getMaxV());
		t.addVertexWithUV(-SPOKESIZE,  SPOKESIZE, -R_MAX, i.getMinU(), i.getMaxV());*/
	}
}
