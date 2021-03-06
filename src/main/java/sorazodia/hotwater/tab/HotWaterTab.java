package sorazodia.hotwater.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sorazodia.hotwater.registry.ItemRegistry;

public class HotWaterTab extends CreativeTabs
{

	public HotWaterTab() 
	{
		super("HotWater");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem() 
	{
        return new ItemStack(ItemRegistry.hotWaterBucket);
    }

}
