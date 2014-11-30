package sorazodia.hotwater.worldGen;

import java.util.Random;

import sorazodia.hotwater.registry.BlockRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeHotSpring extends BiomeGenBase{
	
	WorldGenLake lake = new WorldGenLake();
	
	public BiomeHotSpring(int id) {
		super(id);
		topBlock = Blocks.cobblestone;
		fillerBlock = Blocks.cobblestone;
		temperature = -0.5F;
	}
	
	@Override
	public void decorate(World world, Random random, int x, int z)
    {
    lake.generateLakes(world, random, BlockRegistry.BlockSpringWater, x, 70, z);
    }
}
