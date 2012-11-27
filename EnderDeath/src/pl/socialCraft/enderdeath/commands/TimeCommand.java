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
		return String.valueOf(time / 60) + ":" + String.valueOf(time % 60);
	}
	
}
