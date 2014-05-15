package immibis.modjam4;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = Modjam4Mod.MODID, version = "0.0")
public class Modjam4Mod
{
	public static final String MODID = "immibis_modjam4";
	
	@Instance(MODID) public static Modjam4Mod INSTANCE;
	@SidedProxy(clientSide="immibis.modjam4.ProxyClient", serverSide="immibis.modjam4.ProxyBase") public static ProxyBase PROXY;
	
	public static BlockShaft blockWoodenShaft; 
	
	public static int NULL_RENDER_ID = 0;
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		blockWoodenShaft = new BlockShaft(Material.wood);
		blockWoodenShaft.setBlockName("immibis_modjam4.woodenShaft");
		
		GameRegistry.registerBlock(blockWoodenShaft, "woodenShaft");
		
		GameRegistry.registerTileEntity(TileShaft.class, "immibisMJ4.shaft");
		
		PROXY.init();
    }

    @SideOnly(Side.CLIENT)
	public static void clientInit() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileShaft.class, new RenderTileShaft());
		
		NULL_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderBlockNothing());
	}
}




@SideOnly(Side.CLIENT)
class RenderBlockNothing implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return Modjam4Mod.NULL_RENDER_ID;
	}

}