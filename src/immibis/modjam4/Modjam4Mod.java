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
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
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
	//public static BlockCreativeEngine blockCreativeEngine;
	public static BlockGearboxDirectional blockStoneDirectionalGearbox;
	public static BlockGearboxDouble blockStoneDoubleGearbox;
	//public static BlockInductionGenerator blockInductionGenerator;
	//public static BlockCable blockCable;
	public static BlockWatermill blockWatermill;
	public static BlockMillstone blockMillstone;
	public static BlockFan blockFan;
	public static BlockSpinnyDeathBlade blockSpinnyDeathBlade;
	public static BlockWindmill blockWindmill;
	public static BlockFiller blockFiller;
	public static BlockCartBooster blockCartBooster;
	public static Item itemFlour;
	public static Item itemGear;
	public static Item itemPaddle;
	public static Item itemBlade;
	public static Item itemSail;
	
	public static DamageSource damageSourceSpinnyBlade = new DamageSource("immibis_modjam4.spinnyBlade");
	
	public static int NULL_RENDER_ID = 0;
	public static int DOUBLE_GEARBOX_RENDER_ID = 0;
	
	public static int windSpeed;
	
	@SubscribeEvent
	public void onTick(TickEvent.ServerTickEvent evt) {
		//System.out.println("tick");
	}
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	FMLCommonHandler.instance().bus().register(this);
    	
		blockWoodenShaft = new BlockShaft(Material.wood);
		blockWoodenShaft.setBlockName("immibis_modjam4.woodenShaft");
		GameRegistry.registerBlock(blockWoodenShaft, "woodenShaft");
		
		//blockCreativeEngine = new BlockCreativeEngine();
		//GameRegistry.registerBlock(blockCreativeEngine, "creativeEngine");
		
		blockStoneDirectionalGearbox = new BlockGearboxDirectional(Material.rock);
		blockStoneDirectionalGearbox.setBlockTextureName("immibis_modjam4:gearbox1");
		blockStoneDirectionalGearbox.setBlockName("immibis_modjam4.stoneDirectionalGearbox");
		GameRegistry.registerBlock(blockStoneDirectionalGearbox, "stoneDirectionalGearbox");
		
		//blockInductionGenerator = new BlockInductionGenerator();
		//GameRegistry.registerBlock(blockInductionGenerator, "inductionGenerator");
		
		//blockCable = new BlockCable();
		//GameRegistry.registerBlock(blockCable, "cable");
		
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
		
		blockFiller = new BlockFiller();
		GameRegistry.registerBlock(blockFiller, "filler");
		
		blockCartBooster = new BlockCartBooster();
		GameRegistry.registerBlock(blockCartBooster, "cartBooster");
		
		itemFlour = new Item();
		itemFlour.setCreativeTab(CreativeTabs.tabFood);
		itemFlour.setTextureName("immibis_modjam4:flour");
		itemFlour.setUnlocalizedName("immibis_modjam4.flour");
		GameRegistry.registerItem(itemFlour, "flour");
		
		itemGear = new Item();
		itemGear.setCreativeTab(CreativeTabs.tabAllSearch);
		itemGear.setTextureName("immibis_modjam4:gear");
		itemGear.setUnlocalizedName("immibis_modjam4.gear");
		GameRegistry.registerItem(itemGear, "gear1");
		
		itemPaddle = new Item();
		itemPaddle.setCreativeTab(CreativeTabs.tabAllSearch);
		itemPaddle.setTextureName("immibis_modjam4:gear");
		itemPaddle.setUnlocalizedName("immibis_modjam4.gear");
		GameRegistry.registerItem(itemPaddle, "paddle");
		
		itemBlade = new Item();
		itemBlade.setCreativeTab(CreativeTabs.tabAllSearch);
		itemBlade.setTextureName("immibis_modjam4:blade");
		itemBlade.setUnlocalizedName("immibis_modjam4.blade");
		GameRegistry.registerItem(itemBlade, "blade");
		
		itemSail = new Item();
		itemSail.setCreativeTab(CreativeTabs.tabAllSearch);
		itemSail.setTextureName("immibis_modjam4:sail");
		itemSail.setUnlocalizedName("immibis_modjam4.sail");
		GameRegistry.registerItem(itemGear, "sail");
		
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
		GameRegistry.registerTileEntity(TileCartBooster.class, "immibisMJ4.booster");
		
		FurnaceRecipes.smelting().func_151396_a(itemFlour, new ItemStack(Items.bread), 0.3f);
		
		GameRegistry.addRecipe(new ItemStack(blockWoodenShaft), "/ /", "/ /", "/ /", '/', Items.stick);
		GameRegistry.addRecipe(new ItemStack(itemGear), " X ", "XIX", " X ", 'X', Blocks.cobblestone, 'I', blockWoodenShaft);
		GameRegistry.addRecipe(new ItemStack(itemSail), "/CC", "/CC", "/CC", '/', Items.stick, 'C', Blocks.wool);
		GameRegistry.addRecipe(new ItemStack(itemPaddle), " W ", "WWW", " / ", '/', Items.stick, 'W', Blocks.planks);
		GameRegistry.addRecipe(new ItemStack(blockStoneDirectionalGearbox), "CGC", "GGG", "CGC", 'G', itemGear, 'C', Blocks.cobblestone);
		GameRegistry.addRecipe(new ItemStack(blockStoneDoubleGearbox), "CCC", "GGG", "CCC", 'G', itemGear, 'C', Blocks.cobblestone);
		GameRegistry.addRecipe(new ItemStack(blockWatermill), "PPP", "PAP", "PPP", 'P', itemPaddle, 'A', blockWoodenShaft);
		GameRegistry.addRecipe(new ItemStack(blockWindmill), " S ", "SAS", " S ", 'S', itemSail, 'A', blockWoodenShaft);
		GameRegistry.addRecipe(new ItemStack(blockMillstone), "SSS", "SAS", "SSS", 'S', blockWoodenShaft, 'S', Blocks.stone);
		GameRegistry.addRecipe(new ItemStack(blockFan), " I ", "IAI", " I ", 'I', Items.iron_ingot, 'A', blockWoodenShaft);
		GameRegistry.addRecipe(new ItemStack(blockSpinnyDeathBlade), "BBB", "BAB", "BBB", 'B', itemBlade, 'A', blockWoodenShaft);
		GameRegistry.addRecipe(new ItemStack(blockCartBooster), "SIS", "AGA", "SSS", 'S', Blocks.cobblestone, 'I', Items.iron_ingot, 'A', blockWoodenShaft);
		
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