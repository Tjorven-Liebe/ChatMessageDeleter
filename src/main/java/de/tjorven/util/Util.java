package de.tjorven.util;

import io.netty.channel.ChannelPipeline;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Util {

    public static void inject(Player player) {
        ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().connection.connection.channel.pipeline();
        if (pipeline.get("chat_" + player.getName()) != null) pipeline.remove("chat_" + player.getName());
        pipeline.addBefore("packet_handler", "chat_" + player.getName(), new PacketLogger());
    }

}
