package dev.trueeh.truestaffmode.File;

import dev.trueeh.truestaffmode.File.impl.YamlFile;
import dev.trueeh.truestaffmode.TrueStaff;

import java.util.Arrays;
import java.util.List;

public class FileManager {
    List<IFile> loadedFiles;

    public FileManager(final TrueStaff trueStaffMode){
        this.loadedFiles = Arrays.asList(
                new YamlFile("locations/freeze.yml", trueStaffMode),
                new YamlFile("alert_blocks.yml", trueStaffMode)
        );
    }

    public IFile getFile(final String identifier){
        for(final IFile iFile : loadedFiles){
            if(iFile.getFileName().equals(identifier)){
                return iFile;
            }
        }

        return null;
    }

}
