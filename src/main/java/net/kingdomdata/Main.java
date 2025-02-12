package net.kingdomdata;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Iterator;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Register events
        Bukkit.getPluginManager().registerEvents(this, this);
        // Save the default config if it doesn't exist
        saveDefaultConfig();
        // Remove old recipes
        removeOldRecipes();

        // Register custom recipes
        registerDiamondHelmetRecipe();
        registerDiamondChestplateRecipe();
        registerDiamondLeggingsRecipe();
        registerDiamondBootsRecipe();

        getLogger().info("KingdomData has been enabled!");
    }

    private void removeOldRecipes() {
        NamespacedKey helmetKey = new NamespacedKey(NamespacedKey.MINECRAFT, "diamond_helmet");
        Bukkit.removeRecipe(helmetKey);

        NamespacedKey chestplatekey = new NamespacedKey(NamespacedKey.MINECRAFT, "diamond_chestplate");
        Bukkit.removeRecipe(chestplatekey);

        NamespacedKey leggingskey = new NamespacedKey(NamespacedKey.MINECRAFT, "diamond_leggings");
        Bukkit.removeRecipe(leggingskey);

        NamespacedKey bootskey = new NamespacedKey(NamespacedKey.MINECRAFT, "diamond_boots");
        Bukkit.removeRecipe(bootskey);
    }



    @Override
    public void onDisable() {
        getLogger().info("KingdomData has been disabled!");
    }

    private void registerDiamondHelmetRecipe() {
        // Create a new shaped recipe for the diamond helmet
        NamespacedKey helmetKey = new NamespacedKey(this, "diamond_helmet");
        ShapedRecipe helmetRecipe = new ShapedRecipe(helmetKey, new ItemStack(Material.DIAMOND_HELMET));

        // Define the recipe shape
        helmetRecipe.shape("DDD",
                           "DYD");

        // Set the ingredients
        helmetRecipe.setIngredient('D', Material.DIAMOND);
        helmetRecipe.setIngredient('Y', Material.DRAGON_BREATH);

        // Register the recipe
        Bukkit.addRecipe(helmetRecipe);
    }

    // Event to handle item drops
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (getConfig().getBoolean("disable-drops.ender-pearl", true)) {
            event.getDrops().removeIf(item -> item.getType() == Material.ENDER_PEARL);
        }
        if (getConfig().getBoolean("disable-drops.enchanted-golden-apple", true)) {
            event.getDrops().removeIf(item -> item.getType() == Material.ENCHANTED_GOLDEN_APPLE);
        }
    }

    // Event to handle loot generation (e.g., in chests)
    @EventHandler
    public void onLootGenerate(LootGenerateEvent event) {
    }


    private void registerDiamondChestplateRecipe() {
        // Create a new shaped recipe for the diamond chestplate
        NamespacedKey chestplateKey = new NamespacedKey(this, "diamond_chestplate");
        ShapedRecipe chestplateRecipe = new ShapedRecipe(chestplateKey, new ItemStack(Material.DIAMOND_CHESTPLATE));

        // Define the recipe shape
        chestplateRecipe.shape(
                "DYD",
                "DDD",
                "DDD");

        // Set the ingredients
        chestplateRecipe.setIngredient('D', Material.DIAMOND);
        chestplateRecipe.setIngredient('Y', Material.DRAGON_BREATH);

        // Register the recipe
        Bukkit.addRecipe(chestplateRecipe);
    }

    private void registerDiamondLeggingsRecipe() {
        // Create a new shaped recipe for the diamond Leggings
        NamespacedKey leggingsKey = new NamespacedKey(this, "diamond_leggings");
        ShapedRecipe leggingsRecipe = new ShapedRecipe(leggingsKey, new ItemStack(Material.DIAMOND_LEGGINGS));

        // Define the recipe shape
        leggingsRecipe.shape(
                "DDD",
                "DYD",
                "D D");

        // Set the ingredients
        leggingsRecipe.setIngredient('D', Material.DIAMOND);
        leggingsRecipe.setIngredient('Y', Material.DRAGON_BREATH);

        // Register the recipe
        Bukkit.addRecipe(leggingsRecipe);
    }

    private void registerDiamondBootsRecipe() {
        // Create a new shaped recipe for the Boots chestplate
        NamespacedKey bootsKey = new NamespacedKey(this, "diamond_boots");
        ShapedRecipe bootsRecipe = new ShapedRecipe(bootsKey, new ItemStack(Material.DIAMOND_BOOTS));

        // Define the recipe shape
        bootsRecipe.shape(
                "   ",
                "DYD",
                "D D");

        // Set the ingredients
        bootsRecipe.setIngredient('D', Material.DIAMOND);
        bootsRecipe.setIngredient('Y', Material.DRAGON_BREATH);

        // Register the recipe
        Bukkit.addRecipe(bootsRecipe);
    }


}
