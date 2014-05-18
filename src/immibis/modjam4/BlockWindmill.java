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
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

// metadata is axis (0=Y 2=Z 4=X)
public class BlockWindmill extends BlockShaft {
	
	public static final float HARDNESS = 5;
	
	public BlockWindmill() {
		super(Material.wood);
		setHardness(HARDNESS);
		setBlockName("immibis_modjam4.windmill");
		setCreativeTab(CreativeTabs.tabAllSearch);
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileWindmill();
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
					if(!w.isAirBlock(x+dx, y+dy, z))
						return false;
			break;
			
		case 4:
			// shaft on X axis, mill on Y/Z axis
			for(int dz = -2; dz <= 2; dz++)
				for(int dy = -2; dy <= 2; dy++)
					if(!w.isAirBlock(x, y+dy, z+dz))
						return false;
			break;
		}
		
		return super.canPlaceBlockOnSide(w, x, y, z, side);
	}
	
	@Override
	public int onBlockPlaced(World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		return side^1;
	}
	
	@Override
	public void onPostBlockPlaced(World w, int x, int y, int z, int meta) {
		switch(meta & 6) {
		case 2:
			w.setBlock(x-1, y, z, Modjam4Mod.blockFiller, Dir.PX, 3);
			w.setBlock(x-2, y, z, Modjam4Mod.blockFiller, Dir.PX, 3);
			w.setBlock(x+1, y, z, Modjam4Mod.blockFiller, Dir.NX, 3);
			w.setBlock(x+2, y, z, Modjam4Mod.blockFiller, Dir.NX, 3);
			for(int dx = -2; dx <= 2; dx++) {
				w.setBlock(x+dx, y-1, z, Modjam4Mod.blockFiller, Dir.PY, 3);
				w.setBlock(x+dx, y-2, z, Modjam4Mod.blockFiller, Dir.PY, 3);
				w.setBlock(x+dx, y+1, z, Modjam4Mod.blockFiller, Dir.NY, 3);
				w.setBlock(x+dx, y+2, z, Modjam4Mod.blockFiller, Dir.NY, 3);
			}
			break;
		case 4:
			w.setBlock(x, y, z-1, Modjam4Mod.blockFiller, Dir.PZ, 3);
			w.setBlock(x, y, z-2, Modjam4Mod.blockFiller, Dir.PZ, 3);
			w.setBlock(x, y, z+1, Modjam4Mod.blockFiller, Dir.NZ, 3);
			w.setBlock(x, y, z+2, Modjam4Mod.blockFiller, Dir.NZ, 3);
			for(int dz = -2; dz <= 2; dz++) {
				w.setBlock(x, y-1, z+dz, Modjam4Mod.blockFiller, Dir.PY, 3);
				w.setBlock(x, y-2, z+dz, Modjam4Mod.blockFiller, Dir.PY, 3);
				w.setBlock(x, y+1, z+dz, Modjam4Mod.blockFiller, Dir.NY, 3);
				w.setBlock(x, y+2, z+dz, Modjam4Mod.blockFiller, Dir.NY, 3);
			}
			break;
		}
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
		new RenderTileWindmill().renderShaft(true);
		Tessellator.instance.draw();
		
		GL11.glDisable(GL11.GL_LIGHTING);
		Tessellator.instance.startDrawingQuads();
		new RenderTileWindmill().renderAttachment();
		Tessellator.instance.draw();
		GL11.glPopMatrix();
		GL11.glEnable(GL11.GL_LIGHTING);
	}
}
