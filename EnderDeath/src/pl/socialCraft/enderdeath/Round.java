package pl.socialCraft.enderdeath;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.socialCraft.enderdeath.timers.RoundTimer;


public class Round {
	private Team team1;
	private Team team2;
	private EnderDeath	plugin;
	private int time;
	private int	taskId;
	public Round(EnderDeath plugin){
		this.plugin = plugin;
		this.team1 = new Team(Config.getColor("team1"));
		this.team2 = new Team(Config.getColor("team2"));
		time = Config.getInt("round.time") * 60;
	}
	public Team getPlayerTeam(Player player){
		if (team1.containsPlayer(player))
			return team1;
		if (team2.containsPlayer(player))
			return team2;
		return null;
	}
	public void stop(){
		if (team1.getScore() > team2.getScore())
			Bukkit.getServer().broadcastMessage(Config.getMessage("winMessage", team1.getTeamName()));
		team1.reset();
		team2.reset();
		Bukkit.getScheduler().cancelTask(taskId);
	}
	public void start(){
		taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new RoundTimer(this), 20, Config.getInt("round.time") * 1200);
	}
	public void tick() {
		time--;
		if (time <= 0){
			stop();
		}
	}
	public int getTime(){
		return time;
	}
	public void join(Player player){
		if (team1.getSize() > team2.getSize()){
			team2.join(player);
			return ;
		}
		if (team2.getSize() > team1.getSize()){
			team1.join(player);
			return ;
		}
		if (team1.getSize() == team2.getSize()){
			team1.join(player);
			return ;
		}
	}
}
