package me.foreverincolor.playerprofiles.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Utils {

	// Chat color code util
	public static String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	// Create items with data from config
	public static ItemStack createItem(Inventory inv, String element, String... loreString) {
		ItemStack item;
		List<String> lore = new ArrayList<String>();

		item = new ItemStack(Material.getMaterial(ConfigGetter.item(element)), 1);

		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Utils.chat(ConfigGetter.title(element)));

		for (String s : loreString) {
			lore.add(Utils.chat(s));
		}

		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setLore(lore);
		item.setItemMeta(meta);

		inv.setItem(ConfigGetter.slot(element) - 1, item);

		return item;
	}

	// Item util
	public static ItemStack createSkullItem(Inventory inv, OfflinePlayer p, int amount, int invSlot, String displayName,
			String... loreString) {
		ItemStack item;
		List<String> lore = new ArrayList<String>();

		item = new ItemStack(Material.matchMaterial("PLAYER_HEAD"), amount);

		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwningPlayer(p);
		meta.setDisplayName(Utils.chat(displayName));

		for (String s : loreString) {
			lore.add(Utils.chat(s));
		}

		meta.setLore(lore);
		item.setItemMeta(meta);

		inv.setItem(invSlot - 1, item);

		return item;
	}

}
