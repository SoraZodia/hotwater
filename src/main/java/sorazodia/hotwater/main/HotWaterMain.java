package sorazodia.hotwater.main;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.MinecraftForge;
import sorazodia.hotwater.config.ConfigHandler;
import sorazodia.hotwater.mechanics.EffectRemover;
import sorazodia.hotwater.registry.BoiledFoodRegistry;
import sorazodia.hotwater.registry.ItemRegistry;
import sorazodia.hotwater.registry.LiquidRegistry;
import sorazodia.hotwater.tab.HotWaterTab;
import sorazodia.hotwater.worldGen.BiomeHotSpring;
import sorazodia.registryhelper.SmeltingRegistry;
import buildcraft.energy.BucketHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(name = "Hot Water Mod", modid = HotWaterMain.MODID, version = HotWaterMain.VERSION)
public class HotWaterMain
{
	public static final String MODID = "hot_water";
	public static final String VERSION = "1.0.5";

	@Mod.Instance
	public static HotWaterMain hotWater;

	public static DamageSource Boiled = new DamageSource("hot_water.boiled");
	public static DamageSource Melted = new DamageSource("hot_water.Melted").setFireDamage().setDamageBypassesArmor().setDamageIsAbsolute();

	public static HotWaterTab hotWaterTab = new HotWaterTab();

	private Logger log;

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		log = event.getModLog();

		log.log(Level.INFO, "Registering Config, Items and Liquid");

		@SuppressWarnings("unused")
		ConfigHandler config = new ConfigHandler(event);

		EffectRemover.init();
		LiquidRegistry.register();
		ItemRegistry.register();
	}

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		log.log(Level.INFO, "Registering Events, Recipes, and Biome");
		BucketHandler.INSTANCE.buckets.put(LiquidRegistry.blockHotWater, ItemRegistry.hotWaterBucket);
		BucketHandler.INSTANCE.buckets.put(LiquidRegistry.blockSpringWater, ItemRegistry.springWaterBucket);
		BucketHandler.INSTANCE.buckets.put(LiquidRegistry.blockSuperLava, ItemRegistry.superlavaBucket);
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);

		BoiledFoodRegistry.init();

		SmeltingRegistry.addSmelting(Items.water_bucket, ItemRegistry.hotWaterBucket, 0.3F);
		if (ConfigHandler.enableSuperLava() == true)
			SmeltingRegistry.addSmelting(Items.lava_bucket, ItemRegistry.superlavaBucket, 0.5F);

		GameRegistry.registerFuelHandler(new FuelHandler());

		if (addBiome(new BiomeHotSpring(ConfigHandler.getBiomeID()), 10, BiomeType.ICY, Type.COLD) == false)
			log.log(Level.ERROR, "Biome Registeration Failed");

		log.log(Level.INFO, "Loaded");
	}

	private boolean addBiome(BiomeGenBase biome, int weight, BiomeType biomeType, Type type)
	{
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, weight));
		return BiomeDictionary.registerBiomeType(biome, type);
	}
}
