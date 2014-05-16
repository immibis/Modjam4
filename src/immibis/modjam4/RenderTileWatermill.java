package immibis.modjam4;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

public class RenderTileWatermill extends RenderTileShaft {
	protected void renderAttachment() {
		final double MINY = 1/16f, MAXY = 15/16f;
		final int NUM_SLICES = 16;
		Tessellator t = Tessellator.instance;
		IIcon i = Blocks.planks.getIcon(0, 0);
		for(int slice = 0; slice < NUM_SLICES; slice++) {
			double x = Math.sin(slice * (Math.PI * 2 / NUM_SLICES));
			double z = Math.cos(slice * (Math.PI * 2 / NUM_SLICES));
			
			double nx = Math.sin((slice+1) * (Math.PI * 2 / NUM_SLICES));
			double nz = Math.cos((slice+2) * (Math.PI * 2 / NUM_SLICES));
			
			t.addVertexWithUV(x, MINY, z, i.getMinU(), i.getMinV());
			t.addVertexWithUV(x, MAXY, z, i.getMaxU(), i.getMinV());
			t.addVertexWithUV(nx, MAXY, nz, i.getMaxU(), i.getMaxV());
			t.addVertexWithUV(nx, MINY, nz, i.getMinU(), i.getMaxV());
			
		}
	}
}
