package bolo.bettertorch.model;

import necesse.engine.localization.Localization;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.toolItem.ToolType;
import necesse.level.gameObject.TorchObject;

import java.awt.*;

public class BetterTorchObject extends TorchObject {

    public BetterTorchObject(String textureName, ToolType toolType, Color mapColor, float lightHue, float lightSat) {
        super(textureName, toolType, mapColor, lightHue, lightSat);
        lightLevel = 300;
    }

    @Override
    public ListGameTooltips getItemTooltips(InventoryItem item, PlayerMob perspective) {
        ListGameTooltips tooltips = super.getItemTooltips(item, perspective);
        tooltips.add(Localization.translate("itemtooltip", "bettertorchplacetip"));
        return tooltips;
    }
}