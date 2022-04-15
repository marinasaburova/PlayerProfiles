package me.foreverincolor.playerprofiles.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.foreverincolor.playerprofiles.Main;
import me.foreverincolor.playerprofiles.gui.ProfileGUI;

public class InventoryClickListener implements Listener {

	// Constructor
	@SuppressWarnings("unused")
	private Main plugin;

	public InventoryClickListener(Main plugin) {
		this.plugin = plugin;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	// Listens for player clicks in profile GUI
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		String title = e.getView().getTitle();
		
		if (title.contentEquals(ProfileGUI.inventory_name)) {
			e.setCancelled(true);

			if (e.getCurrentItem() == null) {
				return;
			}

			if (title.equals(ProfileGUI.inventory_name)) {
				ProfileGUI.clicked((Player) e.getWhoClicked(), ProfileGUI.viewing, e.isRightClick(), e.getSlot(),
						e.getCurrentItem(), e.getInventory());
			}

		}

	}

}
