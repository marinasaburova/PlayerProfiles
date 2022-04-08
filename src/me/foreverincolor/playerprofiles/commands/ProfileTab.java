package me.foreverincolor.playerprofiles.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class ProfileTab implements TabCompleter {

	List<String> arguments = new ArrayList<String>();
	List<String> argumentsSet = new ArrayList<String>();

	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (arguments.isEmpty()) {
			arguments.add("set");
			arguments.add("remove");
		}

		if (argumentsSet.isEmpty()) {
			argumentsSet.add("age");
			argumentsSet.add("discord");
		}

		List<String> result = new ArrayList<String>();

		if (args.length == 1) {
			for (String a : arguments) {
				if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
					result.add(a);
				}
			}
			return result;
		}

		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("remove")) {
				for (String a : argumentsSet) {
					if (a.toLowerCase().startsWith(args[1].toLowerCase())) {
						result.add(a);
					}
				}
				return result;
			}
		}

		return null;
	}

}
