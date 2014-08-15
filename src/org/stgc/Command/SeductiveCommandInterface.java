package org.stgc.Command;

import org.stgc.SeductiveSkyLands;
import java.util.List;
import org.bukkit.command.CommandSender;

public interface SeductiveCommandInterface
{
    public SeductiveSkyLands plugin = SeductiveSkyLands.plugin;

    /**
     * Returns the name of the command
     *
     * @return String
     */
    public String getName();

    /**
     * Returns an array of command aliases
     *
     * @return String[]
     */
    public String[] getAliases();

    /**
     * Returns true if this command can be run in the console
     *
     * @return Boolean
     */
    public Boolean canBeConsole();

    /**
     * Returns the description of the command
     *
     * @return String
     */
    public String getDescription();

    /**
     *
     * @return String[]
     */
    public String[] getParameters();

    /**
     * Returns the usage string
     *
     * @return String[]
     */
    public String[] getUsage();

    /**
     * Returns the error message for using not having permissions
     *
     * @return String
     */
    public String getPermissionMessage();

    /**
     * Returns the permission node
     *
     * @return String
     */
    public String getPermission();

    /**
     * @param sender CommandSender
     * @param skyLands SeductiveSkyLands
     * @param label String
     * @param args String[]
     * @return List<String>
     */
    public boolean onCommand(CommandSender sender, SeductiveSkyLands skyLands, String label, String[] args);

    /**
     * @param sender CommandSender
     * @param skyLands SeductiveSkyLands
     * @param alias String
     * @param args String[]
     * @return List<String>
     */
    public List<String> onTabComplete(CommandSender sender, SeductiveSkyLands skyLands, String alias, String[] args);
}
