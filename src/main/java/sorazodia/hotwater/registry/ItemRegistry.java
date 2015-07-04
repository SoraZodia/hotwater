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
	public static ItemFood boiledFlesh = (ItemFood) new ItemFood(4, 1.0F, true)
		.setUnlocalizedName("boiled_flesh").setTextureName("hot_water:boiledFlesh").setCreativeTab(HotWaterMain.hotWaterTab);
	public static ItemFood detoxifiedSpiderEyes = (ItemFood) new ItemFood(1, 1.0F, true)
		.setUnlocalizedName("detoxified_spider_eyes").setTextureName("hot_water:detoxifiedSpiderEyes").setCreativeTab(HotWaterMain.hotWaterTab);
	public static ItemFood boiledLeather = (ItemFood) new ItemFood(2, 1.0F, true)
		.setUnlocalizedName("boiled_leather").setTextureName("hot_water:boiledLeather").setCreativeTab(HotWaterMain.hotWaterTab);
	
	//Items
	public static Item hotWaterBucket = new ItemBucket(LiquidRegistry.blockHotWater).setTextureName("hot_water:bucketHotWater").setUnlocalizedName("bucket_hot_water").setCreativeTab(HotWaterMain.hotWaterTab); 
	public static Item springWaterBucket = new ItemBucket(LiquidRegistry.blockSpringWater).setTextureName("hot_water:bucketHotSpringWater").setUnlocalizedName("bucket_spring_water").setCreativeTab(HotWaterMain.hotWaterTab); 
	public static Item superlavaBucket = new ItemModBucket(LiquidRegistry.blockSuperLava, "Ahhh... How did you find this...","I though my toy was hidden...").setTextureName("bucket_lava").setUnlocalizedName("bucket_superlava").setCreativeTab(HotWaterMain.hotWaterTab);
	
	public static void register()
	{
		GameRegistry.registerItem(boiledFlesh, "boiled_flesh", HotWaterMain.MODID);
		GameRegistry.registerItem(boiledLeather, "boiled_leather", HotWaterMain.MODID);
		GameRegistry.registerItem(detoxifiedSpiderEyes, "detoxified_spider_eyes", HotWaterMain.MODID);
		
		GameRegistry.registerItem(hotWaterBucket, "bucket_hot_water", HotWaterMain.MODID);
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(LiquidRegistry.WATER_NAME, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(hotWaterBucket), new ItemStack(Items.bucket));
		GameRegistry.registerItem(springWaterBucket, "bucket_spring_water", HotWaterMain.MODID);
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(LiquidRegistry.SPRING_WATER_NAME, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(springWaterBucket), new ItemStack(Items.bucket));
		GameRegistry.registerItem(ItemRegistry.superlavaBucket, "bucket_superlava", HotWaterMain.MODID);
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(LiquidRegistry.SUPERLAVA_NAME, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(ItemRegistry.superlavaBucket), new ItemStack(Items.bucket));
		
	}

}
