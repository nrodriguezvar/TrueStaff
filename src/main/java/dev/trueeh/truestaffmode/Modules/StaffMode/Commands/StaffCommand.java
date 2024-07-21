package dev.trueeh.truestaffmode.Modules.StaffMode.Commands;

import dev.trueeh.truestaffmode.Modules.StaffMode.Managers.StaffModeManager;
import dev.trueeh.truestaffmode.TrueStaff;
import dev.trueeh.truestaffmode.model.ICommand;
import dev.trueeh.truestaffmode.utils.ColorUtils;
import org.bukkit.Bukkit;
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
            if(args.length == 0){
                if(shouldExecute(player)){
                    if(manager.isInStaffMode(player)){
                        manager.disableStaffMode(player);
                    } else {
                        manager.enableStaffMode(player);
                    }
                } else {
                    player.sendMessage(TrueStaff.getNoPermission());
                }
            } else {
                if(player.hasPermission(TrueStaff.getAdminPermission())){
                    String targetName = args[0];
                    Player target = Bukkit.getPlayer(targetName);

                    if(target != null){
                        if (manager.isInStaffMode(target)) {
                            manager.disableStaffMode(target);
                            player.sendMessage(ColorUtils.colorize("&8&l[&3&lStaffMode&8&l] &3disabled for player &a&l" +target.getName()));
                        } else {
                            manager.enableStaffMode(target);
                            player.sendMessage(ColorUtils.colorize("&8&l[&3&lStaffMode&8&l] &3enabled for player &a&l" + target.getName()));
                        }
                    } else {
                        player.sendMessage(ColorUtils.colorize("&cPlayer not found"));
                    }
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
