package dev.trueeh.truestaffmode.commands;

import dev.trueeh.truestaffmode.gui.StaffListGui;
import dev.trueeh.truestaffmode.model.ICommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffList implements CommandExecutor, ICommand {
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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(shouldExecute(player)){
                new StaffListGui(player);
            }

        }

        return true;
    }
}
