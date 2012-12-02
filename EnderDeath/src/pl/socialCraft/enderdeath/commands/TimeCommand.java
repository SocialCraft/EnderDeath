package pl.socialCraft.enderdeath.commands;

import org.bukkit.entity.Player;

import pl.socialCraft.enderdeath.Config;
import pl.socialCraft.enderdeath.EnderDeath;

public class TimeCommand extends Command {
	
	@Override
	public void performCommand(Player sender, String cmd, String[] args) {
		if (EnderDeath.getRound().getPlayerTeam(sender) != null)
			sender.sendMessage(Config.getMessage("time", formatTime(EnderDeath
					.getRound().getTime())));
	}
	
	@Override
	public String getLabel() {
		return Config.getCommand("time");
	}
	
	private String formatTime(int time) {
		String minutes = String.valueOf(time / 60);
		String seconds = String.valueOf(time % 60);
		if (minutes.length() == 1)
			minutes = "0" + minutes;
		else if (minutes.length() == 0) minutes = "00";
		if (seconds.length() == 1)
			seconds = "0" + seconds;
		else if (seconds.length() == 0) seconds = "00";
		return minutes + ":" + seconds;
	}
	
}
