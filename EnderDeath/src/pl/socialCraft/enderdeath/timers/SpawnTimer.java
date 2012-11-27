package pl.socialCraft.enderdeath.timers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import pl.socialCraft.enderdeath.EnderDeath;
import pl.socialCraft.enderdeath.Team;

public class SpawnTimer implements Runnable {
	
	private String	playerName;
	private Team	team;
	
	public SpawnTimer(Player player) {
		playerName = player.getName();
		team = EnderDeath.getRound().getPlayerTeam(player);
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
		if (team.getPlayerPoints(player) > 0)
			player.getInventory().addItem(
					new ItemStack(Material.GOLDEN_APPLE, team
							.getPlayerPoints(player)));
		player.getInventory().addItem(new ItemStack(Material.ARROW, 1));
	}
	
}
