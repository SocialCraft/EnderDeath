package pl.socialCraft.enderdeath.commands;

import org.bukkit.entity.Player;

import pl.socialCraft.enderdeath.Config;

public abstract class Command {
	
	public void preprocessCommand(Player sender, String cmd, String[] args) {
		if (getMinimumArgsLength() > args.length) {
			sender.sendMessage(Config.getError("parameters"));
			return;
		}
		if (getRequiredPermission().equalsIgnoreCase("op") && !sender.isOp()) {
			sender.sendMessage(Config.getError("permission"));
			return;
		}
		if (!sender.hasPermission(getRequiredPermission())
				&& !getRequiredPermission().equalsIgnoreCase("")) {
			sender.sendMessage(Config.getError("permission"));
			return;
		}
		performCommand(sender, cmd, args);
	}
	
	public abstract void performCommand(Player sender, String cmd, String[] args);
	
	public abstract String getLabel();
	
	public int getMinimumArgsLength() {
		return 0;
	}
	
	public String getRequiredPermission() {
		return "";
	}
}
