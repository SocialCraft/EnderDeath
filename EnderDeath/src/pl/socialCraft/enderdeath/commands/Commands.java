package pl.socialCraft.enderdeath.commands;

import java.util.HashMap;

import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Commands {
	private static HashMap<String, Command> commands;
	public static void init(){
		commands = new HashMap<String, Command>();
		registerCommand(JoinCommand.class);
		registerCommand(DebugCommand.class);
		registerCommand(StatsCommand.class);
		registerCommand(TimeCommand.class);
	}
	public static void registerCommand(Class<? extends Command> clazz){
		try {
			Command command = clazz.newInstance();
			if (commands == null)
				init();
			commands.put(command.getLabel(), command);
		}
		catch (InstantiationException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	public static void performCommand(PlayerCommandPreprocessEvent e){
		if (commands == null)
			init();
		String[] split = e.getMessage().replace("/", "").split(" ");
		if (commands.get(split[0]) != null){
			String[] args = new String[split.length-1];
			for (int i = 1; i < split.length; i++) {
				args[i-1] = split[i];
			}
			e.setCancelled(true);
			commands.get(split[0]).preprocessCommand(e.getPlayer(), split[0], args);
		}
	}
}
