package sorazodia.hotwater.main;

import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import sorazodia.hotwater.mechanics.EffectRemover;
import sorazodia.hotwater.registry.ItemRegistry;
import sorazodia.hotwater.registry.LiquidRegistry;
import sorazodia.hotwater.tab.HotWaterTab;
import sorazodia.hotwater.worldGen.BiomeHotSpring;
import sorazodia.registryhelper.SmeltingRegistry;
import buildcraft.energy.BucketHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(name = "Hot Water Mod", modid = HotWaterMain.MODID, version = HotWaterMain.VERSION)
public class HotWaterMain
{

	// MODID info
	public static final String MODID = "hot_water";
	public static final String VERSION = "1.0.3";

	@Mod.Instance
	public static HotWaterMain hotWater;

	// DamageSource
	public static DamageSource Boiled = new DamageSource("hot_water.boiled");
	public static DamageSource Melted = new DamageSource("hot_water.Melted").setFireDamage().setDamageBypassesArmor().setDamageIsAbsolute();

	// Biome
	public static BiomeHotSpring biomeHotSpring;
		
	// Booleans
	public static boolean enableSuperLava;
	
	public static int biomeID;

	public static HotWaterTab hotWaterTab = new HotWaterTab();

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		// Configs and variables
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		biomeID = config.get(Configuration.CATEGORY_GENERAL, "BiomeID For Hot Springs", 35).getInt();
		enableSuperLava = config.get(Configuration.CATEGORY_GENERAL,"Enable Super Lava", false).getBoolean();
        EffectRemover.init();
		
		// Fluid Registation
		FMLLog.info("[Hot Water] Registering Stuff");
		LiquidRegistry.register();

		// Item Registations
		ItemRegistry.register();
		
	}

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		// Event Registations
		FMLLog.info("[Hot Water] Registering Forge Events");
		BucketHandler.INSTANCE.buckets.put(LiquidRegistry.blockHotWater,ItemRegistry.hot_water_bucket);
		BucketHandler.INSTANCE.buckets.put(LiquidRegistry.blockSpringWater,ItemRegistry.spring_water_bucket);
		BucketHandler.INSTANCE.buckets.put(LiquidRegistry.blockSuperLava,ItemRegistry.superlava_bucket);
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);

		// Fuel Registations
		FMLLog.info("[Hot Water] Registering Cooking Recipes and Fuel");
		SmeltingRegistry.addSmelting(Items.water_bucket,ItemRegistry.hot_water_bucket, 0.3F);
		SmeltingRegistry.addSmelting(Items.lava_bucket, ItemRegistry.superlava_bucket, 0.5F);
		GameRegistry.registerFuelHandler(new FuelHandler());
		
		FMLLog.info("[Hot Water] Adding Hot Spring Biome");
		biomeHotSpring = new BiomeHotSpring(biomeID);
		if(addBiome(biomeHotSpring, 10000, BiomeType.ICY, Type.COLD)) 
			FMLLog.info("[Hot Water] Success!");
		else 
			FMLLog.info("[Hot Water] Failed :(");

		FMLLog.info("[Hot Water] Annnnd I'm done, on to you Joe");
	}
	
	private boolean addBiome(BiomeGenBase biome, int weight, BiomeType biomeType,Type type) 
	{
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, weight));
		return BiomeDictionary.registerBiomeType(biomeHotSpring, type);
	}
}
