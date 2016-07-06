package sorazodia.hotwater.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		boolean flag = false;
		RayTraceResult mouseLocation = this.rayTrace(world, player, flag);

		if (mouseLocation == null)
		{
			return itemStack;
		}
		else
		{
			FillBucketEvent event = new FillBucketEvent(player, itemStack, world, mouseLocation);
			if (MinecraftForge.EVENT_BUS.post(event))
			{
				return itemStack;
			}

			if (event.getResult() == Event.Result.ALLOW)
			{
				if (player.capabilities.isCreativeMode)
				{
					return itemStack;
				}

				if (--itemStack.stackSize <= 0)
				{
					return event.getEmptyBucket();
				}

				if (!player.inventory.addItemStackToInventory(event.getFilledBucket()))
				{
					player.dropItem(event.getFilledBucket(), false);
				}

				return itemStack;
			}
			if (mouseLocation.typeOfHit == RayTraceResult.Type.BLOCK)
			{
				BlockPos pos = mouseLocation.getBlockPos();

				if (!world.canMineBlockBody(player, pos))
				{
					return itemStack;
				}
				else
				{
					switch (mouseLocation.sideHit)
					{
					case UP:
						pos.down();
						break;
					case DOWN:
						pos.up();
						break;
					case EAST:
						pos.west();
						break;
					case WEST:
						pos.east();
						break;
					case NORTH:
						pos.south();
						break;
					case SOUTH:
						pos.north();
						break;
					}

					if (!player.canPlayerEdit(pos, mouseLocation.sideHit, itemStack))
					{
						return itemStack;
					}

					if (itemStack.getItemDamage() == 1)
					{
						ItemStack bucket = new ItemStack(Items.BUCKET);
						if (player.capabilities.isCreativeMode)
							bucket = itemStack;

						world.setBlockState(pos, this.fluid.getDefaultState());

						return bucket;
					}
				}
			}

			return itemStack;
		}
	}

}
