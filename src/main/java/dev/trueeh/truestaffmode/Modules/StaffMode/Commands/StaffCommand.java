package dev.trueeh.truestaffmode.Modules.StaffMode.Commands;

import dev.trueeh.truestaffmode.Modules.StaffMode.Managers.StaffModeManager;
import dev.trueeh.truestaffmode.model.ICommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffCommand implements CommandExecutor, ICommand {

    StaffModeManager manager;
    private boolean isEnabled = true;
    public StaffCommand(StaffModeManager manager){
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(shouldExecute(player)){
                if(manager.isInStaffMode(player)){
                    manager.disableStaffMode(player);
                } else {
                    manager.enableStaffMode(player);
                }
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
        return isEnabled;
    }

    @Override
    public void setEnabled(boolean b) {
        this.isEnabled = b;
    }
}
