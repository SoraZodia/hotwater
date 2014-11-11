package com.sorazodia.hotwater;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import buildcraft.energy.BucketHandler;

import com.sorazodia.api.SmeltingRegistry;
import com.sorazodia.hotwater.registry.BlockRegistry;
import com.sorazodia.hotwater.registry.ItemRegistry;
import com.sorazodia.hotwater.registry.RegistryFluid;
import com.sorazodia.hotwater.tab.HotWaterTab;
import com.sorazodia.hotwater.worldGen.BiomeDecorateEvent;
import com.sorazodia.hotwater.worldGen.BiomeHotSpring;
import com.sorazodia.hotwater.worldGen.WorldGenRocks;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(name = "Hot Water Mod", modid = HotWaterMain.MODID, version = HotWaterMain.VERSION)
public class HotWaterMain {
	
//MODID info
public static final String MODID = "hot_water";
public static final String VERSION = "1.0.3";

@Mod.Instance
public static HotWaterMain hotWater;

//ItemStacks
private ItemStack blazerod = new ItemStack(Items.blaze_rod);
private ItemStack iron = new ItemStack(Items.iron_ingot);

//DamageSource
public static DamageSource Boiled = new DamageSource("hot_water.boiled");
public static DamageSource Melted = new DamageSource("hot_water.Melted").setFireDamage().setDamageBypassesArmor().setDamageIsAbsolute();

//Biome
public static BiomeGenBase biomeHotSpring;

//Booleans
private static boolean enableSuperLava;
private boolean enableWIPFeatures = true; //Saying this now, I will forget to disable this someday.

public static HotWaterTab hotWaterTab = new HotWaterTab();

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event){

		//Configs and variables
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
	    int BiomeID = config.get(Configuration.CATEGORY_GENERAL, "BiomeID For Hot Springs", 35).getInt();
	    enableSuperLava = config.get(Configuration.CATEGORY_GENERAL, "Enable Super Lava Easter Egg", false).getBoolean();
	    
	    //Fluid Registation
		FMLLog.info("[Hot Water] Registering Fluid, Blocks, and Items");
		RegistryFluid.register();
	    
	    //Block variables
	  	BlockRegistry.register();
	  	
		//Item Registations
		ItemRegistry.register();
	    
	    config.load();
	    
	    //Config Stuffs
	    if(enableSuperLava){	 
	    FMLLog.info("[Hot Water Extra] Adding in Super Lava, I hope you know what you're doing >:)");
	    RegistryFluid.registerEgg();
	    BlockRegistry.registerEgg();
	    ItemRegistry.registerEgg();
	    FMLLog.info("[Hot Water Extra] Super Lava Added");
	    }
	    
	    //Biome Registations
	    FMLLog.info("[Hot Water] Adding new Spring Biome");
	    biomeHotSpring = new BiomeHotSpring(BiomeID).setEnableSnow().setBiomeName("Hot Springs").setHeight(new Height(0F, 0F)).setDisableRain();
	    BiomeManager.icyBiomes.add(new BiomeEntry(biomeHotSpring, 5));
	    BiomeDictionary.registerBiomeType(biomeHotSpring, Type.COLD);
	    if(BiomeDictionary.isBiomeRegistered(biomeHotSpring)) FMLLog.info("[Hot Water] Spring Biome Added");
	    else FMLLog.info("[Hot Water] Something went wrong in biome registation, Spring Biome is not Added");
		
	    config.save();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event){

		//Event Registations
		FMLLog.info("[Hot Water] Registering Forge Events");
		BucketHandler.INSTANCE.buckets.put(BlockRegistry.BlockHotWater, ItemRegistry.hot_water_bucket);	   
		BucketHandler.INSTANCE.buckets.put(BlockRegistry.BlockSpringWater, ItemRegistry.spring_water_bucket);
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);

		//Fuel Registations
		FMLLog.info("[Hot Water] Registering Cooking Recipes and Fuel");
		SmeltingRegistry.addSmelting(Items.water_bucket, ItemRegistry.hot_water_bucket, 0.3F);		
		GameRegistry.registerFuelHandler(new FuelHandler());
		if(enableSuperLava){
			GameRegistry.addSmelting(Items.lava_bucket, new ItemStack(ItemRegistry.superlava_bucket), 0.5F);
			BucketHandler.INSTANCE.buckets.put(BlockRegistry.BlockSuperLava, ItemRegistry.superlava_bucket);
		}
			
		if(enableWIPFeatures){
		FMLLog.info("[Hot Water Dev] Adding in WIP Stuff, if you're not sorazodia... Tell him to slap himself");
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.cauldon, 1), "I I","IBI","III", 'I', iron, 'B', blazerod);
		}
		
		FMLLog.info("[Hot Water] Annnnd I'm done, on to you Joe");
	}
}
