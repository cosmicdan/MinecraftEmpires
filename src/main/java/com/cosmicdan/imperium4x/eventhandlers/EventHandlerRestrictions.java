package com.cosmicdan.imperium4x.eventhandlers;

import com.cosmicdan.imperium4x.data.player.ImperiumPlayer;
import com.cosmicdan.imperium4x.data.player.PlayerData;
import com.cosmicdan.imperium4x.data.player.PlayerData.PlayerAbilities;
import com.cosmicdan.imperium4x.data.player.PlayerEventsEssential.EssentialEvents;
import com.cosmicdan.imperium4x.data.player.PlayerEventsTutorial.TutorialEvents;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;


 /* 
  * This class is responsible for imposing restrictions depending on their progression.
  * This will become massive in future, so it gets it's own class based on purpose rather than specific events
  */
public class EventHandlerRestrictions {
    @SubscribeEvent
    public void onBreakSpeed(BreakSpeed event) { // player is trying to break a block
        final ImperiumPlayer playerImperium = ImperiumPlayer.get(event.entityPlayer); 
        if (event.block == Blocks.log)
            checkAbortTree(event, playerImperium);
        else if (event.block == Blocks.log2)
            checkAbortTree(event, playerImperium);
    }
    
    // this method is for determining if a tree block break is to be permitted or denied
    public void checkAbortTree(final BreakSpeed event, final ImperiumPlayer playerImperium) {
        if (!PlayerData.hasAbility(playerImperium, PlayerAbilities.CANPUNCHWOOD)) {
            if (!playerImperium.eventListDone.toString().contains("WOODPUNCH")) {
                playerImperium.addInstantEvent(TutorialEvents.WOODPUNCH);
                playerImperium.syncToServer("events");
            }
            if(event.isCancelable()) { // not sure if needed but doesn't hurt
                event.entityPlayer.swingProgressInt = 0;
                event.setCanceled(true);
            }
        }
    }
}
