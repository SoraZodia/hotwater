package sorazodia.hotwater.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import sorazodia.hotwater.mechanics.EffectManager;

public class BlockSpringWater extends BlockFluidClassic implements IName
{
	private float hunger = 0;
	private final String SPRING_WATER_NAME;

	public BlockSpringWater(Fluid fluid, String name, Material material)
	{
		super(fluid, material);
		this.setUnlocalizedName(name);
		this.SPRING_WATER_NAME = name;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state,Entity entity)
	{
		if ((entity instanceof EntityLivingBase)&& !world.isRemote)
		{
			EntityLivingBase living = (EntityLivingBase) entity;
			for (Potion remove : EffectManager.getBlacklist())
			{
				living.removePotionEffect(remove);
			}
			
			for (PotionEffect activeEffect : living.getActivePotionEffects())
			{
				Potion potion = activeEffect.getPotion();
				
				if (potion.isBadEffect() && !EffectManager.getWhitelist().contains(potion))
					living.removePotionEffect(potion);
			}

			if (living.ticksExisted % 100 == 0 && living.getHealth() > 0)
			{
				living.setHealth(living.getHealth() + 0.5F);
				if (living instanceof EntityPlayer)
					((EntityPlayer) living).getFoodStats().addExhaustion(hunger);
			}
		}
	}
	
	@Override
	public String getName()
	{
		return SPRING_WATER_NAME;
	}

	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random)
    {
		for (int l = 0; l < 4; l++)	
		{
			double x = pos.getX() + random.nextFloat();
			double z = pos.getZ() + random.nextFloat();
			world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, x, pos.getY(), z, 0.0, random.nextFloat(), 0.0);
		}
			
	}

}
