package immibis.modjam4;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCartBooster extends BlockShaft {

	public BlockCartBooster() {
		super(Material.rock);
		setHardness(5f);
		setBlockName("immibis_modjam4.booster");
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileCartBooster();
	}
	
	@Override
	public int getRenderType() {
		return 0;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return true;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return true;
	}

}
