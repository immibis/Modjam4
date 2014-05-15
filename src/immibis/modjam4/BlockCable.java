package immibis.modjam4;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCable extends BlockContainer {

	protected BlockCable() {
		super(Material.cloth);
		
		setBlockName("immibis_modjam4.cable");
		setCreativeTab(CreativeTabs.tabAllSearch);
		setHardness(2);
	}
	
	@Override
	public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_) {
		return Blocks.wool.getIcon(0, 0);
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileCable();
	}
	
	@Override
	public void onNeighborBlockChange(World w, int x, int y, int z, Block p_149695_5_) {
		((TileCable)w.getTileEntity(x, y, z)).onBlockUpdate();
	}
	
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer pl, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		TileCable tc = ((TileCable)w.getTileEntity(x, y, z));
		pl.addChatMessage(new ChatComponentText(tc.getNetwork().toString()));
		return true;
	}

}
