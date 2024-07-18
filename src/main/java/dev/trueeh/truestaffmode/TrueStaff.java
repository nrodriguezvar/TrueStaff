package dev.trueeh.truestaffmode;

import dev.trueeh.truestaffmode.commands.*;
import dev.trueeh.truestaffmode.File.FileManager;
import dev.trueeh.truestaffmode.Listeners.FreezeListener;
import dev.trueeh.truestaffmode.Listeners.VanishListener;
import dev.trueeh.truestaffmode.Managers.FreezeManager;
import dev.trueeh.truestaffmode.Managers.StaffModeManager;
import dev.trueeh.truestaffmode.Managers.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TrueStaff extends JavaPlugin {

    private static final String STAFF_PERMISSION = "truestaff.staff";
    private static final String ADMIN_PERMISSION = "truestaff.admin";
    VanishManager vanishManager = new VanishManager();
    StaffModeManager staffCommandManager = new StaffModeManager();
    FreezeManager freezeManager = new FreezeManager(this);
    FileManager fileManager;
    @Override
    public void onEnable() {
        getCommand("v").setExecutor(new VanishCommand(vanishManager));
        getCommand("staff").setExecutor(new StaffCommand(staffCommandManager));
        getCommand("freeze").setExecutor(new FreezeCommand(freezeManager));
        getCommand("setfreeze").setExecutor(new SetFreezeRoomCommand(freezeManager));
        getCommand("freezeinfo").setExecutor(new FreezeInfo(freezeManager));

        Bukkit.getPluginManager().registerEvents(new VanishListener(vanishManager, staffCommandManager), this);
        Bukkit.getPluginManager().registerEvents(new FreezeListener(freezeManager), this);

        this.fileManager = new FileManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static String getStaffPermission() {
        return STAFF_PERMISSION;
    }

    public static String getAdminPermission() {
        return ADMIN_PERMISSION;
    }
}
