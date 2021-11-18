package me.foreverincolor.playerprofiles.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.foreverincolor.playerprofiles.Main;

public class PlayerClickListener implements Listener {

	// Constructor
	@SuppressWarnings("unused")
	private Main plugin;

	public PlayerClickListener(Main plugin) {
		this.plugin = plugin;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	// Listens for player right clicking on another player
	@EventHandler
	public void onClick(PlayerInteractEntityEvent e) {
		Entity clicked = e.getRightClicked();

		// Weeds out all non-players.
		if (clicked instanceof Player) {
			String name = clicked.getName();
			e.getPlayer().performCommand("profile " + name);
		}

	}

}
