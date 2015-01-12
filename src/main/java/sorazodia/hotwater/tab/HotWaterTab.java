package sorazodia.hotwater.tab;

import sorazodia.hotwater.registry.ItemRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class HotWaterTab extends CreativeTabs
{

	public HotWaterTab() 
	{
		super("HotWater");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() 
	{
        return ItemRegistry.hot_water_bucket;
    }

}
