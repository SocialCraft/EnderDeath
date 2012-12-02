package pl.socialCraft.enderdeath.commands;

import org.bukkit.entity.Player;

import pl.socialCraft.enderdeath.Config;
import pl.socialCraft.enderdeath.EnderDeath;

public class JoinCommand extends Command {
	@Override
	public void performCommand(Player sender, String cmd, String[] args) {
		if (EnderDeath.getRound().getPlayerTeam(sender) == null) {
			EnderDeath.getRound().join(sender);
			sender.sendMessage(Config.getMessage("startHelp",
					Config.getCommand("time")));
			sender.sendMessage(Config.getMessage("startHelp1",
					Config.getCommand("stats")));
		}
		else
			sender.sendMessage(Config.getMessage("hasTeam"));
	}
	
	@Override
	public String getLabel() {
		return Config.getCommand("join");
	}
}
