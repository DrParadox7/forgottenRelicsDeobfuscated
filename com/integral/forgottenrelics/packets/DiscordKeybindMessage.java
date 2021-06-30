/*    */ package com.integral.forgottenrelics.packets;
/*    */ 
/*    */ import com.integral.forgottenrelics.Main;
/*    */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class DiscordKeybindMessage
/*    */   implements IMessage {
/*    */   private boolean doIt;
/*    */   
/*    */   public DiscordKeybindMessage() {}
/*    */   
/*    */   public DiscordKeybindMessage(boolean doIt) {
/* 20 */     this.doIt = doIt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 25 */     this.doIt = buf.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 30 */     buf.writeBoolean(this.doIt);
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<DiscordKeybindMessage, IMessage>
/*    */   {
/*    */     public IMessage onMessage(DiscordKeybindMessage message, MessageContext ctx) {
/* 37 */       EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).playerEntity;
/* 38 */       ItemStack stack = SuperpositionHandler.findFirst((EntityPlayer)entityPlayerMP, Main.itemTeleportationTome);
/*    */       
/* 40 */       if ((message.doIt & ((stack != null) ? 1 : 0) & SuperpositionHandler.hasBauble((EntityPlayer)entityPlayerMP, Main.itemDiscordRing)) != 0) {
/* 41 */         Main.itemTeleportationTome.onItemRightClick(stack, ((EntityPlayer)entityPlayerMP).worldObj, (EntityPlayer)entityPlayerMP);
/*    */       }
/*    */       
/* 44 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\DiscordKeybindMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */