package net.a4z0.minesweeper;

import org.bukkit.Bukkit;

public enum WrappedClass {
    I_CHAT_BASE_COMPONENT("net.minecraft.server.*.IChatBaseComponent", "net.minecraft.network.chat.IChatBaseComponent"),
    I_CHAT_BASE_COMPONENT$CHAT_SERIALIZER("net.minecraft.server.*.IChatBaseComponent$ChatSerializer", "net.minecraft.network.chat.IChatBaseComponent$ChatSerializer"),

    WORLD("net.minecraft.server.*.World", "net.minecraft.world.level.World"),
    WORLD_SERVER("net.minecraft.server.*.WorldServer", "net.minecraft.server.level.WorldServer"),

    CRAFT_WORLD("org.bukkit.craftbukkit.*.CraftWorld"),
    CRAFT_PLAYER("org.bukkit.craftbukkit.*.entity.CraftPlayer"),
    CRAFT_CHAT_MESSAGE("org.bukkit.craftbukkit.*.util.CraftChatMessage"),
    CRAFT_HUMAN_ENTITY("org.bukkit.craftbukkit.*.entity.CraftHumanEntity"),

    ENTITY("net.minecraft.server.*.Entity", "net.minecraft.world.entity.Entity"),
    ENTITY_LIVING("net.minecraft.server.*.EntityLiving", "net.minecraft.world.entity.EntityLiving"),
    ENTITY_PLAYER("net.minecraft.server.*.EntityPlayer", "net.minecraft.server.level.EntityPlayer"),
    ENTITY_ARMOR_STAND("net.minecraft.server.*.EntityArmorStand", "net.minecraft.world.entity.decoration.EntityArmorStand"),

    DATA_WATCHER("net.minecraft.server.*.DataWatcher", "net.minecraft.network.syncher.DataWatcher"),

    PLAYER_CONNECTION("net.minecraft.server.*.PlayerConnection", "net.minecraft.server.network.PlayerConnection"),

    PACKET("net.minecraft.server.*.Packet", "net.minecraft.network.protocol.Packet"),

    @Until(Version.V1_18_R2)
    PACKET_PLAY_OUT_SPAWN_ENTITY_LIVING("net.minecraft.server.*.PacketPlayOutSpawnEntityLiving", "net.minecraft.network.protocol.game.PacketPlayOutSpawnEntityLiving"),

    PACKET_PLAY_OUT_SPAWN_ENTITY("net.minecraft.server.*.PacketPlayOutSpawnEntity", "net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity"),
    PACKET_PLAY_OUT_ENTITY_METADATA("net.minecraft.server.*.PacketPlayOutEntityMetadata", "net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata"),
    PACKET_PLAY_OUT_ENTITY_DESTROY("net.minecraft.server.*.PacketPlayOutEntityDestroy", "net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy");

    private final Class<?> WrappedClass;

    /**
    * Construct a {@link WrappedClass}.
    *
    * @param Classnames Names that the desired class can have.
    */

    WrappedClass(String... Classnames) {
        this.WrappedClass = WrappedUtils.getClassIfExists(Classnames);

        if(this.WrappedClass == null && Version.C(this))
            throw new NullPointerException("Couldn't find class for " + this.name());
    }

    /**
    * @return a NMS {@link Class}.
    */

    public Class<?> getNMSClass() {
        return this.WrappedClass;
    }

    /**
    * {@link WrappedClass}'s Utils.
    */

    public static class WrappedUtils {

        /**
        * @param Classnames Names that the desired class can have.
        *
        * @return the desired class if it exists.
        */

        public static Class<?> getClassIfExists(String... Classnames) {
            for(String Classname : Classnames) {
                try {
                    return Class.forName(Classname.replace("*", (Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3])));
                } catch (ClassNotFoundException ignored) {}
            }

            return null;
        }
    }
}