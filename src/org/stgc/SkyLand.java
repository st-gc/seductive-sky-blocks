package org.stgc;

import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SkyLand
{
    private World world;

    private ArrayList<Player> players = new ArrayList<Player>();

    public SkyLand(World world)
    {
        this.world = world;
    }

    public boolean addPlayer(Player player)
    {
        return this.players.add(player);
    }

    public boolean removePlayer(Player player)
    {
        return this.players.remove(player);
    }
}
