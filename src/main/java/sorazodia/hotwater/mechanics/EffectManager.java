package sorazodia.hotwater.mechanics;

import java.util.ArrayList;

import sorazodia.hotwater.main.HotWater;
import net.minecraft.potion.Potion;

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
		
		if (HotWater.isInteger(id) && (potionID = Integer.parseInt(id)) < Potion.potionTypes.length)
		{
			blacklist.add(Potion.potionTypes[potionID]);
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
		
		if (HotWater.isInteger(id) && (potionID = Integer.parseInt(id)) < Potion.potionTypes.length)
		{
			whitelist.add(Potion.potionTypes[potionID]);
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
	
}
