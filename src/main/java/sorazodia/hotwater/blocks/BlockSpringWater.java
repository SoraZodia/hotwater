package sorazodia.hotwater.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import sorazodia.hotwater.mechanics.EffectRemover;

public class BlockSpringWater extends BlockHotWater
{
	private float hunger = 0;

	public BlockSpringWater(Fluid fluid, Material material)
	{
		super(fluid, material);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{

		if ((entity instanceof EntityLivingBase) && !world.isRemote)
		{
			EntityLivingBase living = (EntityLivingBase) entity;
			for (Potion remove : EffectRemover.getRemovalList())
			{
				living.removePotionEffect(remove.id);
			}

			if (living.ticksExisted % 100 == 0)
			{
				living.setHealth(living.getHealth() + 0.5F);
				if (living instanceof EntityPlayer)
					((EntityPlayer) living).getFoodStats().addExhaustion(hunger);
			}
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		for (int l = 0; l < 4; l++)
		{
			double X = x + random.nextFloat();
			double Z = z + random.nextFloat();
			world.spawnParticle("bubble", X, y, Z, 0.0, random.nextFloat(), 0.0);
		}
		
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		stillWater = register.registerIcon("hot_water:springWaterStill");
		flowingWater = register.registerIcon("hot_water:hotWaterFlow");
	}

}
