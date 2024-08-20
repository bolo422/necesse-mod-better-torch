package bolo.bettertorch;

import necesse.engine.GlobalData;
import necesse.engine.registries.RecipeTechRegistry;
import necesse.inventory.recipe.Ingredient;
import necesse.inventory.recipe.Recipe;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;

public class Config {

    private Float lightHue = 1F;
    private Float lightSat = 0F;
    private Integer lightLevel = 300;
    private String recipeDifficulty;

    public Config() {
        this(1F, 0F, 300, "MEDIUM");
    }

    public Config(Float lightHue, Float lightSat, Integer lightLevel, String recipeDifficulty) {
        this.lightHue = lightHue;
        this.lightSat = lightSat;
        this.lightLevel = lightLevel;
        this.setRecipeDifficulty(recipeDifficulty);
    }

    public Config(String configFileName) {
        System.out.println("Loading config for Better Torch Mod...");
        String filename = GlobalData.rootPath() + "/settings/bettertorch/" + configFileName;
        try {
            File file = new File(filename);
            if (!file.exists()) createNewFile(file);

            InputStreamReader isr = new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            loadConfig(br);
            br.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadConfig(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.length() != 0 && !line.startsWith("#")) {
                String[] temp = line.split("=");
                if (Objects.equals(temp[0], "lightHue")) this.setLightHue(Float.parseFloat(temp[1]));
                if (Objects.equals(temp[0], "lightSat")) this.setLightSat(Float.parseFloat(temp[1]));
                if (Objects.equals(temp[0], "lightLevel")) this.setLightLevel(Integer.parseInt(temp[1]));
                if (Objects.equals(temp[0], "recipeDifficulty")) this.setRecipeDifficulty(temp[1]);
            }
        }
    }

    private void createNewFile(File file) throws IOException {
        if (!file.getParentFile().mkdirs() && !file.createNewFile()) {
            throw new IOException("Error creating file: " + file.toPath());
        }

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(file.toPath()), StandardCharsets.UTF_8))) {
            writer.write("# lines starting with # are comments and will be ignored");
            writer.write("\n#");
            writer.write("\n# default lightLevel is 300, which is 2x the vanilla torch light level");
            writer.write("\nlightLevel=300");
            writer.write("\n#");
            writer.write("\n# hue and saturation are kinda weird values, you can mess with them to get different colors, default is a white light");
            writer.write("\n# default lightHue is 1.0");
            writer.write("\nlightHue=1.0");
            writer.write("\n# default lightSat is 0.0");
            writer.write("\nlightSat=0.0");
            writer.write("\n#");
            writer.write("\n# recipeDifficulty can be EASY, MEDIUM, or HARD");
            writer.write("\n# EASY is 2 torches for 1 better torch");
            writer.write("\n# MEDIUM is 4 torches for 1 better torch");
            writer.write("\n# HARD is 4 torches and 4 any stone for 1 better torch (be aware, on uncrafting the stone is lost in this case)");
            writer.write("\nrecipeDifficulty=MEDIUM");
        }
    }

    public Float getLightHue() {
        return this.lightHue;
    }

    private void setLightHue(float lightHue) {
        this.lightHue = lightHue;
    }

    public Float getLightSat() {
        return this.lightSat;
    }

    private void setLightSat(float lightSat) {
        this.lightSat = lightSat;
    }

    public Integer getLightLevel() {
        return this.lightLevel;
    }

    private void setLightLevel(int lightLevel) {
        this.lightLevel = lightLevel;
    }

    public String getRecipeDifficulty() {
        return recipeDifficulty;
    }

    private void setRecipeDifficulty(String recipeDifficulty) {
        this.recipeDifficulty = recipeDifficulty;
    }

    public Recipe getCraftingRecipe() {
        switch (this.recipeDifficulty) {
            case "EASY":
                return new Recipe(
                        "bettertorch",
                        1,
                        RecipeTechRegistry.NONE,
                        new Ingredient[]{
                                new Ingredient("torch", 2)
                        }
                );
            case "HARD":
                return new Recipe(
                        "bettertorch",
                        1,
                        RecipeTechRegistry.WORKSTATION,
                        new Ingredient[]{
                                new Ingredient("torch", 4),
                                new Ingredient("anystone", 4)
                        }
                );
            case "MEDIUM":
            default:
                return new Recipe(
                        "bettertorch",
                        1,
                        RecipeTechRegistry.WORKSTATION,
                        new Ingredient[]{
                                new Ingredient("torch", 4)
                        }
                );
        }
    }

    public Recipe getUncraftingRecipe() {
        switch (this.recipeDifficulty) {
            case "EASY":
                return new Recipe(
                        "torch",
                        2,
                        RecipeTechRegistry.NONE,
                        new Ingredient[]{
                                new Ingredient("bettertorch", 1)
                        }
                );
            case "MEDIUM":
            case "HARD":
            default:
                return new Recipe(
                        "torch",
                        4,
                        RecipeTechRegistry.WORKSTATION,
                        new Ingredient[]{
                                new Ingredient("bettertorch", 1)
                        }
                );
        }
    }

    public float getRecipeBrokerValue() {
        switch (this.recipeDifficulty) {
            case "EASY":
                return 0.2F;
            case "HARD":
                return 0.8F;
            case "MEDIUM":
            default:
                return 0.4F;
        }
    }

}
