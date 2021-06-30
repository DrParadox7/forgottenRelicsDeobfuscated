/*    */ package com.integral.forgottenrelics.handlers;
/*    */ 
/*    */ import com.integral.forgottenrelics.Main;
/*    */ import com.integral.forgottenrelics.items.ItemVoidGrimoire;
/*    */ import com.integral.forgottenrelics.packets.DiscordKeybindMessage;
/*    */ import cpw.mods.fml.client.registry.ClientRegistry;
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import cpw.mods.fml.common.gameevent.TickEvent;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.settings.KeyBinding;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RelicsKeybindHandler
/*    */ {
/*    */   @SideOnly(Side.CLIENT)
/*    */   public static boolean checkVariable;
/*    */   public static KeyBinding discordRingKey;
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public static void registerKeybinds() {
/* 25 */     discordRingKey = new KeyBinding("key.discordRing", 45, "key.categories.forgottenrelics");
/*    */     
/* 27 */     ClientRegistry.registerKeyBinding(discordRingKey);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void onClientTick(TickEvent.ClientTickEvent event) {
/* 33 */     this; if ((discordRingKey.isPressed() & (!checkVariable ? 1 : 0)) != 0)
/* 34 */     { Main.packetInstance.sendToServer((IMessage)new DiscordKeybindMessage(true));
/* 35 */       checkVariable = true; }
/* 36 */     else { this; if (((!discordRingKey.isPressed() ? 1 : 0) & ((checkVariable == true) ? 1 : 0)) != 0)
/* 37 */         checkVariable = false;  }
/*    */     
/* 39 */     if (ItemVoidGrimoire.localCooldown > 0)
/* 40 */       ItemVoidGrimoire.localCooldown--; 
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\handlers\RelicsKeybindHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */