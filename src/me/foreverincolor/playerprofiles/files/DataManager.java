package me.foreverincolor.playerprofiles.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.foreverincolor.playerprofiles.Main;

public class DataManager {

	private Main plugin;
	private FileConfiguration dataConfig = null;
	private File configFile = null;

	public DataManager(Main plugin) {
		this.plugin = plugin;

		// saves/initializes config
		saveDefaultConfig();
	}

	public void reloadConfig() {
		if (configFile == null) {
			configFile = new File(plugin.getDataFolder(), "data.yml");
		}

		dataConfig = YamlConfiguration.loadConfiguration(configFile);

		InputStream defaultStream = plugin.getResource("data.yml");
		if (defaultStream != null) {
			YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
			dataConfig.setDefaults(defaultConfig);
		}
	}

	public FileConfiguration getConfig() {
		if (dataConfig == null)
			reloadConfig();

		return dataConfig;
	}

	public void saveConfig() {
		if (dataConfig == null || configFile == null)
			return;
		try {
			getConfig().save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
			plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, e);
		}
	}

	public void saveDefaultConfig() {
		if (configFile == null)
			configFile = new File(plugin.getDataFolder(), "data.yml");
		if (!configFile.exists())
			plugin.saveResource("data.yml", false);

	}
}
