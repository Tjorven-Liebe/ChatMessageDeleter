package de.tjorven;

import de.tjorven.util.PacketLogger;
import net.minecraft.network.chat.MessageSignature;
import net.minecraft.network.protocol.game.ClientboundDeleteChatPacket;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DeleteChatMessageCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Bukkit.getOnlinePlayers().forEach(online -> ((CraftPlayer) online).getHandle().connection.send(new ClientboundDeleteChatPacket(new MessageSignature.Packed(PacketLogger.signatures.get(args[0])))));
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
