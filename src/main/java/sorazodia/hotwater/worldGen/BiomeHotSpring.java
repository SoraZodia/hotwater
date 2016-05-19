package sorazodia.hotwater.worldGen;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import sorazodia.hotwater.registry.LiquidRegistry;

public class BiomeHotSpring extends BiomeGenBase
{
	
	public BiomeHotSpring(int id) 
	{
		super(id);
		topBlock = LiquidRegistry.blockSpringWater.getDefaultState();
		fillerBlock = Blocks.cobblestone.getDefaultState();
		temperature = -0.5F;
		setEnableSnow();
		setBiomeName("Hot Springs");
		setHeight(new Height(0F, 0F));
		setDisableRain();
	}

}
