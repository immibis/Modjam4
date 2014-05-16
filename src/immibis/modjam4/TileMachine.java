package immibis.modjam4;

import immibis.modjam4.shaftnet.ShaftNode;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileMachine extends TileEntity implements IShaft {
	
	public int angle;
	public int angvel;
	public int realAngvel;
	
	public int shaftSide = 0;
	
	public ShaftNode shaftNode;
	
	@Override
	public ShaftNode getShaftNode(int side) {
		return side == shaftSide ? shaftNode : null;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		shaftSide = tag.getInteger("shaftSide");
		shaftNode = new ShaftNode(this, 1 << shaftSide);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("shaftSide", shaftSide);
	}
	
	public void initSide(int shaftSide) {
		this.shaftSide = shaftSide;
		this.shaftNode = new ShaftNode(this, 1 << shaftSide);
	}
	
	/*@Override
	public boolean doesShaftConnect(int side) {
		return side == shaftSide;
	}
	
	@Override
	public int getAngle(int side) {
		return angle;
	}
	
	@Override
	public int getAngVel(int side) {
		return angvel;
	}*/
	
	@Override
	public void updateEntity() {
		int lastAngle = angle;
		
		angle += angvel;
		
		/*IShaft conn = getConnectedShaft();
		if(conn != null) {
			int s_angvel = conn.getAngVel(shaftSide^1);
			int s_angle = conn.getAngle(shaftSide^1);
			
			angle += ShaftUtils.angdiff(s_angle, angle)/2;
		}
		
		angvel = angle - lastAngle;*/
	}
	
	public IShaft getConnectedShaft() {
		/*ForgeDirection fd = ForgeDirection.VALID_DIRECTIONS[shaftSide];
		int x = xCoord+fd.offsetX, y = yCoord+fd.offsetY, z = zCoord+fd.offsetZ;
		if(!worldObj.blockExists(x, y, z))
			return null;
		
		TileEntity te = worldObj.getTileEntity(x, y, z);
		if(!(te instanceof IShaft))
			return null;
		
		IShaft s = (IShaft)te;
		if(!s.doesShaftConnect(shaftSide^1))
			return null;
		
		return s;*/
		return null;
	}
	
	public CableNetwork getConnectedCable() {
		ForgeDirection fd = ForgeDirection.VALID_DIRECTIONS[shaftSide^1];
		int x = xCoord+fd.offsetX, y = yCoord+fd.offsetY, z = zCoord+fd.offsetZ;
		if(!worldObj.blockExists(x, y, z))
			return null;
		
		TileEntity te = worldObj.getTileEntity(x, y, z);
		if(!(te instanceof ICable))
			return null;
		
		return ((ICable)te).getNetwork();
	}
}
