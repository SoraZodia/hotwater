package sorazodia.hotwater.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import sorazodia.hotwater.config.BoilList;
import sorazodia.hotwater.main.HotWaterMain;

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
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (!(entity instanceof EntityItem))
		{
			entity.attackEntityFrom(HotWaterMain.Boiled, 2.0F);
			world.playSoundEffect((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
		}
		if (entity instanceof EntityItem && !world.isRemote)
			cookingEffect(world, x, y, z, entity);
	}

	private void cookingEffect(World world, int x, int y, int z, Entity entity)
	{
		EntityItem itemEntity = (EntityItem) entity;
		ItemStack input = itemEntity.getEntityItem();

		for (int q = 0; q < BoilList.size(); q++)
		{
			ItemStack itemStack = BoilList.getInput(q);

			boolean ignoreMeta = BoilList.ignoreMetaData(q);

			if (ignoreMeta == true)
			{
				if (itemStack.getItem() == input.getItem())
					boil(world, x, y, z, itemEntity, BoilList.getOutput(q), input.stackSize);
			} else if (itemStack.getItem() == input.getItem() && itemStack.getItemDamage() == input.getItemDamage())
			{
				boil(world, x, y, z, itemEntity, BoilList.getOutput(q), input.stackSize);
			}

		}

	}

	private void boil(World world, int x, int y, int z, EntityItem itemEntity, ItemStack output, int amount)
	{
		world.playSoundEffect((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

		if (amount < output.stackSize * amount)
			amount *= output.stackSize;

		for (int q = 0; q < amount; q++)
			itemEntity.entityDropItem(new ItemStack(output.getItem(), 1, output.getItemDamage()), 0F);

		itemEntity.setDead();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if (random.nextInt(2) != 0)
			return;

		for (int l = 0; l < 2; l++)
		{
			double X = x + random.nextFloat();
			double Y = y + 1.2;
			double Z = z + random.nextFloat();
			world.spawnParticle("cloud", X, Y, Z, 0.0, 0.1, 0.0);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return (IIcon) ((side == 0 || side == 1) ? stillWater : flowingWater);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		stillWater = register.registerIcon("hot_water:hotWaterStill");
		flowingWater = register.registerIcon("hot_water:hotWaterFlow");
	}

}
