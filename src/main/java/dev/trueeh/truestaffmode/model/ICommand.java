package dev.trueeh.truestaffmode.model;

import org.bukkit.entity.Player;

public interface ICommand {

    String getPermission();

    boolean isEnabled();

    void setEnabled(boolean b);

    default boolean shouldExecute(Player player){
        return player.hasPermission(getPermission()) && isEnabled();
    }
}
