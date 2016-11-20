package wanion.biggercraftingtables.block;

/*
 * Created by WanionCane(https://github.com/WanionCane).
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import wanion.biggercraftingtables.Reference;

import javax.annotation.Nonnull;

public final class ItemBlockBiggerCraftingTables extends ItemBlock
{
	public static final ItemBlockBiggerCraftingTables instance = new ItemBlockBiggerCraftingTables();

	private ItemBlockBiggerCraftingTables()
	{
		super(BlockBiggerCraftingTables.instance);
		setRegistryName(Reference.MOD_ID, "biggercraftingtables");
		setHasSubtypes(true);
	}

	@Nonnull
	@Override
	public String getUnlocalizedName(final ItemStack itemStack)
	{
		return "tile." + Reference.MOD_ID + "." + BlockBiggerCraftingTables.EnumType.byMetadata(getDamage(itemStack)).getUnlocalizedName();
	}

	public int getMetadata(final int damage)
	{
		return damage;
	}
}