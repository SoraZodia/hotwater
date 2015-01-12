package sorazodia.hotwater.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemReed;
import net.minecraft.item.ItemStack;

public class ItemBlock extends ItemReed
{

	private String lore;
	private boolean hasLore;


	public ItemBlock(Block block) 
	{
		super(block);
		hasLore = false;
	}

	public ItemBlock(Block block, String lore) 
	{
		super(block);		
		this.lore = lore;
		hasLore = true;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer player, List par3List, boolean par4) 
	{
		if(hasLore) par3List.add(lore);
	}

}
