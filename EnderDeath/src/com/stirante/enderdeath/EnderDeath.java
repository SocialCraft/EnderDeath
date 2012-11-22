package com.stirante.enderdeath;

import org.bukkit.plugin.java.JavaPlugin;

import com.stirante.enderdeath.listeners.PlayerListener;

public class EnderDeath extends JavaPlugin {
	private Round	round;

	public void onEnable(){
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		round = new Round(this);
	}
	public Round getRound(){
		return round;
	}
}
