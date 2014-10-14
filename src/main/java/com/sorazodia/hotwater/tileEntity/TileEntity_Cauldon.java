package com.sorazodia.hotwater.tileEntity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntity_Cauldon extends TileEntity{

	private ItemStack potionItems;
	
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
	
	 public void inputPotionItems(ItemStack itemstack){
		 potionItems = itemstack;
		 markDirty();		 
	 }
}
