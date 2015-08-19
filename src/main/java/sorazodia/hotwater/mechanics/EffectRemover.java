package sorazodia.hotwater.mechanics;

import java.util.ArrayList;

import net.minecraft.potion.Potion;

public class EffectRemover
{	
	private static ArrayList<Integer> remove = new ArrayList<>();
	private static int[] negEffectID = {
			Potion.blindness.id, 
			Potion.confusion.id,
			Potion.weakness.id,
			Potion.poison.id,
			Potion.wither.id,
			Potion.moveSlowdown.id,
			Potion.digSlowdown.id
			};
	

	public static void init()
	{
		for(int p: negEffectID)
			remove.add(p);
	}
	
	public static ArrayList<Integer> getRemovalList()
	{
		return remove;
	}
	
}
