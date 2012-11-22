package pl.socialCraft.enderdeath.listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import pl.socialCraft.enderdeath.Config;


import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerListener implements Listener{
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		e.getPlayer().sendMessage(Config.getMessage("joinHelp"));
	}
	@EventHandler
	public void onShoot(EntityShootBowEvent e){
		if (e.getProjectile() instanceof Arrow){
			EnderPearl ender = e.getProjectile().getWorld().spawn(e.getProjectile().getLocation(), EnderPearl.class);
			ender.setVelocity(e.getProjectile().getVelocity());
			ender.setShooter(((Projectile)e.getProjectile()).getShooter());
			e.setCancelled(true);
			e.getProjectile().remove();
		}
	}
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		if (e.getEntityType() == EntityType.PLAYER && e.getCause() == DamageCause.FALL){
			e.setCancelled(true);
		}
		else if (e.getEntityType() == EntityType.PLAYER && e.getDamager() instanceof Player){
			if (PermissionsEx.getUser((Player) e.getEntity()).getPrefix().equalsIgnoreCase(PermissionsEx.getUser((Player) e.getDamager()).getPrefix())){
				e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onHit(ProjectileHitEvent e){
		if (e.getEntityType() == EntityType.ENDER_PEARL){
			List<Entity> entities = e.getEntity().getNearbyEntities(1, 1, 1);
			for (int i = 0; i < entities.size(); i++) {
				Entity ent = entities.get(i);
				if (ent instanceof LivingEntity){
					if (ent != e.getEntity().getShooter())
					((LivingEntity)ent).damage(2, e.getEntity().getShooter());
				}
			}
		}
	}
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e){
		if (e.getMessage().equalsIgnoreCase(Config.getCommand("join"))){
			e.setCancelled(true);
			
		}
	}
	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		e.setDroppedExp(0);
		for (int i = 0; i < e.getDrops().size(); i++) {
			if (e.getDrops().get(i).getType() == Material.GOLDEN_APPLE)
				e.getDrops().remove(i);
		}
		e.getDrops().add(new ItemStack(Material.GOLDEN_APPLE));
		if (e.getEntity().getKiller() != null){
			e.setDeathMessage(Config.getMessage("deathMessage", e.getEntity().getKiller().getDisplayName(), e.getEntity().getDisplayName()));
		}
	}
}
