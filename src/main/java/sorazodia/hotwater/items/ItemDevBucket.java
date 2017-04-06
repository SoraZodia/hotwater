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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDevBucket extends Item
{
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		
		if (player.world.isRemote)
			return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
		
		BoilList.reset();
		BoiledFoodRegistry.init();
		
        try
		{
        	player.sendMessage(new TextComponentTranslation("hotwater.parse.read"));
			HotWater.getScanner().parse();
			player.sendMessage(new TextComponentTranslation("hotwater.parse.read.success"));
		}
		catch (IOException e)
		{
			player.sendMessage(new TextComponentTranslation("hotwater.parse.read.fail", HotWater.getScanner().getLastRead()));
			e.printStackTrace();
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
	
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack)
	{
		return true;
	}
}
