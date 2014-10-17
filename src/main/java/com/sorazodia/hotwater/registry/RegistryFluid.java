package com.sorazodia.hotwater.registry;

import com.sorazodia.hotwater.HotWaterMain;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class RegistryFluid {
	
	//Mod names
	private static final String Water_Name = "hot_water";
	private static final String SuperLava_Name = "super_lava";
	private static final String SpringWater_Name = "hot_spring_water";
	//Fluids
	public static Fluid hotWater = new Fluid(Water_Name).setDensity(999).setTemperature(373).setViscosity(682);
	public static Fluid superlava = new Fluid(SuperLava_Name).setDensity(999).setTemperature(6150).setViscosity(682);
	public static Fluid springWater = new Fluid(SpringWater_Name).setDensity(999).setTemperature(373).setViscosity(682);
	
	public static void register(){
		FluidRegistry.registerFluid(hotWater);	
		FluidRegistry.registerFluid(springWater);
	}	
	
	 public static void registerEgg(){
		 FluidRegistry.registerFluid(superlava);
	 }

}
