package dev.trueeh.truestaffmode.Modules.StaffMode.Listeners;

import dev.trueeh.truestaffmode.Modules.StaffMode.Managers.StaffModeManager;
import dev.trueeh.truestaffmode.TrueStaff;
import dev.trueeh.truestaffmode.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.MapInitializeEvent;

public class StaffChatListener implements Listener {

    StaffModeManager staffModeManager;

    public StaffChatListener(StaffModeManager staffModeManager) {
        this.staffModeManager = staffModeManager;
    }

    @EventHandler
    private void onStaffQuit(PlayerQuitEvent event) {
        if(!staffModeManager.isInStaffChat(event.getPlayer())) return;

        staffModeManager.toggleStaffChat(event.getPlayer());
    }

    @EventHandler
    private void onChatStaff(AsyncPlayerChatEvent event){
        if(!staffModeManager.isInStaffChat(event.getPlayer())) return;

        event.setCancelled(true);

        String msg = ColorUtils.colorize("&8&l[&6&lStaffChat&8&l] &e" +event.getPlayer().getName() + "&8 → &6" + event.getMessage());

        Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission(TrueStaff.getStaffPermission())).forEach(staff -> {
            staff.sendMessage(msg);
        });
    }

    @EventHandler
    private void onStaffChatItemClick(final PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(player.hasPermission(TrueStaff.getStaffPermission())){
            if(event.getItem() != null && event.getItem().getType() == Material.NAME_TAG){
                if(staffModeManager.isInStaffMode(player)){
                    staffModeManager.toggleStaffChat(player);
                }
            }
        }
    }
}
