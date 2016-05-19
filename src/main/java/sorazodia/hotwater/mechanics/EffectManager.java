package sorazodia.hotwater.mechanics;

import java.util.ArrayList;

public class EffectManager
{	
	private static ArrayList<Integer> remove = new ArrayList<>();
	private static ArrayList<Integer> whitelist = new ArrayList<>();
	
	public static ArrayList<Integer> getRemovalList()
	{
		return remove;
	}
	
	public static ArrayList<Integer> getWhitelist()
	{
		return whitelist;
	}
	
}
