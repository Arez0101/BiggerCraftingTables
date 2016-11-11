package wanion.biggercraftingtables.block;

/*
 * Created by WanionCane(https://github.com/WanionCane).
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wanion.biggercraftingtables.BiggerCraftingTables;
import wanion.biggercraftingtables.Reference;
import wanion.biggercraftingtables.block.BigCraftingTable.TileEntityBigCraftingTable;
import wanion.biggercraftingtables.block.HugeCraftingTable.TileEntityHugeCraftingTable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class BlockBiggerCraftingTables extends BlockContainer
{
	private final static Random rand = new Random();

	public static final BlockBiggerCraftingTables instance = new BlockBiggerCraftingTables();
	public static final List<String> types = Arrays.asList("Big", "Huge");

	private BlockBiggerCraftingTables()
	{
		super(Material.WOOD);
		setHardness(2.5F).setCreativeTab(BiggerCraftingTables.creativeTabs).setRegistryName(Reference.MOD_ID, "BiggerCraftingTables");
	}

	@Nonnull
	@Override
	public TileEntity createNewTileEntity(@Nonnull final World world, final int metadata)
	{
		return metadata == 0 ? new TileEntityBigCraftingTable() : new TileEntityHugeCraftingTable();
	}

	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(@Nonnull final Item block, final CreativeTabs creativeTabs, final List list)
	{
		for (int i = 0; i < 2; i++)
			list.add(new ItemStack(block, 1, i));
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!worldIn.isRemote) {
			final TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof TileEntityBigCraftingTable)
				FMLNetworkHandler.openGui(playerIn, BiggerCraftingTables.instance, BiggerCraftingTables.GUI_ID_BIG_CRAFTING_TABLE, worldIn, pos.getX(), pos.getY(), pos.getZ());
			else if (tileEntity instanceof TileEntityHugeCraftingTable)
				FMLNetworkHandler.openGui(playerIn, BiggerCraftingTables.instance, BiggerCraftingTables.GUI_ID_HUGE_CRAFTING_TABLE, worldIn, pos.getX(), pos.getY(), pos.getZ());
			else
				return false;
		}
		return true;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return getMetaFromState(state);
	}

	public void breakBlock(final World world, @Nonnull final BlockPos pos, @Nonnull final IBlockState state)
	{
		final TileEntityBiggerCraftingTables tileEntityBiggerCraftingTables = (TileEntityBiggerCraftingTables) world.getTileEntity(pos);
		if (tileEntityBiggerCraftingTables != null) {
			final ItemStack droppedStack = new ItemStack(state.getBlock(), 1, getMetaFromState(state));
			droppedStack.setTagCompound(tileEntityBiggerCraftingTables.writeCustomNBT(new NBTTagCompound()));
			world.spawnEntityInWorld(new EntityItem(world, pos.getX() + rand.nextFloat() * 0.8F + 0.1F, pos.getY() + rand.nextFloat() * 0.8F + 0.1F, pos.getZ() + rand.nextFloat() * 0.8F + 0.1F, droppedStack));

			world.notifyNeighborsOfStateChange(pos, state.getBlock());
		}
		world.removeTileEntity(pos);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return null;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		final TileEntityBiggerCraftingTables tileEntityBiggerCraftingTables = (TileEntityBiggerCraftingTables) world.getTileEntity(pos);
		if (tileEntityBiggerCraftingTables != null)
			if (stack.getTagCompound() != null)
				tileEntityBiggerCraftingTables.readCustomNBT(stack.getTagCompound());
	}
}