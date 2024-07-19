package dev.trueeh.truestaffmode.Modules.Vanish.Managers;

import dev.trueeh.truestaffmode.TrueStaff;
import dev.trueeh.truestaffmode.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VanishManager {
    List<UUID> vanishedPlayers = new ArrayList<>();
    public void enableVanish(Player player){
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if(!onlinePlayer.hasPermission(TrueStaff.getStaffPermission())){
                onlinePlayer.hidePlayer(player);
            }
        }
        vanishedPlayers.add(player.getUniqueId());
        player.sendMessage(ColorUtils.colorize("&8&l[&f&lVanish&8&l] &7Activado"));
    }

    public void disableVanish(Player player){
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.showPlayer(player);
        }
        vanishedPlayers.remove(player.getUniqueId());
        player.sendMessage(ColorUtils.colorize("&8&l[&f&lVanish&8&l] &7Desactivado"));
    }

    public boolean isVanished(Player player){
        return vanishedPlayers.contains(player.getUniqueId());
    }

    public List<UUID> getVanishedPlayers() {
        return vanishedPlayers;
    }
}
