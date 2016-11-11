package wanion.biggercraftingtables;

/*
 * Created by WanionCane(https://github.com/WanionCane).
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wanion.biggercraftingtables.block.BigCraftingTable.TileEntityBigCraftingTable;
import wanion.biggercraftingtables.block.BlockBiggerCraftingTables;
import wanion.biggercraftingtables.block.HugeCraftingTable.TileEntityHugeCraftingTable;
import wanion.biggercraftingtables.block.ItemBlockBiggerCraftingTables;
import wanion.biggercraftingtables.core.GuiHandler;
import wanion.biggercraftingtables.minetweaker.Tweaker;

import static wanion.biggercraftingtables.Reference.MOD_ID;

public class CommonProxy
{
	public final void preInit()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(BiggerCraftingTables.instance, GuiHandler.instance);
		GameRegistry.register(BlockBiggerCraftingTables.instance);
		GameRegistry.register(ItemBlockBiggerCraftingTables.instance);
		GameRegistry.registerTileEntity(TileEntityBigCraftingTable.class, MOD_ID + ":BigTable");
		GameRegistry.registerTileEntity(TileEntityHugeCraftingTable.class, MOD_ID + ":HugeTable");
	}

	public final void postInit()
	{
		if (Loader.isModLoaded("MineTweaker3"))
			Tweaker.init();
	}
}