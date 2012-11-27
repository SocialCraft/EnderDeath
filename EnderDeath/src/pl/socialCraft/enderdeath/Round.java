package pl.socialCraft.enderdeath;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.socialCraft.enderdeath.timers.RoundTimer;

public class Round {
	private Team		team1;
	private Team		team2;
	private EnderDeath	plugin;
	private int			time;
	private int			taskId	= 0;
	
	public Round(EnderDeath plugin) {
		this.plugin = plugin;
		team1 = new Team(Config.getColor("team1"));
		team2 = new Team(Config.getColor("team2"));
		time = Config.getInt("round.time") * 60;
	}
	
	public Team getPlayerTeam(Player player) {
		if (team1.containsPlayer(player)) return team1;
		if (team2.containsPlayer(player)) return team2;
		return null;
	}
	
	public void stop() {
		if (team1.getScore() > team2.getScore())
			broadcast(Config.getMessage("winMessage", team1.getTeamName()));
		if (team2.getScore() > team1.getScore())
			broadcast(Config.getMessage("winMessage", team2.getTeamName()));
		if (team1.getScore() == team2.getScore())
			broadcast(Config.getMessage("noWinMessage"));
		team1.reset();
		team2.reset();
		Bukkit.getScheduler().cancelTask(taskId);
		taskId = 0;
		time = Config.getInt("round.time") * 60;
	}
	
	public void start() {
		taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,
				new RoundTimer(this), 20);
	}
	
	public void tick() {
		time--;
		if (time <= 0) {
			stop();
			return;
		}
		if (time == 3)
			broadcast(Config.getMessage("time", String.valueOf(time)));
		if (time == 2)
			broadcast(Config.getMessage("time", String.valueOf(time)));
		if (time == 1)
			broadcast(Config.getMessage("time", String.valueOf(time)));
		start();
	}
	
	public int getTime() {
		return time;
	}
	
	public void join(Player player) {
		if (!isRunning()) start();
		if (team1.getSize() > team2.getSize()) {
			team2.join(player);
			return;
		}
		if (team2.getSize() > team1.getSize()) {
			team1.join(player);
			return;
		}
		if (team1.getSize() == team2.getSize()) {
			team1.join(player);
			return;
		}
	}
	
	public boolean isRunning() {
		return taskId > 0;
	}
	
	public void broadcast(String message) {
		team1.broadcast(message);
		team2.broadcast(message);
	}
	
	public void broadcastToTeam1(String message) {
		team1.broadcast(message);
	}
	
	public void broadcastToTeam2(String message) {
		team2.broadcast(message);
	}
	
	public String[] getFormattedScore() {
		return new String[] { team1.getTeamName() + ":" + team1.getScore(),
				team2.getTeamName() + ":" + team2.getScore() };
	}
}
