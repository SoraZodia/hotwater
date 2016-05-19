package sorazodia.hotwater.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import sorazodia.hotwater.main.HotWater;

public class BlockSuperLava extends BlockFluidClassic{

	public BlockSuperLava(Fluid fluid, Material material) 
	{
		super(fluid, material);
		disableStats();
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity)
	{
		entity.attackEntityFrom(HotWater.Melted, 18.0F);
	}

	@Override
	public boolean canDisplace(IBlockAccess world, BlockPos pos)
	{			
		Block block = world.getBlockState(pos).getBlock();
		if(block.isAir(world, pos))return true;
		if(block == this)return false;
		else return true;
	}

	@Override
	public boolean displaceIfPossible(World world, BlockPos pos)
	{
		Block block = world.getBlockState(pos).getBlock();
		if(block == this)return false;
		else return true;
	}

}
