package dev.trueeh.truestaffmode.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public interface SimpleGUI {

    void createGui();

    void openGui(Player player);

    void updateGui(Player player);

    boolean isProtected();


    String getIdentifier();


}
