package dev.trueeh.truestaffmode.Modules.StaffMode.Managers;

import dev.trueeh.truestaffmode.TrueStaff;
import dev.trueeh.truestaffmode.utils.ColorUtils;
import dev.trueeh.truestaffmode.utils.ItemUtils;
import lombok.Getter;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.util.*;
import java.util.List;

public class StaffModeManager {

    @Getter Map<Integer, ItemStack> staffItems = new HashMap<>();
    ItemStack[] inventoryItems;
    ItemUtils itemUtils = new ItemUtils();

    List<UUID> playersInStaffMode = new ArrayList<>();
    List<UUID> playersInStaffChat = new ArrayList<>();

    public void enableStaffMode(Player player){
        if(!isInStaffMode(player)){
            playersInStaffMode.add(player.getUniqueId());
            inventoryItems = player.getInventory().getContents();
            player.getInventory().clear();
            player.sendMessage(ColorUtils.colorize("&8&l[&3&lStaffMode&8&l] &3enabled"));


            staffItems.put(0, itemUtils.createItem(Material.ICE, 1, ColorUtils.colorize("&bFreeze"), null));
            staffItems.put(3, itemUtils.createItem(Material.BOOK, 1, ColorUtils.colorize("&6Stats"), null));
            staffItems.put(4, itemUtils.createItem(Material.ENDER_EYE, 1, ColorUtils.colorize("&cRandomTP"), null));
            staffItems.put(5, itemUtils.createItem(Material.ENDER_CHEST, 1, ColorUtils.colorize("&6Inventory"), null));
            staffItems.put(7, itemUtils.createItem(Material.NAME_TAG, 1, ColorUtils.colorize("&7StaffChat"), null));
            staffItems.put(8, itemUtils.createItem(Material.PAPER, 1, ColorUtils.colorize("&aVanish"), null));

            for(Map.Entry<Integer, ItemStack> entry : staffItems.entrySet()){
                player.getInventory().setItem(entry.getKey(), entry.getValue());
            }

            player.setCollidable(false);
            player.setGameMode(GameMode.CREATIVE);

        }
    }

    public void disableStaffMode(Player player){
        if(isInStaffMode(player)){
            playersInStaffMode.remove(player.getUniqueId());
            player.getInventory().clear();
            player.sendMessage(ColorUtils.colorize("&8&l[&3&lStaffMode&8&l]&3 disabled"));
            player.getInventory().setContents(inventoryItems);

            player.setCollidable(true);
            player.setGameMode(GameMode.SURVIVAL);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ColorUtils.colorize("")));
        }
    }

    public void enableStaffChat(Player player){
        playersInStaffChat.add(player.getUniqueId());
        player.sendMessage(ColorUtils.colorize("&8&l[&6&lStaffChat&8&l] &e is now active."));
    }

    public void disableStaffChat(Player player){
        playersInStaffChat.remove(player.getUniqueId());
        player.sendMessage(ColorUtils.colorize("&8&l[&6&lStaffChat&8&l] &e is not longer active."));
    }

    public void toggleStaffChat(Player player){
        if(isInStaffChat(player)){
            disableStaffChat(player);
            return;
        }
        enableStaffChat(player);
    }

    public boolean isInStaffChat(Player player){
        return playersInStaffChat.contains(player.getUniqueId());
    }

    public boolean isInStaffMode(Player player){
        return playersInStaffMode.contains(player.getUniqueId());
    }

    public int getPlayersInStaffMode(){
        return (int) Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission(TrueStaff.getStaffPermission())).count();
    }

}
