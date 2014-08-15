package org.stgc.Command.Admin;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.stgc.Command.SeductiveCommandInterface;
import org.stgc.SeductiveSkyLands;

import java.util.ArrayList;
import java.util.List;

public class PrepareWorldCommand implements SeductiveCommandInterface
{
    @Override
    public String getName()
    {
        return "prepare";
    }

    @Override
    public String[] getAliases()
    {
        return null;
    }

    @Override
    public Boolean canBeConsole()
    {
        return true;
    }

    @Override
    public String getDescription()
    {
        return "Prepares the given world (or the current world) for SkyLands.";
    }

    @Override
    public String[] getParameters()
    {
        return new String[]{"world"};
    }

    @Override
    public String[] getUsage()
    {
        return new String[]{"/skylands prepare <world>", "/skylands prepare"};
    }

    @Override
    public String getPermissionMessage()
    {
        return "You don't have the permissions to run this command";
    }

    @Override
    public String getPermission()
    {
        return "skylands.admin.prepare";
    }

    @Override
    public boolean onCommand(CommandSender sender, SeductiveSkyLands skyLands, String label, String[] args)
    {
        World world = null;

        if (!(sender instanceof Player)) {
            if (args.length != 1) {
                sender.sendMessage(ChatColor.RED + "You must specify a world from the console");

                return false;
            }

            world = plugin.getServer().getWorld(args[0]);
        } else {
            if (args.length == 1) {
                world = plugin.getServer().getWorld(args[0]);
            } else {
                world = ((Player) sender).getWorld();
            }
        }

        List<String> worlds = null;
        if(plugin.getConfig().contains("worlds")){
            worlds = plugin.getConfig().getStringList("worlds");
        }
        else{
            worlds = new ArrayList<String>();
        }
        worlds.add(world.getName());
        plugin.getConfig().set("worlds", worlds);
        plugin.saveConfig();


        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, SeductiveSkyLands skyLands, String alias, String[] args)
    {
        return null;
    }
}
