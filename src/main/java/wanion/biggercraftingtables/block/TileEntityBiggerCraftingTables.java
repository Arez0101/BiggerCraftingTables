package wanion.biggercraftingtables.block;

/*
 * Created by WanionCane(https://github.com/WanionCane).
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class TileEntityBiggerCraftingTables extends TileEntity implements ISidedInventory
{
	private final ItemStack[] slots;

	public TileEntityBiggerCraftingTables()
	{
		slots = new ItemStack[getSizeInventory()];
	}

	@Override
	public final SPacketUpdateTileEntity getUpdatePacket()
	{
		final NBTTagCompound nbttagcompound = new NBTTagCompound();
		writeToNBT(nbttagcompound);
		return new SPacketUpdateTileEntity(pos, 3, nbttagcompound);
	}

	@Override
	public final void onDataPacket(final NetworkManager networkManager, final SPacketUpdateTileEntity packet)
	{
		readFromNBT(packet.getNbtCompound());
	}

	@Override
	public final void readFromNBT(final NBTTagCompound nbtTagCompound)
	{
		super.readFromNBT(nbtTagCompound);
		readCustomNBT(nbtTagCompound);
	}

	@Nonnull
	@Override
	public final NBTTagCompound writeToNBT(final NBTTagCompound nbtTagCompound)
	{
		super.writeToNBT(nbtTagCompound);
		return (writeCustomNBT(nbtTagCompound));
	}

	public void readCustomNBT(final NBTTagCompound nbtTagCompound)
	{
		final NBTTagList nbtTagList = nbtTagCompound.getTagList("Contents", 10);
		for (int i = 0; i < nbtTagList.tagCount(); i++) {
			final NBTTagCompound slotCompound = nbtTagList.getCompoundTagAt(i);
			final int slot = slotCompound.getShort("Slot");
			if (slot >= 0 && slot < getSizeInventory())
				setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(slotCompound));
		}
	}

	public NBTTagCompound writeCustomNBT(final NBTTagCompound nbtTagCompound)
	{
		final NBTTagList nbtTagList = new NBTTagList();
		for (int i = 0; i < getSizeInventory() - 1; i++) {
			final ItemStack itemStack = getStackInSlot(i);
			if (itemStack != null) {
				final NBTTagCompound slotCompound = new NBTTagCompound();
				slotCompound.setShort("Slot", (short) i);
				nbtTagList.appendTag(itemStack.writeToNBT(slotCompound));
			}
		}
		nbtTagCompound.setTag("Contents", nbtTagList);
		return nbtTagCompound;
	}

	@Override
	public ItemStack getStackInSlot(final int slot)
	{
		return slots[slot];
	}

	@Override
	public ItemStack decrStackSize(final int slot, final int howMuch)
	{
		final ItemStack slotStack = slots[slot];
		if (slotStack == null)
			return null;
		final ItemStack newStack = slotStack.copy();
		newStack.stackSize = howMuch;
		if ((slotStack.stackSize -= howMuch) == 0)
			slots[slot] = null;
		return newStack;
	}

	@Override
	public void setInventorySlotContents(final int slot, final ItemStack itemStack)
	{
		slots[slot] = itemStack;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(@Nonnull final EntityPlayer entityPlayer)
	{
		return true;
	}

	@Override
	public boolean isItemValidForSlot(final int slot, @Nonnull final ItemStack itemStack)
	{
		return true;
	}

	@Override
	public void clear()
	{
		for (int i = 0; i < slots.length; i++)
			slots[i] = null;
	}

	@Nonnull
	@Override
	public int[] getSlotsForFace(@Nonnull final EnumFacing side)
	{
		return new int[0];
	}

	@Override
	public void openInventory(@Nullable final EntityPlayer player) {}

	@Override
	public void closeInventory(@Nullable final EntityPlayer player) {}

	@Override
	public int getField(int id)
	{
		return 0;
	}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount()
	{
		return 0;
	}

	@Override
	public boolean hasCustomName()
	{
		return false;
	}

	@Nullable
	@Override
	public ItemStack removeStackFromSlot(final int index)
	{
		final ItemStack slotStack = slots[index];
		slots[index] = null;
		return slotStack;
	}

	@Override
	public boolean canInsertItem(final int index, @Nonnull final ItemStack itemStackIn, @Nonnull final EnumFacing direction)
	{
		return false;
	}

	@Override
	public boolean canExtractItem(final int index, @Nonnull final ItemStack stack, @Nonnull final EnumFacing direction)
	{
		return false;
	}
}