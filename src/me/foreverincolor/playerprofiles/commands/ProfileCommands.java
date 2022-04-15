package me.foreverincolor.playerprofiles.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.foreverincolor.playerprofiles.Main;
import me.foreverincolor.playerprofiles.files.FileCoordinator;
import me.foreverincolor.playerprofiles.gui.ProfileGUI;
import me.foreverincolor.playerprofiles.utils.Utils;

public class ProfileCommands implements CommandExecutor {

	private Main plugin;
	private static FileCoordinator file;

	// Constructors
	public ProfileCommands(Main plugin) {
		this.plugin = plugin;
		file = new FileCoordinator(plugin);

		plugin.getCommand("profile").setExecutor(this);
	}

	// Different actions based on commands
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		Player p = (Player) sender;

		// *****************************
		// PROFILE COMMAND
		// *****************************
		if (label.equalsIgnoreCase("profile")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Only players may execute this command!");
				return true;
			}

			// View your own GUI
			if (args.length == 0) {
				ProfileGUI inv = new ProfileGUI(plugin);
				p.openInventory(inv.createProfile(p, p.getUniqueId()));

				return true;
			}

			// All set commands
			if (args[0].equalsIgnoreCase("set")) {

				if (args.length < 2) {
					p.sendMessage(Utils.chat("&cUsage: /profile set (discord | age) <value>"));
					return true;
				}

				// SET AGE
				if (args[1].equalsIgnoreCase("age")) {
					if (args.length != 3) {
						p.sendMessage(Utils.chat("&cUsage: /profile set age <number>"));
						return true;
					}

					try {
						int age = Integer.parseUnsignedInt(args[2]);
						file.setStat(p.getUniqueId(), "age", "" + age);
						p.sendMessage(Utils.chat("&aAge set to " + age + "&a!"));
					} catch (NumberFormatException e) {
						p.sendMessage(Utils.chat("&cPlease type in a valid age."));
					}

					return true;
				}

				// SET DISCORD LINK
				if (args[1].equalsIgnoreCase("discord")) {
					if (args.length != 3) {
						p.sendMessage(Utils.chat("&cUsage: /profile set Discord <discord-tag>"));
						return true;
					}
					file.setStat(p.getUniqueId(), "discord", args[2]);
					p.sendMessage(Utils.chat("&aDiscord set to " + args[2] + "&a!"));
					return true;
				}
			}

			// All remove commands
			if (args[0].equalsIgnoreCase("remove")) {

				if (args.length != 2) {
					p.sendMessage(Utils.chat("&cUsage: /profile remove (discord | age)"));
					return true;
				}

				// REMOVE DISCORD LINK
				if (args[1].equalsIgnoreCase("discord")) {
					file.removeStat(p.getUniqueId(), "discord");
					p.sendMessage(Utils.chat("&aYour Discord was removed."));
					return true;
				}

				// REMOVE AGE
				if (args[1].equalsIgnoreCase("age")) {
					file.removeStat(p.getUniqueId(), "age");
					p.sendMessage(Utils.chat("&aYour age was removed."));
					return true;
				}

			}

			// View others' GUI
			try {
				UUID uuid = Bukkit.getPlayer(args[0]).getUniqueId();
				if (file.exists(uuid)) {

					ProfileGUI inv = new ProfileGUI(plugin);
					p.openInventory(inv.createProfile(p, uuid));

					return true;
				}

			} catch (Exception e) {
				p.sendMessage("Player does not exist!");
				return true;
			}

			p.sendMessage(Utils.chat("&cThe command was not recognized"));
			return true;
		}

		return false;
	}

}
