package me.wsman217.FlyPlugin;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.wsman217.FlyPlugin.messages.Messages;

public class Main extends JavaPlugin {
	public Messages messages;

	public ArrayList<String> flying = new ArrayList<String>();
	public ArrayList<String> falling = new ArrayList<String>();

	@Override
	public void onEnable() {
		saveDefaultConfig();

		messages = new Messages(this);

		getServer().getPluginManager().registerEvents(new me.wsman217.FlyPlugin.item.FlyTimerItem(this), this);

		getCommand("fly").setExecutor(new me.wsman217.FlyPlugin.commands.FlyCommand(this));
		getCommand("flyTimer").setExecutor(new me.wsman217.FlyPlugin.commands.FlyTimer(this));

	}

	@Override
	public void onDisable() {
		System.out.println("Fly has been DISABLED");
		System.out.println("Author: wsman217");
	}

	@SuppressWarnings("deprecation")
	public void setFlightTime(int time, Player target) {

		flying.add(target.getName());
		target.setAllowFlight(true);

		time = time * 20 * 60 * 60;
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new BukkitRunnable() {
			public void run() {
				target.setAllowFlight(false);
				target.sendMessage(messages.MSG_FLIGHT_OVER);
				flying.remove(target.getName());
				falling.add(target.getName());
				negateFall(600, target);
			}
		}, time);
	}
	
	@SuppressWarnings("deprecation")
	public void negateFall(int time, Player target) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new BukkitRunnable() {
			public void run() {
				if (falling.contains(target.getName())) {
					falling.remove(target.getName());
 				}
			}
		}, time);
	}
}
