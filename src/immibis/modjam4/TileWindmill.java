package immibis.modjam4;

import immibis.modjam4.shaftnet.ShaftNode;
import immibis.modjam4.shaftnet.SpeedTorqueCurve;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

/**
 * Windmill is affected by non-air blocks up to 9 blocks in front of the windmill centre block, 4 blocks down, 4 blocks left and right,
 * and 4 blocks up. If a column cannot see the sky, all blocks in that column also count as non-air.
 * 
 * This is a 9x9x9 area. 729 blocks total. 9 blocks are checked each tick, so it refreshes every 81 ticks (4.05 seconds)
 */
public class TileWindmill extends TileShaft implements SpeedTorqueCurve {
	
	private int maxSpeed = 1;
	private int maxTorque;
	private int obstructingBlocks = 729;
	private int obstructingBlocksAcc;
	private int nextX, nextZ;
	
	private int clientWindSpeed;
	
	// power = torque * speed
	// torque = max torque * (1 - speed / max speed)
	// power = max torque * speed * (1 - speed / max speed)
	// power = max torque * speed - max torque * speed * speed / max_speed
	// scale so max torque = max speed = 1
	// power = speed - speed * speed -> max power at speed = 0.5
	// actual max power at speed = max speed / 2
	// max power = max torque * (max speed / 2) * (1 - (max speed / 2) / max spee)
	// max power = max torque * (max speed / 2) * (1/2)
	// max power = 1/4 * max torque * max speed
	
	// note: the water wheel CONSUMES power if speed > max speed
	
	@Override
	public long getTorqueAtSpeed(long speed) {
		if(speed >= Integer.MAX_VALUE || speed <= Integer.MIN_VALUE)
			return -speed;
		if(maxTorque == 0)
			return 0;
		return maxTorque - maxTorque * speed / (maxTorque < 0 ? -maxSpeed : maxSpeed);
	}
	
	@Override
	protected ShaftNode createShaftNode() {
		return new ShaftNode(this) {
			@Override
			public SpeedTorqueCurve getSpeedTorqueCurve() {
				return TileWindmill.this;
			}
		};
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		int meta = getBlockMetadata();
		
		checkObstructions();
		
		
		{
			int NORMAL_TORQUE = ShaftUtils.fromDegreesPerSecond(60) / 10;
			int NORMAL_SPEED = ShaftUtils.fromDegreesPerSecond(45);
			
			int windSpeed = worldObj.isRemote ? clientWindSpeed : Modjam4Mod.windSpeed;
			
			double obstructionMultiplier = 1 - obstructingBlocks / 729.0;
			double windSpeedMultiplier = 0;
			
			maxTorque = NORMAL_TORQUE;
			maxTorque *= obstructionMultiplier;
			
			maxSpeed = NORMAL_SPEED;
			maxSpeed *= obstructionMultiplier;
		}
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("wind", Modjam4Mod.windSpeed);
		tag.setLong("angvel", shaftNode.getNetwork().angvel);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		clientWindSpeed = pkt.func_148857_g().getInteger("wind");
		shaftNode.getNetwork().forceAngVel(pkt.func_148857_g().getLong("angvel"));
	}
	
	private void checkObstructions() {
		
		if(++nextX == 9) {
			nextX = 0;
			if(++nextZ == 9) {
				nextZ = 0;
				obstructingBlocks = obstructingBlocksAcc;
				obstructingBlocksAcc = 0;
			}
		}
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getAABBPool().getAABB(xCoord-3, yCoord-3, zCoord-3, xCoord+3, yCoord+3, zCoord+3);
	}
}
