package dev.trueeh.truestaffmode.Managers;

import dev.trueeh.truestaffmode.utils.ColorUtils;
import dev.trueeh.truestaffmode.utils.ItemUtils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class StaffModeManager {

    Map<Integer, ItemStack> staffItems = new HashMap<>();
    ItemStack[] inventoryItems;
    ItemUtils itemUtils = new ItemUtils();

    List<UUID> playersInStaffMode = new ArrayList<>();

    public void enableStaffMode(Player player){
        if(!isInStaffMode(player)){
            playersInStaffMode.add(player.getUniqueId());
            inventoryItems = player.getInventory().getContents();
            player.getInventory().clear();
            player.sendMessage(ColorUtils.colorize("&aStaffMode activado"));

            staffItems.put(0, itemUtils.createItem(Material.ICE, 1, ColorUtils.colorize("&bFreeze"), null));
            staffItems.put(1, itemUtils.createItem(Material.WOODEN_AXE, 1, ColorUtils.colorize("&CWorld Edit"), null));
            staffItems.put(3, itemUtils.createItem(Material.BOOK, 1, ColorUtils.colorize("&6Stats"), null));
            staffItems.put(4, itemUtils.createItem(Material.COMPASS, 1, ColorUtils.colorize("&cRandomTP"), null));
            staffItems.put(5, itemUtils.createItem(Material.CHEST, 1, ColorUtils.colorize("&6Inventory"), null));
            staffItems.put(7, itemUtils.createItem(Material.MAP, 1, ColorUtils.colorize("&7StaffChat"), null));
            staffItems.put(8, itemUtils.createItem(Material.PAPER, 1, ColorUtils.colorize("&aVanish"), null));

            for(Map.Entry<Integer, ItemStack> entry : staffItems.entrySet()){
                player.getInventory().setItem(entry.getKey(), entry.getValue());
            }

            player.setGameMode(GameMode.CREATIVE);
        }
    }

    public void disableStaffMode(Player player){
        if(isInStaffMode(player)){
            playersInStaffMode.remove(player.getUniqueId());
            player.getInventory().clear();
            player.sendMessage(ColorUtils.colorize("&cStaffMode desactivado"));
            player.getInventory().setContents(inventoryItems);

            player.setGameMode(GameMode.SURVIVAL);
        }
    }

    public boolean isInStaffMode(Player player){
        return playersInStaffMode.contains(player.getUniqueId());
    }



}
