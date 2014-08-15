package org.stgc.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.stgc.Command.Admin.*;
import org.stgc.SeductiveSkyLands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandDispatcher implements CommandExecutor, TabCompleter
{
    private Map<String, SeductiveCommandInterface> commands = new HashMap<String, SeductiveCommandInterface>();

    private SeductiveSkyLands plugin;

    public CommandDispatcher(SeductiveSkyLands plugin)
    {
        this.plugin = plugin;

        registerCommand(new HelpCommand(this));
        registerCommand(new PrepareWorldCommand());
    }

    private void registerCommand(SeductiveCommandInterface command)
    {
        this.commands.put(command.getName(), command);
    }

    public Map<String, SeductiveCommandInterface> getCommands()
    {
        return this.commands;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        Player player = sender instanceof Player ? (Player) sender : null;

        if (args != null && args.length > 0) {
            SeductiveCommandInterface command = null;
            String[] shortArgs = null;

            if (commands.containsKey(args[0].toLowerCase())) {
                command = commands.get(args[0].toLowerCase());
            } else {
                for (SeductiveCommandInterface com : commands.values()) {
                    if (com.getAliases() != null) {
                        for (String alias : com.getAliases()) {
                            if (args[0].equalsIgnoreCase(alias)) {
                                command = com;
                                break;
                            }
                        }
                    }
                }
            }

            if (args.length > 1) {
                shortArgs = new String[args.length - 1];
                System.arraycopy(args, 1, shortArgs, 0, args.length - 1);
            }

            if(command == null) {
                return false;
            }

            if (player == null && !command.canBeConsole()) {
                sender.sendMessage(ChatColor.RED + "You must be a player to execute this command!");

                return true;
            }

            if (player == null || (command.getPermission() == null || player.hasPermission(command.getPermission()))) {
                boolean returnValue = command.onCommand(sender, null, label, shortArgs);
                if(returnValue) {
                    return true;
                }
                sender.sendMessage(ChatColor.GREEN + "------------------Command Info------------------");
                sender.sendMessage(ChatColor.BLUE + "Description: " + ChatColor.WHITE + command.getDescription());

                // Echo parameters
                if (command.getParameters() != null) {
                    String parameters = "";
                    boolean switchColor = false;
                    for (String par : command.getParameters()) {
                        parameters += (switchColor ? ChatColor.WHITE : ChatColor.GRAY) + par;
                        switchColor = !switchColor;
                    }
                    sender.sendMessage(ChatColor.BLUE + "Parameters: " + parameters);
                }

                // Echo usage
                sender.sendMessage(ChatColor.BLUE + "Usage: ");
                sender.sendMessage(command.getUsage());
                if(command.getAliases() != null){
                    String aliases = "";
                    boolean switchColor = false;
                    for(String alias : command.getAliases()){


                        aliases += (switchColor ? ChatColor.WHITE : ChatColor.GRAY) + alias;
                        switchColor = !switchColor;
                        if(!alias.equalsIgnoreCase(command.getAliases()[command.getAliases().length - 1])){
                            aliases += ChatColor.WHITE + ", ";
                        }
                    }
                    sender.sendMessage(ChatColor.BLUE + "Aliases: " + aliases);
                }

                return true;
            }

            sender.sendMessage(ChatColor.RED + command.getPermissionMessage());
            sender.sendMessage(ChatColor.RED + command.getPermission());

            return true;
        }
        sender.sendMessage(ChatColor.GREEN + "Seductive SkyLands");
        sender.sendMessage(ChatColor.GRAY + "By: " + plugin.getDescription().getAuthors().get(0));
        sender.sendMessage(ChatColor.GRAY + "Version: " + plugin.getDescription().getVersion());
        sender.sendMessage(ChatColor.GRAY + "Type /skylands help for help");

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings)
    {
        return null;
    }
}
