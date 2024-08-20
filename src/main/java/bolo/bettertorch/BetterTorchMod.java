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

    public void init() {
        System.out.println("Starting Better Torch Mod, Hello World!");
        ObjectRegistry.registerObject(
                "bettertorch",
                new BetterTorchObject("bettertorch", ToolType.ALL, new Color(255, 255, 255), 1F, 0F), 2, true);
    }

    public void postInit() {
        Recipes.registerModRecipe(new Recipe(
                "bettertorch",
                1,
                RecipeTechRegistry.WORKSTATION,
                new Ingredient[]{
                        new Ingredient("torch", 4)
                }
        ));

        Recipes.registerModRecipe(new Recipe(
                "torch",
                4,
                RecipeTechRegistry.WORKSTATION,
                new Ingredient[]{
                        new Ingredient("bettertorch", 1)
                }
        ));
    }

}
