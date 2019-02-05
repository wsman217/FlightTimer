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

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println(plugin.messages.MSG_PLAYERSONLY);
			return true;
		} else if (sender instanceof Player) {

			Player p = (Player) sender;

			if (!(p.hasPermission("fly.item.create"))) {

				p.sendMessage(plugin.messages.MSG_NOPERMISSION);
				return true;

			} else if (args.length == 0) {

				p.sendMessage(ChatColor.AQUA + "[Fly]: " + ChatColor.BLUE + "Incorrect arguements.");
				p.sendMessage(ChatColor.AQUA + "[Fly]: " + ChatColor.BLUE + "Use /flytimer <hours>.");
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
				p.getInventory().addItem(feather);
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
