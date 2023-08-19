package de.tjorven;

import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.codehaus.plexus.util.Base64;

public class ChatListener implements Listener {

    @EventHandler
    public void onAsyncChat(AsyncChatEvent event) {
        TextComponent x = Component.text("");
        event.renderer(ChatRenderer.viewerUnaware((source, sourceDisplayName, message) -> x.append(Component.text("\uD83D\uDDD1", TextColor.color(187, 67, 88)).hoverEvent(HoverEvent.showText(Component.text("removes this message"))).clickEvent(ClickEvent.runCommand("/deletechatmessage " + new String(Base64.encodeBase64(event.signedMessage().signature().bytes())).substring(0, 60)))).appendSpace().append(LegacyComponentSerializer.legacy('&').deserialize("§4Owner §8| §7%s: §f%s".formatted(PlainTextComponentSerializer.plainText().serialize(sourceDisplayName), PlainTextComponentSerializer.plainText().serialize(message))))));
    }

}
