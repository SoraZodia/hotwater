package sorazodia.hotwater.registry;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import sorazodia.hotwater.items.ItemSuperLavaBucket;
import sorazodia.hotwater.main.HotWater;
import sorazodia.registryhelper.SimpleItemsRegistry;

public class ItemRegistry
{

	//Food
	public static ItemFood boiledFlesh = (ItemFood) new ItemFood(4, 1.0F, true);
	public static ItemFood detoxifiedSpiderEyes = (ItemFood) new ItemFood(1, 1.0F, true);
	public static ItemFood boiledLeather = (ItemFood) new ItemFood(2, 1.0F, true);

	//Items
	public static Item hotWaterBucket = new ItemBucket(LiquidRegistry.blockHotWater).setContainerItem(Items.bucket);
	public static Item springWaterBucket = new ItemBucket(LiquidRegistry.blockSpringWater);
	public static Item superlavaBucket = new ItemSuperLavaBucket(true);

	public static void register()
	{
        SimpleItemsRegistry.init(HotWater.MODID, HotWater.hotWaterTab);
		
        SimpleItemsRegistry.registerItems(boiledFlesh, "boiled_flesh");
        SimpleItemsRegistry.registerItems(boiledLeather, "boiled_leather");
        SimpleItemsRegistry.registerItems(detoxifiedSpiderEyes, "detoxified_spider_eyes");

        SimpleItemsRegistry.registerItems(hotWaterBucket, "bucket_hot_water");
        SimpleItemsRegistry.registerItems(springWaterBucket, "bucket_spring_water");
        SimpleItemsRegistry.registerItems(superlavaBucket, "bucket_superlava");

		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(LiquidRegistry.WATER_NAME, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(hotWaterBucket), new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(LiquidRegistry.SPRING_WATER_NAME, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(springWaterBucket), new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(LiquidRegistry.SUPERLAVA_NAME, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(ItemRegistry.superlavaBucket, 1, 1), new ItemStack(Items.bucket));

	}

}
