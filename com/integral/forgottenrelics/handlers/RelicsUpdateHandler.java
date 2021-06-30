/*    */ package com.integral.forgottenrelics.handlers;
/*    */ 
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import java.net.URL;
/*    */ import java.util.Scanner;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RelicsUpdateHandler
/*    */ {
/* 22 */   private static String currentVersion = "1.7.3 Beta";
/*    */   private static String newestVersion;
/* 24 */   public static String updateStatus = null;
/*    */   public static boolean show = false;
/*    */   static boolean worked = false;
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPlayerLogin(EntityJoinWorldEvent event) {
/* 30 */     if (!(event.entity instanceof EntityPlayer)) {
/*    */       return;
/*    */     }
/* 33 */     if (event.entity.worldObj.isRemote) { this; if (show) {
/*    */ 
/*    */         
/* 36 */         EntityPlayer player = (EntityPlayer)event.entity;
/*    */         
/* 38 */         this; if (show) {
/* 39 */           player.addChatMessage((IChatComponent)new ChatComponentText(updateStatus));
/* 40 */           this; show = false;
/*    */         } 
/*    */         return;
/*    */       }  }
/*    */   
/*    */   } public static void init() {
/* 46 */     if (!RelicsConfigHandler.updateNotificationsEnabled) {
/* 47 */       show = false;
/*    */       
/*    */       return;
/*    */     } 
/* 51 */     getNewestVersion();
/*    */     
/* 53 */     if (newestVersion != null) {
/*    */       
/* 55 */       if (newestVersion.equalsIgnoreCase(currentVersion))
/*    */       {
/* 57 */         show = false;
/*    */       }
/* 59 */       else if (!newestVersion.equalsIgnoreCase(currentVersion))
/*    */       {
/* 61 */         show = true;
/* 62 */         updateStatus = EnumChatFormatting.DARK_PURPLE + String.format(StatCollector.translateToLocal("status.outdated"), new Object[] { newestVersion });
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 67 */       show = true;
/* 68 */       updateStatus = StatCollector.translateToLocal("status.noconnection");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private static void getNewestVersion() {
/*    */     try {
/* 75 */       URL url = new URL("https://raw.githubusercontent.com/Extegral/Forgotten-Relics/master/version.txt");
/* 76 */       Scanner s = new Scanner(url.openStream());
/* 77 */       newestVersion = s.nextLine();
/* 78 */       s.close();
/*    */     }
/* 80 */     catch (Exception ex) {
/*    */       
/* 82 */       ex.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\handlers\RelicsUpdateHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */