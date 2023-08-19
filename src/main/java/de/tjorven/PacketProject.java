package de.tjorven;

import de.tjorven.listener.ChatListener;
import de.tjorven.listener.JoinListener;
import de.tjorven.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PacketProject extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getOnlinePlayers().forEach(Util::inject);
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new ChatListener(), this);
        pluginManager.registerEvents(new JoinListener(), this);
        getCommand("deletechatmessage").setExecutor(new DeleteChatMessageCommand());
    }
}
