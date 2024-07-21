package dev.trueeh.truestaffmode.gui.Impl;

import dev.trueeh.truestaffmode.Modules.StaffMode.Managers.StaffModeManager;
import dev.trueeh.truestaffmode.Modules.Vanish.Managers.VanishManager;
import dev.trueeh.truestaffmode.TrueStaff;
import dev.trueeh.truestaffmode.gui.SimpleGUI;
import dev.trueeh.truestaffmode.utils.ColorUtils;
import dev.trueeh.truestaffmode.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StaffListGui implements SimpleGUI {

    private List<Player> staffOnline = new ArrayList<>();
    private List<String> lore;
    private StaffModeManager staffModeManager;
    private VanishManager vanishManager;
    private Inventory staffGui;

    private boolean protection = true;

    public StaffListGui(StaffModeManager staffModeManager, VanishManager vanishManager){
        this.staffModeManager = staffModeManager;
        this.vanishManager = vanishManager;
        createGui();
    }

    @Override
    public void createGui() {
        staffGui = Bukkit.createInventory(null, 27, "Staffs Online (" + staffModeManager.getPlayersInStaffMode() + ")");

        staffOnline = getStaffOnline();

        int slot = 0;
        for (Player staff : staffOnline) {
            ItemStack staffHead = ItemUtils.createSkull(staff,1, ColorUtils.colorize("&c" + staff.getName()), getStaffLore(staff));
            Bukkit.broadcastMessage(staff.getName());
            staffGui.setItem(slot++, staffHead);
        }

        ItemStack glasspane = ItemUtils.createItem(Material.BLACK_STAINED_GLASS_PANE,1, " ", null);
        for(int i = 0; i<27; i++){
            if(staffGui.getItem(i) == null){
                staffGui.setItem(i, glasspane);
            }
        }
    }

    @Override
    public void openGui(Player player) {
        player.openInventory(staffGui);
    }

    @Override
    public void updateGui(Player player) {
        player.closeInventory();
        staffGui.clear();
        createGui();
        openGui(player);
    }

    @Override
    public boolean isProtected() {
        return protection;
    }

    @Override
    public String getIdentifier() {
        return "gui.stafflist";
    }


    public List<Player> getStaffOnline() {
        return Bukkit.getOnlinePlayers().stream()
                .filter(player -> player.hasPermission(TrueStaff.getStaffPermission()))
                .collect(Collectors.toList());
    }

    public List<String> getStaffLore(Player player){
        lore = new ArrayList<>();

        lore.add(ColorUtils.colorize("&eInformation"));
        lore.add(ColorUtils.colorize(""));
        lore.add(ColorUtils.colorize(staffModeManager.isInStaffMode(player) ? "&eStaffMode: &aON" : "&eStaffMode: &cOFF"));
        lore.add(ColorUtils.colorize("&eGamemode: &c" + player.getGameMode()));
        lore.add(ColorUtils.colorize(vanishManager.isVanished(player) ? "&eVanished: &aTRUE" : "&eVanished: &cFALSE"));

        return lore;
    }
}
