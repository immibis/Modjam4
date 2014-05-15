package immibis.modjam4;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.util.ForgeDirection;

public class TileGearboxDirectional extends TileEntity implements IShaft {
	public int angle; // INT_MIN to INT_MAX
	public int angvel; // angle units per tick
	
	long lastUpdate = -1;
	
	private IShaft[] conn = new IShaft[6];
	private int[] angleOffset = new int[6];
	private int[] angles = new int[6];
	
	@Override
	public void updateEntity() {
		lastUpdate = worldObj.getTotalWorldTime();
		angle += angvel;
		
		int nConn = 0;
		int totAngVel = 0;
		for(int dir = 0; dir < 6; dir++) {
			IShaft prev = conn[dir];
			IShaft now = getConnected(dir);
			if(prev != now) {
				conn[dir] = now;
				if(now != null)
					angleOffset[dir] = ShaftUtils.angdiff(now.getAngle(dir^1), angle);
			}
			if(now != null) {
				angles[nConn] = now.getAngle(dir^1) - angleOffset[dir];
				totAngVel += now.getAngVel(dir^1);
				
				nConn++;
			}
		}
		
		if(nConn == 0)
			return;
		
		angle = ShaftUtils.averageAngle(angles, nConn);
		angvel = totAngVel / nConn;
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
		if(!worldObj.isRemote)
			return;
		
		p.addChatMessage(new ChatComponentText("Angvel: "+ShaftUtils.toDegreesPerSecond(angvel)));
		
		int meta = getBlockMetadata(); 
		IShaft c1 = getConnected(meta);
		IShaft c2 = getConnected(meta^1);
	
		if(c1 != null) {
			p.addChatMessage(new ChatComponentText("End 1 angvel: "+ShaftUtils.toDegreesPerSecond(c1.getAngVel(meta^1))));
			p.addChatMessage(new ChatComponentText("End 1 slip: "+ShaftUtils.toDegrees(angle - c1.getAngle(meta^1))));
		}
		
		if(c2 != null) {
			p.addChatMessage(new ChatComponentText("End 2 angvel: "+ShaftUtils.toDegreesPerSecond(c2.getAngVel(meta))));
			p.addChatMessage(new ChatComponentText("End 2 slip: "+ShaftUtils.toDegrees(angle - c2.getAngle(meta))));
		}
	}

	@Override
	public int getAngle(int side) {
		if(lastUpdate != worldObj.getTotalWorldTime())
			return angle + angvel + angleOffset[side];
		return angle + angleOffset[side];
	}
	
	@Override
	public int getAngVel(int side) {
		return angvel;
	}
	
	@Override
	public boolean doesShaftConnect(int side) {
		return true;
	}
}
