package sorazodia.hotwater.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import sorazodia.hotwater.main.HotWater;

public class ConfigGUI extends GuiConfig
{
	private static final String TITLE = "Hot Water Config";

	public ConfigGUI(GuiScreen parent)
	{
		super (parent, new ConfigElement(ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), HotWater.MODID, false, false, TITLE);
	}
}
