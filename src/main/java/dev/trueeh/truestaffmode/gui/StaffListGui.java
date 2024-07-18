package dev.trueeh.truestaffmode.gui;

import dev.trueeh.truestaffmode.Managers.StaffModeManager;
import dev.trueeh.truestaffmode.Managers.VanishManager;
import dev.trueeh.truestaffmode.TrueStaff;
import dev.trueeh.truestaffmode.utils.ColorUtils;
import dev.trueeh.truestaffmode.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class StaffListGui {

    private List<Player> staffOnline;
    private List<String> lore;
    private StaffModeManager staffModeManager;
    private VanishManager vanishManager;
    public StaffListGui(Player player){
        this.staffModeManager = staffModeManager;
    }

    public void openStaffListGui (){
        Inventory staffGui = Bukkit.createInventory(null, 27, "Staffs Online");

        staffOnline = getStaffOnline();

        int slot = 0;
        for (Player staff : staffOnline) {
            ItemStack staffHead = ItemUtils.createItem(Material.PLAYER_HEAD,1, ColorUtils.colorize("6c " + staff.getName()), getStaffLore(staff));
            staffGui.setItem(slot++, staffHead);
        }

        ItemStack glasspane = ItemUtils.createItem(Material.BLACK_STAINED_GLASS_PANE,1, " ", null);
        for(int i = 0; i<54; i++){
            if(staffGui.getItem(i) == null){
                staffGui.setItem(i, glasspane);
            }
        }

    }

    public List<Player> getStaffOnline() {
        staffOnline = new ArrayList<>();
        Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission(TrueStaff.getStaffPermission())).forEach((staff -> {
            staffOnline.add(staff);
        }));
        return staffOnline;
    }

    public List<String> getStaffLore(Player player){
        lore = new ArrayList<>();

        lore.add(ColorUtils.colorize("&eInformation"));
        lore.add(ColorUtils.colorize(""));
        lore.add(ColorUtils.colorize(staffModeManager.isInStaffMode(player) ? "&eStaffMode: &aOn" : "&eStaffMode: &cOff"));
        lore.add(ColorUtils.colorize("&eGamemode: &c" + player.getGameMode()));
        lore.add(ColorUtils.colorize(vanishManager.isVanished(player) ? "&eVanished: &atrue" : "&eVanished: &cfalse"));

        return lore;
    }

}
