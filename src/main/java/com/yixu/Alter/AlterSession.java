package com.yixu.Alter;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AlterSession {

    private HashMap<UUID, String> playerAlterStatus = new HashMap<>();
    ;

    private HashMap<UUID, Location> main_alter_Location = new HashMap<>();
    ;
    private HashMap<UUID, List<Location>> sub_alter_Location = new HashMap<>();
    ;

    public String getPlayerAlterStatus(UUID uuid) {
        return playerAlterStatus.get(uuid);
    }

    public void setPlayerAlterStatus(UUID uuid, String playerStatus) {
        playerAlterStatus.put(uuid, playerStatus);
    }

    public Location getMain_alter_Location(UUID uuid) {
        return main_alter_Location.get(uuid);
    }

    public void setMain_alter_Location(UUID uuid, Location mainAlterLocation) {
        main_alter_Location.put(uuid, mainAlterLocation);
    }

    public HashMap<UUID, List<Location>> getSub_alter_Location() {
        return sub_alter_Location;
    }

    public Boolean addSub_alter_Location(UUID uuid, Location subAlterLocation) {

        if (!sub_alter_Location.containsKey(uuid)) {
            ArrayList<Location> locations = new ArrayList<>();
            locations.add(subAlterLocation);
            sub_alter_Location.put(uuid, locations);
            return false;
        }

        List<Location> locations = sub_alter_Location.get(uuid);

        if (!locations.contains(subAlterLocation)) {
            locations.add(subAlterLocation);
            sub_alter_Location.put(uuid, locations);
            return false;
        }

        return true;

    }

    public void clearMain_alter_Location() {
        main_alter_Location.clear();
    }

    public void clearSub_alter_Location() {
        sub_alter_Location.clear();
    }

    public void clearPlayerAlterStatus(UUID uuid) {
        playerAlterStatus.remove(uuid);
    }
}
