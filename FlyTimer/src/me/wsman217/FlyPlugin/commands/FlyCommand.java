package me.wsman217.FlyPlugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.wsman217.FlyPlugin.Main;

public class FlyCommand implements CommandExecutor {

	Main plugin;

	public FlyCommand(Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {

			System.out.println(plugin.messages.MSG_PLAYERSONLY);
			return true;

		} else if (sender instanceof Player) {
			Player p = (Player) sender;
			if (plugin.getConfig().getBoolean("Commands.FlyCommandEnable")) {

				if (!(p.hasPermission("fly.command.use"))) {

					p.sendMessage(plugin.messages.MSG_NOPERMISSION);
					return true;

				} else if (!(args.length == 0)) {

					p.sendMessage(
							ChatColor.AQUA + "[Fly]: " + ChatColor.BLUE + "Incorrect arguement: " + args[0] + ".");
					p.sendMessage(ChatColor.AQUA + "[Fly]: " + ChatColor.BLUE + "Use /fly instead.");
					return true;

				}

				if (p.getAllowFlight() == false) {

					p.setAllowFlight(true);
					p.sendMessage(plugin.messages.MSG_FLYON);
					if (!plugin.flying.contains(p.getName())) {
						plugin.flying.add(p.getName());
					}
					return true;

				} else if (p.getAllowFlight() == true) {

					p.setAllowFlight(false);
					p.sendMessage(plugin.messages.MSG_FLYOFF);

					if (plugin.flying.contains(p.getName())) {
						plugin.flying.remove(p.getName());
					}
					return true;

				}
			} else if (!plugin.getConfig().getBoolean("Commands.FlyCommandEnabled")) {
				p.sendMessage(ChatColor.AQUA + "[Fly]: " + ChatColor.RED + "This command has been disabled.");
				return true;
			}
		}
		return false;
	}
}
