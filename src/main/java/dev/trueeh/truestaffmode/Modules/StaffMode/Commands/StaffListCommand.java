package dev.trueeh.truestaffmode.Modules.StaffMode.Commands;

import dev.trueeh.truestaffmode.TrueStaff;
import dev.trueeh.truestaffmode.gui.Impl.StaffListGui;
import dev.trueeh.truestaffmode.Modules.Vanish.Managers.VanishManager;
import dev.trueeh.truestaffmode.Modules.StaffMode.Managers.StaffModeManager;
import dev.trueeh.truestaffmode.gui.SimpleGuiManager;
import dev.trueeh.truestaffmode.model.ICommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffListCommand implements CommandExecutor, ICommand {

    StaffModeManager staffModeManager;
    VanishManager vanishManager;
    SimpleGuiManager simpleGuiManager;

    public StaffListCommand(StaffModeManager staffModeManager, VanishManager vanishManager, SimpleGuiManager simpleGuiManager) {
        this.staffModeManager = staffModeManager;
        this.vanishManager = vanishManager;
        this.simpleGuiManager = simpleGuiManager;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(shouldExecute(player)){
                new StaffListGui(staffModeManager, vanishManager).openGui(player);
            } else {
                player.sendMessage(TrueStaff.getNoPermission());
            }
        }
        return true;
    }

    @Override
    public String getPermission() {
        return "truestaff.staff";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setEnabled(boolean b) {

    }
}
