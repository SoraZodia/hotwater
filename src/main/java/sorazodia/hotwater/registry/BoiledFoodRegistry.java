package sorazodia.hotwater.registry;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import sorazodia.hotwater.config.BoilList;
import sorazodia.hotwater.config.ItemData;

public class BoiledFoodRegistry
{
	public static void init()
	{
		BoilList.put(new ItemData(Items.MUTTON, Items.COOKED_MUTTON));
		BoilList.put(new ItemData(Items.RABBIT, Items.COOKED_RABBIT));
		BoilList.put(new ItemData(Items.CHICKEN, Items.COOKED_CHICKEN));
		BoilList.put(new ItemData(Items.PORKCHOP, Items.COOKED_PORKCHOP));
		BoilList.put(new ItemData(Items.BEEF, Items.COOKED_BEEF));
		BoilList.put(new ItemData(Items.POTATO, Items.BAKED_POTATO));
		BoilList.put(new ItemData(Items.POISONOUS_POTATO, Items.BAKED_POTATO));
		BoilList.put(new ItemData(Items.SPIDER_EYE, ItemRegistry.detoxifiedSpiderEyes));
        BoilList.put(new ItemData(Items.LEATHER, ItemRegistry.boiledLeather));
        BoilList.put(new ItemData(Items.ROTTEN_FLESH, ItemRegistry.boiledFlesh));
        BoilList.put(new ItemData(new ItemStack(Items.FISH, 1, 0), Items.COOKED_FISH));
        BoilList.put(new ItemData(new ItemStack(Items.FISH, 1, 1), new ItemStack(Items.COOKED_FISH, 1, 1)));
        
        BoilList.put(new ItemData(Items.LEATHER_BOOTS, new ItemStack(ItemRegistry.boiledLeather, 4), true));
        BoilList.put(new ItemData(Items.LEATHER_CHESTPLATE, new ItemStack(ItemRegistry.boiledLeather, 8), true));
        BoilList.put(new ItemData(Items.LEATHER_HELMET, new ItemStack(ItemRegistry.boiledLeather, 5), true));
        BoilList.put(new ItemData(Items.LEATHER_LEGGINGS, new ItemStack(ItemRegistry.boiledLeather, 7), true));
	}
}
