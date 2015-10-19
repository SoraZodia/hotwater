package sorazodia.hotwater.entity.projectile;

import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySteamBottle extends EntityThrowable
{

	public EntitySteamBottle(World world, int duration)
	{
		super(world);
	}

	public EntitySteamBottle(World world, int potionID, int duration)
	{
		super(world);
	}
	
	@Override
	public void onImpact(MovingObjectPosition position)
	{
		
	}

}
