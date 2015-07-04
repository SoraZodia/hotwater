package sorazodia.hotwater.config;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;


public class BoilList
{
	private static ArrayList<ItemData> boilList = new ArrayList<ItemData>();
	
	public static void put(ItemData data)
	{
		boilList.add(data);
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
	
	public static int size()
	{
		return boilList.size();
	}

}
