package immibis.modjam4;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Modjam4Mod.MODID, version = "0.0")
public class Modjam4Mod
{
	public static final String MODID = "immibis_modjam4";
	
	@Instance(MODID) public static Modjam4Mod INSTANCE;
	
	public static BlockShaft blockWoodenShaft; 
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		blockWoodenShaft = new BlockShaft(Material.wood);
		blockWoodenShaft.setBlockName("immibis_modjam4.woodenShaft");
		
		GameRegistry.registerBlock(blockWoodenShaft, "woodenShaft");
    }
}
