package bolo.bettertorch;

import bolo.bettertorch.model.*;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.*;
import necesse.inventory.item.toolItem.ToolType;
import necesse.inventory.recipe.Ingredient;
import necesse.inventory.recipe.Recipe;
import necesse.inventory.recipe.Recipes;

import java.awt.*;

@ModEntry
public class BetterTorchMod {

    private static Config config;

    public void preInit() {
        System.out.println("Pre-Init Better Torch Mod...");
        config = new Config("settings.cfg");
        config = new Config();
        System.out.println(
                "Better Torch Mod initialization completed! " +
                        "lightHue {" + config.getLightHue() + "}, " +
                        "lightSat {" + config.getLightSat() + "}, " +
                        "lightLevel {" + config.getLightLevel() + "}, " +
                        "recipeDifficulty {" + config.getRecipeDifficulty().toString() + "}");
    }

    public void init() {
        System.out.println("Starting Better Torch Mod, Hello World!");
        ObjectRegistry.registerObject(
                "bettertorch",
                new BetterTorchObject(config.getLightHue(), config.getLightSat(), config.getLightLevel()),
                config.getRecipeBrokerValue(),
                true);
    }

    public void postInit() {
        Recipes.registerModRecipe(config.getCraftingRecipe());
        Recipes.registerModRecipe(config.getUncraftingRecipe());
    }
}
