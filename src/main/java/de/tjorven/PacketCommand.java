package de.tjorven;

import io.netty.channel.ChannelPipeline;
import net.minecraft.core.BlockPos;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundOpenScreenPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.util.CraftInventoryCreator;
import org.bukkit.craftbukkit.v1_20_R1.inventory.util.CraftTileInventoryConverter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class PacketCommand implements CommandExecutor {

    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player))
            return true;
        CraftPlayer craftPlayer = (CraftPlayer) player;
        Connection connection = craftPlayer.getHandle().connection.connection;
        if (args.length == 0) {
            ChannelPipeline pipeline = connection.channel.pipeline();
            if (pipeline.get("logger_" + player.getName()) != null)
                pipeline.remove("logger_" + player.getName());
            pipeline.addBefore("packet_handler", "logger_" + player.getName(), new PacketLogger());
            player.sendMessage("Packet logger reloaded");
            return true;
        }
        if (args[0].equalsIgnoreCase("send")) {
//            ClientboundOpenScreenPacket test = new ClientboundOpenScreenPacket(Integer.parseInt(args[1]), MenuType.FURNACE, Component.literal("test"));
//            connection.send(test);
            CraftBlock block = (CraftBlock) player.getWorld().getBlockAt(0, 0, 0);
            block.setType(Material.FURNACE);
            Inventory inventory = CraftInventoryCreator.INSTANCE.createInventory(null, InventoryType.CREATIVE);
            player.openInventory(inventory);
//            craftPlayer.getHandle().openMenu(new FurnaceBlockEntity(BlockPos.ZERO, Blocks.FURNACE.defaultBlockState()));
        }
        return false;
    }
}
