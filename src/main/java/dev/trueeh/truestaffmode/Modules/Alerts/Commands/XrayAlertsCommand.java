package dev.trueeh.truestaffmode.Modules.Alerts.Commands;

import dev.trueeh.truestaffmode.Modules.Alerts.Managers.XrayManager;
import dev.trueeh.truestaffmode.model.ICommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class XrayAlertsCommand implements CommandExecutor, ICommand {

    private boolean isEnabled = true;
    private XrayManager xrayManager;

    public XrayAlertsCommand(XrayManager xrayManager) {
        this.xrayManager = xrayManager;
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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(shouldExecute(player)){
                xrayManager.toggleAlerts(player);
            }
        }
        return true;
    }
}
