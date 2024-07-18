package dev.trueeh.truestaffmode.Listeners;

import dev.trueeh.truestaffmode.Managers.FreezeManager;
import dev.trueeh.truestaffmode.TrueStaff;
import dev.trueeh.truestaffmode.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class FreezeListener implements Listener {
    FreezeManager manager;

    public FreezeListener(FreezeManager manager){
        this.manager = manager;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        if(!manager.isFrozen(event.getPlayer())) return;

        Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission(TrueStaff.getStaffPermission())).forEach(staff -> {
            staff.sendMessage(ColorUtils.colorize("6a&l" + event.getPlayer())+ "&7 Left while frozen.");
        });
    }

    @EventHandler
    public void onFrozenChat(AsyncPlayerChatEvent event){
        if(!manager.isFrozen(event.getPlayer())) return;

        event.setCancelled(true);

        String msg = ColorUtils.colorize("&8&l[&b&lFrozenChat&8&l] &3 " + event.getPlayer().getName() + " &8→ &7" + event.getMessage());

        Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission(TrueStaff.getStaffPermission())).forEach(staff -> {
            staff.sendMessage(msg);
        });
    }
}



