package sorazodia.hotwater.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;

public class ItemModBucket extends ItemBucket
{

	private String[] lore = {"hi"};

	public ItemModBucket(Block block) 
	{
		super(block);
	}

	public ItemModBucket(Block block, String... lore) 
	{
		super(block);
		this.lore = lore;
	}


	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer player, List par3List, boolean par4) 
	{
		if(lore.length > 0)
		{
			for(String str: lore)
			{
				par3List.add(str);
			}
		}
	}
}
