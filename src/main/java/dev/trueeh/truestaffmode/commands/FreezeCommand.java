package dev.trueeh.truestaffmode.commands;

import dev.trueeh.truestaffmode.Managers.FreezeManager;
import dev.trueeh.truestaffmode.model.ICommand;
import dev.trueeh.truestaffmode.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeCommand implements CommandExecutor, ICommand {
    FreezeManager manager;
    private boolean isEnabled = true;
    public FreezeCommand(FreezeManager manager){
        this.manager = manager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(shouldExecute(player)){
                if(args.length == 0){
                    player.sendMessage(ColorUtils.colorize("&cUsage /freeze {player}"));
                    return true;
                }

                final String targetName = args[0];
                if(Bukkit.getPlayer(targetName) == null){
                    player.sendMessage(ColorUtils.colorize("&cPlayer not found!"));
                    return true;
                }

                final Player target = Bukkit.getPlayer(targetName);

                manager.toggleFreeze(target);
                player.sendMessage(manager.isFrozen(target) ? ColorUtils.colorize("&c Succesfully frozen &b&l" + targetName ) : ColorUtils.colorize("&a Succesfully unfrozen &b&l " +targetName));


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
        //Leer desde configuración
        return isEnabled;
    }

    @Override
    public void setEnabled(boolean b) {
        this.isEnabled = b;
    }
}
