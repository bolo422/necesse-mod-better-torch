package bolo.bettertorch.model;

import necesse.engine.localization.Localization;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.toolItem.ToolType;
import necesse.level.gameObject.TorchObject;

import java.awt.*;

public class BetterTorchObject extends TorchObject {

    public BetterTorchObject(float lightHue, float lightSat, int lightLevel) {
        super("bettertorch", ToolType.ALL, new Color(200, 200, 0), lightHue, lightSat);
        this.lightLevel = lightLevel;
        System.out.println("Better Torch Object created with lightHue {" + lightHue + "}, lightSat {" + lightSat + "}, lightLevel {" + lightLevel + "}");
    }

    @Override
    public ListGameTooltips getItemTooltips(InventoryItem item, PlayerMob perspective) {
        ListGameTooltips tooltips = super.getItemTooltips(item, perspective);
        tooltips.add(Localization.translate("itemtooltip", "bettertorchplacetip"));
        return tooltips;
    }
}