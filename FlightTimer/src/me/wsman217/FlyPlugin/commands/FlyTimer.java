package me.wsman217.FlyPlugin.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.wsman217.FlyPlugin.Main;

public class FlyTimer implements CommandExecutor {

	Main plugin;

	public FlyTimer(Main plugin) {
		this.plugin = plugin;
	}

	public void consoleError(CommandSender sender) {
		sender.sendMessage("&b[Fly]: &cIncorrect usage of command!\n &9Use /flytimer <hours> <player>.");
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {

			if (args.length < 2 || args.length > 2) {
				consoleError(sender);
				return true;
			}
			
			if (!isInt(args[0])) {
				consoleError(sender);
				return true;
			}
			
			if (plugin.getServer().getPlayer(args[1]) != null) {
				Player target = plugin.getServer().getPlayer(args[1]);
					int num = Integer.parseInt(args[0]);
					ItemStack feather = new ItemStack(Material.FEATHER);
					ItemMeta meta = feather.getItemMeta();
					ArrayList<String> lore = new ArrayList<String>();
					meta.setDisplayName(ChatColor.AQUA + "Feather Of Flight");
					lore.add("Right click for " + num + " hours of flight");
					meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					meta.setLore(lore);
					feather.setItemMeta(meta);
					feather.addUnsafeEnchantment(Enchantment.LUCK, 1);
					target.getInventory().addItem(feather);
					return true;
				
			} else if (plugin.getServer().getPlayer(args[1]) == null) {
				sender.sendMessage(plugin.messages.PLAYER_NOT_ONLINE.replace("%player%", args[1]));
				return true;
			}			
		} else if (sender instanceof Player) {

			Player p = (Player) sender;

			if (!(p.hasPermission("fly.item.create"))) {

				p.sendMessage(plugin.messages.MSG_NOPERMISSION.replace("%player%", p.getName()));
				return true;

			} else if (args.length == 0 || !isInt(args[0])) {

				p.sendMessage(ChatColor.AQUA + "[Fly]: " + ChatColor.BLUE + "Incorrect arguements.");
				p.sendMessage(ChatColor.AQUA + "[Fly]: " + ChatColor.BLUE + "Use /flytimer <hours> or /flytimer <hours> <player>");
				return true;

			}

			if (isInt(args[0])) {
				int num = Integer.parseInt(args[0]);
				ItemStack feather = new ItemStack(Material.FEATHER);
				ItemMeta meta = feather.getItemMeta();
				ArrayList<String> lore = new ArrayList<String>();
				meta.setDisplayName(ChatColor.AQUA + "Feather Of Flight");
				lore.add("Right click for " + num + " hours of flight");
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				meta.setLore(lore);
				feather.setItemMeta(meta);
				feather.addUnsafeEnchantment(Enchantment.LUCK, 1);
				if (args.length < 2) {
					p.sendMessage(plugin.messages.FEATHER_ADDED.replace("%player%", p.getName()));
					p.getInventory().addItem(feather);
				} else if (args.length == 2) {
					if (p.getServer().getPlayer(args[1]) != null) {
						p.sendMessage(plugin.messages.FEATHER_ADDED.replace("%player%", args[1]));
						Player target = p.getServer().getPlayer(args[1]);
						target.getInventory().addItem(feather);
					} else if (p.getServer().getPlayer(args[1]) == null) {
						p.sendMessage(plugin.messages.PLAYER_NOT_ONLINE.replace("%player%", args[1]));
					}
				}
				return true;
			}
		}
		return false;
	}

	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
