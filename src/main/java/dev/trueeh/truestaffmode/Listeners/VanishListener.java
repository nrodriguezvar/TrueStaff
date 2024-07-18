package dev.trueeh.truestaffmode.Listeners;

import dev.trueeh.truestaffmode.Managers.StaffModeManager;
import dev.trueeh.truestaffmode.Managers.VanishManager;
import dev.trueeh.truestaffmode.TrueStaff;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class VanishListener implements Listener {
    VanishManager vanishManager;
    StaffModeManager staffCommandManager;

    public VanishListener(VanishManager manager, StaffModeManager staffCommandManager){
        this.vanishManager = manager;
        this.staffCommandManager = staffCommandManager;
    }

    @EventHandler
    private void handleJoin(final PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(player.hasPermission(TrueStaff.getStaffPermission())) return;

        for (UUID vanishedPlayer : vanishManager.getVanishedPlayers()) {
            player.hidePlayer(Bukkit.getPlayer(vanishedPlayer));
        }
    }

    @EventHandler
    private void handleDisconnect(final PlayerQuitEvent event){
        Player player  = event.getPlayer();

        if(player.hasPermission(TrueStaff.getStaffPermission())){
            if(vanishManager.isVanished(player)){
                vanishManager.getVanishedPlayers().remove(player);
            }
        }
    }

    @EventHandler
    private void onVanishItemClick(final PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(player.hasPermission(TrueStaff.getStaffPermission())){
            if(event.getItem() != null && event.getItem().getType() == Material.PAPER) {
                if(staffCommandManager.isInStaffMode(player)){
                    if(vanishManager.isVanished(player)){
                        vanishManager.disableVanish(player);

                    } else {
                        vanishManager.enableVanish(player);
                    }
                }
            }
        }
    }
}
