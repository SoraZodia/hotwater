package sorazodia.hotwater.main;

import static sorazodia.hotwater.main.HotWater.*;

import java.io.IOException;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.apache.logging.log4j.Logger;

import sorazodia.hotwater.config.ConfigHandler;
import sorazodia.hotwater.config.FoodPaser;
import sorazodia.hotwater.registry.BoiledFoodRegistry;
import sorazodia.hotwater.registry.ItemRegistry;
import sorazodia.hotwater.registry.LiquidRegistry;
import sorazodia.hotwater.tab.HotWaterTab;
import sorazodia.hotwater.worldGen.BiomeHotSpring;
import sorazodia.registryhelper.SmeltingRegistry;

@Mod(name = NAME, modid = MODID, version = VERSION, guiFactory = GUI_FACTORY)
public class HotWater
{
	public static final String MODID = "hot_water";
	public static final String NAME = "Hot Water Mod";
	public static final String VERSION = "2.0.0";
	public static final String GUI_FACTORY = "sorazodia.hotwater.config.ConfigGUIFactory";

	@Mod.Instance
	public static HotWater hotWater;
	
	@SidedProxy(clientSide = "sorazodia.hotwater.main.ClientProxy", serverSide = "sorazodia.hotwater.main.ServerProxy")
	public static CommonProxy proxy;

	public static DamageSource Boiled = new DamageSource("hot_water.boiled");
	public static DamageSource Melted = new DamageSource("hot_water.Melted").setFireDamage().setDamageBypassesArmor().setDamageIsAbsolute();

	public static HotWaterTab hotWaterTab = new HotWaterTab();

	private Logger log;
	private static ConfigHandler config;
	private static FoodPaser scanner;

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		log = event.getModLog();

		log.info("Registering Config, Items and Liquid");

		config = new ConfigHandler(event, log);
		scanner = new FoodPaser(event.getModConfigurationDirectory().getAbsolutePath());
		
		LiquidRegistry.register();
		ItemRegistry.register();
		
        log.info("Registering Events, and Biome");
		
		BucketHandler.addBucketMapping(LiquidRegistry.blockHotWater, ItemRegistry.hotWaterBucket);
		BucketHandler.addBucketMapping(LiquidRegistry.blockSpringWater, ItemRegistry.springWaterBucket);
		BucketHandler.addBucketMapping(LiquidRegistry.blockSuperLava, new ItemStack(ItemRegistry.superlavaBucket, 1, 1));
		MinecraftForge.EVENT_BUS.register(new BucketHandler());
		MinecraftForge.EVENT_BUS.register(config);

		GameRegistry.registerFuelHandler(new FuelHandler());

		if (addBiome(new BiomeHotSpring(), 10, BiomeType.ICY, Type.COLD) == false)
			log.error("Biome Registeration Failed");
	}

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		log.info("Registering Recipes");
		
        SmeltingRegistry.addSmelting(Items.WATER_BUCKET, ItemRegistry.hotWaterBucket, 0.3F);
        BoiledFoodRegistry.init();
        
        try
		{
			scanner.parse();
		}
		catch (IOException e)
		{
			log.error("Unable to read file " + "["+ scanner.getLastRead() +"]");
			e.printStackTrace();
		}
        
		if (ConfigHandler.enableSuperLava() == true)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.superlavaBucket, 1, 0), Items.LAVA_BUCKET, Items.NETHER_STAR);
			SmeltingRegistry.addSmelting(ItemRegistry.superlavaBucket, new ItemStack(ItemRegistry.superlavaBucket, 1, 1));
		}
		
		log.info("Loaded");
	}
	
	private boolean addBiome(Biome biome, int weight, BiomeType biomeType, Type type)
	{
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, weight));
		return BiomeDictionary.registerBiomeType(biome, type);
	}
	
	public static FoodPaser getScanner()
	{
		return scanner;
	}

	// Thank you StackOverflow
	public static boolean isInteger(String arg)
	{
		if (arg == null)
			return false;
	
		int length = arg.length();
	
		if (length == 0)
			return false;
	
		int x = 0;
	
		if (arg.charAt(0) == '-')
		{
			if (length == 1)
				return false;
			x = 1;
		}
	
		for (; x < length; x++)
		{
			char c = arg.charAt(x);
			if (c <= '/' || c >= ':')
				return false;
		}
	
		return true;
	}
}
