package sorazodia.hotwater.main;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import sorazodia.hotwater.items.ItemModBucket;

public class BucketHandler
{
	private static HashMap<Block, ItemStack> map = new HashMap<Block, ItemStack>();
	
	@SubscribeEvent
	public void useBucketEvent(FillBucketEvent onFill)
	{
		if (onFill.getTarget() == null)
			return;
		
		World world = onFill.getWorld();
		RayTraceResult blockPosition = onFill.getTarget();
		BlockPos pos = blockPosition.getBlockPos();
		Block targetBlock = world.getBlockState(pos).getBlock();
		
		if (map.containsKey(targetBlock) && world.getBlockState(pos).equals(targetBlock.getDefaultState()))
		{
			ItemStack stack = map.get(targetBlock).copy();
			ItemModBucket filledBucket = (ItemModBucket) stack.getItem();
					
			onFill.setFilledBucket(stack);
			world.setBlockToAir(blockPosition.getBlockPos());
			world.playSound(null, blockPosition.getBlockPos().add(0.5, 0.5, 0.5), filledBucket.getFilledSound(), SoundCategory.BLOCKS, 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
			onFill.setResult(Event.Result.ALLOW);
		}
	}
	
	public static boolean addBucketMapping(Block fluid, ItemStack bucket)
	{
		if (!map.containsKey(fluid) && bucket.getItem() instanceof ItemModBucket)
		{
			map.put(fluid, bucket);
			
			return true;
		}
		
		return false;
	}
	
	public static boolean addBucketMapping(Block fluid, Item bucket)
	{
		return addBucketMapping(fluid, new ItemStack(bucket));
	}

}
