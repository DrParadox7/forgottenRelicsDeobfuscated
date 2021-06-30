/*    */ package com.integral.forgottenrelics.packets;
/*    */ 
/*    */ import cpw.mods.fml.client.FMLClientHandler;
/*    */ import cpw.mods.fml.common.network.ByteBufUtils;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import thaumcraft.client.gui.GuiResearchBrowser;
/*    */ import thaumcraft.client.lib.PlayerNotifications;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ForgottenResearchMessage
/*    */   implements IMessage {
/*    */   private String researchKey;
/*    */   
/*    */   public ForgottenResearchMessage() {}
/*    */   
/*    */   public ForgottenResearchMessage(String key) {
/* 27 */     this.researchKey = key;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 32 */     this.researchKey = ByteBufUtils.readUTF8String(buf);
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 37 */     ByteBufUtils.writeUTF8String(buf, this.researchKey);
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<ForgottenResearchMessage, IMessage>
/*    */   {
/*    */     @SideOnly(Side.CLIENT)
/*    */     public IMessage onMessage(ForgottenResearchMessage message, MessageContext ctx) {
/* 45 */       EntityClientPlayerMP entityClientPlayerMP = FMLClientHandler.instance().getClientPlayerEntity();
/*    */       
/* 47 */       if (message.researchKey != null && message.researchKey.length() > 0) {
/* 48 */         Thaumcraft.proxy.getResearchManager().completeResearch((EntityPlayer)(Minecraft.getMinecraft()).thePlayer, message.researchKey);
/*    */         
/* 50 */         if (message.researchKey.startsWith("@")) {
/* 51 */           PlayerNotifications.addNotification(StatCollector.translateToLocal("notification.research_triggered"));
/* 52 */           (Minecraft.getMinecraft()).thePlayer.playSound("thaumcraft:learn", 0.2F, 1.0F + (Minecraft.getMinecraft()).thePlayer.worldObj.rand.nextFloat() * 0.1F);
/*    */         } 
/*    */         
/* 55 */         if ((Minecraft.getMinecraft()).currentScreen instanceof GuiResearchBrowser) {
/* 56 */           ArrayList<String> al = (ArrayList<String>)GuiResearchBrowser.completedResearch.get((Minecraft.getMinecraft()).thePlayer.getCommandSenderName());
/* 57 */           if (al == null) {
/* 58 */             al = new ArrayList<>();
/*    */           }
/* 60 */           al.add(message.researchKey);
/* 61 */           GuiResearchBrowser.completedResearch.put((Minecraft.getMinecraft()).thePlayer.getCommandSenderName(), al);
/* 62 */           ((GuiResearchBrowser)(Minecraft.getMinecraft()).currentScreen).updateResearch();
/*    */         } 
/*    */       } 
/*    */       
/* 66 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\ForgottenResearchMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */