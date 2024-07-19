package dev.trueeh.truestaffmode.Modules.Vanish.Commands;

import dev.trueeh.truestaffmode.Modules.Vanish.Managers.VanishManager;
import dev.trueeh.truestaffmode.model.ICommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class VanishCommand implements CommandExecutor, ICommand {
    VanishManager manager;
    private boolean isEnabled = true;
    public VanishCommand(VanishManager manager){
        this.manager = manager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(shouldExecute(player)){
                if(manager.isVanished(player)){
                    manager.disableVanish(player);
                } else {
                    manager.enableVanish(player);
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
