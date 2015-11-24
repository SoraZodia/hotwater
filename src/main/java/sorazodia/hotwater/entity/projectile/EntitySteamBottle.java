package sorazodia.hotwater.entity.projectile;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySteamBottle extends EntityThrowable
{
	int id;
	int duration;
	int power;
	
	public EntitySteamBottle(World world, int duration)
	{
		super(world);
		this.duration = duration;
	}

	public EntitySteamBottle(World world, int potionID, int duration, int powerLevel)
	{
		super(world);
		id = potionID;
		this.duration = duration;
		power = powerLevel;
	}
	
	@Override
	public void onImpact(MovingObjectPosition position)
	{
		@SuppressWarnings("unchecked")
		List<EntityLivingBase> entities = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(position.blockX - 2.5, position.blockY - 1, position.blockZ - 2.5, position.blockX + 2.5, position.blockY + 2, position.blockZ + 2.5));
	    
		for (EntityLivingBase entity : entities)
			 entity.addPotionEffect(new PotionEffect(id, duration, power));
		
	}

}
