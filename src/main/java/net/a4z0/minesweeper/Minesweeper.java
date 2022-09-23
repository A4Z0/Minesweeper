package net.a4z0.minesweeper;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

@Since(Version.V1_8_R3) public final class Minesweeper extends JavaPlugin {

    private static Minesweeper Instance;

    private final MSManager Manager = new MSManager();

    /**
    * Construct a {@link Minesweeper}.
    *
    * Useless if there is already an Instance.
    */

    public Minesweeper() {
        if(Instance != null)
            throw new IllegalAccessError("You can't construct another " + this.getClass().getSimpleName());
    }

    @Override
    public void onEnable() {
        Instance = this;

        try {
            Manager.reload();
        }catch (IOException | ClassNotFoundException | InvalidConfigurationException e) {
            this.getLogger().warning("Couldn't load required files.");
            this.getPluginLoader().disablePlugin(this);

            return;
        }

        if(!Version.C(this)) {
            this.getLogger().warning("Running version isn't supported, shutting down.");
            this.getPluginLoader().disablePlugin(this);

            return;
        }

        this.getServer().getPluginManager().registerEvents(new MSListener(), this);
    }

    @Override
    public void onDisable() {
        MSListener.TRACKING.forEach((Player, WrappedArmorStands) -> {
            for(WrappedArmorStand WrappedArmorStand : WrappedArmorStands) {
                Utils.D(Player, new WrappedPacketPlayOutEntityDestroy(WrappedArmorStand.getEntityID()));
            }
        });
    }

    /**
    * @return a {@link Minesweeper}'s Instance.
    */

    public static Minesweeper getInstance() {
        if(Instance == null)
            throw new IllegalAccessError("You can only get the Instance if the Plugin is already enabled");

        return Instance;
    }

    /**
    * @return a {@link Minesweeper}'s MSManager.
    */

    public MSManager getManager() {
        return this.Manager;
    }

    /**
    * {@link Minesweeper}'s Utils.
    */

    public static class Utils {

        /**
        * @param Block ...
        *
        * @return ...
        */

        public static long A(Block Block) {
            if(Block == null)
                throw new IllegalArgumentException("Block can't be null");

            return A(Block.getLocation());
        }

        /**
        * @param Location ...
        *
        * @return ...
        */

        public static long A(Location Location) {
            if(Location == null)
                throw new IllegalArgumentException("Location can't be null");

            return A(Location.getWorld(), Location.getBlockX(), Location.getBlockY(), Location.getBlockZ());
        }

        /**
        * @param World ...
        * @param X ...
        * @param Y ...
        * @param Z ...
        *
        * @return ...
        */

        public static long A(World World, int X, int Y, int Z) {
            if(World == null)
                throw new IllegalArgumentException("WrappedWorld can't be null");

            return ((new Random(World.getSeed())).nextLong() * ((long) (X + 1) * (Y+1) * (Z+1)));
        }

        /**
        * @param Block ...
        * @param Chance ...
        *
        * @return ...
        */

        public static boolean B(Block Block, float Chance) {
            return B(Block.getLocation(), Block.getType(), Chance);
        }

        /**
        * @param Location ...
        * @param Material ...
        * @param Chance ...
        *
        * @return ...
        */

        public static boolean B(Location Location, Material Material, float Chance) {
            return B(Location.getWorld(), Material, Chance, Location.getBlockX(), Location.getBlockY(), Location.getBlockZ());
        }

        /**
        * @param World ...
        * @param Chance ...
        * @param Material ...
        * @param X ...
        * @param Y ...
        * @param Z ...
        *
        * @return ...
        */

        public static boolean B(World World, Material Material, float Chance, int X, int Y, int Z) {
            return ((new Random(A(World, X, Y, Z))).nextFloat() < (Chance / 100)) && Material.isSolid() && !MSListener.EXPLODED_BOMBS.contains(World.getBlockAt(X, Y, Z));
        }

        /**
        * @param Block ...
        * @param Chance ...
        *
        * @return ...
        */

        public static Collection<Block> C(Block Block, float Chance) {
            return C(Block.getLocation(), Block.getType(), Chance);
        }

        /**
        * @param Location ...
        * @param Material ...
        * @param Chance ...
        *
        * @return ...
        */

        public static Collection<Block> C(Location Location, Material Material, float Chance) {
            return C(Location.getWorld(), Material, Chance, Location.getBlockX(), Location.getBlockY(), Location.getBlockZ());
        }

        /**
        * @param World ...
        * @param Chance ...
        * @param Material ...
        * @param X ...
        * @param Y ...
        * @param Z ...
        *
        * @return ...
        */

        public static Collection<Block> C(World World, Material Material, float Chance, int X, int Y, int Z) {
            final Collection<Block> Blocks = new HashSet<>();

            for(int X2 = (X -1); X2 < (X +1); X2++) {
                for(int Y2 = (Y -1); Y2 < (Y +1); Y2++) {
                    for(int Z2 = (Z -1); Z2 < (Z +1); Z2++) {
                        if(B(World, Material, Chance, X2, Y2, Z2)) Blocks.add(World.getBlockAt(X2, Y2, Z2));
                    }
                }
            }

            return Blocks;
        }

        /**
        * ...
        *
        * @param Player ...
        * @param WrappedPacket ...
        */

        public static void D(Player Player, WrappedPacket WrappedPacket) {
            if(Player == null)
                throw new IllegalArgumentException("Player can't be null");

            if(WrappedPacket == null)
                throw new IllegalArgumentException("WrappedPacket can't be null");

            WrappedPlayerConnection Connection = (new WrappedCraftPlayer(Player)).getHandle().C;

            if(Connection == null)
                throw new IllegalArgumentException("Connection can't be null");

            Connection.sendPacket(WrappedPacket);
        }

        /**
        * ...
        *
        * @param Player ...
        * @param Block ...
        * @param Map ...
        */

        public static void E(Player Player, Block Block, final Map<Player, WrappedArmorStand[]> Map) {
            if(Player == null)
                throw new NullPointerException("Player can't be null");

            if(Map == null)
                throw new IllegalArgumentException("Map can't be null");

            if(Map.get(Player) != null) {
                for(WrappedArmorStand WrappedArmorStand : Map.get(Player)) {
                    D(Player, new WrappedPacketPlayOutEntityDestroy(WrappedArmorStand.getEntityID()));
                }
            }

            Collection<WrappedArmorStand> ArmorStands = new HashSet<>();

            for(int X = (Block.getX() -1); X <= (Block.getX() +1); X++) {
                for(int Z = (Block.getZ() -1); Z <= (Block.getZ() +1); Z++) {
                    if(!Block.getType().isSolid() || Block.getX() == X && Block.getZ() == Z)
                        continue;

                    Collection<Block> Blocks =
                            C(Player.getWorld(), Block.getType(), Minesweeper.getInstance().getManager().getConfiguration().getChance(), X, Block.getY(), Z);

                    WrappedArmorStand ArmorStand = new WrappedArmorStand(Player.getWorld(), X + 0.5, Block.getY() + 1.5, Z + 0.5);

                    if(Version.V1_14_R1.N(Version.V)) {
                        ArmorStand.setCustomName(WrappedCraftChatMessage.fromStringOrNull(String.valueOf(Blocks.size())));
                    }else{
                        ArmorStand.setCustomName(String.valueOf(Blocks.size()));
                    }

                    ArmorStand.setCustomNameVisible(true);
                    ArmorStand.setInvisible(true);
                    ArmorStand.setMarker(true);

                    ArmorStands.add(ArmorStand);

                    for(WrappedPacket Packet : new WrappedPacket[]{
                            (Version.V1_19_R1.N(Version.V) ? new WrappedPacketPlayOutSpawnEntity(ArmorStand) : new WrappedPacketPlayOutSpawnEntityLiving(ArmorStand)),
                            new WrappedPacketPlayOutEntityMetadata(ArmorStand.getEntityID(), ArmorStand.getEntityData(), false)
                    }) {
                        D(Player, Packet);
                    }
                }
            }

            Map.put(Player, ArmorStands.toArray(new WrappedArmorStand[0]));
        }
    }
}