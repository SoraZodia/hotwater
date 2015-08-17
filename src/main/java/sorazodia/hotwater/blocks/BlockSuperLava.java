package sorazodia.hotwater.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import sorazodia.hotwater.main.HotWaterMain;

public class BlockSuperLava extends BlockFluidClassic{

	protected IIcon stillLava;
	protected IIcon flowingLava;

	public BlockSuperLava(Fluid fluid, Material material) 
	{
		super(fluid, material);
		disableStats();
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		entity.attackEntityFrom(HotWaterMain.Melted, 18.0F);
	}

	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z)
	{			
		Block block = world.getBlock(x, y, z);
		if(world.getBlock(x, y, z).isAir(world, x, y, z))return true;
		if(block == this)return false;
		else return true;
	}

	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		if(block == this)return false;
		else return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon (int side, int meta)
	{
		return (IIcon) ((side == 0 || side == 1) ? stillLava : flowingLava);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		for (int l = 0; l < 4; l++)
		{
			double X = x + random.nextFloat();
			double Z = z + random.nextFloat();
			world.spawnParticle("lava", X, y, Z, 0.0, random.nextFloat(), 0.0);
		}
		
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) 
	{
		stillLava = register.registerIcon("lava_still");
		flowingLava = register.registerIcon("lava_flow");
	}

}
