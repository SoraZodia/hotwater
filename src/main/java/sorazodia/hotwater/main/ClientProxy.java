package sorazodia.hotwater.main;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import sorazodia.hotwater.blocks.IName;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerBlockState(Block block)
	{
		ModelResourceLocation blockJson = new ModelResourceLocation(HotWater.MODID + ":fluids", ((IName) block).getName());
	
	    ModelLoader.setCustomStateMapper(block, new StateMapperBase()
	    {
	    	@Override
	    	protected ModelResourceLocation getModelResourceLocation(IBlockState state)
	    	{
	    		return blockJson;
	    	}
	    }
	    );
	}

}
