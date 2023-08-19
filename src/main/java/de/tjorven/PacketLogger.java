package de.tjorven;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.base64.Base64Encoder;
import net.kyori.adventure.chat.SignedMessage;
import net.minecraft.network.chat.MessageSignature;
import net.minecraft.network.protocol.game.ClientboundDeleteChatPacket;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundChatPacket;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.codehaus.plexus.util.Base64;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class PacketLogger extends ChannelInboundHandlerAdapter {

    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    public static Map<String, MessageSignature> signatures = new HashMap<>();
    public static MessageSignature signature;

    @Override
    public void channelRead(@NotNull ChannelHandlerContext ctx, @NotNull Object msg) throws Exception {
        if (msg instanceof ServerboundChatPacket packet) {
            System.out.println(packet);
            Player cericx = Bukkit.getPlayer("Cericx_");
            ServerPlayer serverPlayer = ((CraftPlayer) cericx).getHandle();
            signature = packet.signature();
            signatures.put(new String(new Base64().encode(signature.adventure().bytes())).substring(0, 60), signature);
        }
        super.channelRead(ctx, msg);
    }
}
