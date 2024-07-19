package dev.trueeh.truestaffmode.gui;

import dev.trueeh.truestaffmode.Modules.StaffMode.Managers.StaffModeManager;
import dev.trueeh.truestaffmode.Modules.Vanish.Managers.VanishManager;
import dev.trueeh.truestaffmode.TrueStaff;
import dev.trueeh.truestaffmode.gui.Impl.StaffListGui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleGuiManager {

    StaffModeManager staffModeManager;
    VanishManager vanishManager;

    List<SimpleGUI> simpleGUIs = new ArrayList<SimpleGUI>();

    public SimpleGuiManager() {
        registerSimpleGuis();
    }

    public void registerSimpleGuis(){
        this.simpleGUIs =
                Arrays.asList(
                new StaffListGui(TrueStaff.getInstance().getStaffModeManager(), TrueStaff.getInstance().getVanishManager())
        );
    }

    public SimpleGUI getGui(String identifier){
        return simpleGUIs.stream().filter(simpleGUI -> simpleGUI.getIdentifier().equals(identifier)).findFirst().orElse(null);
    }


}
