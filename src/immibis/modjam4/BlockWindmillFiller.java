package immibis.modjam4;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * The invisible air blocks surrounding a windmill block.
 */
public class BlockWindmillFiller extends Block {

	protected BlockWindmillFiller() {
		super(Material.wood);
		setHardness(BlockWindmill.HARDNESS);
	}

}
