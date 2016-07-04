package sorazodia.hotwater.mechanics;

import java.util.ArrayList;

import net.minecraft.potion.Potion;
import sorazodia.hotwater.main.HotWater;

public class EffectManager
{	
	private static ArrayList<Potion> blacklist = new ArrayList<>();
	private static ArrayList<Potion> whitelist = new ArrayList<>();
	
	/**
	 * Convert the int or string into a potion constant, and output true if that potion existed
	 */
	public static boolean addToBlacklist(String id)
	{
		int potionID = 0;
		boolean result = false;
		
		if (HotWater.isInteger(id))
		{
			blacklist.add(Potion.getPotionById(potionID));
			result = true;
		}
		else if (Potion.getPotionFromResourceLocation(id) != null)
		{
			blacklist.add(Potion.getPotionFromResourceLocation(id));
			result = true;
		}
		
		return result;
			
	}
	
	/**
	 * Convert the int or string into a potion constant, and output true if that potion existed
	 */
	public static boolean addToWhitelist(String id)
	{
		int potionID = 0;
		boolean result = false;
		
		if (HotWater.isInteger(id))
		{
			whitelist.add(Potion.getPotionById(potionID));
			result = true;
		}
		else if (Potion.getPotionFromResourceLocation(id) != null)
		{
			whitelist.add(Potion.getPotionFromResourceLocation(id));
			result = true;
		}
		
		return result;
			
	}
	
	public static ArrayList<Potion> getBlacklist()
	{
		return blacklist;
	}
	
	public static ArrayList<Potion> getWhitelist()
	{
		return whitelist;
	}
	
	public static void clear()
	{
		blacklist.clear();
		whitelist.clear();
	}
}
