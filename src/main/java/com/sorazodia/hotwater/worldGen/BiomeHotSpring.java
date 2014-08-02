package com.sorazodia.hotwater.worldGen;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import com.sorazodia.hotwater.HotWaterMain;
import com.sorazodia.hotwater.blocks.BlockSpringWater;

public class BiomeHotSpring extends BiomeGenBase{

	public BiomeHotSpring(int id) {
		super(id);
		topBlock = HotWaterMain.BlockSpringWater;
		fillerBlock = Blocks.cobblestone;
		temperature = -0.5F;
	}

}
