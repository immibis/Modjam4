package immibis.modjam4;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.util.ForgeDirection;

public class TileShaft extends TileEntity implements IShaft {
	public int angle; // INT_MIN to INT_MAX
	public int angvel; // angle units per tick
	
	@Override
	public void updateEntity() {
		angle += angvel;
		
		int meta = getBlockMetadata();
		
		IShaft conn1 = getConnected(meta);
		IShaft conn2 = getConnected(meta^1);
	
		if(conn1 != null)
			if(conn2 != null)
				updateTwoConnections(conn1, meta^1, conn2, meta);
			else
				updateOneConnection(conn1, meta^1);
		else if(conn2 != null)
			updateOneConnection(conn2, meta);
	}

	IShaft getConnected(int dir) {
		ForgeDirection fd = ForgeDirection.VALID_DIRECTIONS[dir];
		int x = xCoord+fd.offsetX, y = yCoord+fd.offsetY, z = zCoord+fd.offsetZ;
		if(!worldObj.blockExists(x, y, z))
			return null;
		
		TileEntity te = worldObj.getTileEntity(x, y, z);
		if(!(te instanceof IShaft))
			return null;
		
		IShaft s = (IShaft)te;
		if(!s.doesShaftConnect(dir^1))
			return null;
		
		return s;
	}

	private void updateOneConnection(IShaft conn, int dir) {
		
		int s_angvel = conn.getAngVel(dir);
		int s_angle = conn.getAngle(dir);
		
		angle += ShaftUtils.angdiff(s_angle, angle)/2;
		angvel += (s_angvel - angvel) / 8;
	}
	
	private void updateTwoConnections(IShaft conn1, int dir1, IShaft conn2, int dir2) {
		
		int s1_angvel = conn1.getAngVel(dir1);
		int s1_angle = conn1.getAngle(dir1);
		int s2_angvel = conn2.getAngVel(dir2);
		int s2_angle = conn2.getAngle(dir2);
		
		angle = ShaftUtils.bisectAngle(s1_angle, s2_angle);
		angvel = (s1_angvel + s2_angvel)/2;
	}
	
	public void debug(EntityPlayer p) {
		p.addChatMessage(new ChatComponentText("Angvel: "+ShaftUtils.toDegreesPerSecond(angvel));
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
