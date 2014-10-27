package com.sorazodia.hotwater.worldGen;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import com.sorazodia.hotwater.registry.BlockRegistry;

public class BiomeHotSpring extends BiomeGenBase{

	public BiomeHotSpring(int id) {
		super(id);
		topBlock = BlockRegistry.BlockSpringWater;
		fillerBlock = Blocks.cobblestone;
		temperature = -0.5F;
	}

}
