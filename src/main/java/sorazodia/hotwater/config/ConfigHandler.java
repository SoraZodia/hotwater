package sorazodia.hotwater.config;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler
{
	private Configuration config;

	private static boolean enableSuperLava = false;
	private static int biomeID = 50;

	public ConfigHandler(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());
		syncConfig();
	}

	public void syncConfig()
	{
		biomeID = config.getInt("BiomeID For Hot Springs", Configuration.CATEGORY_GENERAL, 50, 40, 128, "The ID for the Hot Springs Biome");
		enableSuperLava = config.getBoolean("Enable Super Lava", Configuration.CATEGORY_GENERAL, false, "If you want crazy lava in your world");
		if (config.hasChanged())
			config.save();
	}

	public static boolean enableSuperLava()
	{
		return enableSuperLava;
	}

	public static int getBiomeID()
	{
		return biomeID;
	}
	
}
