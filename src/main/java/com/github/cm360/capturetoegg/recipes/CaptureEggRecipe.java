package com.github.cm360.capturetoegg.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.cm360.capturetoegg.main.CaptureToEggPlugin;

import net.md_5.bungee.api.ChatColor;

public class CaptureEggRecipe {

	public static ShapedRecipe createCaptureEggRecipe(NamespacedKey key) {
		ItemStack captureEgg = new ItemStack(Material.EGG);
		ItemMeta im = captureEgg.getItemMeta();
		
		im.setDisplayName(ChatColor.RESET + "Capture Egg");
		im.setCustomModelData(CaptureToEggPlugin.customModelData);
		im.addEnchant(Enchantment.DURABILITY, 0, true);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		captureEgg.setItemMeta(im);
		
		ShapedRecipe recipe = new ShapedRecipe(key, captureEgg);
		
        recipe.shape(
        		" G ",
        		"BER",
        		" S ");
        recipe.setIngredient('E', Material.EGG);
        recipe.setIngredient('G', Material.GUNPOWDER);
        recipe.setIngredient('B', Material.BONE);
        recipe.setIngredient('R', Material.ROTTEN_FLESH);
        recipe.setIngredient('S', Material.STRING);
        
        return recipe;
	}

}
