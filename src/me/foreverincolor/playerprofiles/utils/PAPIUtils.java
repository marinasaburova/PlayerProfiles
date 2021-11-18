package me.foreverincolor.playerprofiles.utils;

import org.bukkit.OfflinePlayer;

import me.clip.placeholderapi.PlaceholderAPI;

public class PAPIUtils {

	public String online;
	public String firstJoin;
	public String level;

	OfflinePlayer player;

	// Constructor, gets info for player and stores in variables
	public PAPIUtils(OfflinePlayer p) {
		player = p;

		firstJoin = "%player_first_join_date%";
		firstJoin = PlaceholderAPI.setPlaceholders(p, firstJoin);

		level = "%player_level%";
		level = PlaceholderAPI.setPlaceholders(p, level);

		onlineStatus();

	}

	// Checks online statu
	public void onlineStatus() {

		String onlineStatus = "%player_online%";
		onlineStatus = PlaceholderAPI.setPlaceholders(player, onlineStatus);

		if (onlineStatus.equalsIgnoreCase("yes")) {
			online = "&aonline";
		} else {
			online = "&cLast Played: %player_last_played_formatted%";
			online = PlaceholderAPI.setPlaceholders(player, online);
		}

	}
}
