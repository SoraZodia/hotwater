package sorazodia.hotwater.registry;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import sorazodia.hotwater.items.ItemModBucket;
import sorazodia.hotwater.main.HotWaterMain;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegistry 
{
	
	//Food
	public static ItemFood boiled_flesh = (ItemFood) new ItemFood(4, 1.0F, true)
		.setUnlocalizedName("boiled_flesh").setTextureName("hot_water:boiledflesh").setCreativeTab(HotWaterMain.hotWaterTab);
	public static ItemFood detoxified_spider_eyes = (ItemFood) new ItemFood(1, 1.0F, true)
		.setUnlocalizedName("detoxified_spider_eyes").setTextureName("hot_water:detoxifiedSpiderEyes").setCreativeTab(HotWaterMain.hotWaterTab);
	public static ItemFood boiled_leather = (ItemFood) new ItemFood(2, 1.0F, true)
		.setUnlocalizedName("boiled_leather").setTextureName("hot_water:boiledLeather").setCreativeTab(HotWaterMain.hotWaterTab);
	
	//Items
	public static Item hot_water_bucket = new ItemBucket(LiquidRegistry.blockHotWater).setTextureName("hot_water:bucketHotWater").setUnlocalizedName("bucket_hot_water").setCreativeTab(HotWaterMain.hotWaterTab); 
	public static Item spring_water_bucket = new ItemBucket(LiquidRegistry.blockSpringWater).setTextureName("hot_water:bucketHotSpringWater").setUnlocalizedName("bucket_spring_water").setCreativeTab(HotWaterMain.hotWaterTab); 
	public static Item superlava_bucket = new ItemModBucket(LiquidRegistry.blockSuperLava, "Ahhh... How did you find this...","I though my toy was hidden...").setTextureName("bucket_lava").setUnlocalizedName("bucket_superlava").setCreativeTab(HotWaterMain.hotWaterTab);
	
	public static void register()
	{
		GameRegistry.registerItem(boiled_flesh, "boiled_flesh", HotWaterMain.MODID);
		GameRegistry.registerItem(boiled_leather, "boiled_leather", HotWaterMain.MODID);
		GameRegistry.registerItem(detoxified_spider_eyes, "detoxified_spider_eyes", HotWaterMain.MODID);
		
		GameRegistry.registerItem(hot_water_bucket, "bucket_hot_water", HotWaterMain.MODID);
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(LiquidRegistry.WATER_NAME, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(hot_water_bucket), new ItemStack(Items.bucket));
		GameRegistry.registerItem(spring_water_bucket, "bucket_spring_water", HotWaterMain.MODID);
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(LiquidRegistry.SPRING_WATER_NAME, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(spring_water_bucket), new ItemStack(Items.bucket));
		GameRegistry.registerItem(ItemRegistry.superlava_bucket, "bucket_superlava", HotWaterMain.MODID);
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(LiquidRegistry.SUPERLAVA_NAME, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(ItemRegistry.superlava_bucket), new ItemStack(Items.bucket));
	
	}

}
