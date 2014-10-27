package com.sorazodia.hotwater.registry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.sorazodia.hotwater.blocks.BlockBoilingCauldon;
import com.sorazodia.hotwater.blocks.BlockHotWater;
import com.sorazodia.hotwater.blocks.BlockSpringWater;
import com.sorazodia.hotwater.blocks.BlockSuperLava;
import com.sorazodia.hotwater.tileEntity.TileEntity_Cauldon;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockRegistry {
	
	//Mod names
	public static final String Water_Name = "hot_water";
	public static final String SuperLava_Name = "super_lava";
	public static final String SpringWater_Name = "hot_spring_water";
	public static final String Cauldon_Name = "cauldron";
	
	//Blocks
	public static Block BlockHotWater = new BlockHotWater(RegistryFluid.hotWater, Material.water).setBlockName(Water_Name).setHardness(100F);
	public static Block BlockSpringWater = new BlockSpringWater(RegistryFluid.springWater, Material.water).setBlockName(SpringWater_Name).setHardness(100F);
	public static Block BlockBoilingCauldon = new BlockBoilingCauldon().setBlockName("Cauldon");;
	public static Block BlockSuperLava;
				
	public static void register(){
		GameRegistry.registerBlock(BlockHotWater, Water_Name);
		GameRegistry.registerBlock(BlockSpringWater, SpringWater_Name);
		GameRegistry.registerBlock(BlockBoilingCauldon, Cauldon_Name);
		GameRegistry.registerTileEntity(TileEntity_Cauldon.class, "hotwater_cauldon");
	}
	
    public static void registerEgg(){
    	BlockSuperLava = new BlockSuperLava(RegistryFluid.superlava, Material.lava).setBlockName(BlockRegistry.SuperLava_Name).setHardness(100F).setLightLevel(10.0F);
    	GameRegistry.registerBlock(BlockRegistry.BlockSuperLava, BlockRegistry.SuperLava_Name);
	}
	
}
