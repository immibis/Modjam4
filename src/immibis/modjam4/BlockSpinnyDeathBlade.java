package immibis.modjam4;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSpinnyDeathBlade extends BlockShaft {
	public BlockSpinnyDeathBlade() {
		super(Material.iron);
		
		setCreativeTab(CreativeTabs.tabAllSearch);
		setHardness(5);
		setBlockName("immibis_modjam4.deathBlade");
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void renderInvBlock(RenderBlocks rb) {
		// TODO Auto-generated method stub
		
	}
}
