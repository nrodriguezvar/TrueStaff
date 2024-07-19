package dev.trueeh.truestaffmode.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import javax.annotation.Nullable;
import java.util.List;

public class ItemUtils {
    public static ItemStack createItem(Material material, int amount, String name, @Nullable List<String> lore){
        ItemStack item = new ItemStack(material,amount);
        ItemMeta meta = item.getItemMeta();

        if(meta != null){
            meta.setDisplayName(name);
            if(lore != null){
                meta.setLore(lore);
                item.setItemMeta(meta);
                return item;
            }
            item.setItemMeta(meta);
        }
        return item;
    }

    public static ItemStack createSkull(Player player, int amount, String name, @Nullable List<String> lore){
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, amount);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        if(meta != null){
            meta.setOwningPlayer(player);
            meta.setDisplayName(name);
            if(lore != null){
                meta.setLore(lore);
                head.setItemMeta(meta);
                return head;
            }
            head.setItemMeta(meta);
        }
        return head;
    }
}
