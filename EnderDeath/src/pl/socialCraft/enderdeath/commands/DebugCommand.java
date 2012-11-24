package pl.socialCraft.enderdeath.commands;

import org.bukkit.entity.Player;

import pl.socialCraft.enderdeath.Config;
import pl.socialCraft.enderdeath.EnderDeath;

public class DebugCommand extends Command {
	
	@Override
	public void performCommand(Player sender, String cmd, String[] args) {
		if (args[0].equalsIgnoreCase("command")){
			sender.sendMessage(Config.getCommand(args[1]));
		}
		if (args[0].equalsIgnoreCase("message")){
			if (args.length == 2)
				sender.sendMessage(Config.getMessage(args[1]));
			else {
				String[] params = new String[args.length - 2];
				for (int i = 2; i < args.length; i++) {
					params[i - 2] = args[i];
				}
				sender.sendMessage(Config.getMessage1(args[1], args));
			}
		}
		if (args[0].equalsIgnoreCase("error")){
			sender.sendMessage(Config.getError(args[1]));
		}
		if (args[0].equalsIgnoreCase("int")){
			sender.sendMessage(String.valueOf(Config.getInt(args[1])));
		}
		if (args[0].equalsIgnoreCase("round")){
			if (args[1].equalsIgnoreCase("time"))
				sender.sendMessage(String.valueOf(EnderDeath.getRound().getTime()));
			if (args[1].equalsIgnoreCase("isRun"))
				sender.sendMessage(String.valueOf(EnderDeath.getRound().isRunning()));
		}
	}
	
	@Override
	public String getLabel() {
		return "debug";
	}
	
	@Override
	public String getRequiredPermission(){
		return "op";
	}
	
	@Override
	public int getMinimumArgsLength(){
		return 2;
	}
}
