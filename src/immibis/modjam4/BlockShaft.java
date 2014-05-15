package immibis.modjam4;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockShaft extends Block {
	public BlockShaft(Material m) {
		super(m);
		
		setCreativeTab(CreativeTabs.tabAllSearch);
	}
}
