package sorazodia.hotwater.registry;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sorazodia.hotwater.items.ItemDevBucket;
import sorazodia.hotwater.items.ItemModBucket;
import sorazodia.hotwater.items.ItemSuperLavaBucket;
import sorazodia.hotwater.main.HotWater;
import sorazodia.registryhelper.SimpleItemsRegistry;

public class ItemRegistry
{

	//Food
	public static ItemFood boiledFlesh = (ItemFood) new ItemFood(4, 1.0F, true);
	public static ItemFood detoxifiedSpiderEyes = (ItemFood) new ItemFood(1, 1.0F, true);
	public static ItemFood boiledLeather = (ItemFood) new ItemFood(2, 1.0F, true);

	//Items
	public static Item hotWaterBucket = new ItemModBucket(LiquidRegistry.blockHotWater).setContainerItem(Items.BUCKET);
	public static Item springWaterBucket = new ItemModBucket(LiquidRegistry.blockSpringWater);
	public static Item superlavaBucket = new ItemSuperLavaBucket().setUnlocalizedName("bucket_superlava").setCreativeTab(HotWater.hotWaterTab);

	private static Item devBucket = new ItemDevBucket().setCreativeTab(HotWater.hotWaterTab);
	
	public static void register()
	{
        SimpleItemsRegistry.init(HotWater.MODID, HotWater.hotWaterTab);
		
        SimpleItemsRegistry.registerItems(boiledFlesh, "boiled_flesh");
        SimpleItemsRegistry.registerItems(boiledLeather, "boiled_leather");
        SimpleItemsRegistry.registerItems(detoxifiedSpiderEyes, "detoxified_spider_eyes");

        SimpleItemsRegistry.registerItems(hotWaterBucket, "bucket_hot_water");
        SimpleItemsRegistry.registerItems(springWaterBucket, "bucket_spring_water");
        SimpleItemsRegistry.registerItems(devBucket, "bucket_dev");
        registerSuperLava();
	}
	
	private static void registerSuperLava()
	{
		 GameRegistry.register(superlavaBucket, new ResourceLocation(HotWater.MODID + ":bucket_superlava"));
	        if(FMLCommonHandler.instance().getSide().isClient())
	        {
	            ModelLoader.setCustomModelResourceLocation(superlavaBucket, 0, new ModelResourceLocation("lava_bucket", "inventory"));
	            ModelLoader.setCustomModelResourceLocation(superlavaBucket, 1, new ModelResourceLocation("lava_bucket", "inventory"));
	        }
	}

}
