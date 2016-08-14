package sorazodia.hotwater.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.capability.ItemFluidContainer;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ItemModBucket extends ItemFluidContainer
{
	private final Block fluid;

	public ItemModBucket(Block fluid)
	{
		super(1000);
		this.fluid = fluid;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand)
	{
		boolean flag = false;
		RayTraceResult mouseLocation = this.rayTrace(world, player, flag);

		if (mouseLocation == null)
		{
			return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStack);
		}
		else
		{
			FillBucketEvent event = new FillBucketEvent(player, itemStack, world, mouseLocation);
			if (MinecraftForge.EVENT_BUS.post(event))
			{
				return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStack);
			}

			if (event.getResult() == Event.Result.ALLOW)
			{
				if (player.capabilities.isCreativeMode)
				{
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
				}

				if (--itemStack.stackSize <= 0)
				{
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, event.getEmptyBucket());
				}

				if (!player.inventory.addItemStackToInventory(event.getFilledBucket()))
				{
					player.dropItem(event.getFilledBucket(), false);
				}

				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
			}
			if (mouseLocation.typeOfHit == RayTraceResult.Type.BLOCK)
			{
				BlockPos pos = mouseLocation.getBlockPos();
				pos = pos.offset(mouseLocation.sideHit);

				if (!world.canMineBlockBody(player, pos))
				{
					return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStack);
				}
				else
				{
					if (!player.canPlayerEdit(pos, mouseLocation.sideHit, itemStack))
					{
						return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStack);
					}
					
					world.playSound(null, pos.add(0.5, 0.5, 0.5), this.getPourSound(), SoundCategory.BLOCKS, 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
					
					return pour(pos, player, world, itemStack);

				}
			}

			return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStack);
		}
	}
	
	public SoundEvent getFilledSound()
	{
		return SoundEvents.ITEM_BUCKET_FILL;
	}
	
	protected SoundEvent getPourSound()
	{
		return SoundEvents.ITEM_BUCKET_EMPTY;
	}

	protected ActionResult<ItemStack> pour(BlockPos pos, EntityPlayer player, World world, ItemStack filledBucket)
	{
		ItemStack bucket = new ItemStack(Items.BUCKET);
		if (player.capabilities.isCreativeMode)
			bucket = filledBucket;

		world.setBlockState(pos, this.fluid.getDefaultState());

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, bucket);
	}

}
