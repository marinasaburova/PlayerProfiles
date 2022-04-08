package me.foreverincolor.playerprofiles.ui;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.foreverincolor.playerprofiles.Main;
import me.foreverincolor.playerprofiles.sql.MySQL;
import me.foreverincolor.playerprofiles.sql.SQLGetter;
import me.foreverincolor.playerprofiles.utils.ConfigUtils;
import me.foreverincolor.playerprofiles.utils.PAPIUtils;
import me.foreverincolor.playerprofiles.utils.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ProfileGUI implements Listener {

	// Constructor
	@SuppressWarnings("unused")
	private static Main plugin;
	public MySQL SQL;
	public static SQLGetter data;
	public static String inventory_name = "Profile Page";
	public static UUID viewing;

	public ProfileGUI(Main plugin, MySQL SQL, SQLGetter data) {
		ProfileGUI.plugin = plugin;
		this.SQL = SQL;
		ProfileGUI.data = data;

	}

	// Creates GUI of player being viewed
	public Inventory createProfile(Player p, UUID viewing) {
		Inventory inv = Bukkit.createInventory(null, 36, inventory_name);
		ProfileGUI.viewing = viewing;
		OfflinePlayer offlineP = Bukkit.getOfflinePlayer(viewing);
		PAPIUtils papi = new PAPIUtils(offlineP); // gets Placeholder API info

		// GETTING STATS
		String online = "&a" + papi.online;
		String age = "&3" + data.getAge(viewing);
		String discord = "&3" + data.getDiscord(viewing);
		String firstJoin = "&3" + papi.firstJoin;
		String mobKills = "&3" + offlineP.getStatistic(Statistic.MOB_KILLS);
		String timePlayed = "&3" + String.format("%.2f", (offlineP.getStatistic(Statistic.PLAY_ONE_MINUTE) / 72000.00))
				+ "&3 hours";
		String deaths = "&3" + offlineP.getStatistic(Statistic.DEATHS);
		String distWalk = "&3" + (offlineP.getStatistic(Statistic.WALK_ONE_CM) / 100000) + "&3 km";
		String level = "&3" + papi.level + "&3 levels";

		// CREATING ALL ITEMS
		Utils.createSkullItem(inv, offlineP, 1, ConfigUtils.slot("name"), ConfigUtils.title("name"),
				"&3" + data.getName(viewing), online);

		if (p == offlineP) {
			Utils.createItem(inv, "age", age, "&7right click to change");
			Utils.createItem(inv, "discord", discord, "&7right click to change");
		} else {
			Utils.createItem(inv, "age", age);
			Utils.createItem(inv, "discord", discord);
		}

		Utils.createItem(inv, "first-join", firstJoin);
		Utils.createItem(inv, "mob-kills", mobKills);
		Utils.createItem(inv, "time-played", timePlayed);
		Utils.createItem(inv, "deaths", deaths);
		Utils.createItem(inv, "dist-walk", distWalk);
		Utils.createItem(inv, "level", level);

		// Sets the inventory
		inv.setContents(inv.getContents());
		return inv;
	}

	// Actions when clicking inventory items
	@SuppressWarnings("deprecation")
	public static void clicked(Player p, UUID viewing, boolean rightClick, int slot, ItemStack clicked, Inventory inv) {

		// DISCORD
		if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat(ConfigUtils.title("discord")))) {

			// Right click: change discord
			if (rightClick && p.getUniqueId().equals(viewing)) {
				p.closeInventory();
				TextComponent msg = new TextComponent(
						Utils.chat("&bUse command: &a/profile set Discord <discord-link> &bto change your link!"));
				msg.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/profile set Discord "));
				msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new ComponentBuilder(Utils.chat("&7Click to use command!")).create()));
				p.spigot().sendMessage(msg);

			} else {
				// Left click: add player
				p.closeInventory();
				TextComponent msg = new TextComponent(
						Utils.chat("&b" + data.getName(viewing) + "&b's Discord: &a" + data.getDiscord(viewing)));
				msg.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, data.getDiscord(viewing)));
				msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new ComponentBuilder(Utils.chat("&7Click to copy!")).create()));
				p.spigot().sendMessage(msg);
			}
		}

		// AGE
		if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat(ConfigUtils.title("age")))) {

			// Right click: change age
			if (rightClick && p.getUniqueId().equals(viewing)) {
				p.closeInventory();
				TextComponent msg = new TextComponent(
						Utils.chat("&bUse command: &a/profile set age <number> &bto change your age!"));
				msg.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/profile set age "));
				msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new ComponentBuilder(Utils.chat("&7Click to use command!")).create()));
				p.spigot().sendMessage(msg);
			}

		}

	}

}
