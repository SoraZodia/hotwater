package sorazodia.hotwater.registry;

import sorazodia.hotwater.HotWaterMain;
import sorazodia.hotwater.items.ItemBlock;
import sorazodia.hotwater.items.ItemModBucket;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegistry {
	
	//Food
	public static ItemFood boiled_flesh = (ItemFood) new ItemFood(4, 1.0F, true)
		.setUnlocalizedName("boiled_flesh").setTextureName("rotten_flesh").setCreativeTab(HotWaterMain.hotWaterTab);
	public static ItemFood detoxified_spider_eyes = (ItemFood) new ItemFood(1, 1.0F, true)
		.setUnlocalizedName("detoxified_spider_eyes").setTextureName("spider_eye").setCreativeTab(HotWaterMain.hotWaterTab);
	public static ItemFood boiled_leather = (ItemFood) new ItemFood(2, 1.0F, true)
		.setUnlocalizedName("boiled_leather").setTextureName("leather").setCreativeTab(HotWaterMain.hotWaterTab);
	
	//Items
	public static Item hot_water_bucket = new ItemBucket(BlockRegistry.BlockHotWater).setTextureName("hot_water:bucket_hot_water").setUnlocalizedName("bucket_hot_water").setCreativeTab(HotWaterMain.hotWaterTab); 
	public static Item spring_water_bucket = new ItemBucket(BlockRegistry.BlockSpringWater).setTextureName("hot_water:bucket_hot_spring_water").setUnlocalizedName("bucket_spring_water").setCreativeTab(HotWaterMain.hotWaterTab); 
	public static Item superlava_bucket;
	
	public static void register(){
		GameRegistry.registerItem(boiled_flesh, "boiled_flesh", HotWaterMain.MODID);
		GameRegistry.registerItem(boiled_leather, "boiled_leather", HotWaterMain.MODID);
		GameRegistry.registerItem(detoxified_spider_eyes, "detoxified_spider_eyes", HotWaterMain.MODID);
		
		GameRegistry.registerItem(hot_water_bucket, "bucket_hot_water", HotWaterMain.MODID);
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(BlockRegistry.Water_Name, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(hot_water_bucket), new ItemStack(Items.bucket));
		GameRegistry.registerItem(spring_water_bucket, "bucket_spring_water", HotWaterMain.MODID);
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(BlockRegistry.SpringWater_Name, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(spring_water_bucket), new ItemStack(Items.bucket));
	}
	
	public static void registerEgg(){
		superlava_bucket = new ItemModBucket(BlockRegistry.BlockSuperLava, "Ahhh... How did you find this...","I though my toy was hidden...").setTextureName("bucket_lava").setUnlocalizedName("bucket_superlava").setCreativeTab(HotWaterMain.hotWaterTab);
        
		GameRegistry.registerItem(ItemRegistry.superlava_bucket, "bucket_superlava", HotWaterMain.MODID);
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(BlockRegistry.SuperLava_Name, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(ItemRegistry.superlava_bucket), new ItemStack(Items.bucket));
	}

}
