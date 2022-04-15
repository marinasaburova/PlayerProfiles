package me.foreverincolor.playerprofiles.files;

import java.util.UUID;

import me.foreverincolor.playerprofiles.Main;

public class FileGetter {

	// Declare any variables here
	@SuppressWarnings("unused")
	private Main plugin;
	private final DataManager file;

	// CONSTRUCTOR
	public FileGetter(Main plugin) {
		this.plugin = plugin;
		this.file = new DataManager(plugin);
	}

	// add stat to list
	public void setStat(UUID p, String stat, String value) {
		file.getConfig().set("players." + p.toString() + "." + stat, (value));
		file.saveConfig();
	}

	// unset stat
	public void removeStat(UUID p, String stat) {
		file.getConfig().set("players." + p.toString() + "." + stat, (null));
		file.saveConfig();
	}

	// get stat from list
	public String getStat(UUID p, String stat) {
		String value = null;
		if (file.getConfig().isSet("players." + p.toString() + "." + stat)) {
			value = file.getConfig().getString("players." + p.toString() + "." + stat);
		}
		return value;
	}

	public boolean exists(UUID p) {
		boolean exists = false;
		if (file.getConfig().contains("players." + p.toString())) {
			exists = true;
		}
		return exists;

	}

}
