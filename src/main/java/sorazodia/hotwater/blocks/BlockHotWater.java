package sorazodia.hotwater.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import sorazodia.hotwater.config.BoilList;
import sorazodia.hotwater.main.HotWater;

public class BlockHotWater extends BlockFluidClassic implements IName
{
	private final String WATER_NAME;
	
	public BlockHotWater(Fluid fluid, String name, Material material)
	{
		super(fluid, material);
		this.setUnlocalizedName(name);
		this.WATER_NAME = name;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity)
	{
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		if (entity instanceof EntityLivingBase)
		{
			entity.attackEntityFrom(HotWater.Boiled, 2.0F);
			world.playSoundEffect((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
		}
		else if (entity instanceof EntityItem && !world.isRemote)
			cook(world, x, y, z, (EntityItem) entity);
	}

	private void cook(World world, int x, int y, int z, EntityItem entity)
	{
		EntityItem itemEntity = entity;
		ItemStack input = itemEntity.getEntityItem();

		for (int q = 0; q < BoilList.size(); q++)
		{
			ItemStack itemStack = BoilList.getInput(q);

			boolean ignoreMeta = BoilList.ignoreMetaData(q);

			if (ignoreMeta == true)
			{
				if (itemStack.getItem() == input.getItem())
					boil(world, x, y, z, itemEntity, BoilList.getOutput(q), input.stackSize);
			}
			else if (itemStack.getItem() == input.getItem() && itemStack.getItemDamage() == input.getItemDamage())
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
	public String getName()
	{
		return WATER_NAME;
	}
	
	@Override
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random random)
    {
     	 if (random.nextInt(2) != 0)
				return;

		for (int l = 0; l < 2; l++)
		{
			double x = pos.getX() + random.nextFloat();
			double y = pos.getY() + 1.2;
			double z = pos.getZ() + random.nextFloat();
			world.spawnParticle(EnumParticleTypes.CLOUD, x, y, z, 0.0, 0.1, 0.0);
		}
	 }

}
