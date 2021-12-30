package me.foreverincolor.playerprofiles.utils;

import me.foreverincolor.playerprofiles.Main;

public class ConfigUtils {

	private static Main plugin;

	// Constructor
	public ConfigUtils(Main plugin) {
		ConfigUtils.plugin = plugin;

	}
	
	// Returns item type from config
	public static String item(String stat) {
		String item;

		item = plugin.getConfig().getString("gui." + stat + ".item");

		return item;
	}

	// Returns item slot from config
	public static int slot(String stat) {
		int slot;

		slot = Integer.parseInt(plugin.getConfig().getString("gui." + stat + ".slot"));

		return slot;

	}

	// Returns item display name from config
	public static String title(String stat) {
		String title;

		title = plugin.getConfig().getString("gui." + stat + ".title");

		return title;
	}
}
