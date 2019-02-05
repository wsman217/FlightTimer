package me.wsman217.FlyPlugin.item;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.wsman217.FlyPlugin.Main;

public class FlyTimerItem implements Listener {

	Main plugin;

	public FlyTimerItem(Main plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerClicks(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		Action action = event.getAction();

		EquipmentSlot e = event.getHand();
		if (e.equals(EquipmentSlot.HAND)) {

			if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
				if (p.getItemInHand().getType() == Material.FEATHER) {
					ItemStack feather = p.getItemInHand();

					if (feather.hasItemMeta()) {
							if (ChatColor.stripColor(feather.getItemMeta().getDisplayName())
									.equals("Feather Of Flight")) {
								if (!plugin.flying.contains(p.getName())) {
								List<String> lore = feather.getItemMeta().getLore();
								StringBuilder sb = new StringBuilder();
								for (String s : lore) {
									sb.append(s);
									sb.append("");
								}

								String stringLore = sb.toString();
								int time = Integer.parseInt(stringLore.replaceAll("[^0-9]", ""));

								int index = p.getInventory().getHeldItemSlot();
								int removed = Math.min(1, feather.getAmount());

								if (feather.getAmount() == removed)
									p.getInventory().setItem(index, null);
								else
									feather.setAmount(feather.getAmount() - removed);

								p.updateInventory();

								plugin.setFlightTime(time, p);
								p.sendMessage(ChatColor.AQUA + "Flight has been enabled for " + time + " hours!");
							} else
								p.sendMessage("Flight has already been enabled.");
						}
					}
				}
			}
		}
	}
}
