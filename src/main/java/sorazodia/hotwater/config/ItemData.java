package sorazodia.hotwater.config;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemData
{
	private ItemStack input;
	private ItemStack output;
	private boolean ignoreMetadata;
	
	public ItemData(ItemStack input, ItemStack output, boolean ignoreMetadata)
	{
		this.input = input;
		this.output = output;
		this.ignoreMetadata = ignoreMetadata;
	}
	
	public ItemData(Item input, Item output, boolean ignoreMetadata)
	{
		this(new ItemStack(input), new ItemStack(output), ignoreMetadata);
	}
	
	public ItemData(Item input, ItemStack output, boolean ignoreMetadata)
	{
		this(new ItemStack(input), output, ignoreMetadata);
	}
	
	public ItemData(ItemStack input, ItemStack output)
	{
		this(input, output, false);
	}
	
	public ItemData(Item input, Item output)
	{
		this(new ItemStack(input), new ItemStack(output), false);
	}
	
	public ItemData(ItemStack input, Item output)
	{
		this(input, new ItemStack(output), false);
	}

	public ItemStack getInput()
	{
		return input;
	}

	public ItemStack getOutput()
	{
		return output;
	}

	public boolean ignoreMetadata()
	{
		return ignoreMetadata;
	}

	@Override
	public String toString()
	{
		return input.getItem().getUnlocalizedName() + input.getItemDamage() + input.stackTagCompound.toString();
	}

}
