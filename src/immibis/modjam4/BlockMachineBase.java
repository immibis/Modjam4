package immibis.modjam4;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class BlockMachineBase extends BlockContainer {
	public BlockMachineBase(Material m) {
		super(m);
	}
	
	@Override
	public void onNeighborBlockChange(World w, int x, int y, int z, Block p_149695_5_) {
		((TileMachine)w.getTileEntity(x, y, z)).onBlockUpdate();
	}
	
	@SideOnly(Side.CLIENT)
	public abstract void renderInvBlock(RenderBlocks rb);
	
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer pl, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		return ((TileMachine)w.getTileEntity(x, y, z)).onBlockActivated(pl);
	}
}
