package dev.trueeh.truestaffmode.File.impl;

import dev.trueeh.truestaffmode.File.IFlatFile;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class YamlFile implements IFlatFile {

    private FileConfiguration config;
    private File file;

    private final String fileName;
    public YamlFile(final String fileName, final JavaPlugin plugin){
        this.fileName = fileName;
        create(plugin);
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    @Override
    public int getInt(String path) {
        return config.getInt(path);
    }

    @Override
    public double getDouble(String path) {
        return config.getDouble(path);
    }

    @Override
    public long getLong(String path) {
        return config.getLong(path);
    }

    @Override
    public void set(String path, Object newValue) {
        config.set(path,newValue);
    }

    @SneakyThrows
    @Override
    public void create(final JavaPlugin plugin) {
        this.file = new File(plugin.getDataFolder() , fileName);
        if(!file.exists()){
            file.getParentFile().mkdirs();
            if(plugin.getResource(fileName) != null){
                plugin.saveResource(fileName,false);
            } else {
                file.createNewFile();
            }
        }

        this.config = YamlConfiguration.loadConfiguration(file);
    }

    @SneakyThrows
    @Override
    public void save() {
        this.config.save(file);
        Bukkit.getLogger().info("File saved succesfully!");
    }

    public File getFile(){
        return file;
    }

    @Override
    public Object getBase() {
        return config;
    }

    public FileConfiguration getConfig() {
        return this.config;
    }
}
