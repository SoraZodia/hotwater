package sorazodia.hotwater.registry;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import sorazodia.hotwater.config.BoilList;
import sorazodia.hotwater.config.ItemData;

public class BoiledFoodRegistry
{
	public static void init()
	{
		BoilList.put(new ItemData(Items.mutton, Items.cooked_mutton));
		BoilList.put(new ItemData(Items.rabbit, Items.cooked_rabbit));
		BoilList.put(new ItemData(Items.chicken, Items.cooked_chicken));
		BoilList.put(new ItemData(Items.porkchop, Items.cooked_porkchop));
		BoilList.put(new ItemData(Items.beef, Items.cooked_beef));
		BoilList.put(new ItemData(Items.potato, Items.baked_potato));
		BoilList.put(new ItemData(Items.poisonous_potato, Items.baked_potato));
		BoilList.put(new ItemData(Items.spider_eye, ItemRegistry.detoxifiedSpiderEyes));
        BoilList.put(new ItemData(Items.leather, ItemRegistry.boiledLeather));
        BoilList.put(new ItemData(Items.rotten_flesh, ItemRegistry.boiledFlesh));
        BoilList.put(new ItemData(new ItemStack(Items.fish, 1, 0), Items.cooked_fish));
        BoilList.put(new ItemData(new ItemStack(Items.fish, 1, 1), new ItemStack(Items.cooked_fish, 1, 1)));
        
        BoilList.put(new ItemData(Items.leather_boots, new ItemStack(ItemRegistry.boiledLeather, 4), true));
        BoilList.put(new ItemData(Items.leather_chestplate, new ItemStack(ItemRegistry.boiledLeather, 8), true));
        BoilList.put(new ItemData(Items.leather_helmet, new ItemStack(ItemRegistry.boiledLeather, 5), true));
        BoilList.put(new ItemData(Items.leather_leggings, new ItemStack(ItemRegistry.boiledLeather, 7), true));
	}
}
