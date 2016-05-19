package sorazodia.hotwater.registry;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sorazodia.hotwater.items.ItemSuperLavaBucket;
import sorazodia.hotwater.main.HotWater;

public class ItemRegistry
{

	//Food
	public static ItemFood boiledFlesh = (ItemFood) new ItemFood(4, 1.0F, true).setUnlocalizedName("boiled_flesh").setCreativeTab(HotWater.hotWaterTab);
	public static ItemFood detoxifiedSpiderEyes = (ItemFood) new ItemFood(1, 1.0F, true).setUnlocalizedName("detoxified_spider_eyes").setCreativeTab(HotWater.hotWaterTab);
	public static ItemFood boiledLeather = (ItemFood) new ItemFood(2, 1.0F, true).setUnlocalizedName("boiled_leather").setCreativeTab(HotWater.hotWaterTab);

	//Items
	public static Item hotWaterBucket = new ItemBucket(LiquidRegistry.blockHotWater).setUnlocalizedName(
			"bucket_hot_water").setCreativeTab(HotWater.hotWaterTab).setContainerItem(Items.bucket);
	public static Item springWaterBucket = new ItemBucket(LiquidRegistry.blockSpringWater).setUnlocalizedName(
			"bucket_spring_water").setCreativeTab(HotWater.hotWaterTab);
	public static Item superlavaBucket = new ItemSuperLavaBucket(true).setUnlocalizedName("bucket_superlava").setCreativeTab(
			HotWater.hotWaterTab);

	public static void register()
	{

		GameRegistry.registerItem(boiledFlesh, "boiled_flesh");
		GameRegistry.registerItem(boiledLeather, "boiled_leather");
		GameRegistry.registerItem(detoxifiedSpiderEyes, "detoxified_spider_eyes");

		GameRegistry.registerItem(hotWaterBucket, "bucket_hot_water");
		GameRegistry.registerItem(springWaterBucket, "bucket_spring_water");
		GameRegistry.registerItem(superlavaBucket, "bucket_superlava");

		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(LiquidRegistry.WATER_NAME, FluidContainerRegistry.BUCKET_VOLUME),
				new ItemStack(hotWaterBucket), new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(
				FluidRegistry.getFluidStack(LiquidRegistry.SPRING_WATER_NAME, FluidContainerRegistry.BUCKET_VOLUME),
				new ItemStack(springWaterBucket), new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(
				FluidRegistry.getFluidStack(LiquidRegistry.SUPERLAVA_NAME, FluidContainerRegistry.BUCKET_VOLUME),
				new ItemStack(ItemRegistry.superlavaBucket, 1, 1), new ItemStack(Items.bucket));

	}

}
