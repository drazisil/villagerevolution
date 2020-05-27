package com.drazisil.villagerevolution;

import org.bukkit.entity.Cat;

import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger;

public class CatManager {
    private final Logger logger;

    private final ArrayList<SmartCat> cats = new ArrayList<>();

    public CatManager(Logger parentLogger) {
        logger = parentLogger;
    }

    public void addCat(Cat newCat) {
        UUID catId = newCat.getUniqueId();

        for (SmartCat cat: cats) {
            if (cat.base.getUniqueId().equals(catId) ) return;
        }

        cats.add(new SmartCat(newCat));
        logger.warning(String.format("Added cat with id %s", catId));

    }
}
