package de.tjorven.util;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.minecraft.network.chat.MessageSignature;
import net.minecraft.network.protocol.game.ServerboundChatPacket;
import org.codehaus.plexus.util.Base64;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class PacketLogger extends ChannelInboundHandlerAdapter {

    public static Map<String, MessageSignature> signatures = new HashMap<>();

    @Override
    public void channelRead(@NotNull ChannelHandlerContext ctx, @NotNull Object msg) throws Exception {
        if (msg instanceof ServerboundChatPacket packet) {
            MessageSignature signature = packet.signature();
            assert signature != null : "Chat message signature is null";
            signatures.put(new String(new Base64().encode(signature.adventure().bytes())).substring(0, 60), signature);
        }
        super.channelRead(ctx, msg);
    }
}
