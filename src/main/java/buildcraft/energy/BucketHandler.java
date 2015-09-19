package buildcraft.energy;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * Copyright (c) SpaceToad, 2011 http://www.mod-buildcraft.com BuildCraft is
 * distributed under the terms of the Minecraft Mod Public License 1.0, or MMPL.
 * Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 * 
 * @author SpaceToad
 */

public class BucketHandler
{

	//private static BucketHandler INSTANCE = new BucketHandler();
	private static HashMap<Block, ItemStack> buckets = new HashMap<Block, ItemStack>();

	@SubscribeEvent
	public void onBucketFill(FillBucketEvent event)
	{

		ItemStack result = fillCustomBucket(event.world, event.target);

		if (result == null)
			return;

		event.result = result;
		event.setResult(cpw.mods.fml.common.eventhandler.Event.Result.ALLOW);
	}
	
	public static void addToList(Block key, ItemStack value)
	{
		BucketHandler.buckets.put(key, value);
	}
	
	public static void addToList(Block key, Item value)
	{
		BucketHandler.buckets.put(key, new ItemStack(value));
	}

	private ItemStack fillCustomBucket(World world, MovingObjectPosition pos)
	{

		Block block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);

		ItemStack bucket = BucketHandler.buckets.get(block);
		if (bucket != null && world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) == 0)
		{
			world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
			return bucket;
		} else
			return null;

	}
}