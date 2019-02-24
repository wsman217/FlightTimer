package me.wsman217.FlyPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.wsman217.FlyPlugin.messages.Messages;

public class Main extends JavaPlugin {
	public Messages messages;

	public ArrayList<String> flying = new ArrayList<String>();
	public ArrayList<String> falling = new ArrayList<String>();

	private File flyFile;
	private FileConfiguration flyData;

	@Override
	public void onEnable() {
		System.out.println("FLIGHT TIMER ENABLED");
		saveDefaultConfig();

		/*try {
			getFiles();
		} catch (IOException ex) {
			log(Level.SEVERE, "[FlightTimer] Was unable to create files!");
			log(Level.INFO, "[FlightTimer] Delete flyFile file and try again");
			this.getPluginLoader().disablePlugin(this);
		}*/

		messages = new Messages(this);

		getServer().getPluginManager().registerEvents(new me.wsman217.FlyPlugin.item.FlyTimerItem(this), this);

		getCommand("fly").setExecutor(new me.wsman217.FlyPlugin.commands.FlyCommand(this));
		getCommand("flyTimer").setExecutor(new me.wsman217.FlyPlugin.commands.FlyTimer(this));

	}

	@Override
	public void onDisable() {
		//saveFlights();

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

	private void getFiles() throws IOException {
		if (!this.getDataFolder().exists())
			this.getDataFolder().mkdir();
		flyFile = new File(this.getDataFolder(), "flyFiles.yml");

		if (!flyFile.exists())
			flyFile.createNewFile();
		flyData = YamlConfiguration.loadConfiguration(flyFile);
		if (flyFile.exists()) {
			loadFlights();
		}

	}

	private void log(Level level, String message) {
		Bukkit.getLogger().log(level, message);
	}

	public void saveFlights() {
		int i = 0;
		for (String p : flying) {
			flyData.set(Integer.toString(i), p);
			i++;
		}

		flyData.set("NumberOfPlayersFlying", i);
		try {
			flyData.save(flyFile);
		} catch (IOException e) {
			log(Level.SEVERE, "[FlightTimer] Was unable to save " + flyFile.getName() + " file");
		}
	}

	public void loadFlights() {

		int playerAmount = flyData.getInt("NumberOfPlayersFlying");

		for (int i = 0; i < playerAmount; i++) {
			flying.add(flyData.getString(Integer.toString(i)));
			flyData.set(Integer.toString(i), null);
		}
		flyData.set("NumberOfPlayersFlying", null);
		try {
			flyData.save(flyFile);
		} catch (IOException e) {
			log(Level.SEVERE, "[FlightTimer] Was unable to save " + flyFile.getName() + " file");
		}
		setFlights();
	}
	
	public void setFlights() {
		for (String player : flying) {
			Player p = Bukkit.getServer().getPlayer(player);
			p.setAllowFlight(true);
		}
	}
}
