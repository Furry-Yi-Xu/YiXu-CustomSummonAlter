package com.yixu.Alter;

import org.bukkit.Location;

import java.util.*;

public class AlterSessionManager {

    private final Map<UUID, String> playerAlterStatus = new HashMap<>();
    private final Map<UUID, Location> mainAlterLocation = new HashMap<>();
    private final Map<UUID, List<Location>> subAlterLocations = new HashMap<>();

    public Location getMainAlterLocation(UUID uuid) {
        return mainAlterLocation.get(uuid);
    }

    public void addMainAlterLocation(UUID uuid, Location location) {
        mainAlterLocation.put(uuid, location);
    }

    public List<Location> getSubAlterLocations(UUID uuid) {
        return subAlterLocations.get(uuid);
    }

    public boolean addSubAlterLocation(UUID uuid, Location location) {

        if (!subAlterLocations.containsKey(uuid)) {
            ArrayList<Location> SubAlterLocation = new ArrayList<>();
            SubAlterLocation.add(location);
            subAlterLocations.put(uuid, new ArrayList<>(SubAlterLocation));
            return false;
        }

        List<Location> SubAlterLocation = subAlterLocations.get(uuid);

        if (!SubAlterLocation.contains(location)) {
            SubAlterLocation.add(location);
            subAlterLocations.put(uuid, SubAlterLocation);
            return false;
        }

        return true;
    }

    public String getPlayerAlterStatus(UUID uuid) {
        return playerAlterStatus.get(uuid);
    }

    public void setPlayerAlterStatus(UUID uuid, String status) {
        playerAlterStatus.put(uuid, status);
    }

    public void clearMainAlterLocations(UUID uuid) {
        mainAlterLocation.remove(uuid);
    }

    public void clearSubAlterLocations(UUID uuid) {
        subAlterLocations.remove(uuid);
    }

    public void clearPlayerAlterStatus(UUID uuid) {
        playerAlterStatus.remove(uuid);
    }
}
