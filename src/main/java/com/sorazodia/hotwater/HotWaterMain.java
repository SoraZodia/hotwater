package com.sorazodia.hotwater;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import buildcraft.energy.BucketHandler;

import com.sorazodia.hotwater.blocks.BlockBoilingCauldon;
import com.sorazodia.hotwater.blocks.BlockHotWater;
import com.sorazodia.hotwater.blocks.BlockSpringWater;
import com.sorazodia.hotwater.blocks.BlockSuperLava;
import com.sorazodia.hotwater.items.ItemBlock;
import com.sorazodia.hotwater.items.ItemModBucket;
import com.sorazodia.hotwater.worldGen.BiomeHotSpring;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(name = "Hot Water Mod", modid = HotWaterMain.MODID, version = HotWaterMain.VERSION)
public class HotWaterMain {
	
public static final String MODID = "hot_water";
public static final String VERSION = "1.0.3";
private static final String Water_Name = "hot_water";
private static final String SuperLava_Name = "super_lava";
private static final String SpringWater_Name = "hot_spring_water";
private static final String Cauldon_Name = "cauldron";

public Fluid hotWater = new Fluid(Water_Name).setDensity(999).setTemperature(373).setViscosity(682);
public static Block BlockHotWater;
public static Block BlockSpringWater;
public static Item hot_water_bucket; 
public static DamageSource Boiled = new DamageSource("hot_water.boiled");

public static Block BlockBoilingCauldon;
public static Item cauldon;

public Fluid superlava = new Fluid(SuperLava_Name).setDensity(999).setTemperature(6150).setViscosity(682);
public static Block BlockSuperLava;
public static Item superlava_bucket; 
public static DamageSource Melted = new DamageSource("hot_water.Melted").setFireDamage();
public static final Material Superlava = new MaterialLiquid(MapColor.tntColor);

public static Fluid springWater = new Fluid(SpringWater_Name).setDensity(999).setTemperature(373).setViscosity(682);

public static Item spring_water_bucket; 

public static BiomeGenBase BiomeHotSpring;


public static ItemFood boiled_flesh;
public static ItemFood detoxified_spider_eyes;
public static ItemFood boiled_leather;

public boolean enableSuperLava;

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event){

		//Configs and variables
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
	    int BiomeID = config.get(Configuration.CATEGORY_GENERAL, "BiomeID", 35).getInt();
	    enableSuperLava = config.get(Configuration.CATEGORY_GENERAL, "enableSuperLava", false).getBoolean(false);			    
	    
	    //Fluid Registation
		FMLLog.info("[Hot Water] Registering the New Fluid");
		FluidRegistry.registerFluid(hotWater);	
		FluidRegistry.registerFluid(springWater);
		FMLLog.info("[Hot Water] New Fluid Registered");
		
		//Block variables
		FMLLog.info("[Hot Water] Registering the Hot Water, Spring Water, (WIP)Cauldon Block");
		BlockSpringWater = new BlockSpringWater(springWater, Material.water).setBlockName(SpringWater_Name).setHardness(100F);			
		BlockHotWater = new BlockHotWater(hotWater, Material.water).setBlockName(Water_Name).setHardness(100F);
		BlockBoilingCauldon = new BlockBoilingCauldon().setBlockTextureName("cauldron").setBlockName("Cauldon");
		FMLLog.info("[Hot Water] Hot Water, Spring Water, and (WIP)Cauldon Registered");
		
		//Food Registations
		FMLLog.info("[Hot Water] Registering the Foods");
		boiled_flesh = (ItemFood) new ItemFood(4, 1.0F, true).setUnlocalizedName("boiled_flesh").setTextureName("rotten_flesh").setCreativeTab(HotWaterMain.hotWaterTab);
	    detoxified_spider_eyes = (ItemFood) new ItemFood(1, 1.0F, true).setUnlocalizedName("detoxified_spider_eyes").setTextureName("spider_eye").setCreativeTab(HotWaterMain.hotWaterTab);
	    boiled_leather = (ItemFood) new ItemFood(2, 1.0F, true).setUnlocalizedName("boiled_leather").setTextureName("leather").setCreativeTab(HotWaterMain.hotWaterTab);
	    
	    GameRegistry.registerItem(boiled_flesh, "boiled_flesh", HotWaterMain.MODID);
	    GameRegistry.registerItem(boiled_leather, "boiled_leather", HotWaterMain.MODID);
		GameRegistry.registerItem(detoxified_spider_eyes, "detoxified_spider_eyes", HotWaterMain.MODID);		
		FMLLog.info("[Hot Water] Registered the Foods");

		//Block Registations
		FMLLog.info("[Hot Water] Registering the mod's blocks");		
		GameRegistry.registerBlock(BlockHotWater, Water_Name);
		GameRegistry.registerBlock(BlockSpringWater, SpringWater_Name);
		GameRegistry.registerBlock(BlockBoilingCauldon, Cauldon_Name);
		FMLLog.info("[Hot Water] Blocks Registered");
		
		//Item Registations
		FMLLog.info("[Hot Water] Registering New Item");
		cauldon = new ItemBlock(BlockBoilingCauldon, "This is WIP, expect derps").setUnlocalizedName("cauldron").setTextureName("cauldron").setCreativeTab(HotWaterMain.hotWaterTab);
		GameRegistry.registerItem(cauldon, "Cauldron", HotWaterMain.MODID);
		FMLLog.info("[Hot Water] New Item Registered");
		
		//Buckets Registations
		FMLLog.info("[Hot Water] Registering the buckets");
		hot_water_bucket = new ItemBucket(BlockHotWater).setTextureName("hot_water:bucket_hot_water").setUnlocalizedName("bucket_hot_water").setCreativeTab(HotWaterMain.hotWaterTab);
		GameRegistry.registerItem(hot_water_bucket, "bucket_hot_water", MODID);
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(Water_Name, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(hot_water_bucket), new ItemStack(Items.bucket));
		
    	spring_water_bucket = new ItemBucket(BlockSpringWater).setTextureName("hot_water:bucket_hot_spring_water").setUnlocalizedName("bucket_spring_water").setCreativeTab(HotWaterMain.hotWaterTab);
		GameRegistry.registerItem(spring_water_bucket, "bucket_spring_water", MODID);
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(SpringWater_Name, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(spring_water_bucket), new ItemStack(Items.bucket));
		FMLLog.info("[Hot Water] Buckets Registered");
		
		//Event Registations
		FMLLog.info("[Hot Water] Registering Buildcraft's BucketFill Event Plus Other Events");
		BucketHandler.INSTANCE.buckets.put(BlockHotWater, hot_water_bucket);	   
	    BucketHandler.INSTANCE.buckets.put(BlockSpringWater, spring_water_bucket);
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
		//MinecraftForge.EVENT_BUS.register(new SpringGen());
	    FMLLog.info("[Hot Water] Buildcraft's BucketFill Event and Other Events Registered");
	    
	    config.load();
	    
	    //Config Stuffs
	    if(enableSuperLava){	    
	    FMLLog.info("[Hot Water] Adding in Super Lava, I hope you know what you're doing >:)");	
	    FluidRegistry.registerFluid(superlava);		
		BlockSuperLava = new BlockSuperLava(superlava, Material.lava).setBlockName(SuperLava_Name).setHardness(100F).setLightLevel(10.0F);
	    GameRegistry.registerBlock(BlockSuperLava, SuperLava_Name);
	    
	    superlava_bucket = new ItemModBucket(BlockSuperLava, "Ahhh... How did you find this...","I though my toy was hidden...").setTextureName("bucket_lava").setUnlocalizedName("bucket_superlava").setCreativeTab(HotWaterMain.hotWaterTab);
		GameRegistry.registerItem(superlava_bucket, "bucket_superlava", MODID);
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(SuperLava_Name, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(superlava_bucket), new ItemStack(Items.bucket));
	    
		BucketHandler.INSTANCE.buckets.put(BlockSuperLava, superlava_bucket);
		
	    GameRegistry.addSmelting(Items.lava_bucket, new ItemStack(superlava_bucket), 0.5F); 
	    FMLLog.info("[Hot Water] Super Lava Added");
	    }else{}
	    
	    //Biome Registations
	    FMLLog.info("[Hot Water] Adding new Spring Biome (BECUASE WORLDGEN IS HARD D:)");
	    BiomeHotSpring = new BiomeHotSpring(BiomeID).setEnableSnow().setBiomeName("Hot Springs").setHeight(new Height(0F, 0F)).setDisableRain();
	    BiomeDictionary.registerBiomeType(BiomeHotSpring, Type.WATER);
	    if(BiomeDictionary.isBiomeRegistered(BiomeHotSpring)) FMLLog.info("[Hot Water] Spring Biome Added");
	    else FMLLog.info("[Hot Water] Something went wrong in biome registation, Spring Biome is not Added");
		
	    config.save();
	    
	    //Fuel Registations
		FMLLog.info("[Hot Water] Registering Cooking Recipes and Fuel");
		GameRegistry.addSmelting(Items.water_bucket, new ItemStack(hot_water_bucket), 0.3F);		
		GameRegistry.registerFuelHandler(new FuelHandler());
		FMLLog.info("[Hot Water] Cooking Recipes and Fuel Registered");
		FMLLog.info("[Hot Water] Finished");
		
	}
	
	public static CreativeTabs hotWaterTab = new CreativeTabs("HotWater"){	
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
	        return HotWaterMain.hot_water_bucket;
	    }
	};

}
