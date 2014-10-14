package com.sorazodia.hotwater.registry;

import net.minecraft.item.ItemFood;

import com.sorazodia.hotwater.HotWaterMain;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegistry {
	
	//Food
	public static ItemFood boiled_flesh = (ItemFood) new ItemFood(4, 1.0F, true)
		.setUnlocalizedName("boiled_flesh").setTextureName("rotten_flesh").setCreativeTab(HotWaterMain.hotWaterTab);
	public static ItemFood detoxified_spider_eyes = (ItemFood) new ItemFood(1, 1.0F, true)
		.setUnlocalizedName("detoxified_spider_eyes").setTextureName("spider_eye").setCreativeTab(HotWaterMain.hotWaterTab);
	public static ItemFood boiled_leather = (ItemFood) new ItemFood(2, 1.0F, true)
		.setUnlocalizedName("boiled_leather").setTextureName("leather").setCreativeTab(HotWaterMain.hotWaterTab);

	public static void registerFood(){
		
		GameRegistry.registerItem(boiled_flesh, "boiled_flesh", HotWaterMain.MODID);
	    GameRegistry.registerItem(boiled_leather, "boiled_leather", HotWaterMain.MODID);
		GameRegistry.registerItem(detoxified_spider_eyes, "detoxified_spider_eyes", HotWaterMain.MODID);

	}

}
