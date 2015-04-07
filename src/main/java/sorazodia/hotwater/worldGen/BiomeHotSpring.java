package sorazodia.hotwater.worldGen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import sorazodia.hotwater.registry.LiquidRegistry;

public class BiomeHotSpring extends BiomeGenBase
{
	
	private WorldGenGeyser lake;
	
	public BiomeHotSpring(int id) 
	{
		super(id);
		topBlock = Blocks.stone;
		fillerBlock = Blocks.cobblestone;
		temperature = -0.5F;
		setEnableSnow();
		setBiomeName("Hot Springs");
		setHeight(new Height(0F, 0F));
		setDisableRain();
		lake = new WorldGenGeyser(LiquidRegistry.blockSpringWater);
	}
	
	@Override
	public void decorate(World world, Random random, int x, int z)
	{
		if (random.nextInt(100) < 50)
			lake.generate(world, random, x, world.getHeightValue(x, z), z);
	}

}
