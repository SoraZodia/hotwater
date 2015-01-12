package sorazodia.hotwater.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;

public class ItemModBucket extends ItemBucket
{

	private String lore;
	private String lore2;
	private boolean hasLore;
	private boolean hasSecordLore;

	public ItemModBucket(Block block) 
	{
		super(block);
		hasLore = false;
		hasSecordLore = false;
	}

	public ItemModBucket(Block block, String lore) 
	{
		super(block);
		this.lore = lore;
		hasLore = true;
		hasSecordLore = false;
	}

	public ItemModBucket(Block block, String lore, String lore2) 
	{
		super(block);
		this.lore = lore;
		this.lore2 = lore2;
		hasLore = true;
		hasSecordLore = true;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer player, List par3List, boolean par4) 
	{
		if(hasLore)
		{
			par3List.add(lore);
			if(hasSecordLore)
			{
				par3List.add(lore2);
			}
		}
	}
}
