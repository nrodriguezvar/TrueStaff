package dev.trueeh.truestaffmode.Modules.StaffMode.Listeners;

import dev.trueeh.truestaffmode.Modules.StaffMode.Managers.StaffModeManager;
import dev.trueeh.truestaffmode.Modules.Vanish.Managers.VanishManager;
import dev.trueeh.truestaffmode.gui.Impl.StaffListGui;
import dev.trueeh.truestaffmode.gui.SimpleGuiManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class StaffListListener implements Listener {
    StaffModeManager staffModeManager;
    VanishManager vanishManager;
    SimpleGuiManager simpleGuiManager;

    public StaffListListener(VanishManager vanishManager, StaffModeManager staffModeManager, SimpleGuiManager simpleGuiManager) {
        this.vanishManager = vanishManager;
        this.staffModeManager = staffModeManager;
        this.simpleGuiManager = simpleGuiManager;
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onInventoryClick(InventoryClickEvent event) {
        if(event.getClickedInventory() == null) return;
        if(event.getView().getTitle().contains("Staff List (")){
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onInventoryDrag(InventoryDragEvent event) {
        if(event.getView().getTitle().contains("Staff List (")) {
            event.setCancelled(true);
        }
    }

}
