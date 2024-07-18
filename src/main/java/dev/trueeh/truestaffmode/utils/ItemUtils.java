package dev.trueeh.truestaffmode.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
                return item;
            }
            item.setItemMeta(meta);
        }
        return item;
    }
}
