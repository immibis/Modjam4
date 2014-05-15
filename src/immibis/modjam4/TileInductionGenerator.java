package immibis.modjam4;

import net.minecraft.tileentity.TileEntity;

public class TileInductionGenerator extends TileOneShaftMachine implements IShaft {
	public TileInductionGenerator(int shaftSide) {
		super(shaftSide);
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		angvel /= 3;
	}
	
}
