package dev.trueeh.truestaffmode.Managers;

import dev.trueeh.truestaffmode.File.impl.YamlFile;
import dev.trueeh.truestaffmode.TrueStaff;
import dev.trueeh.truestaffmode.utils.ColorUtils;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FreezeManager {
    private final YamlFile freezeFile;
    private final FileConfiguration config;
    private final TrueStaff plugin;
    private Location freezeLocation;
    private Location lastestLocation;
    private GameMode lastestGamemode;

    List<UUID> frozenPlayers = new ArrayList<>();

    public FreezeManager(final TrueStaff plugin) {
        this.plugin = plugin;
        this.freezeFile = new YamlFile("locations/freeze.yml", plugin);
        this.config = freezeFile.getConfig();
        loadFreezeLocation();

    }

    private void loadFreezeLocation(){
        if(config.isConfigurationSection("freeze-room")){
            double x = config.getDouble("freeze-room.X");
            double y = config.getDouble("freeze-room.Y");
            double z = config.getDouble("freeze-room.Z");
            String world = config.getString("freeze-room.world");
            if(world != null){
                freezeLocation = new Location(Bukkit.getWorld(world), x,y,z);
            }
        }
    }

    @SneakyThrows
    public void setFreezeLocation(Location location){
        this.freezeLocation = location;

        config.createSection("freeze-room");

        freezeFile.set("freeze-room.X", freezeLocation.getBlockX());
        freezeFile.set("freeze-room.Y", freezeLocation.getBlockY());
        freezeFile.set("freeze-room.Z", freezeLocation.getBlockZ());
        freezeFile.set("freeze-room.world", freezeLocation.getWorld().getName());

        config.save(freezeFile.getFile());
    }

    public boolean isFreezeLocationSet() {
        return freezeLocation != null;
    }

    public boolean isFrozen (Player player){
        return frozenPlayers.contains(player.getUniqueId());
    }

    public void freezePlayer(Player player){
        if(freezeLocation == null){
            player.sendMessage(ColorUtils.colorize("&cFreeze Room is not set!"));
            return;
        }


        if(!isFrozen(player)){
            lastestGamemode = player.getGameMode();
            lastestLocation = player.getLocation();

            frozenPlayers.add(player.getUniqueId());
            player.setInvulnerable(true);

            player.teleport(freezeLocation);
            player.setGameMode(GameMode.ADVENTURE);
            player.sendMessage(ColorUtils.colorize("&4Has sido frozeado"));

        }
    }

    public void unfreezePlayer(Player player){
        if(isFrozen(player)){
            player.teleport(lastestLocation);
            player.setGameMode(lastestGamemode);

            player.setInvulnerable(false);
            player.setGameMode(GameMode.SURVIVAL);

            frozenPlayers.remove(player.getUniqueId());
            player.sendMessage(ColorUtils.colorize("&aHas sido desfrozeado"));
        }
    }

    public void toggleFreeze(Player player){
        if(isFrozen(player)){
            unfreezePlayer(player);
            return;
        }

        freezePlayer(player);
    }
    public List<UUID> getFrozenPlayers() {
        return frozenPlayers;
    }

    public Location getFreezeLocation() {
        return freezeLocation;
    }
}
