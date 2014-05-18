package immibis.modjam4;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.DamageSource;
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
	public static BlockCreativeEngine blockCreativeEngine;
	public static BlockGearboxDirectional blockStoneDirectionalGearbox;
	public static BlockGearboxDouble blockStoneDoubleGearbox;
	public static BlockInductionGenerator blockInductionGenerator;
	public static BlockCable blockCable;
	public static BlockWatermill blockWatermill;
	public static BlockMillstone blockMillstone;
	public static BlockFan blockFan;
	public static BlockSpinnyDeathBlade blockSpinnyDeathBlade;
	public static BlockWindmill blockWindmill;
	public static BlockWindmillFiller blockFiller;
	public static Item itemFlour;
	
	public static DamageSource damageSourceSpinnyBlade = new DamageSource("immibis_modjam4.spinnyBlade");
	
	public static int NULL_RENDER_ID = 0;
	public static int DOUBLE_GEARBOX_RENDER_ID = 0;
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		blockWoodenShaft = new BlockShaft(Material.wood);
		blockWoodenShaft.setBlockName("immibis_modjam4.woodenShaft");
		GameRegistry.registerBlock(blockWoodenShaft, "woodenShaft");
		
		blockCreativeEngine = new BlockCreativeEngine();
		GameRegistry.registerBlock(blockCreativeEngine, "creativeEngine");
		
		blockStoneDirectionalGearbox = new BlockGearboxDirectional(Material.rock);
		blockStoneDirectionalGearbox.setBlockTextureName("immibis_modjam4:gearbox1");
		blockStoneDirectionalGearbox.setBlockName("immibis_modjam4.stoneDirectionalGearbox");
		GameRegistry.registerBlock(blockStoneDirectionalGearbox, "stoneDirectionalGearbox");
		
		blockInductionGenerator = new BlockInductionGenerator();
		GameRegistry.registerBlock(blockInductionGenerator, "inductionGenerator");
		
		blockCable = new BlockCable();
		GameRegistry.registerBlock(blockCable, "cable");
		
		blockStoneDoubleGearbox = new BlockGearboxDouble(Material.rock);
		blockStoneDoubleGearbox.setBlockName("immibis_modjam4.stoneDoubleGearbox");
		GameRegistry.registerBlock(blockStoneDoubleGearbox, "stoneDoubleGearbox");
		
		blockWatermill = new BlockWatermill(Material.wood);
		blockWatermill.setBlockName("immibis_modjam4.woodenWatermill");
		GameRegistry.registerBlock(blockWatermill, "woodenWatermill");
		Blocks.fire.setFireInfo(blockWatermill, 60, 20);
		
		blockMillstone = new BlockMillstone();
		GameRegistry.registerBlock(blockMillstone, "millstone");
		
		blockFan = new BlockFan(Material.iron);
		blockFan.setBlockName("immibis_modjam4.fan");
		GameRegistry.registerBlock(blockFan, "fan");
		
		blockSpinnyDeathBlade = new BlockSpinnyDeathBlade();
		GameRegistry.registerBlock(blockSpinnyDeathBlade, "spinnyDeathBlade");
		
		blockWindmill = new BlockWindmill();
		GameRegistry.registerBlock(blockWindmill, "windmill");
		Blocks.fire.setFireInfo(blockWindmill, 60, 20);
		
		blockFiller = new BlockWindmillFiller();
		GameRegistry.registerBlock(blockFiller, "filler");
		
		itemFlour = new Item();
		itemFlour.setCreativeTab(CreativeTabs.tabFood);
		itemFlour.setTextureName("immibis_modjam4:flour");
		itemFlour.setUnlocalizedName("immibis_modjam4.flour");
		GameRegistry.registerItem(itemFlour, "flour");
		
		GameRegistry.registerTileEntity(TileShaft.class, "immibisMJ4.shaft");
		GameRegistry.registerTileEntity(TileCreativeEngine.class, "immibisMJ4.creativeEngine");
		GameRegistry.registerTileEntity(TileGearboxDirectional.class, "immibisMJ4.dirGearbox");
		GameRegistry.registerTileEntity(TileInductionGenerator.class, "immibisMJ4.inductionGenerator");
		GameRegistry.registerTileEntity(TileCable.class, "immibisMJ4.cable");
		GameRegistry.registerTileEntity(TileGearboxDouble.class, "immibisMJ4.doubleGearbox");
		GameRegistry.registerTileEntity(TileWatermill.class, "immibisMJ4.watermill");
		GameRegistry.registerTileEntity(TileMillstone.class, "immibisMJ4.millstone");
		GameRegistry.registerTileEntity(TileFan.class, "immibisMJ4.fan");
		GameRegistry.registerTileEntity(TileSpinnyDeathBlade.class, "immibisMJ4.spinnyDeathBlade");
		GameRegistry.registerTileEntity(TileWindmill.class, "immibisMJ4.windmill");
		
		FurnaceRecipes.smelting().func_151396_a(itemFlour, new ItemStack(Items.bread), 0.3f);
		
		PROXY.init();
    }

    @SideOnly(Side.CLIENT)
	public static void clientInit() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileShaft.class, new RenderTileShaft());
		ClientRegistry.bindTileEntitySpecialRenderer(TileWatermill.class, new RenderTileWatermill());
		ClientRegistry.bindTileEntitySpecialRenderer(TileMillstone.class, new RenderTileMillstone());
		ClientRegistry.bindTileEntitySpecialRenderer(TileFan.class, new RenderTileFan());
		ClientRegistry.bindTileEntitySpecialRenderer(TileSpinnyDeathBlade.class, new RenderTileSpinnyDeathBlade());
		ClientRegistry.bindTileEntitySpecialRenderer(TileWindmill.class, new RenderTileWindmill());
		
		NULL_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderBlockNothing());
		
		DOUBLE_GEARBOX_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderBlockGearboxDouble());
	}
}




@SideOnly(Side.CLIENT)
class RenderBlockNothing implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		if(block instanceof BlockMachineBase)
			((BlockMachineBase)block).renderInvBlock(renderer);
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