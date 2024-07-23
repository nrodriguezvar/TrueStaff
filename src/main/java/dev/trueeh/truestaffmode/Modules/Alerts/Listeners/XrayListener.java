package dev.trueeh.truestaffmode.Modules.Alerts.Listeners;

import dev.trueeh.truestaffmode.Modules.Alerts.Managers.XrayManager;
import dev.trueeh.truestaffmode.TrueStaff;
import dev.trueeh.truestaffmode.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;


public class XrayListener implements Listener {

    XrayManager xrayManager;

    public XrayListener(XrayManager xrayManager) {
        this.xrayManager = xrayManager;
    }

    @EventHandler
    private void mineAlert(BlockBreakEvent event) {
        Material blockBroken = event.getBlock().getType();
        Player target = event.getPlayer();

        if(xrayManager.getMaterials().contains(blockBroken)){
            if(target.hasPermission(TrueStaff.getStaffPermission())){
                return;
            }

            if(target.getGameMode().equals(GameMode.CREATIVE)){
                return;
            }
            Bukkit.getOnlinePlayers().stream().filter(player -> xrayManager.hasAlerts(player)).forEach(staff -> {
                staff.sendMessage(ColorUtils.colorize("&8&l[&6XRay&8&l]&e&l " + target.getName() + " &6is mining &e&l" + blockBroken));
            });
        }

    }

}
