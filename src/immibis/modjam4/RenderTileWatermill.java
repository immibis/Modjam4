package immibis.modjam4;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

public class RenderTileWatermill extends RenderTileShaft {
	protected void renderAttachment() {
		final double MINY = -7/16f, MAXY = -MINY;
		final int NUM_SLICES = 12;
		Tessellator t = Tessellator.instance;
		IIcon i = Blocks.planks.getIcon(0, 0);
		
		final double R_MIN = 24/16f;
		final double R_MAX = 26/16f;
		
		final double SPOKESIZE = 4/16f;
		
		for(int slice = 0; slice < NUM_SLICES; slice++) {
			double x = Math.sin(slice * (Math.PI * 2 / NUM_SLICES));
			double z = Math.cos(slice * (Math.PI * 2 / NUM_SLICES));
			
			double nx = Math.sin((slice+1) * (Math.PI * 2 / NUM_SLICES));
			double nz = Math.cos((slice+1) * (Math.PI * 2 / NUM_SLICES));
			
			double px = Math.sin((slice-1) * (Math.PI * 2 / NUM_SLICES));
			double pz = Math.cos((slice-1) * (Math.PI * 2 / NUM_SLICES));
			
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
			
			
			// render paddle
			{
				double _x = x*R_MAX, _z = z*R_MAX;
				double _nx=(nx*0.1+x*0.9)*R_MAX, _nz=(nz*0.1+z*0.9)*R_MAX;
				double _px=(px*0.1+x*0.9)*R_MAX, _pz=(pz*0.1+z*0.9)*R_MAX;
				
				double rmult = 2.5 / R_MAX;
				
				t.addVertexWithUV(_nx      , MINY, _nz      , i.getMinU(), i.getMaxV());
				t.addVertexWithUV(_nx      , MAXY, _nz      , i.getMaxU(), i.getMaxV());
				t.addVertexWithUV(_nx*rmult, MAXY, _nz*rmult, i.getMaxU(), i.getMinV());
				t.addVertexWithUV(_nx*rmult, MINY, _nz*rmult, i.getMinU(), i.getMinV());
				
				t.addVertexWithUV(_px*rmult, MINY, _pz*rmult, i.getMinU(), i.getMinV());
				t.addVertexWithUV(_px*rmult, MAXY, _pz*rmult, i.getMaxU(), i.getMinV());
				t.addVertexWithUV(_px      , MAXY, _pz      , i.getMaxU(), i.getMaxV());
				t.addVertexWithUV(_px      , MINY, _pz      , i.getMinU(), i.getMaxV());
				
				t.addVertexWithUV(_nx*rmult, MINY, _nz*rmult, i.getMinU(), i.getMinV());
				t.addVertexWithUV(_px*rmult, MINY, _pz*rmult, i.getMaxU(), i.getMinV());
				t.addVertexWithUV(_px      , MINY, _pz      , i.getMaxU(), i.getMaxV());
				t.addVertexWithUV(_nx      , MINY, _nz      , i.getMinU(), i.getMaxV());
				
				t.addVertexWithUV(_nx      , MAXY, _nz      , i.getMinU(), i.getMaxV());
				t.addVertexWithUV(_px      , MAXY, _pz      , i.getMaxU(), i.getMaxV());
				t.addVertexWithUV(_px*rmult, MAXY, _pz*rmult, i.getMaxU(), i.getMinV());
				t.addVertexWithUV(_nx*rmult, MAXY, _nz*rmult, i.getMinU(), i.getMinV());
				
				t.addVertexWithUV(_nx*rmult, MAXY, _nz*rmult, i.getMinU(), i.getMinV());
				t.addVertexWithUV(_px*rmult, MAXY, _pz*rmult, i.getMaxU(), i.getMinV());
				t.addVertexWithUV(_px*rmult, MINY, _pz*rmult, i.getMaxU(), i.getMaxV());
				t.addVertexWithUV(_nx*rmult, MINY, _nz*rmult, i.getMinU(), i.getMaxV());
				
			}
			
			// render spoke
			if((slice & 1) == 0) {
				double spokemult = Math.sqrt((nx-x)*(nx-x)+(nz-z)*(nz-z))*SPOKESIZE;
				double dx = (nx-x)*spokemult, dz=(nz-z)*spokemult;
				x = (x+nx)/2; z = (z+nz)/2;
				x *= R_MIN; z *= R_MIN;
				
				t.addVertexWithUV(x+dx, -SPOKESIZE, z+dz, i.getMinU(), i.getMinV());
				t.addVertexWithUV( +dx, -SPOKESIZE,  +dz, i.getMaxU(), i.getMinV());
				t.addVertexWithUV( +dx,  SPOKESIZE,  +dz, i.getMaxU(), i.getMaxV());
				t.addVertexWithUV(x+dx,  SPOKESIZE, z+dz, i.getMinU(), i.getMaxV());
				
				t.addVertexWithUV(x+dx,  SPOKESIZE, z+dz, i.getMinU(), i.getMinV());
				t.addVertexWithUV( +dx,  SPOKESIZE,  +dz, i.getMaxU(), i.getMinV());
				t.addVertexWithUV( -dx,  SPOKESIZE,  -dz, i.getMaxU(), i.getMaxV());
				t.addVertexWithUV(x-dx,  SPOKESIZE, z-dz, i.getMinU(), i.getMaxV());
				
				t.addVertexWithUV(x-dx, -SPOKESIZE, z-dz, i.getMinU(), i.getMaxV());
				t.addVertexWithUV( -dx, -SPOKESIZE,  -dz, i.getMaxU(), i.getMaxV());
				t.addVertexWithUV( +dx, -SPOKESIZE,  +dz, i.getMaxU(), i.getMinV());
				t.addVertexWithUV(x+dx, -SPOKESIZE, z+dz, i.getMinU(), i.getMinV());
				
				t.addVertexWithUV(x-dx,  SPOKESIZE, z-dz, i.getMinU(), i.getMinV());
				t.addVertexWithUV( -dx,  SPOKESIZE,  -dz, i.getMaxU(), i.getMinV());
				t.addVertexWithUV( -dx, -SPOKESIZE,  -dz, i.getMaxU(), i.getMaxV());
				t.addVertexWithUV(x-dx, -SPOKESIZE, z-dz, i.getMinU(), i.getMaxV());
				
			}
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
