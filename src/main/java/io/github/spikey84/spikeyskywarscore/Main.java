package io.github.spikey84.spikeyskywarscore;

import com.onarandombox.MultiverseCore.MultiverseCore;
import io.github.spikey84.spikeyskywarscore.commands.Teleport;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;


public class Main extends JavaPlugin {


    @Override
    public void onEnable() {
        if(Bukkit.getWorld("preset") == null) {
            Bukkit.getServer().createWorld(new WorldCreator("preset"));
        }
        copy("test");
        getCommand("teleport").setExecutor(new Teleport());

    }

    public void onDisable() {
        delete("test");
    }

    public void copy(String world) {
        String root = Bukkit.getWorldContainer().getAbsolutePath();

        //Bukkit.getLogger().info(""+ root.getAbsolutePath());

        //begins the copying
        String tempSrcDir = root+"backup";
        tempSrcDir = tempSrcDir.replace(".backup", "backup");

        File srcDir = new File(tempSrcDir);

        Bukkit.getLogger().info("" + srcDir.getPath());
        if(!srcDir.exists()) {
            Bukkit.getLogger().info("Backup does not exist.");
            return;
        }
        File destDir = new File(root+File.pathSeparator+world);
        //FileUtils.copyFolder(srcDir,destDir,Bukkit.getLogger());
        try {
            FileUtils.copyDirectory(srcDir,destDir);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        WorldCreator wC = new WorldCreator("test");
        wC = wC.copy(Bukkit.getWorld("preset"));

        //Bukkit.getServer().createWorld(new WorldCreator(world));
        Bukkit.getServer().createWorld(wC);
    }

    public void delete(String world) {
        Bukkit.getServer().unloadWorld(world,true);
        /*String tempDir = Bukkit.getServer().getWorld(world).getWorldFolder().getPath();
        tempDir = tempDir.replace(".", "");
        File dir = new File(tempDir);*/
        String root = Bukkit.getWorldContainer().getAbsolutePath();

        //Bukkit.getLogger().info(""+ root.getAbsolutePath());

        //begins the copying
        String tempSrcDir = root;
        tempSrcDir = tempSrcDir.replace(".", "");

        File srcDir = new File(tempSrcDir);

        String tempDir = srcDir.getAbsoluteFile()+"/" + world;
        tempDir = tempDir.replace("." + world, world);
        Bukkit.getLogger().info("" + tempDir);
        File dir = new File(tempDir);
        //FileUtils.deleteFolder(dir);
        try {
            FileUtils.deleteDirectory(dir);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



}
