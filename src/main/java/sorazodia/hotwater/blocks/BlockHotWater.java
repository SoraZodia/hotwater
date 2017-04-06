package sorazodia.hotwater.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
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
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{

		if (entity instanceof EntityLivingBase)
		{
			entity.attackEntityFrom(HotWater.Boiled, 2.0F);

			//if (entity instanceof EntityPlayer) new SoundEvent(new ResourceLocation("random.fizz"))
			world.playSound(null, pos.add(0.5, 0.5, 0.5), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.AMBIENT, 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
		}
		else if (entity instanceof EntityItem && !world.isRemote)
			cook(world, pos, (EntityItem) entity);
	}

	private void cook(World world, BlockPos pos, EntityItem entity)
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
					boil(world, pos, itemEntity, BoilList.getOutput(q), input.getCount());
			}
			else if (itemStack.getItem() == input.getItem() && itemStack.getItemDamage() == input.getItemDamage())
			{
				boil(world, pos, itemEntity, BoilList.getOutput(q), input.getCount());
			}

		}

	}

	private void boil(World world, BlockPos pos, EntityItem itemEntity, ItemStack output, int amount)
	{
		world.playSound(null, pos.add(0.5, 0.5, 0.5), new SoundEvent(new ResourceLocation("random.fizz")), SoundCategory.AMBIENT, 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

		if (amount < output.getCount() * amount)
			amount *= output.getCount();

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
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random)
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
