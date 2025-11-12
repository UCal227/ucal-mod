package ucal.ucallib.registry;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ucal.ucallib.UcalLib;

public class AdvancementHelper {

    public static void registerAdvancements(String modId, String name) {
        Identifier swordId = Identifier.of(modId, name + "_sword");
        Item item = Registries.ITEM.get(swordId);

        Advancement.Builder.create()
                .display(
                        item,
                        Text.literal("Forgé en " + capitalize(name)),
                        Text.literal("Obtenez une épée en " + name),
                        Identifier.of("textures/gui/advancements/backgrounds/stone.png"),
                        AdvancementFrame.TASK,
                        true, true, false
                )
                .criterion("has_" + name,
                        InventoryChangedCriterion.Conditions.items(item))
                .build(Identifier.of(modId, name + "_advancement"));

        UcalLib.LOGGER.info("Advancement '{}' registered.", name);
    }

    private static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
