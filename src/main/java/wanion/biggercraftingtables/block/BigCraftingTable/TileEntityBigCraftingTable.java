package wanion.biggercraftingtables.block.BigCraftingTable;

/*
 * Created by WanionCane(https://github.com/WanionCane).
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

import wanion.biggercraftingtables.block.TileEntityBiggerCraftingTables;

public final class TileEntityBigCraftingTable extends TileEntityBiggerCraftingTables
{
	@Override
	public int getSizeInventory()
	{
		return 26;
	}

	@Override
	public String getName()
	{
		return "container.BigCraftingTable";
	}
}