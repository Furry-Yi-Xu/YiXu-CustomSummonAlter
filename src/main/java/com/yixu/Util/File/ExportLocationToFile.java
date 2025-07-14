package com.yixu.Util.File;

import com.yixu.Alter.AlterSessionManager;
import com.yixu.CustomSummonAlter;
import com.yixu.Util.Message.MessageUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportLocationToFile {

    public void exportAlterLocationsToFile(Player player) throws IOException {

        AlterSessionManager alterSessionManager = CustomSummonAlter.getAlterSession();

        Location mainAlterLocation = alterSessionManager.getMainAlterLocation(player.getUniqueId());
        List<Location> subAlterLocations = alterSessionManager.getSubAlterLocations(player.getUniqueId());

        File filePath = new File("plugins/" + CustomSummonAlter.getInstance().getName() + "/Alter_Locations.txt");

        File filePathIsExists = filePath.getParentFile();

        if (!filePathIsExists.exists()) {
            filePathIsExists.mkdirs();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for (Location blockLocation : subAlterLocations) {

            Vector subAlterVector = blockLocation.toVector().subtract(mainAlterLocation.toVector());

            String formatted = String.format("- " + "%.1f,%.1f,%.1f",
                    subAlterVector.getX(),
                    subAlterVector.getY(),
                    subAlterVector.getZ()
            );

            writer.write(formatted);
            writer.newLine();
        }

        writer.close();

        MessageUtil.sendMessage(player, "all_alter.all_alter_output_succeed");

    }

}
