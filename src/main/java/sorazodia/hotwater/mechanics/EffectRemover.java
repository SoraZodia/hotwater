package sorazodia.hotwater.mechanics;

import java.util.ArrayList;

import net.minecraft.potion.Potion;

public class EffectRemover
{
	
	private static ArrayList<Potion> remove = new ArrayList<Potion>();
	private static Potion[] negEffect = {
			Potion.blindness, 
			Potion.confusion,
			Potion.weakness,
			Potion.poison,
			Potion.wither,
			Potion.moveSlowdown,
			Potion.digSlowdown
			};
	

	public static void init()
	{
		for(Potion p: negEffect)
			remove.add(p);
	}
	
	public static ArrayList<Potion> getRemovalList()
	{
		return remove;
	}
	
}
