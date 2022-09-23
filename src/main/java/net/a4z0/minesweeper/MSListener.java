package net.a4z0.minesweeper;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.*;

public class MSListener implements Listener {

    public static final Map<Player, WrappedArmorStand[]> TRACKING = new HashMap<>();
    public static final Collection<Block> EXPLODED_BOMBS = new HashSet<>();

    /*
    * Listen to Player's movements.
    */

    @EventHandler private void Movement(PlayerMoveEvent e) {

        Location From = e.getFrom();
        Location To = e.getTo();

        if(To == null || !(From.getBlockX() != To.getBlockX() || From.getBlockY() != To.getBlockY() || From.getBlockZ() != To.getBlockZ())) return;

        Player Player = e.getPlayer();
        Block Block = To.clone().subtract(0, 1, 0).getBlock();

        Minesweeper.Utils.E(Player, Block, TRACKING);

        if(!Minesweeper.Utils.B(Block, Minesweeper.getInstance().getManager().getConfiguration().getChance())) return;

        TNTPrimed TNT = Player.getWorld().spawn(To, TNTPrimed.class);
        TNT.setFuseTicks(0);

        EXPLODED_BOMBS.add(Block);
    }
}