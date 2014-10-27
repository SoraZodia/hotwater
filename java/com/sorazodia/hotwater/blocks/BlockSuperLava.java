package com.sorazodia.hotwater.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import com.sorazodia.hotwater.HotWaterMain;

public class BlockSuperLava extends BlockFluidClassic{

	protected IIcon stillLava;
	protected IIcon flowingLava;
	
	Random rand = new Random();

	public BlockSuperLava(Fluid fluid, Material material) {
		super(fluid, material);
		disableStats();
	}
	
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        entity.attackEntityFrom(HotWaterMain.Melted, 18.0F);
    }

	public boolean canDisplace(IBlockAccess world, int x, int y, int z)
    {			
        Block block = world.getBlock(x, y, z);
        if(world.getBlock(x, y, z).isAir(world, x, y, z))return true;
        if(block == this)return false;
        else return true;
    }

	
	 public boolean displaceIfPossible(World world, int x, int y, int z)
	    {
		 Block block = world.getBlock(x, y, z);
	        if(block == this)return false;
	        else return true;
	    }
	 
	 public int getMeltChance(int var1){
		 int melt = rand.nextInt(var1);
		 return melt;
	 }
	 
	 @Override
		public IIcon getIcon (int side, int meta){
			return (IIcon) ((side == 0 || side == 1) ? stillLava : flowingLava);
		}
		
	    @Override
	    public void registerBlockIcons(IIconRegister register) {
	            stillLava = register.registerIcon("lava_still");
	            flowingLava = register.registerIcon("lava_flow");
	    }

}
