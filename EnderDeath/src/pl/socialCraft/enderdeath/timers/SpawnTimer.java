package pl.socialCraft.enderdeath.timers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
		if (team != null) team.spawnPlayer(player);
	}
	
}
