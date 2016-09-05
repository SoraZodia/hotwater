package sorazodia.hotwater.registry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sorazodia.hotwater.blocks.BlockHotWater;
import sorazodia.hotwater.blocks.BlockSpringWater;
import sorazodia.hotwater.blocks.BlockSuperLava;
import sorazodia.hotwater.main.CommonProxy;
import sorazodia.hotwater.main.HotWater;

public class LiquidRegistry
{

	//Mod names
	public static final String WATER_NAME = "hot_water";
	public static final String SUPERLAVA_NAME = "super_lava";
	public static final String SPRING_WATER_NAME = "hot_spring_water";

	//Fluids
	public static Fluid hotWater = new Fluid(WATER_NAME, new ResourceLocation(HotWater.MODID + ":blocks/hotWaterStill"), new ResourceLocation(HotWater.MODID + ":blocks/hotWaterFlow")).setDensity(999).setTemperature(373).setViscosity(682);
	public static Fluid superlava = new Fluid(SUPERLAVA_NAME, new ResourceLocation("blocks/lava_still"), new ResourceLocation("blocks/lava_flow")).setDensity(999).setTemperature(6150).setViscosity(682);
	public static Fluid springWater = new Fluid(SPRING_WATER_NAME, new ResourceLocation(HotWater.MODID + ":blocks/springWaterStill"), new ResourceLocation(HotWater.MODID + ":blocks/hotWaterFlow")).setDensity(999).setTemperature(373).setViscosity(682);
	
	//Blocks
	public static Block blockHotWater;
	public static Block blockSpringWater;
	public static Block blockSuperLava;
	
	public static void register()
	{	
		CommonProxy proxy = HotWater.proxy;
		
		FluidRegistry.registerFluid(hotWater);	
		FluidRegistry.registerFluid(springWater);
		FluidRegistry.registerFluid(superlava);

		blockHotWater = new BlockHotWater(hotWater, WATER_NAME,Material.WATER).setHardness(100F);
		blockSpringWater = new BlockSpringWater(springWater, SPRING_WATER_NAME,Material.WATER).setHardness(100F);
		blockSuperLava = new BlockSuperLava(superlava, SUPERLAVA_NAME,Material.LAVA).setHardness(100F).setLightLevel(10.0F);
		
		GameRegistry.register(blockHotWater, new ResourceLocation(HotWater.MODID + ":" + WATER_NAME));		
		GameRegistry.register(blockSpringWater, new ResourceLocation(HotWater.MODID + ":" + SPRING_WATER_NAME));
		GameRegistry.register(blockSuperLava, new ResourceLocation(HotWater.MODID + ":" + SUPERLAVA_NAME));		
		
		proxy.registerBlockState(blockHotWater);
		proxy.registerBlockState(blockSpringWater);
		proxy.registerBlockState(blockSuperLava);
	}

}
