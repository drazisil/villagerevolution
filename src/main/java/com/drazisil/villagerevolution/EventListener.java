package com.drazisil.villagerevolution;

import org.bukkit.entity.Cat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.VillagerCareerChangeEvent;

import java.util.UUID;

import static com.drazisil.villagerevolution.Villagerevolution.*;

public class EventListener implements Listener {

    @EventHandler
    public void onVillagerSpawn(EntitySpawnEvent event) {
        if (!(event.getEntityType() == EntityType.VILLAGER)) {
            return;
        }

        Villager villager = (Villager) event.getEntity();

        villagerManager.addVillager(villager);

    }

    @EventHandler
    public void onCatSpawn(EntitySpawnEvent event) {
        if (!(event.getEntityType() == EntityType.CAT)) {
            return;
        }

        Cat cat = (Cat) event.getEntity();

        catManager.addCat(cat);

    }

    @EventHandler
    public void onVillagerCareerChange(VillagerCareerChangeEvent event) {

        Villager villager = event.getEntity();

        UUID villagerId = villager.getUniqueId();

        if (villagerManager.hasVillagerById(villagerId)) return;

        villagerManager.addVillager(villager);
    }

    @EventHandler
    public void onVillagerTargeted(EntityTargetLivingEntityEvent event) {

        if (event.getTarget() == null) return;

        if (!(event.getTarget().getType() == EntityType.VILLAGER)) {
            return;
        }

        Villager villager = (Villager) event.getTarget();

        UUID villagerId = villager.getUniqueId();

        // This makes the villagers unable to be targeted
        event.setCancelled(true);

        if (villagerManager.hasVillagerById(villagerId)) return;

        villagerManager.addVillager(villager);
    }

    @EventHandler
    public void onVillagerSeesCat(EntityTargetLivingEntityEvent event) {

        if (event.getTarget() == null) return;

        if (!(event.getTarget().getType() == EntityType.CAT)
                && !(event.getEntityType() == EntityType.VILLAGER)) {
            return;
        }

        Villager villager = (Villager) event.getEntity();

        Cat cat = (Cat) event.getTarget();

        UUID villagerId = villager.getUniqueId();

        if (!(villagerManager.hasVillagerById(villagerId))) return;

        logger.warning("Villager has targeted a cat");
    }

}
