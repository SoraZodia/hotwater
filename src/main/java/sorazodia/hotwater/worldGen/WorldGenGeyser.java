package sorazodia.hotwater.worldGen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGeyser extends WorldGenerator
{

	private Block lake;

	public WorldGenGeyser(Block lakeBlock)
	{
		this.lake = lakeBlock;		
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		Block air = Blocks.air;
		if (world.getBlock(x, y, z) == air)
			y--;
		
//		if (world.getBlock(x, y, z) != Blocks.snow || world.getBlock(x, y, z) != Blocks.stone || world.getBlock(x, y, z) != Blocks.cobblestone)
//			return false;
		
		for (int w = 0; w < 4; w++)
		{
			world.setBlock(x+w, y, z, air);
			world.setBlock(x-w, y, z, air);
					
			world.setBlock(x+w, y - 1, z, lake);
			world.setBlock(x-w, y - 1, z, lake);
			
			world.setBlock(x, y, z+w, air);
			world.setBlock(x, y, z-w, air);
			
			world.setBlock(x, y - 1, z+w, lake);
			world.setBlock(x, y - 1, z-w, lake);
		}
		
			
		return true;
	}

}