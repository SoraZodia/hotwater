package sorazodia.hotwater.worldGen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import sorazodia.hotwater.registry.BlockRegistry;

public class WorldGenGeyser extends WorldGenerator
{

	private Block block;

	public void generateLakes(World world, Random random, Block block, int x, int y, int z)
	{
		this.block = block;
		generate(world,random, x,y,z);
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) 
	{
		int randomY = (int) (60 + (Math.random()*((y-60) + 1)));
		for(int c = 0;c < 32;c++)
		{
			for(int v = 0; v < 30; v++)
			{
			world.setBlock(x, randomY, z, block);
			y--;
			}
		}
		return true;
	}

}