package immibis.modjam4;

import immibis.modjam4.shaftnet.ShaftNetwork;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

public class TileMillstone extends TileShaft implements IInventory, ISidedInventory{
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getAABBPool().getAABB(xCoord-2, yCoord-1, zCoord-2, xCoord+2, yCoord+1, zCoord+2);
	}
	
	private ItemStack processing;
	private int progress;
	
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
		if(!worldObj.isRemote && processing != null) {
			ShaftNetwork net = shaftNode.getNetwork();
			
			System.out.println("xxx"+ShaftUtils.toDegreesPerSecond(net.angvel));
			
			progress++;
			if(progress >= 100) {
				progress = 0;
				processing = null;
				worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord + 0.5, yCoord + 1.5, zCoord + 0.5, new ItemStack(Modjam4Mod.itemFlour)));
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
