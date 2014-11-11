package com.sorazodia.hotwater.worldGen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import com.sorazodia.hotwater.registry.BlockRegistry;

public class BiomeHotSpring extends BiomeGenBase{
	
	WorldGenRocks rocks = new WorldGenRocks();
	
	public BiomeHotSpring(int id) {
		super(id);
		topBlock = BlockRegistry.BlockSpringWater;
		fillerBlock = Blocks.cobblestone;
		temperature = -0.5F;
	}
	
	public void decorate(World world, Random random, int x, int z)
    {
    rocks.generate(world, random, x, 70, z);
    }
}
