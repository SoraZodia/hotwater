package sorazodia.hotwater.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import sorazodia.hotwater.main.HotWaterMain;
import cpw.mods.fml.client.config.GuiConfig;

public class ConfigGUI extends GuiConfig
{
	private static final String TITLE = "Hot Water Config";

	public ConfigGUI(GuiScreen parent)
	{
		super (parent, new ConfigElement<ConfigGUI>(ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), HotWaterMain.MODID, false, false, TITLE);
	}
}
