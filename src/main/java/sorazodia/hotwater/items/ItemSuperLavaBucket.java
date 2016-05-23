package sorazodia.hotwater.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sorazodia.hotwater.registry.LiquidRegistry;

public class ItemSuperLavaBucket extends Item
{
	private boolean burn = false;

	public ItemSuperLavaBucket(boolean burn)
	{
		this.burn = burn;
		this.maxStackSize = 1;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		boolean flag = false;
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world,
				player, flag);

		if (movingobjectposition == null)
		{
			return itemStack;
		} else
		{
			FillBucketEvent event = new FillBucketEvent(player, itemStack, world, movingobjectposition);
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
					return event.result;
				}

				if (!player.inventory.addItemStackToInventory(event.result))
				{
					player.dropPlayerItemWithRandomChoice(event.result, false);
				}

				return itemStack;
			}
			if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
			{
				BlockPos pos = movingobjectposition.getBlockPos();

				if (!world.canMineBlockBody(player, pos))
				{
					return itemStack;
				} else
				{
					switch (movingobjectposition.sideHit)
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

					if (!player.canPlayerEdit(pos, movingobjectposition.sideHit, itemStack))
					{
						return itemStack;
					}

					if (itemStack.getItemDamage() == 1)
					{
						ItemStack bucket = new ItemStack(Items.bucket);
						if (player.capabilities.isCreativeMode)
							bucket = itemStack;

						world.setBlockState(pos, LiquidRegistry.blockSuperLava.getDefaultState());

						return bucket;
					}
				}
			}

			return itemStack;
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack)
	{
		boolean shiny = false;

		if (itemStack.getItemDamage() == 1)
			shiny = true;

		return shiny;
	}

	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps
	 * to check if is on a player hand and update it's contents.
	 */
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int solt, boolean isSelected)
	{
		if (entity instanceof EntityLivingBase && burn)
		{
			float damage = 0;
			int duration = 0;
			switch (itemstack.getItemDamage())
			{
			case 0:
				damage = 3.0F;
				duration = 5;
				break;
			case 1:
				damage = 5.0F;
				duration = 10;
				break;
			}

			entity.attackEntityFrom(DamageSource.lava, damage);
			entity.setFire(duration);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
	{
		if (itemStack.getMetadata() == 1)
			list.add("You shouldn't be playing with this...");
		else
			list.add("Inactive");
	}
}
