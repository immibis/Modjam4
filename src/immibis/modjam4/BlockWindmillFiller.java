package immibis.modjam4;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * The invisible air blocks surrounding a windmill block.
 * 
 * Meta values: (these correspond to directions)
 * 0. Centre block is above this (and maybe to the side)
 * 1. Centre block is below this (and maybe to the side)
 * 2. Centre block is -Z on the same level
 * 3. Centre block is +Z on the same level
 * 4. Centre block is -X on the same level
 * 5. Centre block is +X on the same level
 */
public class BlockWindmillFiller extends Block {

	protected BlockWindmillFiller() {
		super(Material.wood);
		setHardness(BlockWindmill.HARDNESS);
		setBlockName("immibis_modjam3.windmill");
	}
	
	public static final ChunkCoordinates INVALID_STRUCTURE = new ChunkCoordinates(0,0,0);
	public static final ChunkCoordinates CENTRE_NOT_LOADED = new ChunkCoordinates(0,0,0);
	/** Return centre block coordinates, or INVALID_STRUCTURE, or CENTRE_NOT_LOADED */
	public static ChunkCoordinates findWindmillCentre(World w, int x, int y, int z) {
		return findWindmillCentre(w, x, y, z, 0);
	}
	private static ChunkCoordinates findWindmillCentre(World w, int x, int y, int z, int recursionDepth) {
		if(recursionDepth > 30)
			return INVALID_STRUCTURE;
		if(!w.blockExists(x, y, z))
			return CENTRE_NOT_LOADED;
		if(w.getBlock(x, y, z) == Modjam4Mod.blockWindmill)
			return new ChunkCoordinates(x, y, z);
		if(w.getBlock(x, y, z) != Modjam4Mod.blockFiller)
			return INVALID_STRUCTURE;
		int meta = w.getBlockMetadata(x, y, z);
		if(meta > 5)
			return INVALID_STRUCTURE;
		ForgeDirection fd = ForgeDirection.VALID_DIRECTIONS[meta];
		return findWindmillCentre(w, fd.offsetX+x, fd.offsetY+y, fd.offsetZ+z, recursionDepth+1);
	}
	
	@Override
	public void onNeighborBlockChange(World w, int x, int y, int z, Block p_149695_5_) {
		if(findWindmillCentre(w, x, y, z) == INVALID_STRUCTURE)
			w.setBlockToAir(x, y, z);
	}
	
	@Override
	public int getRenderType() {
		return 0;
	}

}
