package dev.trueeh.truestaffmode.commands;

import dev.trueeh.truestaffmode.Managers.FreezeManager;
import dev.trueeh.truestaffmode.model.ICommand;
import dev.trueeh.truestaffmode.utils.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeInfo implements CommandExecutor, ICommand {
    FreezeManager manager;
    private boolean isEnabled = true;

    public FreezeInfo(FreezeManager manager){
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(shouldExecute(player)){
                if(manager.isFreezeLocationSet()){
                    String x = String.valueOf(manager.getFreezeLocation().getBlockX());
                    String y = String.valueOf(manager.getFreezeLocation().getBlockY());
                    String z = String.valueOf(manager.getFreezeLocation().getBlockZ());
                    String world = manager.getFreezeLocation().getWorld().getName();

                    player.sendMessage(ColorUtils.colorize("&c Freeze room is on X: &f" + x + " &cY: &f" + y + " &cZ: &f" + z + " &cWorld: &f" + world));
                } else {
                    player.sendMessage(ColorUtils.colorize("&c Freeze room is not set!"));
                }
            }
        }
        return true;
    }

    @Override
    public String getPermission() {
        return "truestaff.admin";
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
