package dev.trueeh.truestaffmode.Modules.Alerts.Managers;

import dev.trueeh.truestaffmode.File.FileManager;
import dev.trueeh.truestaffmode.File.impl.YamlFile;
import dev.trueeh.truestaffmode.TrueStaff;
import dev.trueeh.truestaffmode.utils.ColorUtils;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

public class XrayManager {

    FileManager fileManager;
    private TrueStaff plugin;

    List<Material> materials = new ArrayList<>();
    List<UUID> playerWithAlerts = new ArrayList<>();

    public XrayManager(TrueStaff plugin, FileManager fileManager) {
        this.plugin = plugin;
        this.fileManager = fileManager;
        loadMaterials();
    }

    private void loadMaterials() {
        final YamlFile materialFile = (YamlFile) fileManager.getFile("alert_blocks.yml");
        final FileConfiguration config = materialFile.getConfig();

        List<String> materialNames = config.getStringList("Blocks.materials");
        for (String materialName : materialNames) {
            try{
                materials.add(Material.valueOf(materialName.toUpperCase()));
            } catch (Exception e){
                Bukkit.getLogger().warning("Material " + materialName + " is not a valid material and will be ignored");
            }
        }
    }

    public void toggleAlerts(Player player){
        if(!hasAlerts(player)){
            playerWithAlerts.add(player.getUniqueId());
            player.sendMessage(ColorUtils.colorize("&8&l[&6XRay&8&l]&e Alerts on."));
            return;
        }
        player.sendMessage(ColorUtils.colorize("&8&l[&6XRay&8&l]&e Alerts off."));
        playerWithAlerts.remove(player.getUniqueId());
    }

    public List<UUID> getPlayerWithAlerts() {
        return playerWithAlerts;
    }

    public boolean hasAlerts(Player player){
        return playerWithAlerts.contains(player.getUniqueId());
    }

    public List<Material> getMaterials() {
        return materials;
    }
}
