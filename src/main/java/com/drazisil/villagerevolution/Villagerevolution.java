package com.drazisil.villagerevolution;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Villagerevolution extends JavaPlugin {

    public static Villagerevolution plugin;

    public static Logger logger;

    public static VillagerManager villagerManager;
    public static CatManager catManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        logger = this.getLogger();
        villagerManager = new VillagerManager(logger);
        catManager = new CatManager(logger);

        // Register the command handler
        PluginCommand pluginCmd = this.getCommand("villager");

        if (pluginCmd == null) throw new Error("Unable to fetch command handler for `villager`");

        pluginCmd.setExecutor(new CommandVillager());

        // Register the event listener
        getServer().getPluginManager().registerEvents(new EventListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
