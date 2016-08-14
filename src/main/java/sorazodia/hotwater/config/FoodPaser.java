package sorazodia.hotwater.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import sorazodia.hotwater.main.HotWater;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FoodPaser
{
	BufferedWriter writer;
	BufferedReader reader;
	boolean ignoreMetadata = true;
	
	public FoodPaser(String path) throws IOException
	{
		reader = new BufferedReader(new FileReader(path));
		writer = new BufferedWriter(new FileWriter(path));
	}
	
	public void parse() throws IOException
	{
		String[] data;
		String line;
		
		while ((line = reader.readLine()) != null)
		{
			data = line.split(";");
			BoilList.register(new ItemData(conventString(data[0]), conventString(data[1]), ignoreMetadata));
		    ignoreMetadata = true;
		}
		
	}
	
	private ItemStack conventString(String itemName)
	{
		ItemStack item = new ItemStack(Items.WOODEN_HOE);
		String[] data;
		
		if (itemName.contains("#"))
		{
			int metadata = 0;
	
			data = itemName.split("#");
			
			if (HotWater.isInteger(data[1]))
			{
				metadata = Integer.parseInt(data[1]);
				ignoreMetadata = false;
			}
			
			item = new ItemStack(Item.getByNameOrId(data[0]), 1, metadata);
		}
		else
		{
			item = new ItemStack(Item.getByNameOrId(itemName));
		}
		
		return item;
	}
	
}
