package dev.trueeh.truestaffmode.Modules.StaffMode.Listeners;

import dev.trueeh.truestaffmode.Modules.StaffMode.Managers.StaffModeManager;
import dev.trueeh.truestaffmode.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;
import java.util.Random;

public class PatrolListener implements Listener {

    StaffModeManager staffModeManager;

    public PatrolListener(StaffModeManager staffModeManager) {
        this.staffModeManager = staffModeManager;
    }

    @EventHandler
    private void executePatrol(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (staffModeManager.isInStaffMode(player)) {
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (player.getItemInHand().getType().equals(Material.ENDER_EYE)) {
                    event.setCancelled(true);
                    if(Bukkit.getOnlinePlayers().size() <= 1){
                        player.sendMessage(ColorUtils.colorize("&6You're the only player online!"));
                        return;
                    }

                    Player target = getRandomPlayer(player);

                    player.teleport(target);
                    player.sendMessage(ColorUtils.colorize("&eNow watching &a" + target.getName()));
                }
            }
        }
    }

    private Player getRandomPlayer(Player currentPlayer){
        List<Player> onlinePlayers = (List<Player>) Bukkit.getOnlinePlayers();
        Random random = new Random();
        Player randomPlayer = null;

        while (randomPlayer == null || randomPlayer.equals(currentPlayer) || staffModeManager.isInStaffMode(randomPlayer)) {
            int randomIndex = random.nextInt(onlinePlayers.size());
            randomPlayer = onlinePlayers.get(randomIndex);
        }

        return randomPlayer;
    }

}