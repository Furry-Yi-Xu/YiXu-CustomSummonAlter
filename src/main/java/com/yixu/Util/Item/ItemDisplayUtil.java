package com.yixu.Util.Item;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ItemDisplayUtil {

    public static Location getItemDisplayLocation(Location location) {
        return location.clone().add(0.5, 2, 0.5);
    }

    public static Boolean existItemDisplay(Location location) {
        return getItemDisplay(location) != null;
    }

    public static ItemDisplay getItemDisplay(Location location) {

        Location itemDisplayLocation = getItemDisplayLocation(location);

        for (Entity entity : itemDisplayLocation.getWorld().getNearbyEntities(itemDisplayLocation, 1, 1, 1)) {
            if (entity instanceof ItemDisplay itemDisplay) {
                return itemDisplay;
            }
        }

        return null;
    }

    public static ItemDisplay createItemDisplay(Player player, Location location) {

        PlayerInventory playerInventory = player.getInventory();
        Location itemDisplayLocation = getItemDisplayLocation(location);

        ItemStack itemInMainHand = playerInventory.getItemInMainHand();
        ItemStack cloneItemInMainHand = itemInMainHand.clone();

        cloneItemInMainHand.setAmount(3);

        ItemDisplay itemDisplay = (ItemDisplay) location.getWorld().spawnEntity(itemDisplayLocation, EntityType.ITEM_DISPLAY);
        itemDisplay.setItemStack(cloneItemInMainHand);

        itemInMainHand.setAmount(itemInMainHand.getAmount() - 3);

        return itemDisplay;
    }


}
