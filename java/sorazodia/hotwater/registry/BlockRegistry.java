package sorazodia.hotwater.registry;

import sorazodia.hotwater.blocks.BlockHotWater;
import sorazodia.hotwater.blocks.BlockSpringWater;
import sorazodia.hotwater.blocks.BlockSuperLava;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockRegistry {
	
	//Mod names
	public static final String Water_Name = "hot_water";
	public static final String SuperLava_Name = "super_lava";
	public static final String SpringWater_Name = "hot_spring_water";
	
	//Blocks
	public static Block BlockHotWater = new BlockHotWater(RegistryFluid.hotWater, Material.water).setBlockName(Water_Name).setHardness(100F);
	public static Block BlockSpringWater = new BlockSpringWater(RegistryFluid.springWater, Material.water).setBlockName(SpringWater_Name).setHardness(100F);
	public static Block BlockSuperLava;
				
	public static void register(){
		GameRegistry.registerBlock(BlockHotWater, Water_Name);
		GameRegistry.registerBlock(BlockSpringWater, SpringWater_Name);
	}
	
    public static void registerEgg(){
    	BlockSuperLava = new BlockSuperLava(RegistryFluid.superlava, Material.lava).setBlockName(BlockRegistry.SuperLava_Name).setHardness(100F).setLightLevel(10.0F);
    	GameRegistry.registerBlock(BlockRegistry.BlockSuperLava, BlockRegistry.SuperLava_Name);
	}
	
}
