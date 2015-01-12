package sorazodia.hotwater.registry;

import sorazodia.hotwater.blocks.BlockHotWater;
import sorazodia.hotwater.blocks.BlockSpringWater;
import sorazodia.hotwater.blocks.BlockSuperLava;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockRegistry 
{
	
	//Mod names
	public static final String WATERNAME = "hot_water";
	public static final String SUPERLAVANAME = "super_lava";
	public static final String SPRINGWATERNAME = "hot_spring_water";
	
	//Blocks
	public static Block blockHotWater = new BlockHotWater(RegistryFluid.hotWater, Material.water).setBlockName(WATERNAME).setHardness(100F);
	public static Block blockSpringWater = new BlockSpringWater(RegistryFluid.springWater, Material.water).setBlockName(SPRINGWATERNAME).setHardness(100F);
	public static Block blockSuperLava;
				
	public static void register()
	{
		GameRegistry.registerBlock(blockHotWater, WATERNAME);
		GameRegistry.registerBlock(blockSpringWater, SPRINGWATERNAME);
	}
	
    public static void registerEgg()
    {
    	blockSuperLava = new BlockSuperLava(RegistryFluid.superlava, Material.lava).setBlockName(BlockRegistry.SUPERLAVANAME).setHardness(100F).setLightLevel(10.0F);
    	GameRegistry.registerBlock(BlockRegistry.blockSuperLava, BlockRegistry.SUPERLAVANAME);
	}
	
}
