/*    */ package com.integral.forgottenrelics.handlers;
/*    */ 
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ import net.minecraftforge.common.util.EnumHelper;
/*    */ 
/*    */ public class RelicsMaterialHandler
/*    */ {
/*  9 */   public static Item.ToolMaterial materialParadoxicalStuff = EnumHelper.addToolMaterial("PARADOXICALSTUFF", 4, 3000, 16.0F, -4.0F, 100);
/* 10 */   public static ItemArmor.ArmorMaterial materialNobleGold = EnumHelper.addArmorMaterial("NOBLEGOLD", 40, new int[] { 3, 9, 6, 3 }, 0);
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\handlers\RelicsMaterialHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */