package sorazodia.hotwater.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import sorazodia.hotwater.main.HotWater;

public class FoodPaser
{
	private BufferedWriter writer;
	private BufferedReader reader;
	private File folder;
	private String lastRead;
	
	private ArrayList<ItemData> customList = new ArrayList<>();

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
			
			lastRead = config.getName();

			reader = new BufferedReader(new FileReader(config));

			while ((line = reader.readLine()) != null)
			{
				data = line.split(",");
				
				if (line.startsWith("//"))
					continue;
				
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
	
	    for (ItemData data : customList)
	    	writer.write(data.toConfig());
	    
	    writer.close();
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
		
		//Failsafe
		if (item.getItem() == null)
			item = new ItemStack(Blocks.AIR);

		return item;
	}

	public String getLastRead()
	{
		return lastRead;
	}


}
