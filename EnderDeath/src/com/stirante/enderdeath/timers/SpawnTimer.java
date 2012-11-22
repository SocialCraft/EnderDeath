package com.stirante.enderdeath.timers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.stirante.enderdeath.Team;

public class SpawnTimer implements Runnable {
	
	
	private String	playerName;
	private Team	team;

	public SpawnTimer(Player player, Team team){
		this.playerName = player.getName();
		this.team = team;
	}
	
	@Override
	public void run() {
		Player player = Bukkit.getPlayerExact(playerName);
		player.getInventory().clear();
		player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		ItemStack item = new ItemStack(Material.BOW);
		item.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		player.getInventory().addItem(item);
		player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 64));
		player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, team.getPlayerPoints(player)));
	}
	
}
