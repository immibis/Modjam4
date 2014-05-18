package immibis.modjam4;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderBlockGearboxDouble implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		Modjam4Mod.DOUBLE_GEARBOX_RENDER_ID = 0;
		setRotation(renderer, metadata);
		renderer.renderBlockAsItem(block, metadata, 1);
		clearRotation(renderer);
		Modjam4Mod.DOUBLE_GEARBOX_RENDER_ID = modelId;
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		setRotation(renderer, world.getBlockMetadata(x, y, z));
		renderer.renderStandardBlock(block, x, y, z);
		clearRotation(renderer);
		return true;
	}
	
	private final static int[][] ROTATION_LOOKUP = {
		// block face:                  high-speed side
		// NY  PY  NZ  PZ  NX  PX
		{  -1, -1,  1,  2,  1,  2 }, // NY
		{  -1, -1,  2,  1,  2,  1 }, // PY
		{   1,  2, -1, -1,  2,  4 }, // NZ
		{   2,  1, -1, -1,  1,  3 }, // PZ
		{   3,  3,  2,  4, -1, -1 }, // NX
		{   0,  0,  1,  0, -1, -1 }  // PX
	};

	private void setRotation(RenderBlocks rb, int hsside) {
		rb.uvRotateBottom = ROTATION_LOOKUP[hsside][0];
		rb.uvRotateEast = ROTATION_LOOKUP[hsside][3];
		rb.uvRotateNorth = ROTATION_LOOKUP[hsside][4];
		rb.uvRotateSouth = ROTATION_LOOKUP[hsside][5];
		rb.uvRotateTop = ROTATION_LOOKUP[hsside][1];
		rb.uvRotateWest = ROTATION_LOOKUP[hsside][2];
	}

	private void clearRotation(RenderBlocks rb) {
		rb.uvRotateBottom = 0;
		rb.uvRotateEast = 0;
		rb.uvRotateNorth = 0;
		rb.uvRotateSouth = 0;
		rb.uvRotateTop = 0;
		rb.uvRotateWest = 0;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return Modjam4Mod.DOUBLE_GEARBOX_RENDER_ID;
	}

}
