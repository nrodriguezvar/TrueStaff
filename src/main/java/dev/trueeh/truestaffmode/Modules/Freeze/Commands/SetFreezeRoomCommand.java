package dev.trueeh.truestaffmode.Modules.Freeze.Commands;

import dev.trueeh.truestaffmode.Modules.Freeze.Managers.FreezeManager;
import dev.trueeh.truestaffmode.model.ICommand;
import dev.trueeh.truestaffmode.utils.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetFreezeRoomCommand implements CommandExecutor, ICommand {

    FreezeManager manager;
    private boolean isEnabled = true;
    public SetFreezeRoomCommand(FreezeManager manager){
        this.manager = manager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(shouldExecute(player)){
                if(manager.isFreezeLocationSet()){
                    player.sendMessage(ColorUtils.colorize("&bChanged freeze room location!"));
                    manager.setFreezeLocation(player.getLocation());
                } else {
                    player.sendMessage(ColorUtils.colorize("&aNew freeze room set!"));
                    manager.setFreezeLocation(player.getLocation());
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
