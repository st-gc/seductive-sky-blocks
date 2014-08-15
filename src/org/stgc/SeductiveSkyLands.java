package org.stgc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.stgc.Command.CommandDispatcher;

public class SeductiveSkyLands extends JavaPlugin
{
    public static SeductiveSkyLands plugin;

    @Override
    public void onEnable()
    {
        plugin = this;
        getLogger().info("Seductive SkyLands has been enabled!");

        CommandDispatcher dispatcher = new CommandDispatcher(this);
        getCommand("skylands").setExecutor(dispatcher);
        getCommand("skylands").setTabCompleter(dispatcher);
    }

    @Override
    public void onDisable()
    {
        getLogger().info("Seductive SkyLands has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

        getLogger().info("Command Fired!");
        getLogger().info(command.getName());
        getLogger().info(commandLabel);

        return true;
    }
}