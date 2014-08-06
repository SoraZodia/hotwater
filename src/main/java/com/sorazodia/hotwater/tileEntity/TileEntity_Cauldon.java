package com.sorazodia.hotwater.tileEntity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntity_Cauldon extends TileEntity implements IFluidHandler{

	public void updateEntity()
    {
    
    }
	 @Override
     public void readFromNBT(NBTTagCompound nbt) {
		 super.readFromNBT(nbt);
	 }
	 
	 @Override
     public void writeToNBT(NBTTagCompound nbt) {
		 super.writeToNBT(nbt);
	 }
	
	@Override
	public boolean canDrain(ForgeDirection dir, Fluid fluid) {
	return true;
	}

	@Override
	public boolean canFill(ForgeDirection arg0, Fluid arg1) {
	
		return true;
	}

	@Override
	public FluidStack drain(ForgeDirection arg0, FluidStack arg1, boolean arg2) {
		
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection arg0, int arg1, boolean arg2) {
		
		return null;
	}

	@Override
	public int fill(ForgeDirection arg0, FluidStack arg1, boolean arg2) {
		
		return 0;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection arg0) {
		
		return null;
	}

}
