package dev.trueeh.truestaffmode;

import dev.trueeh.truestaffmode.Modules.Alerts.Commands.XrayAlertsCommand;
import dev.trueeh.truestaffmode.Modules.Alerts.Listeners.XrayListener;
import dev.trueeh.truestaffmode.Modules.Alerts.Managers.XrayManager;
import dev.trueeh.truestaffmode.Modules.StaffMode.Commands.StaffChatCommand;
import dev.trueeh.truestaffmode.Modules.StaffMode.Listeners.PatrolListener;
import dev.trueeh.truestaffmode.Modules.StaffMode.Listeners.StaffChatListener;
import dev.trueeh.truestaffmode.Modules.StaffMode.Listeners.StaffListListener;
import dev.trueeh.truestaffmode.Modules.Freeze.Commands.FreezeCommand;
import dev.trueeh.truestaffmode.Modules.Freeze.Commands.FreezeInfo;
import dev.trueeh.truestaffmode.Modules.Freeze.Commands.SetFreezeRoomCommand;
import dev.trueeh.truestaffmode.Modules.StaffMode.Commands.StaffCommand;
import dev.trueeh.truestaffmode.Modules.StaffMode.Commands.StaffListCommand;
import dev.trueeh.truestaffmode.Modules.StaffMode.Listeners.StaffModeListener;
import dev.trueeh.truestaffmode.Modules.Vanish.Commands.VanishCommand;
import dev.trueeh.truestaffmode.File.FileManager;
import dev.trueeh.truestaffmode.Modules.Freeze.Listeners.FreezeListener;
import dev.trueeh.truestaffmode.Modules.Vanish.Listeners.VanishListener;
import dev.trueeh.truestaffmode.Modules.Freeze.Managers.FreezeManager;
import dev.trueeh.truestaffmode.Modules.StaffMode.Managers.StaffModeManager;
import dev.trueeh.truestaffmode.Modules.Vanish.Managers.VanishManager;
import dev.trueeh.truestaffmode.gui.SimpleGuiManager;
import dev.trueeh.truestaffmode.utils.ColorUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TrueStaff extends JavaPlugin {

    private static TrueStaff instance;

    private static final String STAFF_PERMISSION = "truestaff.staff";
    private static final String ADMIN_PERMISSION = "truestaff.admin";

    @Getter VanishManager vanishManager;
    @Getter StaffModeManager staffModeManager;
    @Getter FreezeManager freezeManager;
    @Getter XrayManager xrayManager;
    @Getter SimpleGuiManager simpleGuiManager;
    FileManager fileManager;

    @Override
    public void onEnable() {
        instance = this;

        this.fileManager = new FileManager(this);

        vanishManager = new VanishManager();
        staffModeManager = new StaffModeManager();
        freezeManager = new FreezeManager(this);
        xrayManager = new XrayManager(this, fileManager);
        simpleGuiManager = new SimpleGuiManager();
        fileManager = new FileManager(this);

        getCommand("v").setExecutor(new VanishCommand(vanishManager));
        getCommand("staff").setExecutor(new StaffCommand(staffModeManager));
        getCommand("freeze").setExecutor(new FreezeCommand(freezeManager));
        getCommand("setfreeze").setExecutor(new SetFreezeRoomCommand(freezeManager));
        getCommand("freezeinfo").setExecutor(new FreezeInfo(freezeManager));
        getCommand("stafflist").setExecutor(new StaffListCommand(staffModeManager, vanishManager, simpleGuiManager));
        getCommand("staffchat").setExecutor(new StaffChatCommand(staffModeManager));
        getCommand("xray").setExecutor(new XrayAlertsCommand(xrayManager));

        Bukkit.getPluginManager().registerEvents(new VanishListener(vanishManager, staffModeManager), this);
        Bukkit.getPluginManager().registerEvents(new FreezeListener(freezeManager, staffModeManager), this);
        Bukkit.getPluginManager().registerEvents(new StaffListListener(vanishManager, staffModeManager, simpleGuiManager), this);
        Bukkit.getPluginManager().registerEvents(new StaffModeListener(staffModeManager), this);
        Bukkit.getPluginManager().registerEvents(new StaffChatListener(staffModeManager), this);
        Bukkit.getPluginManager().registerEvents(new PatrolListener(staffModeManager), this);
        Bukkit.getPluginManager().registerEvents(new XrayListener(xrayManager), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerManagers(){
        //plantilla de managers
    }

    public static String getStaffPermission() {
        return STAFF_PERMISSION;
    }

    public static String getAdminPermission() {
        return ADMIN_PERMISSION;
    }

    public static TrueStaff getInstance() {
        return instance;
    }

    public static String getNoPermission(){
        return ColorUtils.colorize("&cYou dont have permission.");
    }
}
