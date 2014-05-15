package immibis.modjam4;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileShaft extends TileEntity implements IShaft {
	public int angle; // INT_MIN to INT_MAX
	public int angvel; // angle units per tick
	
	@Override
	public void updateEntity() {
		angle += angvel;
		
		int meta = getBlockMetadata();
		updateFromDirection(meta);
		updateFromDirection(meta^1);
	}

	private void updateFromDirection(int dir) {
		ForgeDirection fd = ForgeDirection.VALID_DIRECTIONS[dir];
		int x = xCoord+fd.offsetX, y = yCoord+fd.offsetY, z = zCoord+fd.offsetZ;
		if(!worldObj.blockExists(x, y, z)) return;
		TileEntity te = worldObj.getTileEntity(x, y, z);
		if(!(te instanceof IShaft)) return;
		
		IShaft s = (IShaft)te;
		if(!s.doesShaftConnect(dir^1)) return;
		
		int s_angvel = s.getAngVel(dir^1);
		int s_angle = s.getAngle(dir^1);
		
		if(s_angvel >= angvel) {
			angle = (angle + s_angle)/2;
		}
	}

	@Override
	public int getAngle(int side) {
		return angle;
	}
	
	@Override
	public int getAngVel(int side) {
		return angvel;
	}
	
	@Override
	public boolean doesShaftConnect(int side) {
		return (side & 6) == getBlockMetadata();
	}
}
