package io.github.spikey84.spikeyskywarscore.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
        Player player = (Player) sender;
        if(args.length == 0) return false;
        player.teleport(new Location(Bukkit.getWorld(args[0]), 100,100,100));
        return true;
    }
}
