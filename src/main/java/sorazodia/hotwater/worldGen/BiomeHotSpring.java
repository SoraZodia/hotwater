package sorazodia.hotwater.worldGen;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import sorazodia.hotwater.registry.LiquidRegistry;

public class BiomeHotSpring extends Biome
{
	public BiomeHotSpring() 
	{
		super(new BiomeProperties("Hot Springs").setRainDisabled().setSnowEnabled().setBaseHeight(0));
		topBlock = LiquidRegistry.blockSpringWater.getDefaultState();
		fillerBlock = Blocks.COBBLESTONE.getDefaultState();
	}

}
