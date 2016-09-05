package sorazodia.hotwater.items;

import java.io.IOException;

import sorazodia.hotwater.config.BoilList;
import sorazodia.hotwater.main.HotWater;
import sorazodia.hotwater.registry.BoiledFoodRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemDevBucket extends Item
{
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
	{
		BoilList.reset();
		BoiledFoodRegistry.init();
		
        try
		{
        	player.addChatMessage(new TextComponentTranslation("hotwater.parse.read"));
			HotWater.getScanner().parse();
			player.addChatMessage(new TextComponentTranslation("hotwater.parse.read.success"));
		}
		catch (IOException e)
		{
			player.addChatMessage(new TextComponentTranslation("hotwater.parse.read.fail", HotWater.getScanner().getLastRead()));
			e.printStackTrace();
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
}
