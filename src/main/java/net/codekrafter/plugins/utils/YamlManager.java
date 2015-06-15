
package net.codekrafter.plugins.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class YamlManager
{

	public static JavaPlugin plugin;
	public File path;
	private FileConfiguration customConfig = null;
	private File customConfigFile = null;
	private String name;

	public YamlManager(JavaPlugin plugin, String name)
	{
		YamlManager.plugin = plugin;
		this.name = name;
	}
	
	public YamlManager(JavaPlugin plugin, String name, String path, boolean relitiveToDataFolder)
	{
		YamlManager.plugin = plugin;
		this.name = name;
		if(relitiveToDataFolder) {
			this.path = new File(plugin.getDataFolder(), path);
		} else {
			this.path = new File(path);
		}
	}

	public void reloadCustomConfig()
	{
		if (customConfigFile == null)
		{
			customConfigFile = new File(path, name + ".yml");
		}

		customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
		InputStream defConfigStream = plugin.getResource(name + ".yml");
		if (defConfigStream != null)
		{
			YamlConfiguration defConfig = YamlConfiguration
					.loadConfiguration(defConfigStream);
			customConfig.setDefaults(defConfig);
		}
	}

	public FileConfiguration getCustomConfig()
	{
		if (customConfig == null)
		{
			reloadCustomConfig();
		}
		return customConfig;
	}

	public void saveCustomConfig()
	{
		if (customConfig == null || customConfigFile == null)
		{
			return;
		}
		try
		{
			getCustomConfig().save(customConfigFile);
		}
		catch (IOException ex)
		{
			plugin.getLogger().log(Level.SEVERE,
					"Could not save config to " + customConfigFile, ex);
		}
	}

	public void saveDefaultConfig()
	{
		if (customConfigFile == null)
		{
			customConfigFile = new File(path, name + ".yml");
		}
		if (!customConfigFile.exists())
		{
			plugin.saveResource(name + ".yml", false);
		}
	}

}
