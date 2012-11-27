package pl.socialCraft.enderdeath.commands;

import org.bukkit.entity.Player;

import pl.socialCraft.enderdeath.Config;
import pl.socialCraft.enderdeath.EnderDeath;

public class StatsCommand extends Command {
	
	@Override
	public void performCommand(Player sender, String cmd, String[] args) {
		sender.sendMessage(EnderDeath.getRound().getFormattedScore()[0]);
		sender.sendMessage(EnderDeath.getRound().getFormattedScore()[1]);
	}
	
	@Override
	public String getLabel() {
		return Config.getCommand("stats");
	}
	
}
