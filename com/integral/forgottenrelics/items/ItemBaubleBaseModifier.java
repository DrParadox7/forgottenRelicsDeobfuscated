/*    */ package com.integral.forgottenrelics.items;
/*    */ 
/*    */ import com.google.common.collect.HashMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ItemBaubleBaseModifier
/*    */   extends ItemBaubleBase
/*    */ {
/* 17 */   Multimap<String, AttributeModifier> attributes = (Multimap<String, AttributeModifier>)HashMultimap.create();
/*    */   
/*    */   public ItemBaubleBaseModifier(String name) {
/* 20 */     super(name);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquippedOrLoadedIntoWorld(ItemStack stack, EntityLivingBase player) {
/* 25 */     this.attributes.clear();
/* 26 */     fillModifiers(this.attributes, stack);
/* 27 */     player.getAttributeMap().applyAttributeModifiers(this.attributes);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequipped(ItemStack stack, EntityLivingBase player) {
/* 32 */     this.attributes.clear();
/* 33 */     fillModifiers(this.attributes, stack);
/* 34 */     player.getAttributeMap().removeAttributeModifiers(this.attributes);
/*    */   }
/*    */   
/*    */   abstract void fillModifiers(Multimap<String, AttributeModifier> paramMultimap, ItemStack paramItemStack);
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemBaubleBaseModifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */