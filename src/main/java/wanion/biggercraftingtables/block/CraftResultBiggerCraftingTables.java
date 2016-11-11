package wanion.biggercraftingtables.block;

/*
 * Created by WanionCane(https://github.com/WanionCane).
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class CraftResultBiggerCraftingTables implements IInventory
{
	private final TileEntityBiggerCraftingTables tileEntityBiggerCraftingTables;
	private final int slot;

	public CraftResultBiggerCraftingTables(@Nonnull final TileEntityBiggerCraftingTables tileEntityBiggerCraftingTables, final int slot)
	{
		this.tileEntityBiggerCraftingTables = tileEntityBiggerCraftingTables;
		this.slot = slot;
	}

	@Override
	public int getSizeInventory()
	{
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(final int slot)
	{
		return tileEntityBiggerCraftingTables.getStackInSlot(this.slot);
	}

	@Override
	public ItemStack decrStackSize(final int slot, int howMuch)
	{
		return tileEntityBiggerCraftingTables.decrStackSize(this.slot, howMuch);
	}

	@Nullable
	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(final int slot, final ItemStack itemStack)
	{
		tileEntityBiggerCraftingTables.setInventorySlotContents(this.slot, itemStack);
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public void markDirty()
	{
		tileEntityBiggerCraftingTables.markDirty();
	}

	@Override
	public boolean isUseableByPlayer(@Nonnull final EntityPlayer entityPlayer)
	{
		return tileEntityBiggerCraftingTables.isUseableByPlayer(entityPlayer);
	}

	@Override
	public void openInventory(@Nonnull final EntityPlayer player) {}

	@Override
	public void closeInventory(@Nonnull final EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(final int slot, @Nonnull final ItemStack itemStack)
	{
		return tileEntityBiggerCraftingTables.isItemValidForSlot(slot, itemStack);
	}

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
	public void clear()
	{

	}

	@Override
	public String getName()
	{
		return null;
	}

	@Override
	public boolean hasCustomName()
	{
		return false;
	}

	@Override
	public ITextComponent getDisplayName()
	{
		return null;
	}
}