package pl.socialCraft.enderdeath;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Team {
	private ArrayList<String> players;
	private ChatColor	color;
	private HashMap<String, Integer> points;
	private int teamPoints;
	
	public Team(ChatColor color){
		players = new ArrayList<String>();
		points = new HashMap<String, Integer>();
		this.color = color;
	}
	public int getSize(){
		return players.size();
	}
	public void join(Player player){
		players.add(player.getName());
		points.put(player.getName(), 0);
		player.setDisplayName(color + player.getName() + ChatColor.RESET);
		player.setPlayerListName(color + player.getName());
		player.getInventory().clear();
		player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		ItemStack item = new ItemStack(Material.BOW);
		item.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		player.getInventory().addItem(item);
		player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 64));
		player.getInventory().addItem(new ItemStack(Material.ARROW, 1));
	}
	public void reset(){
		for (int i = 0; i < getSize(); i++) {
			quit(Bukkit.getPlayerExact(players.get(i)));
		}
		players = new ArrayList<String>();
		points = new HashMap<String, Integer>();
		teamPoints = 0;
	}
	public int getPlayerPoints(Player player){
		return points.get(player.getName());
	}
	public boolean containsPlayer(Player player){
		return players.contains(player.getName());
	}
	public int getScore(){
		return teamPoints;
	}
	public String getTeamName() {
		return color.toString() + color.name();
	}
	public void addPoint(Player player){
		if (!points.containsKey(player.getName())){
			points.put(player.getName(), 1);
		}
		else {
			points.put(player.getName(), getPlayerPoints(player) + 1);
		}
	}
	public void quit(Player player) {
		player.setDisplayName(player.getName());
		player.setPlayerListName(player.getName());
		points.remove(player.getName());
		players.remove(player.getName());
		player.getInventory().clear();
	}
	public void broadcast(String message) {
		for (int i = 0; i < players.size(); i++) {
			Bukkit.getPlayerExact(players.get(i)).sendMessage(message);
		}
	}
}
