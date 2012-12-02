package pl.socialCraft.enderdeath;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {
	private static FileConfiguration	config;
	
	public static void init(JavaPlugin plugin) {
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
		plugin.reloadConfig();
		config = plugin.getConfig();
	}
	
	public static int getInt(String node) {
		return config.getInt(node);
	}
	
	public static String getMessage(String node, String... params) {
		String message = config.getString("messages." + node);
		for (int i = 0; i < params.length; i++)
			if (message.contains("{" + i + "}"))
				message = message.replace("{" + i + "}", params[i]);
		for (ChatColor color : ChatColor.values())
			if (message.contains("&" + color.getChar()))
				message = message.replaceAll("&" + color.getChar(),
						color.toString());
		return message;
	}
	
	public static String getMessageWithParams(String node, String[] params) {
		String message = config.getString("messages." + node);
		for (int i = 0; i < params.length; i++)
			if (message.contains("{" + i + "}"))
				message = message.replace("{" + i + "}", params[i]);
		for (ChatColor color : ChatColor.values())
			if (message.contains("&" + color.getChar()))
				message = message.replaceAll("&" + color.getChar(),
						color.toString());
		return message;
	}
	
	public static String getError(String node, String... params) {
		String message = config.getString("errors." + node);
		for (int i = 0; i < params.length; i++)
			if (message.contains("{" + i + "}"))
				message = message.replace("{" + i + "}", params[i]);
		return ChatColor.DARK_RED + message;
	}
	
	public static ChatColor getColor(String node) {
		return ChatColor.getByChar(config.getString("round." + node));
	}
	
	public static String getCommand(String node) {
		return config.getString("commands." + node);
	}
	
	public static Location getLocation(String node, World world) {
		String[] split = config.getString("round." + node).split(" ");
		return new Location(world, Integer.parseInt(split[0]),
				Integer.parseInt(split[1]), Integer.parseInt(split[2]));
	}
	
	public static String getString(String node) {
		return config.getString(node);
	}
}
