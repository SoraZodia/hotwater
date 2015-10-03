package sorazodia.hotwater.main;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.Logger;

import sorazodia.hotwater.config.ConfigHandler;
import sorazodia.hotwater.mechanics.EffectRemover;
import sorazodia.hotwater.registry.BoiledFoodRegistry;
import sorazodia.hotwater.registry.ItemRegistry;
import sorazodia.hotwater.registry.LiquidRegistry;
import sorazodia.hotwater.tab.HotWaterTab;
import sorazodia.hotwater.worldGen.BiomeHotSpring;
import sorazodia.registryhelper.SmeltingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import static sorazodia.hotwater.main.HotWaterMain.*;

@Mod(name = NAME, modid = MODID, version = VERSION, guiFactory = GUI_FACTORY)
public class HotWaterMain
{
	public static final String MODID = "hot_water";
	public static final String NAME = "Hot Water Mod";
	public static final String VERSION = "1.1.0";
	public static final String GUI_FACTORY = "sorazodia.hotwater.config.ConfigGUIFactory";

	@Mod.Instance
	public static HotWaterMain hotWater;

	public static DamageSource Boiled = new DamageSource("hot_water.boiled");
	public static DamageSource Melted = new DamageSource("hot_water.Melted").setFireDamage().setDamageBypassesArmor().setDamageIsAbsolute();

	public static HotWaterTab hotWaterTab = new HotWaterTab();

	private Logger log;
	public static ConfigHandler config;

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		log = event.getModLog();

		log.info("Registering Config, Items and Liquid");

		config = new ConfigHandler(event, log);

		LiquidRegistry.register();
		ItemRegistry.register();
	}

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		log.info("Registering Events, Recipes, and Biome");
		BucketHandler.addBucketMapping(LiquidRegistry.blockHotWater, ItemRegistry.hotWaterBucket);
		BucketHandler.addBucketMapping(LiquidRegistry.blockSpringWater, ItemRegistry.springWaterBucket);
		BucketHandler.addBucketMapping(LiquidRegistry.blockSuperLava, new ItemStack(ItemRegistry.superlavaBucket, 1, 1));
		MinecraftForge.EVENT_BUS.register(new BucketHandler());
		FMLCommonHandler.instance().bus().register(config);

		EffectRemover.init();
		BoiledFoodRegistry.init();

		SmeltingRegistry.addSmelting(Items.water_bucket, ItemRegistry.hotWaterBucket, 0.3F);
		
		if (ConfigHandler.enableSuperLava() == true)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.superlavaBucket, 1, 0), Items.lava_bucket, Items.nether_star);
			SmeltingRegistry.addSmelting(ItemRegistry.superlavaBucket, new ItemStack(ItemRegistry.superlavaBucket, 1, 1));
		}

		GameRegistry.registerFuelHandler(new FuelHandler());

		if (addBiome(new BiomeHotSpring(ConfigHandler.getBiomeID()), 10, BiomeType.ICY, Type.COLD) == false)
			log.error("Biome Registeration Failed");

		log.info("Loaded");
	}
	
	private boolean addBiome(BiomeGenBase biome, int weight, BiomeType biomeType, Type type)
	{
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, weight));
		return BiomeDictionary.registerBiomeType(biome, type);
	}
}
