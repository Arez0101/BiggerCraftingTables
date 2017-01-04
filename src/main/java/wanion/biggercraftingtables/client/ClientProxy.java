package wanion.biggercraftingtables.client;

/*
 * Created by WanionCane(https://github.com/WanionCane).
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import wanion.biggercraftingtables.CommonProxy;
import wanion.biggercraftingtables.Reference;
import wanion.biggercraftingtables.block.BlockBiggerCraftingTables;

public final class ClientProxy extends CommonProxy
{
	public void init()
	{
		super.init();
		final Item biggerTablesItem = Item.getItemFromBlock(BlockBiggerCraftingTables.instance);
		if (biggerTablesItem != null)
			ModelBakery.registerItemVariants(biggerTablesItem, new ResourceLocation(Reference.MOD_ID, "big_table"), new ResourceLocation(Reference.MOD_ID, "huge_table"));
		final ItemModelMesher itemModelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		itemModelMesher.register(Item.getItemFromBlock(BlockBiggerCraftingTables.instance), 0, new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, "big_table"), "inventory"));
		itemModelMesher.register(Item.getItemFromBlock(BlockBiggerCraftingTables.instance), 1, new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, "huge_table"), "inventory"));
	}
}