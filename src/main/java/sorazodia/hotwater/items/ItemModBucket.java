package sorazodia.hotwater.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemModBucket extends Item
{

	private String[] lore;
	private boolean burn = false;

	public ItemModBucket(boolean burn, String... lore)
	{
		this.lore = lore;
		this.burn = burn;
		this.maxStackSize = 1;
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack)
	{
		boolean shiny = false;
		
		if (itemStack.getItemDamage() == 1)
			shiny = true;
		
		return shiny;
	}
	
	 /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int solt, boolean isSelected) 
    {
    	if (entity instanceof EntityLivingBase && burn)
    	{
    		float damage = 0;
    		int duration = 0;
    		switch (itemstack.getItemDamage())
    		{
    		case 0:
    			damage = 3.0F;
    			duration = 5;
    			break;
    		case 1:
    			damage = 5.0F;
    			duration = 10;
    			break;
    		}
    		
    		entity.attackEntityFrom(DamageSource.lava, damage);
    		entity.setFire(duration);
    	}
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer player, List par3List, boolean par4)
	{
		if (lore.length > 0)
		{
			for (String str : lore)
			{
				par3List.add(str);
			}
		}
	}
}
