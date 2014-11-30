package sorazodia.hotwater.worldGen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import sorazodia.hotwater.registry.BlockRegistry;

public class WorldGenLake extends WorldGenerator{
	
	private Block block;
	
	public void generateLakes(World world, Random random, Block block, int x, int y, int z){
		this.block = block;
		generate(world,random, x,y,z);
	}
	
	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		
	while(world.getBlock(x, y, z) == Blocks.air){
		y--;
	}
	if(world.getBlock(x, y, z) != Blocks.air){
		world.setBlockToAir(x, y, z);
		y--;
		for(int c=0;c<32;c++){
			world.setBlock(x, y, z, block);
		}
	}
		return true;
	}

}