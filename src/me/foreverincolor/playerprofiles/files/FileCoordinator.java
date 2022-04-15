package me.foreverincolor.playerprofiles.files;

import java.util.UUID;

import me.foreverincolor.playerprofiles.Main;
import me.foreverincolor.playerprofiles.sql.SQLGetter;
import me.foreverincolor.playerprofiles.utils.ConfigGetter;

public class FileCoordinator {

	// Declare any variables here
	@SuppressWarnings("unused")
	private Main plugin;
	private final ConfigGetter config;
	private final FileGetter file;
	private final SQLGetter sql;

	private boolean usingSQL;

	// CONSTRUCTOR
	public FileCoordinator(Main plugin) {
		this.plugin = plugin;

		config = new ConfigGetter(plugin);
		file = new FileGetter(plugin);
		sql = new SQLGetter(plugin);

		usingSQL = config.usingSQL();
	}

	// add stat to list
	public void setStat(UUID p, String stat, String value) {
		if (usingSQL) {
			sql.setStat(p, stat, value);
		} else {
			file.setStat(p, stat, value);
		}
	}

	// unset stat
	public void removeStat(UUID p, String stat) {
		if (usingSQL) {
			sql.removeStat(p, stat);
		} else {
			file.removeStat(p, stat);
		}
	}

	// get stat from list
	public String getStat(UUID p, String stat) {
		String value;
		if (usingSQL) {
			value = sql.getStat(p, stat);
		} else {
			value = file.getStat(p, stat);
		}
		return value;
	}

	public boolean exists(UUID p) {
		boolean value;
		if (usingSQL) {
			value = sql.exists(p);
		} else {
			value = file.exists(p);
		}
		return value;

	}

}
