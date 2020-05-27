package com.drazisil.villagerevolution;

import org.bukkit.entity.Cat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

import java.util.List;

import static com.drazisil.villagerevolution.Villagerevolution.logger;

public class SmartVillager {

    public Villager base;

    List<Entity> nearbyEntities;
    Cat cat;



    SmartVillager(Villager bashVillager) {
        this.base = bashVillager;
    }

    public void tick() {

        nearbyEntities = this.base.getNearbyEntities(3, 3, 3);

        checkForCat();


    }

    private void checkForCat() {
        for (Entity entity: nearbyEntities) {
            if (entity.getType() == EntityType.CAT) {
                logger.warning("Found a cat!");

                if (hasCat()) {
                    logger.warning("...but I already have one.");
                    return;
                }

                if (base.hasLineOfSight(entity)) {
                    logger.warning("Can see a cat!");
                }

                return;
            }
        }
    }

    private boolean hasCat() {
        return !(cat == null);
    }

}
