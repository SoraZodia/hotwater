package com.sorazodia.hotwater.worldGen;

import java.util.Random;

import com.sorazodia.hotwater.HotWaterMain;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BiomeDecorateEvent {

	WorldGenRocks rocks = new WorldGenRocks();
	Random rand = new Random();
	
	@SubscribeEvent(receiveCanceled = true, priority = EventPriority.NORMAL)
	public void decorate(DecorateBiomeEvent.Pre event){
		BiomeGenBase biome = event.world.getBiomeGenForCoords(event.chunkX, event.chunkZ);
		if(biome.biomeName.equals("Hot Springs")){
			int x = event.chunkX + rand.nextInt(16);
			int y = rand.nextInt(100);
			int z = event.chunkZ + rand.nextInt(16);
			rocks.generate(event.world, rand, x, y, z);
		}
	}
}
