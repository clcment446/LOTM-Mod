package com.c446.lotm_craft.key_binds;

import com.c446.lotm_craft.LotmCraft;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public final class KeyBindings {
    public static KeyBindings INSTANCE = new KeyBindings();

    private KeyBindings(){}



    public final KeyMapping useSpell = new KeyMapping(
            "key."+ LotmCraft.MOD_ID + "trigger_selected_spell",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_C,-1),
            KeyMapping.CATEGORY_GAMEPLAY
    );
    public final KeyMapping nextSpell = new KeyMapping(
            "key."+ LotmCraft.MOD_ID + "trigger_next_spell",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_X,-1),
            KeyMapping.CATEGORY_GAMEPLAY
    );
    public final KeyMapping prevSpell = new KeyMapping(
            "key."+ LotmCraft.MOD_ID + "trigger_prev_spell",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_W,-1),
            KeyMapping.CATEGORY_GAMEPLAY
    );
}
