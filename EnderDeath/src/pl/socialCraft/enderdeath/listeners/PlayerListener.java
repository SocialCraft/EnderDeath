package pl.socialCraft.enderdeath.listeners;

import java.util.List;

import org.bukkit.Bukkit;
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
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import pl.socialCraft.enderdeath.Config;
import pl.socialCraft.enderdeath.EnderDeath;
import pl.socialCraft.enderdeath.commands.Commands;
import pl.socialCraft.enderdeath.timers.SpawnTimer;

public class PlayerListener implements Listener {
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.getPlayer().sendMessage(Config.getMessage("joinHelp"));
	}
	
	@EventHandler
	public void onShoot(EntityShootBowEvent e) {
		if (e.getProjectile() instanceof Arrow) {
			EnderPearl ender = e.getEntity().launchProjectile(EnderPearl.class);
			ender.setVelocity(e.getProjectile().getVelocity());
			ender.setShooter(((Projectile) e.getProjectile()).getShooter());
			e.setCancelled(true);
			e.getProjectile().remove();
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (e.getEntityType() == EntityType.PLAYER
				&& e.getCause() == DamageCause.FALL)
			e.setCancelled(true);
		else if (e.getEntityType() == EntityType.PLAYER
				&& e.getDamager() instanceof Player)
			if (EnderDeath.getRound().getPlayerTeam((Player) e.getEntity()) == EnderDeath
					.getRound().getPlayerTeam((Player) e.getDamager()))
				e.setCancelled(true);
	}
	
	@EventHandler
	public void onHit(ProjectileHitEvent e) {
		if (e.getEntityType() == EntityType.ENDER_PEARL) {
			List<Entity> entities = e.getEntity().getNearbyEntities(2, 2, 2);
			for (int i = 0; i < entities.size(); i++) {
				Entity ent = entities.get(i);
				if (ent instanceof LivingEntity)
					if (ent != e.getEntity().getShooter())
						((LivingEntity) ent).damage(4, e.getEntity()
								.getShooter());
			}
		}
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Commands.performCommand(e);
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		e.setDroppedExp(0);
		e.getDrops().clear();
		if (e.getEntity().getKiller() == null) return;
		e.setDeathMessage(Config.getMessage("deathMessage", e.getEntity()
				.getKiller().getDisplayName(), e.getEntity().getDisplayName()));
		EnderDeath.getRound().getPlayerTeam(e.getEntity().getKiller())
				.addPoint(e.getEntity().getKiller());
	}
	
	@EventHandler
	public void onSpawn(PlayerRespawnEvent e) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(EnderDeath.getInstance(),
				new SpawnTimer(e.getPlayer()), 5);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if (EnderDeath.getRound().getPlayerTeam(e.getPlayer()) != null)
			EnderDeath.getRound().getPlayerTeam(e.getPlayer())
					.quit(e.getPlayer());
	}
	
}
