package ucal.azyr.registry;

import java.util.Map;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import ucal.azyr.Azyr;
import ucal.ucallib.registry.ItemsOfMaterial;

public class AzyrItems {
	public static final ItemsOfMaterial SIGMARITE_ITEMS = new ItemsOfMaterial(Azyr.MOD_ID, "sigmarite", ItemGroups.COMBAT, 455, 8, 1.5f, 5.0f, 10, 2.0f, 2.0f,
			Map.of(
					EquipmentType.HELMET, 3,
					EquipmentType.CHESTPLATE, 8,
					EquipmentType.LEGGINGS, 6,
					EquipmentType.BOOTS, 3
			));
	
	public static void initialize() {}
}
