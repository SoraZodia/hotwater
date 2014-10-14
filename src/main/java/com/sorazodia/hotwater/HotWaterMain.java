package com.sorazodia.hotwater;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import buildcraft.energy.BucketHandler;

import com.sorazodia.api.SmeltingRegistry;
import com.sorazodia.hotwater.blocks.BlockBoilingCauldon;
import com.sorazodia.hotwater.blocks.BlockHotWater;
import com.sorazodia.hotwater.blocks.BlockSpringWater;
import com.sorazodia.hotwater.blocks.BlockSuperLava;
import com.sorazodia.hotwater.items.ItemBlock;
import com.sorazodia.hotwater.items.ItemModBucket;
import com.sorazodia.hotwater.registry.ItemRegistry;
import com.sorazodia.hotwater.registry.RegistryFluid;
import com.sorazodia.hotwater.tileEntity.TileEntity_Cauldon;
import com.sorazodia.hotwater.worldGen.BiomeHotSpring;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(name = "Hot Water Mod", modid = HotWaterMain.MODID, version = HotWaterMain.VERSION)
public class HotWaterMain {
	
//MODID info
public static final String MODID = "hot_water";
public static final String VERSION = "1.0.3";

//Mod names
private static final String Water_Name = "hot_water";
private static final String SuperLava_Name = "super_lava";
private static final String SpringWater_Name = "hot_spring_water";
private static final String Cauldon_Name = "cauldron";

//Blocks
public static Block BlockHotWater;
public static Block BlockSpringWater;
public static Block BlockBoilingCauldon;
public static Block BlockSuperLava;

//Items
public static Item hot_water_bucket; 
public static Item cauldon;
public static Item spring_water_bucket; 
public static Item superlava_bucket;

//ItemStacks
private ItemStack blazerod = new ItemStack(Items.blaze_rod);
private ItemStack iron = new ItemStack(Items.iron_ingot);



//DamageSource
public static DamageSource Boiled = new DamageSource("hot_water.boiled");
public static DamageSource Melted = new DamageSource("hot_water.Melted").setFireDamage().setDamageBypassesArmor().setDamageIsAbsolute();

//Biome
public static BiomeGenBase BiomeHotSpring;

//Booleans
public static boolean enableSuperLava;
public boolean enableWIPFeatures;

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event){

		//Configs and variables
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
	    int BiomeID = config.get(Configuration.CATEGORY_GENERAL, "BiomeID For Hot Springs", 35).getInt();
	    enableSuperLava = config.get(Configuration.CATEGORY_GENERAL, "Enable Super Lava Easter Egg", false).getBoolean();			    
	    enableWIPFeatures = config.getBoolean(Configuration.CATEGORY_GENERAL, "Enable WIP Features", false, "This is mainly for testing in a DEV ENVIRONMENT, DO NOT ENABLE");
	    
	    //Fluid Registation
		FMLLog.info("[Hot Water] Registering the New Fluid");
		
		RegistryFluid.register();
		
		FMLLog.info("[Hot Water] New Fluid Registered");
	    
	    //Block variables
	  	FMLLog.info("[Hot Water] Registering the Hot Water, Spring Water, (WIP)Cauldon Block");
	  	BlockSpringWater = new BlockSpringWater(RegistryFluid.springWater, Material.water).setBlockName(SpringWater_Name).setHardness(100F);			
	  	BlockHotWater = new BlockHotWater(RegistryFluid.hotWater, Material.water).setBlockName(Water_Name).setHardness(100F);
	  	BlockBoilingCauldon = new BlockBoilingCauldon().setBlockName("Cauldon");
	  	FMLLog.info("[Hot Water] Hot Water, Spring Water, and (WIP)Cauldon Registered");
	  	
		//Food Registations
		FMLLog.info("[Hot Water] Registering the Foods");
		
		ItemRegistry.registerFood();
	    		
		FMLLog.info("[Hot Water] Registered the Foods");

		//Block Registations
		FMLLog.info("[Hot Water] Registering the mod's blocks");		
		GameRegistry.registerBlock(BlockHotWater, Water_Name);
		GameRegistry.registerBlock(BlockSpringWater, SpringWater_Name);
		GameRegistry.registerBlock(BlockBoilingCauldon, Cauldon_Name);
		GameRegistry.registerTileEntity(TileEntity_Cauldon.class, "hotwater_cauldon");
		FMLLog.info("[Hot Water] Blocks Registered");
		
		//Item Registations
		FMLLog.info("[Hot Water] Registering New Item");
		cauldon = new ItemBlock(BlockBoilingCauldon, "WIP, Not Ready for use").setUnlocalizedName("cauldron").setTextureName("cauldron").setCreativeTab(HotWaterMain.hotWaterTab);
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
	    BlockSuperLava = new BlockSuperLava(RegistryFluid.superlava, Material.lava).setBlockName(SuperLava_Name).setHardness(100F).setLightLevel(10.0F);
	    superlava_bucket = new ItemModBucket(BlockSuperLava, "Ahhh... How did you find this...","I though my toy was hidden...").setTextureName("bucket_lava").setUnlocalizedName("bucket_superlava").setCreativeTab(HotWaterMain.hotWaterTab);	    
	   
	    GameRegistry.registerBlock(BlockSuperLava, SuperLava_Name);     
		GameRegistry.registerItem(superlava_bucket, "bucket_superlava", MODID);
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(SuperLava_Name, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(superlava_bucket), new ItemStack(Items.bucket));
	    
		BucketHandler.INSTANCE.buckets.put(BlockSuperLava, superlava_bucket);
		
	    GameRegistry.addSmelting(Items.lava_bucket, new ItemStack(superlava_bucket), 0.5F); 
	    
	    FMLLog.info("[Hot Water] Super Lava Added");
	    }
	    else{
	    }
	    
	    //Biome Registations
	    FMLLog.info("[Hot Water] Adding new Spring Biome (BECUASE WORLDGEN IS HARD D:)");
	    BiomeHotSpring = new BiomeHotSpring(BiomeID).setEnableSnow().setBiomeName("Hot Springs").setHeight(new Height(0F, 0F)).setDisableRain();
	    BiomeDictionary.registerBiomeType(BiomeHotSpring, Type.WATER);
	    if(BiomeDictionary.isBiomeRegistered(BiomeHotSpring)) FMLLog.info("[Hot Water] Spring Biome Added");
	    else FMLLog.info("[Hot Water] Something went wrong in biome registation, Spring Biome is not Added");
		
	    config.save();
	    
	    //Fuel Registations
		FMLLog.info("[Hot Water] Registering Cooking Recipes and Fuel");
		SmeltingRegistry.addSmelting(Items.water_bucket, hot_water_bucket, 0.3F);		
		GameRegistry.registerFuelHandler(new FuelHandler());
		FMLLog.info("[Hot Water] Cooking Recipes and Fuel Registered");
		FMLLog.info("[Hot Water] Finished");
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event){
		if(enableWIPFeatures){
		FMLLog.info("[Hot Water Dev] Adding in WIP Stuff");
		GameRegistry.addShapedRecipe(new ItemStack(cauldon, 1), "I I","IBI","III", 'I', iron, 'B', blazerod);
		FMLLog.info("[Hot Water Dev] Added");
		}
	}
	
	public static CreativeTabs hotWaterTab = new CreativeTabs("HotWater"){	
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
	        return HotWaterMain.hot_water_bucket;
	    }
	};
	
	public static boolean allowLava(){
		return enableSuperLava;
	}

}
