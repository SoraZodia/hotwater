package com.sorazodia.hotwater.worldGen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenRocks extends WorldGenerator{

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		int yLimit = y;
	
	if(y > yLimit){
		for(int c=0;c<16;c++){
			x -= random.nextInt(16);
			y -=2;
			z -= random.nextInt(16);
			world.setBlock(x, y, z, Blocks.cobblestone);
			for(int e = 0; e < 5 ; e++){
				x++;
				y--;
				z++;
				world.setBlock(x, y, z, Blocks.cobblestone);
			}
		}
	}
		return true;
	}

	
}