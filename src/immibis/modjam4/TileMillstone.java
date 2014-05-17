package immibis.modjam4;

import immibis.modjam4.shaftnet.ShaftNetwork;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileMillstone extends TileShaft implements IInventory, ISidedInventory{
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getAABBPool().getAABB(xCoord-2, yCoord-1, zCoord-2, xCoord+2, yCoord+1, zCoord+2);
	}
	
	private ItemStack processing;
	private int progress;
	private boolean isProcessing;
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		isProcessing = pkt.func_148853_f() != 0;
	}
	
	@Override
	public Packet getDescriptionPacket() {
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, processing != null ? 1 : 0, null);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		if(tag.hasKey("processing"))
			processing = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("processing"));
		progress = tag.getInteger("progress");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		if(processing != null) {
			tag.setTag("processing", new NBTTagCompound());
			processing.writeToNBT(tag.getCompoundTag("processing"));
		}
		tag.setInteger("progress", progress);
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		shaftNode.tick();
		if(worldObj.isRemote ? isProcessing : processing != null) {
			ShaftNetwork net = shaftNode.getNetwork();
			
			// ~20 seconds at 38 degrees/second
			// = one tick at 38*20*20=15200 degrees/second
			// 15200 degrees/second = 10000 progress units/tick but this overflows
			// 3000 degrees/second = 2000 progress units/tick
			// 1 progress unit/tick = 1.5 degrees/second
			int scale = ShaftUtils.fromDegreesPerSecond(3000) / 2000;
			int progressPerTick = 1000;//Math.abs(net.angvel / scale);
			
			net.angvel -= (net.angvel < 0 ? -1 : 1) * progressPerTick * ShaftUtils.fromDegreesPerSecond(1);
			
			if(!worldObj.isRemote) {
				progress += progressPerTick;
				if(progress >= 10000) {
					progress = 0;
					processing = null;
					worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord + 0.5, yCoord + 1.5, zCoord + 0.5, new ItemStack(Modjam4Mod.itemFlour)));
				}
			}
		}
	}
	
	
	
	@Override
	public boolean onBlockActivated(EntityPlayer pl) {
		if(processing != null)
			return false;
		if(!canProcess(pl.inventory.getCurrentItem()))
			return false;
		if(!worldObj.isRemote) {
			processing = pl.inventory.getCurrentItem().splitStack(1);
			if(pl.inventory.getCurrentItem().stackSize == 0)
				pl.inventory.mainInventory[pl.inventory.currentItem] = null;
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
		return true;
	}
	
	
	private static boolean canProcess(ItemStack stack) {
		return stack != null && stack.getItem() == Items.wheat;
	}
	
	

	private int[] slots = {0};
	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return slots;
	}

	@Override
	public boolean canInsertItem(int var1, ItemStack var2, int var3) {
		return processing == null && canProcess(var2);
	}

	@Override
	public boolean canExtractItem(int var1, ItemStack var2, int var3) {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return null;
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		processing = var2;
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public String getInventoryName() {
		return "millstone";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return false;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return canProcess(var2);
	}
	
	
	
}
