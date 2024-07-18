package dev.trueeh.truestaffmode.File;

import org.bukkit.plugin.java.JavaPlugin;

public interface IFlatFile extends IFile{

    void create(final JavaPlugin plugin);

    void save();

    Object getBase();
}
