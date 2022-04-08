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
import me.foreverincolor.playerprofiles.listeners.InventoryClickListener;
import me.foreverincolor.playerprofiles.listeners.PlayerClickListener;
import me.foreverincolor.playerprofiles.sql.MySQL;
import me.foreverincolor.playerprofiles.sql.SQLGetter;
import me.foreverincolor.playerprofiles.ui.ProfileGUI;
import me.foreverincolor.playerprofiles.utils.ConfigUtils;
import me.foreverincolor.playerprofiles.utils.Utils;

public class Main extends JavaPlugin implements Listener {

	public MySQL SQL;
	public SQLGetter data;

	// Actions on plugin enable
	@Override
	public void onEnable() {

		// loads SQL
		this.SQL = new MySQL(this);
		this.data = new SQLGetter(this);

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

		// PAPI
		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
			Bukkit.getLogger().info(Utils.chat("&c&lPlaceholderAPI IS NOT INSTALLED!"));
		}

		// Other modules
		new ConfigUtils(this);
		new ProfileCommands(this, SQL, data);
		this.getCommand("profile").setTabCompleter(new ProfileTab());
		new ProfileGUI(this, SQL, data);
		new InventoryClickListener(this);
		new PlayerClickListener(this);
		saveDefaultConfig();
	}

	// Actions to do on plugin disable
	@Override
	public void onDisable() {
		SQL.disconnect();

	}

	// Adds players when they join
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		data.createPlayer(p);	// creates data for player

	}

}
