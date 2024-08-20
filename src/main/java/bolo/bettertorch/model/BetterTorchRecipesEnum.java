package bolo.bettertorch.model;

import necesse.engine.registries.RecipeTechRegistry;
import necesse.inventory.recipe.Ingredient;
import necesse.inventory.recipe.Recipe;

import java.util.Map;

public enum BetterTorchRecipesEnum {
    EASY(
        new Recipe(
            "bettertorch",
            1,
            RecipeTechRegistry.NONE,
            new Ingredient[]{
                new Ingredient("torch", 2)
            }
        ),
        new Recipe(
            "torch",
            4,
            RecipeTechRegistry.NONE,
            new Ingredient[]{
                new Ingredient("bettertorch", 1)
            }
        ),
            0.4F
    ),
    MEDIUM(
            new Recipe(
                    "bettertorch",
                    1,
                    RecipeTechRegistry.WORKSTATION,
                    new Ingredient[]{
                            new Ingredient("torch", 4)
                    }
            ),
            new Recipe(
                    "torch",
                    4,
                    RecipeTechRegistry.WORKSTATION,
                    new Ingredient[]{
                            new Ingredient("bettertorch", 1)
                    }
            ),
            0.4F
    ),
    HARD(
            new Recipe(
                    "bettertorch",
                    1,
                    RecipeTechRegistry.WORKSTATION,
                    new Ingredient[]{
                            new Ingredient("torch", 4),
                            new Ingredient("anystone", 4)
                    }
            ),
            new Recipe(
                    "torch",
                    4,
                    RecipeTechRegistry.WORKSTATION,
                    new Ingredient[]{
                            new Ingredient("bettertorch", 1)
                    }
            ),
    0.8F
    );

    private Recipe crafting, uncrafting;
    private Float brokerValue;

    BetterTorchRecipesEnum(Recipe crafting, Recipe uncrafting, Float brokerValue) {
        this.crafting = crafting;
        this.uncrafting = uncrafting;
        this.brokerValue = brokerValue;
    }

    public Recipe getCrafting() {
        return crafting;
    }

    public Recipe getUncrafting() {
        return uncrafting;
    }

    public Float getBrokerValue() {
        return brokerValue;
    }

    private static Map<String, BetterTorchRecipesEnum> map = Map.of(
        "EASY", EASY,
        "MEDIUM", MEDIUM,
        "HARD", HARD
    );

    public static BetterTorchRecipesEnum fromString(String recipeDifficulty) {
        return map.get(recipeDifficulty);
    }
}
