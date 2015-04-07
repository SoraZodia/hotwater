package sorazodia.hotwater.main;

import sorazodia.hotwater.registry.ItemRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler
{

	@Override
	public int getBurnTime(ItemStack itemstack)
	{
		Item itemObj = itemstack.getItem();
		if (itemObj.equals(ItemRegistry.superlava_bucket))
			return 40000;
		if (itemObj.equals(ItemRegistry.hot_water_bucket))
			return 400;
		return 0;
	}

}
