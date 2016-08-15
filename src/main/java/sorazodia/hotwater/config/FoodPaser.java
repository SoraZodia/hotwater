package sorazodia.hotwater.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import sorazodia.hotwater.main.HotWater;

public class FoodPaser
{
	BufferedWriter writer;
	BufferedReader reader;
	File folder;

	public FoodPaser(String path)
	{
		folder = new File(path + "\\" + HotWater.MODID);
		
		if (!folder.exists())
			folder.mkdir();
	}

	public void parse() throws IOException
	{
		String[] data;
		String line;
		File[] configs = folder.listFiles();

		if (configs == null)
			return;

		for (File config : configs)
		{

			reader = new BufferedReader(new FileReader(config));

			while ((line = reader.readLine()) != null)
			{
				data = line.split(",");
				
				if (data.length == 3)
					BoilList.register(new ItemData(conventString(data[0]), conventString(data[1]), Boolean.valueOf(data[2].trim())));
				if (data.length == 2)
					BoilList.register(new ItemData(conventString(data[0]), conventString(data[1])));
			}

			reader.close();
		}
	}

	public void write() throws IOException
	{
		writer = new BufferedWriter(new FileWriter(folder.getAbsolutePath() + "\\custom.txt"));
	}

	private ItemStack conventString(String itemName)
	{
		itemName = itemName.trim();
		ItemStack item = new ItemStack(Items.WOODEN_HOE);
		String[] data;

		if (itemName.contains("#"))
		{
			int metadata = 0;

			data = itemName.split("#");

			if (HotWater.isInteger(data[1]))
			{
				metadata = Integer.parseInt(data[1]);
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
