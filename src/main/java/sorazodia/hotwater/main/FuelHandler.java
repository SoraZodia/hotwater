package sorazodia.hotwater.main;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import sorazodia.hotwater.registry.ItemRegistry;

public class FuelHandler implements IFuelHandler
{

	@Override
	public int getBurnTime(ItemStack itemstack)
	{
		Item itemObj = itemstack.getItem();
		if (itemObj == ItemRegistry.superlavaBucket && itemstack.getMetadata() == 1)
			return 400000;
		if (itemObj == ItemRegistry.hotWaterBucket)
			return 400;
		return 0;
	}

}
