package sorazodia.hotwater.config;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BoilList
{
	
	private static ArrayList<ItemData> boilList = new ArrayList<ItemData>();

	public static void register(ItemData data)
	{
		boilList.add(data);
	}

	public static void reset()
	{
		boilList.clear();
	}
	
	public static boolean ignoreMetaData(int index)
	{
		return boilList.get(index).ignoreMetadata();
	}

	public static ItemStack getInput(int index)
	{
		return boilList.get(index).getInput();
	}

	public static ItemStack getOutput(int index)
	{
		return boilList.get(index).getOutput();
	}

	public static boolean containInput(ItemStack itemstack)
	{
		for (ItemData data : boilList)
		{
			ItemStack storedItemStack = data.getInput();
			Item inputItem = itemstack.getItem();
			Item storedItem = storedItemStack.getItem();

			int storedMetadata = storedItemStack.getItemDamage();
			int inputMetadata = storedItemStack.getItemDamage();

			if (data.ignoreMetadata())
			{
				if (inputItem == storedItem)
					return true;
			} else if (inputItem == storedItem && inputMetadata == storedMetadata)
			{
				return true;
			}
		}

		return false;
	}

	public static int size()
	{
		return boilList.size();
	}

}
