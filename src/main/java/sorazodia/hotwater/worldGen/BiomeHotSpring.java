package sorazodia.hotwater.worldGen;

import java.util.Random;

import sorazodia.hotwater.HotWaterMain;
import sorazodia.hotwater.registry.BlockRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;

public class BiomeHotSpring extends BiomeGenBase
{
	
	private WorldGenGeyser lake = new WorldGenGeyser();
	
	public BiomeHotSpring(int id) 
	{
		super(id);
		topBlock = BlockRegistry.blockSpringWater;
		fillerBlock = Blocks.cobblestone;
		temperature = -0.5F;
		setEnableSnow();
		setBiomeName("Hot Springs");
		setHeight(new Height(0F, 0F));
		setDisableRain();
	}
	
	@Override
	public void decorate(World world, Random random, int x, int z)
	{
		int generate = random.nextInt(50);
		if(generate < 25)
			lake.generateLakes(world, random, BlockRegistry.blockHotWater, x, 66, z);
	}
	
}
