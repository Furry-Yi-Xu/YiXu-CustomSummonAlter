package com.yixu.Event.CraftEngine;

import com.yixu.Util.Item.ItemDisplayUtil;
import net.momirealms.craftengine.bukkit.api.CraftEngineItems;
import net.momirealms.craftengine.core.util.Key;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class CustomBlockInteractEvent implements Listener {

    @EventHandler
    public void onCustomBlockInteractEvent(net.momirealms.craftengine.bukkit.api.event.CustomBlockInteractEvent event) {

        String blockKey = event.customBlock().id().asString();

        if (!blockKey.equals("default:stripped_palm_wood")) {
            return;
        }

        event.setCancelled(true);

        Player player = event.getPlayer();
        Location location = event.bukkitBlock().getLocation();
        ItemStack handItem = player.getInventory().getItemInMainHand();

        if (ItemDisplayUtil.existItemDisplay(location)) {
            handleTakeBackItemDisplay(player, location);
            return;
        }

        if (handItem == null || handItem.getType() == Material.AIR) {
            player.sendMessage("请手持有效物品！");
            return;
        }

        if (!CraftEngineItems.isCustomItem(handItem)) {
            player.sendMessage("这不是有效的祭品！");
            return;
        }

        String itemId = CraftEngineItems.getCustomItemId(handItem).asString();
        if (!itemId.equals("default:topaz_ore")) {
            player.sendMessage("这不是正确的触发物品！");
            return;
        }

        if (handItem.getAmount() < 3) {
            player.sendMessage("你至少需要携带 " + 3 + " 个该物品！");
            return;
        }

        ItemDisplay itemDisplay = ItemDisplayUtil.createItemDisplay(player, location);
        itemDisplay.setGravity(false);
        itemDisplay.setItemDisplayTransform(ItemDisplay.ItemDisplayTransform.GUI);

    }

    private void handleTakeBackItemDisplay(Player player, Location location) {

        ItemDisplay itemDisplay = ItemDisplayUtil.getItemDisplay(location);
        ItemStack storedItem = itemDisplay.getItemStack();

        player.getInventory().addItem(storedItem);
        itemDisplay.remove();
    }

}
