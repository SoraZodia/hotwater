package sorazodia.hotwater.main;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BucketHandler
{
	private static HashMap<Block, ItemStack> map = new HashMap<Block, ItemStack>();
	
	@SubscribeEvent
	public void useBucketEvent(FillBucketEvent onFill)
	{
		World world = onFill.world;
		MovingObjectPosition blockPosition = onFill.target;
		Block targetBlock = world.getBlockState(blockPosition.getBlockPos()).getBlock();
		
		if (map.containsKey(targetBlock) && world.getBlockState(blockPosition.getBlockPos()).equals(targetBlock.getDefaultState()))
		{
			onFill.result = map.get(targetBlock).copy();
			world.setBlockToAir(blockPosition.getBlockPos());
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
