package com.drazisil.villagerevolution;

import org.bukkit.Bukkit;
import org.bukkit.entity.Villager;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger;

public class VillagerManager {

    private final Logger logger;

    private final ArrayList<SmartVillager> villagers = new ArrayList<>();

    public VillagerManager(Logger parentLogger) {
        logger = parentLogger;

        BukkitTask villagerManagerTickTask = Bukkit.getScheduler().runTaskTimer(Villagerevolution.plugin,
                this::tick, 20, 20);
    }

    public boolean hasVillagerById(UUID villagerId) {

        for (SmartVillager villager: villagers) {
            if (villager.base.getUniqueId().equals(villagerId) ) return true;
        }

        return false;
    }

    public SmartVillager getVillagerById(UUID villagerId) throws Error {

        for (SmartVillager villager: villagers) {
            if (villager.base.getUniqueId().equals(villagerId) ) return villager;
        }

        throw new Error(String.format("No SmartVillager found with %s", villagerId.toString()));
    }

    public void addVillager(Villager newVillager) {
        UUID villagerId = newVillager.getUniqueId();

        for (SmartVillager villager: villagers) {
            if (villager.base.getUniqueId().equals(villagerId) ) return;
        }

        villagers.add(new SmartVillager(newVillager));
        logger.warning(String.format("Added villager with id %s", villagerId));

    }

    public int getVillagerCount() {
        return villagers.size();
    }

    public void tick() {
        for (SmartVillager villager: villagers) {
            villager.tick();
        }
    }
}
