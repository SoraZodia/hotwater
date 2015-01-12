package sorazodia.hotwater.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

public class BlockSpringWater extends BlockHotWater
{
	
	private Potion badEffect[]= {
			Potion.blindness, 
			Potion.confusion,
			Potion.weakness,
			Potion.poison,
			Potion.wither,
			Potion.moveSlowdown,
			Potion.digSlowdown
			};
	
    private Minecraft minecraft;
	
    private float hunger = 0;
    private double timer = 0;
	
	public BlockSpringWater(Fluid fluid, Material material) 
	{
		super(fluid, material);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{	

		if((entity instanceof EntityLivingBase || entity instanceof EntityPlayer) && !world.isRemote)
		{		
			for(int l=0; l<badEffect.length; l++)
			{
				((EntityLivingBase)entity).removePotionEffect(badEffect[l].id);
			}
			if(!(entity instanceof EntityPlayer))
				((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.regeneration.id, (int)timer, 0, true));

			if(entity instanceof EntityPlayer)
			{
				((EntityPlayer)entity).getFoodStats().addExhaustion(hunger);
				if(!((EntityPlayer)entity).isPotionActive(Potion.regeneration))
					((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.regeneration.id, (int)timer, 0, true));
			}
			if(entity.ticksExisted % 20 == 0)timer++;
			if(entity.ticksExisted % 140 == 0) hunger+=1;
				 
			if(timer>4820 && !minecraft.theWorld.getWorldInfo().isHardcoreModeEnabled()) timer=4820;
		}

	}

	private void WaterEffect(World world, int x, int y, int z)
	{
		for(int l = 0; l < 1; l++)
		{
			world.playSoundEffect((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
			world.spawnParticle("cloud", (double)x + Math.random(), (double)y + 1.2D, (double)z + Math.random(), 0.0D, 0.0D, 0.0D);   
		}
	}
	
	@Override
    public void registerBlockIcons(IIconRegister register) 
	{
            stillWater = register.registerIcon("hot_water:spring_water_still");
            flowingWater = register.registerIcon("hot_water:hot_water_flow");
    }	
	
}
