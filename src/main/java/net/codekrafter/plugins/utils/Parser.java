package net.codekrafter.plugins.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Parser {

	public static String colorparse(String s) {
		
		return ChatColor.translateAlternateColorCodes("&".toCharArray()[0], s);

	};
	
public static String strip(String s) {
		
		return ChatColor.stripColor(s);

	};
	
public static String commandparse(String s, Player p) {
	String newS = "";
	newS = s.replaceAll("(\\$\\{username\\})", p.getName());
	newS = newS.replaceAll("(\\$\\{displayname\\})", p.getDisplayName());
	newS = newS.replaceAll("(\\$\\{uuid\\})", p.getUniqueId().toString());
	return newS;
}
}
