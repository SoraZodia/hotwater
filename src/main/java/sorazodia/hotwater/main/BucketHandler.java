package sorazodia.hotwater.main;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BucketHandler
{
	private static HashMap<Block, ItemStack> map = new HashMap<Block, ItemStack>();
	
	@SubscribeEvent
	public void useBucketEvent(FillBucketEvent onFill)
	{
		World world = onFill.world;
		MovingObjectPosition blockPosition = onFill.target;
		Block targetBlock = world.getBlock(blockPosition.blockX, blockPosition.blockY, blockPosition.blockZ);
		
		if (map.containsKey(targetBlock) && world.getBlockMetadata(blockPosition.blockX, blockPosition.blockY, blockPosition.blockZ) == 0)
		{
			onFill.result = map.get(targetBlock);
			world.setBlockToAir(blockPosition.blockX, blockPosition.blockY, blockPosition.blockZ);
			onFill.setResult(Event.Result.ALLOW);
		}
	}
	
	public static void addBucketMapping(Block fluid, ItemStack bucket)
	{
		if (!map.containsKey(fluid))
			map.put(fluid, bucket);
	}
	
	public static void addBucketMapping(Block fluid, Item bucket)
	{
		addBucketMapping(fluid, new ItemStack(bucket));
	}

}
