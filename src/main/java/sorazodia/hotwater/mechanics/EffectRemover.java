package sorazodia.hotwater.mechanics;

import java.util.ArrayList;

public class EffectRemover
{	
	private static ArrayList<Integer> remove = new ArrayList<>();
	private static int[] negEffectID = {

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
