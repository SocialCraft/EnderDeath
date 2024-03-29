package pl.socialCraft.enderdeath;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import pl.socialCraft.enderdeath.listeners.PlayerListener;

public class EnderDeath extends JavaPlugin {
	private static Round		round;
	private static EnderDeath	instance;
	
	@Override
	public void onEnable() {
		Config.init(this);
		getServer().getPluginManager().registerEvents(new PlayerListener(),
				this);
		round = new Round(this);
		instance = this;
	}
	
	public static Round getRound() {
		return round;
	}
	
	public static Plugin getInstance() {
		return instance;
	}
}
