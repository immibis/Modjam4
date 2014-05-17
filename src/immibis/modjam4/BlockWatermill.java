package immibis.modjam4;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

// metadata is axis (0=Y 2=Z 4=X)
public class BlockWatermill extends BlockShaft {
	public BlockWatermill(Material m) {
		super(m);
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileWatermill();
	}
	
	@Override
	public boolean canPlaceBlockOnSide(World w, int x, int y, int z, int side) {
		
		switch(side & 6) {
		case 0:
			// prevent horizontal placement
			return false;
			
		case 2:
			// shaft on Z axis, mill on X/Y axis
			for(int dx = -2; dx <= 2; dx++)
				for(int dy = -2; dy <= 2; dy++)
					if(TileWatermill.getLiquidLevel(w, x+dx, y+dy, z) == TileWatermill.LL_OBSTRUCTION)
						return false;
			break;
			
		case 4:
			// shaft on X axis, mill on Y/Z axis
			for(int dz = -2; dz <= 2; dz++)
				for(int dy = -2; dy <= 2; dy++)
					if(TileWatermill.getLiquidLevel(w, x, y+dy, z+dz) == TileWatermill.LL_OBSTRUCTION)
						return false;
			break;
		}
		
		return super.canPlaceBlockOnSide(w, x, y, z, side);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void renderInvBlock(RenderBlocks rb) {
		GL11.glPushMatrix();
		GL11.glScalef(0.4f, 0.4f, 0.4f);
		GL11.glRotatef(-22.5f, 0, 1, 0);
		GL11.glRotatef(90, 0, 0, 1);
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		Tessellator.instance.startDrawingQuads();
		new RenderTileWatermill().renderShaft(true);
		Tessellator.instance.draw();
		
		GL11.glDisable(GL11.GL_LIGHTING);
		Tessellator.instance.startDrawingQuads();
		new RenderTileWatermill().renderAttachment();
		Tessellator.instance.draw();
		GL11.glPopMatrix();
	}
}
