package me.wsman217.FlyPlugin.messages;

import org.bukkit.ChatColor;
import me.wsman217.FlyPlugin.Main;

public class Messages {
	public final String MSG_PLAYERSONLY, MSG_NOPERMISSION, MSG_FLYON, MSG_FLYOFF, MSG_FLYON_OTHERS, MSG_FLYOFF_OTHERS,
			MSG_FLIGHT_OVER, FEATHER_ADDED_OTHERS, FEATHER_ADDED, PLAYER_NOT_ONLINE, COMMAND_DISABLED, FLIGHT_START, FLIGHT_ALREADY_ENABLED;

	public Messages(Main plugin) {

		MSG_PLAYERSONLY = ChatColor.translateAlternateColorCodes('&', plugin.getConfig()
				.getString("Error_Messages.Console_Error", "[Fly]: This is not to be used by the console!"));

		MSG_NOPERMISSION = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("Messages.Insufficient_Perms"));

		MSG_FLYON = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("Messages.Fly_On", "&b[Fly]: &aOn."));

		MSG_FLYON_OTHERS = ChatColor.translateAlternateColorCodes('&', plugin.getConfig()
				.getString("Messages.Fly_On_Others", "&b[Fly]: &9Flight has been enabled for %player%"));

		MSG_FLYOFF = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("Messages.Fly_Off", "&b[Fly]: &cOff."));

		MSG_FLYOFF_OTHERS = ChatColor.translateAlternateColorCodes('&', plugin.getConfig()
				.getString("Messages.Fly_Off_Others", "&b[Fly]: &9Flight has been disabled for %player%"));

		MSG_FLIGHT_OVER = ChatColor.translateAlternateColorCodes('&', plugin.getConfig()
				.getString("Messages.FlightTimerOff", "&b[Fly]: &cFlight your flight time is finished."));

		FEATHER_ADDED_OTHERS = ChatColor.translateAlternateColorCodes('&', plugin.getConfig()
				.getString("Messages.Feather_Added_Other", "&9A feather has been added to %player% inventory!"));

		FEATHER_ADDED = ChatColor.translateAlternateColorCodes('&', plugin.getConfig()
				.getString("Messages.Feather_Added_Yours", "&9A feather has been added to your inventory!"));

		PLAYER_NOT_ONLINE = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("Messages.Player_Not_Online", "&cThis player is not currently online!"));

		COMMAND_DISABLED = ChatColor.translateAlternateColorCodes('&', plugin.getConfig()
				.getString("Messages.Command_Disabled", "&cThis command has been disabled by server administrators!"));
		
		FLIGHT_START = ChatColor.translateAlternateColorCodes('&', plugin.getConfig()
				.getString("Messages.Flight_Timer_Start", "&bFlight has been enabled for %t% hours!"));
		
		FLIGHT_ALREADY_ENABLED = ChatColor.translateAlternateColorCodes('&', plugin.getConfig()
				.getString("Messages.Flight_ALREADY_ENABLED", "&cFlight has already been enabled."));
	}
}
