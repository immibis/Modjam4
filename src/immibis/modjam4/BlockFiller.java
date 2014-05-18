package immibis.modjam4;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * The invisible air blocks surrounding a large block (like a windmill or water wheel).
 * 
 * Meta values: (these correspond to directions)
 * 0. Main block is above this (and maybe to the side)
 * 1. Main block is below this (and maybe to the side)
 * 2. Main block is -Z on the same level
 * 3. Main block is +Z on the same level
 * 4. Main block is -X on the same level
 * 5. Main block is +X on the same level
 */
public class BlockFiller extends Block {

	protected BlockFiller() {
		super(Material.wood);
		setHardness(BlockWindmill.HARDNESS);
		setBlockName("immibis_modjam3.windmill");
		setBlockTextureName("log_oak");
	}
	
	public static final ChunkCoordinates INVALID_STRUCTURE = new ChunkCoordinates(0,0,0);
	public static final ChunkCoordinates CENTRE_NOT_LOADED = new ChunkCoordinates(0,0,0);
	/** Return centre block coordinates, or INVALID_STRUCTURE, or CENTRE_NOT_LOADED */
	public static ChunkCoordinates findMainBlock(World w, int x, int y, int z) {
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
		if(findMainBlock(w, x, y, z) == INVALID_STRUCTURE)
			w.setBlockToAir(x, y, z);
	}
	
	@Override
	public int getRenderType() {
		return Modjam4Mod.NULL_RENDER_ID;
	}
	
	@Override
	public void breakBlock(World w, int x, int y, int z, Block p_149749_5_, int meta) {
		ForgeDirection fd = ForgeDirection.VALID_DIRECTIONS[meta];
		ChunkCoordinates cc = findMainBlock(w, x+fd.offsetX, y+fd.offsetY, z+fd.offsetZ);
		if(cc == INVALID_STRUCTURE)
			return;
		if(cc == CENTRE_NOT_LOADED) {
			w.setBlock(x, y, z, this);
			return;
		}
		w.setBlockToAir(cc.posX, cc.posY, cc.posZ);
	}
	
	@Override
	public float getBlockHardness(World w, int x, int y, int z) {
		ChunkCoordinates cc = findMainBlock(w, x, y, z);
		if(cc == INVALID_STRUCTURE)
			return 1;
		if(cc == CENTRE_NOT_LOADED)
			return -1;
		return w.getBlock(cc.posX, cc.posY, cc.posZ).getBlockHardness(w, cc.posX, cc.posY, cc.posZ);
	}
	
	@Override
	public void harvestBlock(World w, EntityPlayer pl, int x, int y, int z, int meta) {
		ForgeDirection fd = ForgeDirection.VALID_DIRECTIONS[meta];
		ChunkCoordinates cc = findMainBlock(w, x+fd.offsetX, y+fd.offsetY, z+fd.offsetZ);
		if(cc == INVALID_STRUCTURE)
			return;
		if(cc == CENTRE_NOT_LOADED)
			return;
		w.getBlock(cc.posX, cc.posY, cc.posZ).harvestBlock(w, pl, cc.posX, cc.posY, cc.posZ, w.getBlockMetadata(cc.posX, cc.posY, cc.posZ));
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

}
