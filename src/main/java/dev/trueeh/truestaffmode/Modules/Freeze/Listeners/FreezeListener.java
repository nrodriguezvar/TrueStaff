package dev.trueeh.truestaffmode.Modules.Freeze.Listeners;

import dev.trueeh.truestaffmode.Modules.Freeze.Managers.FreezeManager;
import dev.trueeh.truestaffmode.Modules.StaffMode.Managers.StaffModeManager;
import dev.trueeh.truestaffmode.TrueStaff;
import dev.trueeh.truestaffmode.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class FreezeListener implements Listener {
    FreezeManager freezeManager;
    StaffModeManager staffModeManager;

    public FreezeListener(FreezeManager freezeManager, StaffModeManager staffModeManager){
        this.freezeManager = freezeManager;
        this.staffModeManager = staffModeManager;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        if(!freezeManager.isFrozen(event.getPlayer())) return;

        Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission(TrueStaff.getStaffPermission())).forEach(staff -> {
            staff.sendMessage(ColorUtils.colorize("&3&l" + event.getPlayer().getName())+ "&7 Left while frozen.");
        });
    }

    @EventHandler
    public void onFrozenChat(AsyncPlayerChatEvent event){
        if(!freezeManager.isFrozen(event.getPlayer())) return;

        event.setCancelled(true);

        String msg = ColorUtils.colorize("&8&l[&b&lFrozenChat&8&l] &3 " + event.getPlayer().getName() + " &8→ &7" + event.getMessage());

        Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission(TrueStaff.getStaffPermission())).forEach(staff -> {
            staff.sendMessage(msg);
        });
    }

    @EventHandler
    public void onFreezeItemInteract(PlayerInteractEntityEvent event){
        Player player = event.getPlayer();
        if(event.getRightClicked() instanceof Player){
            Player target = (Player) event.getRightClicked();

            if(player.getInventory().getItemInMainHand().getType().equals(Material.ICE)){
                if(player.hasPermission(TrueStaff.getStaffPermission())){
                    if(staffModeManager.isInStaffMode(player)){
                        freezeManager.toggleFreeze(target);
                        player.sendMessage(freezeManager.isFrozen(target) ? ColorUtils.colorize("&c Succesfully frozen &b&l" + target.getName() ) : ColorUtils.colorize("&a Succesfully unfrozen &b&l " + target.getName()));
                    }
                }
            }
        }
    }
}



