package de.tjorven;

import net.minecraft.network.Connection;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

public class PacketProject extends JavaPlugin {

    @Override
    public void onEnable() {
        for (Connection connection : ((CraftServer) getServer()).getServer().getConnection().getConnections()) {
            connection.channel.pipeline().addBefore("packet_handler", "logger_" + System.currentTimeMillis(),  new PacketLogger());
            connection.channel.pipeline().addBefore("timeout", "logger_" + System.currentTimeMillis(),  new PacketLogger());
            connection.channel.pipeline().addBefore("haproxy-decoder", "logger_" + System.currentTimeMillis(),  new PacketLogger());
            connection.channel.pipeline().addBefore("legacy_query", "logger_" + System.currentTimeMillis(),  new PacketLogger());
        }
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getCommand("deletechatmessage").setExecutor(new DeleteChatMessageCommand());
        getCommand("packet").setExecutor(new PacketCommand());
    }
}
