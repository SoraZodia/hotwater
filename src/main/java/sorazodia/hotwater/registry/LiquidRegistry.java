package sorazodia.hotwater.registry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import sorazodia.hotwater.blocks.BlockHotWater;
import sorazodia.hotwater.blocks.BlockSpringWater;
import sorazodia.hotwater.blocks.BlockSuperLava;
import cpw.mods.fml.common.registry.GameRegistry;

public class LiquidRegistry
{

	//Mod names
	public static final String WATER_NAME = "hot_water";
	public static final String SUPERLAVA_NAME = "super_lava";
	public static final String SPRING_WATER_NAME = "hot_spring_water";

	//Fluids
	public static Fluid hotWater = new Fluid(WATER_NAME).setDensity(999).setTemperature(373).setViscosity(682);
	public static Fluid superlava = new Fluid(SUPERLAVA_NAME).setDensity(999).setTemperature(6150).setViscosity(682);
	public static Fluid springWater = new Fluid(SPRING_WATER_NAME).setDensity(999).setTemperature(373).setViscosity(682);
	
	//Blocks
	public static Block blockHotWater;
	public static Block blockSpringWater;
	public static Block blockSuperLava;

	private static void registerFluid()
	{
		FluidRegistry.registerFluid(hotWater);	
		FluidRegistry.registerFluid(springWater);
		FluidRegistry.registerFluid(superlava);
		
		blockHotWater = new BlockHotWater(hotWater, Material.water).setBlockName(WATER_NAME).setHardness(100F);
		blockSpringWater = new BlockSpringWater(springWater, Material.water).setBlockName(SPRING_WATER_NAME).setHardness(100F);
		blockSuperLava = new BlockSuperLava(superlava, Material.lava).setBlockName(SUPERLAVA_NAME).setHardness(100F).setLightLevel(10.0F);

	}
	
	public static void register()
	{	
		registerFluid();
		
		GameRegistry.registerBlock(blockHotWater, WATER_NAME);		
		GameRegistry.registerBlock(blockSpringWater, SPRING_WATER_NAME);
		GameRegistry.registerBlock(blockSuperLava, SUPERLAVA_NAME);		
	}

}
