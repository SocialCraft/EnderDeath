package pl.socialCraft.enderdeath;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {
	private static FileConfiguration	config;
	public static void init(JavaPlugin plugin){
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
		plugin.reloadConfig();
		config = plugin.getConfig();
	}
	public static int getInt(String node) {
		return config.getInt(node);
	}
	public static String getMessage(String node, String...params) {
		String message = config.getString("messages." + node);
		for (ChatColor color : ChatColor.values()) {
			message = message.replaceAll("&" + color.getChar(), color.toString());
		}
		for (int i = 0; i < params.length; i++) {
			message = message.replaceAll("{" + i + "}", params[i]);
		}
		return message;
	}
	public static ChatColor getColor(String node){
		return ChatColor.getByChar(config.getString("round." + node));
	}
	public static String getCommand(String node){
		return config.getString("commands." + node);
	}
}
