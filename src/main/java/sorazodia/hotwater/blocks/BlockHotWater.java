package sorazodia.hotwater.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import sorazodia.hotwater.main.HotWaterMain;
import sorazodia.hotwater.registry.ItemRegistry;

public class BlockHotWater extends BlockFluidClassic
{

	protected IIcon stillWater;
	protected IIcon flowingWater;
	protected World worldobj;
	protected Entity entity;

	public BlockHotWater(Fluid fluid, Material material)
	{
		super(fluid, material);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z,
			Entity entity)
	{
		if (!(entity instanceof EntityItem))
		{
			entity.attackEntityFrom(HotWaterMain.Boiled, 2.0F);
			if (entity.ticksExisted % 120 == 0)
				WaterEffect(world, x, y, z, 1, 1);
		}
		if (entity instanceof EntityItem && !world.isRemote)
		{
			CookingEffect(world, x, y, z, entity);
		}
	}

	private void CookingEffect(World world, int x, int y, int z, Entity entity)
	{
		if (((EntityItem) entity).getEntityItem().getItem() == Items.chicken)
		{
			WaterEffect(world, x, y, z, 2, 2);
			entity.setDead();
			entity.entityDropItem(new ItemStack(Items.cooked_chicken), 0F);
		}
		if (((EntityItem) entity).getEntityItem().getItem() == Items.porkchop)
		{
			WaterEffect(world, x, y, z, 2, 2);
			entity.setDead();
			entity.entityDropItem(new ItemStack(Items.cooked_porkchop), 0F);
		}
		if (((EntityItem) entity).getEntityItem().getItem() == Items.beef)
		{
			WaterEffect(world, x, y, z, 2, 2);
			entity.setDead();
			entity.entityDropItem(new ItemStack(Items.cooked_beef), 0F);
		}
		if (((EntityItem) entity).getEntityItem().getItem() == Items.potato
				|| ((EntityItem) entity).getEntityItem().getItem() == Items.poisonous_potato)
		{
			WaterEffect(world, x, y, z, 2, 2);
			entity.setDead();
			entity.entityDropItem(new ItemStack(Items.baked_potato), 0F);
		}
		if (((EntityItem) entity).getEntityItem().getItem() == Items.fish)
		{
			if (((EntityItem) entity).getEntityItem().getItemDamage() == 0)
			{
				WaterEffect(world, x, y, z, 2, 2);
				entity.setDead();
				entity.entityDropItem(new ItemStack(Items.cooked_fished, 1), 0F);
			}
			if (((EntityItem) entity).getEntityItem().getItemDamage() == 1)
			{
				WaterEffect(world, x, y, z, 2, 2);
				entity.setDead();
				entity.entityDropItem(new ItemStack(Items.cooked_fished, 1, 1),
						0F);
			}

		}
		if (((EntityItem) entity).getEntityItem().getItem() == Items.rotten_flesh)
		{
			WaterEffect(world, x, y, z, 2, 2);
			entity.setDead();
			entity.entityDropItem(new ItemStack(ItemRegistry.boiled_flesh), 0F);
		}
		if (((EntityItem) entity).getEntityItem().getItem() == Items.spider_eye)
		{
			WaterEffect(world, x, y, z, 2, 2);
			entity.setDead();
			entity.entityDropItem(new ItemStack(
					ItemRegistry.detoxified_spider_eyes), 0F);
		}
		if (((EntityItem) entity).getEntityItem().getItem() == Items.leather)
		{
			WaterEffect(world, x, y, z, 2, 2);
			entity.setDead();
			entity.entityDropItem(new ItemStack(ItemRegistry.boiled_leather),
					0F);
		}

	}

	private void WaterEffect(World world, int x, int y, int z, int retries, int type)
	{
		world.playSoundEffect((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
		for (int l = 0; l < retries; l++)
		{
			switch (type)
			{
			case 1:
				world.spawnParticle("largesmoke", (double) x + Math.random(), (double) y + 1.2D, (double) z
						+ Math.random(), 0.0D, 0.0D, 0.0D);
				break;
			case 2:
				world.spawnParticle("cloud", (double) x + Math.random(), (double) y + 1.2D, (double) z
						+ Math.random(), 0.0D, 0.0D, 0.0D);
				break;
			}
		}
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return (IIcon) ((side == 0 || side == 1) ? stillWater : flowingWater);
	}

	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		stillWater = register.registerIcon("hot_water:hotWaterStill");
		flowingWater = register.registerIcon("hot_water:hotWaterFlow");
	}

}