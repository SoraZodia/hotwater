package sorazodia.hotwater.items;

import sorazodia.hotwater.entity.projectile.EntitySteamBottle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;

public class ItemWaterBottle extends Item
{
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (!player.capabilities.isCreativeMode)
			stack.stackSize --;
		
		world.spawnEntityInWorld(new EntitySteamBottle(world, Potion.digSpeed.id, 1, 1));
		
		return stack;
	}
}
