package ucal.ucallib.registry;

import java.util.Map;
import java.util.function.Function;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class ItemsOfMaterial {
	public final TagKey<Item> REPAIRS_ARMOR;
	public final ToolMaterial TOOL_MATERIAL;
	
	public final Item INGOT;
	
	public final Item SWORD;
	
	public final RegistryKey<EquipmentAsset> ARMOR_MATERIAL_KEY;
	public final ArmorMaterial ARMOR_MATERIAL;
	public final Item HELMET;
	public final Item CHESTPLATE;
	public final Item LEGGINGS;
	public final Item BOOTS;
	
	public static Item register(String modid, String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
		RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(modid, name));
		Item item = itemFactory.apply(settings.registryKey(itemKey));
		Registry.register(Registries.ITEM, itemKey, item);
		return item;
	}
	
	public ItemsOfMaterial(String modid, String matid, RegistryKey<ItemGroup> group, int durability, float attackDamage, float attackSpeed, int miningspeed, int enchantability, float toughness, float knockbackRes, Map<EquipmentType,Integer> armorPieces) {
		REPAIRS_ARMOR = TagKey.of(Registries.ITEM.getKey(), Identifier.of(modid, "repairs_" + matid + "_armor"));
		TOOL_MATERIAL = new ToolMaterial(
				BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
				durability,
				miningspeed,
				attackDamage,
				enchantability,
				REPAIRS_ARMOR
		);
		
		INGOT = register(modid, matid + "_ingot", Item::new, new Item.Settings());
		
		SWORD = register(modid, matid + "_sword", Item::new, new Item.Settings().sword(TOOL_MATERIAL, attackDamage, attackSpeed));
		
		ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(modid, matid));
		ARMOR_MATERIAL = new ArmorMaterial(
				durability,
				armorPieces,
				enchantability,
				SoundEvents.ITEM_ARMOR_EQUIP_IRON,
				toughness,
				knockbackRes,
				REPAIRS_ARMOR,
				ARMOR_MATERIAL_KEY
		);
		HELMET = register(modid, matid + "_helmet", Item::new, new Item.Settings().armor(ARMOR_MATERIAL, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(durability)));
		CHESTPLATE = register(modid, matid + "_chestplate", Item::new, new Item.Settings().armor(ARMOR_MATERIAL, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(durability)));
		LEGGINGS = register(modid, matid + "_leggings", Item::new, new Item.Settings().armor(ARMOR_MATERIAL, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(durability)));
		BOOTS = register(modid, matid + "_boots", Item::new, new Item.Settings().armor(ARMOR_MATERIAL, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(durability)));
		
		ItemGroupEvents.modifyEntriesEvent(group)
		.register((itemGroup) -> {
			itemGroup.add(INGOT);
			itemGroup.add(SWORD);
			itemGroup.add(HELMET);
			itemGroup.add(BOOTS);
			itemGroup.add(LEGGINGS);
			itemGroup.add(CHESTPLATE);
			});
		AdvancementHelper.registerAdvancements(modid, matid);
	}
}