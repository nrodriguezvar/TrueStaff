package dev.trueeh.truestaffmode.Modules.StaffMode.Listeners;

import dev.trueeh.truestaffmode.Modules.StaffMode.Managers.StaffModeManager;
import dev.trueeh.truestaffmode.TrueStaff;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.player.*;

public class StaffModeListener implements Listener {

    StaffModeManager staffModeManager;

    public StaffModeListener(StaffModeManager staffModeManager) {
        this.staffModeManager = staffModeManager;
    }

    @EventHandler
    private void onStaffJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if(player.hasPermission(TrueStaff.getStaffPermission())){
            staffModeManager.enableStaffMode(player);
        }
    }

    @EventHandler
    private void onStaffLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(player.hasPermission(TrueStaff.getStaffPermission())){
            staffModeManager.disableStaffMode(player);
        }
    }

    @EventHandler
    private void onPickupEvent(EntityPickupItemEvent event) {
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();

            if(staffModeManager.isInStaffMode(player)) event.setCancelled(true);
        }

    }

    @EventHandler
    private void onItemDrop(PlayerDropItemEvent event) {
        if(staffModeManager.isInStaffMode(event.getPlayer())) event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void OnEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if(staffModeManager.isInStaffMode(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onBlockPlace(BlockPlaceEvent event){
        event.setCancelled(staffModeManager.isInStaffMode(event.getPlayer()));
    }

    @EventHandler
    private void onBlockBreak(BlockBreakEvent event){
        event.setCancelled(staffModeManager.isInStaffMode(event.getPlayer()));
    }

    @EventHandler
    private void onInventoryModify(InventoryCreativeEvent event) {
        Player player = (Player) event.getWhoClicked();

        if(player.hasPermission(TrueStaff.getStaffPermission())){
            if(staffModeManager.isInStaffMode(player)){
                event.setCancelled(true);
            }
        }
    }

}
