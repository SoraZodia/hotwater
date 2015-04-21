package sorazodia.hotwater.worldGen;

import sorazodia.hotwater.registry.LiquidRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeHotSpring extends BiomeGenBase
{
	
	public BiomeHotSpring(int id) 
	{
		super(id);
		topBlock = LiquidRegistry.blockSpringWater;
		fillerBlock = Blocks.cobblestone;
		temperature = -0.5F;
		setEnableSnow();
		setBiomeName("Hot Springs");
		setHeight(new Height(0F, 0F));
		setDisableRain();
	}

}
