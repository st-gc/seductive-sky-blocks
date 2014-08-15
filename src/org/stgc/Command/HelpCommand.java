package org.stgc.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.stgc.SeductiveSkyLands;

import java.util.List;

public class HelpCommand implements SeductiveCommandInterface
{
    private CommandDispatcher dispatcher = null;

    public HelpCommand(CommandDispatcher dispatcher)
    {
        this.dispatcher = dispatcher;
    }

    @Override
    public String getName()
    {
        return "help";
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
        return "Outputs the help for SkyLands";
    }

    @Override
    public String[] getParameters()
    {
        return null;
    }

    @Override
    public String[] getUsage()
    {
        return new String[] {"/skylands help"};
    }

    @Override
    public String getPermissionMessage()
    {
        return "You somehow don't have permissions for this.";
    }

    @Override
    public String getPermission()
    {
        return "skylands.help";
    }

    @Override
    public boolean onCommand(CommandSender sender, SeductiveSkyLands skyLands, String label, String[] args)
    {
        Player player = null;
        if(sender instanceof Player){
            player = (Player)sender;
        }
        sender.sendMessage(ChatColor.GREEN + "List of SkyLands commands");

        sender.sendMessage("----------------------");
        sender.sendMessage(ChatColor.BLUE + "/skylands");
        sender.sendMessage(ChatColor.GRAY + "The default command (alias /sl)");
        sender.sendMessage("----------------------");

        for (SeductiveCommandInterface command : this.dispatcher.getCommands().values()) {
            if (player == null || player.hasPermission(command.getPermission())) {
                for (String usage : command.getUsage()) {
                    sender.sendMessage(ChatColor.BLUE + usage);
                }
                sender.sendMessage(command.getDescription());
                sender.sendMessage("----------------------");
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, SeductiveSkyLands skyLands, String alias, String[] args)
    {
        return null;
    }
}
