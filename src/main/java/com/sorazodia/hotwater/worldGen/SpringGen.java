package com.sorazodia.hotwater.worldGen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.event.terraingen.TerrainGen;

import com.sorazodia.hotwater.HotWaterMain;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SpringGen {

	Random rand = new Random();
	int height = rand.nextInt(70);
	int type = rand.nextInt(100);
	@SubscribeEvent
	public void createWaterGen(TerrainGen event){	
		
		WorldGenLakes hotSprings = new WorldGenLakes(getWaterType());
	}
	
	private Block getWaterType(){
		if(type >=10) return HotWaterMain.BlockHotWater;
		if(type < 10)return HotWaterMain.BlockSpringWater;
	    else return HotWaterMain.BlockHotWater;	
	}
	
}
