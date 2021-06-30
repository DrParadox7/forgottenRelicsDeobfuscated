/*    */ package com.integral.forgottenrelics.packets;
/*    */ 
/*    */ import cpw.mods.fml.client.FMLClientHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class ItemUseMessage
/*    */   implements IMessage {
/*    */   private int duration;
/*    */   
/*    */   public ItemUseMessage() {}
/*    */   
/*    */   public ItemUseMessage(int duration) {
/* 20 */     this.duration = duration;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 25 */     this.duration = buf.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 30 */     buf.writeInt(this.duration);
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<ItemUseMessage, IMessage>
/*    */   {
/*    */     @SideOnly(Side.CLIENT)
/*    */     public IMessage onMessage(ItemUseMessage message, MessageContext ctx) {
/* 38 */       EntityClientPlayerMP entityClientPlayerMP = FMLClientHandler.instance().getClientPlayerEntity();
/* 39 */       ItemStack stack = entityClientPlayerMP.getHeldItem();
/*    */       
/* 41 */       if (stack != null) {
/* 42 */         entityClientPlayerMP.setItemInUse(stack, message.duration);
/*    */       }
/* 44 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\packets\ItemUseMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */