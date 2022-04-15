// PlayerProfiles.jar
// Created by Marina Saburova (aka ForeverInColor) 
// Nov 17, 2021

package me.foreverincolor.playerprofiles;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.foreverincolor.playerprofiles.commands.ProfileCommands;
import me.foreverincolor.playerprofiles.commands.ProfileTab;
import me.foreverincolor.playerprofiles.files.FileGetter;
import me.foreverincolor.playerprofiles.files.DataManager;
import me.foreverincolor.playerprofiles.gui.ProfileGUI;
import me.foreverincolor.playerprofiles.listeners.InventoryClickListener;
import me.foreverincolor.playerprofiles.listeners.PlayerClickListener;
import me.foreverincolor.playerprofiles.sql.MySQL;
import me.foreverincolor.playerprofiles.sql.SQLGetter;
import me.foreverincolor.playerprofiles.utils.ConfigGetter;
import me.foreverincolor.playerprofiles.utils.Utils;

public class Main extends JavaPlugin implements Listener {

	public MySQL SQL;
	public SQLGetter data;
	public ConfigGetter config;

	// Actions on plugin enable
	@Override
	public void onEnable() {

		// loads Config
		config = new ConfigGetter(this);

		if (config.usingSQL()) {

			// loads SQL
			SQL = new MySQL(this);
			data = new SQLGetter(this);

			try {
				SQL.connect();
			} catch (ClassNotFoundException | SQLException e) {
				Bukkit.getLogger().info(Utils.chat("&c&lDatabase is not connected"));
			}

			// Make sure database is connected
			if (SQL.isConnected()) {
				Bukkit.getLogger().info(Utils.chat("&bDatabase is connected!"));
				data.createTable();
				this.getServer().getPluginManager().registerEvents(this, this);
			}
		} else {
			new DataManager(this);
			new FileGetter(this);
			Bukkit.getLogger().info(Utils.chat("&bUsing flatfile."));
		}

		// PAPI
		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
			Bukkit.getLogger().info(Utils.chat("&c&lPlaceholderAPI IS NOT INSTALLED!"));
		}

		// Other modules
		new ProfileCommands(this);
		this.getCommand("profile").setTabCompleter(new ProfileTab());
		new ProfileGUI(this);
		new InventoryClickListener(this);
		new PlayerClickListener(this);

		saveDefaultConfig();
	}

	// Actions to do on plugin disable
	@Override
	public void onDisable() {
		if (config.usingSQL()) {
			SQL.disconnect();
		}
	}

	// Adds players when they join
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		data.createPlayer(p); // creates data for player

	}

}
