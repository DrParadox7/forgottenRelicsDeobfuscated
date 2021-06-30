/*     */ package com.integral.forgottenrelics.items;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.entities.EntityCrimsonOrb;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.IWarpingGear;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import vazkii.botania.common.core.helper.Vector3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemCrimsonSpell
/*     */   extends Item
/*     */   implements IWarpingGear
/*     */ {
/*  39 */   public static final int AerCost = (int)(0.0F * RelicsConfigHandler.chaosTomeVisMult);
/*  40 */   public static final int TerraCost = (int)(0.0F * RelicsConfigHandler.chaosTomeVisMult);
/*  41 */   public static final int IgnisCost = (int)(480.0F * RelicsConfigHandler.chaosTomeVisMult);
/*  42 */   public static final int AquaCost = (int)(0.0F * RelicsConfigHandler.chaosTomeVisMult);
/*  43 */   public static final int OrdoCost = (int)(0.0F * RelicsConfigHandler.chaosTomeVisMult);
/*  44 */   public static final int PerditioCost = (int)(360.0F * RelicsConfigHandler.chaosTomeVisMult);
/*     */   
/*     */   public static final float SEARCH_RANGE = 3.0F;
/*     */ 
/*     */   
/*     */   public ItemCrimsonSpell() {
/*  50 */     setMaxStackSize(1);
/*  51 */     setUnlocalizedName("ItemCrimsonSpell");
/*  52 */     setCreativeTab(Main.tabForgottenRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean b) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
/*  74 */     if (GuiScreen.isShiftKeyDown()) {
/*  75 */       par3List.add(StatCollector.translateToLocal("item.ItemCrimsonSpell1.lore"));
/*  76 */       par3List.add(StatCollector.translateToLocal("item.ItemCrimsonSpell2.lore"));
/*  77 */       par3List.add(StatCollector.translateToLocal("item.ItemCrimsonSpell3.lore"));
/*  78 */       par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*  79 */       par3List.add(StatCollector.translateToLocal("item.ItemCrimsonSpell4.lore"));
/*  80 */       par3List.add(StatCollector.translateToLocal("item.ItemCrimsonSpell5.lore"));
/*  81 */       par3List.add(StatCollector.translateToLocal("item.ItemCrimsonSpell6_1.lore") + " " + (int)RelicsConfigHandler.crimsonSpellDamageMIN + "-" + (int)RelicsConfigHandler.crimsonSpellDamageMAX + " " + StatCollector.translateToLocal("item.ItemCrimsonSpell6_2.lore"));
/*  82 */     } else if (GuiScreen.isCtrlKeyDown()) {
/*  83 */       par3List.add(StatCollector.translateToLocal("item.FRVisPerCast.lore"));
/*  84 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRIgnisCost.lore") + (IgnisCost / 100.0D));
/*  85 */       this; par3List.add(" " + StatCollector.translateToLocal("item.FRPerditioCost.lore") + (PerditioCost / 100.0D));
/*     */     } else {
/*     */       
/*  88 */       par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
/*  89 */       par3List.add(StatCollector.translateToLocal("item.FRViscostTooltip.lore"));
/*     */     } 
/*     */     
/*  92 */     par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack itemStack) {
/*  99 */     return EnumRarity.epic;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IIconRegister iconRegister) {
/* 105 */     this.itemIcon = iconRegister.registerIcon("forgottenrelics:Crimson_Spell");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean spawnOrb(World world, EntityPlayer player, EntityLivingBase target) {
/* 110 */     if (!world.isRemote) {
/*     */       
/* 112 */       Vector3 originalPos = Vector3.fromEntityCenter((Entity)player);
/* 113 */       Vector3 vector = originalPos.add((new Vector3(player.getLookVec())).multiply(1.0D));
/* 114 */       vector.y += 0.5D;
/*     */       
/* 116 */       Vector3 motion = (new Vector3(player.getLookVec())).multiply(0.75D);
/*     */       
/* 118 */       EntityCrimsonOrb orb = new EntityCrimsonOrb(world, (EntityLivingBase)player, target, true);
/* 119 */       orb.setPosition(vector.x, vector.y, vector.z);
/*     */       
/* 121 */       orb.motionX = motion.x;
/* 122 */       orb.motionY = motion.y;
/* 123 */       orb.motionZ = motion.z;
/*     */       
/* 125 */       world.playSoundEffect(player.posX, player.posY, player.posZ, "thaumcraft:egattack", 0.6F, 0.8F + (float)Math.random() * 0.2F);
/* 126 */       world.spawnEntityInWorld((Entity)orb);
/*     */       
/* 128 */       return true;
/*     */     } 
/*     */     
/* 131 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
/* 137 */     if (!world.isRemote && 
/* 138 */       !SuperpositionHandler.isOnCoodown(player)) {
/*     */       
/* 140 */       int gRange = 32;
/*     */       
/* 142 */       EntityLivingBase target = null;
/*     */       
/* 144 */       Vector3 vec = Vector3.fromEntityCenter((Entity)player);
/* 145 */       List<EntityLivingBase> entities = new ArrayList<>();
/* 146 */       int distance = 1;
/* 147 */       while (entities.size() == 0 && distance < gRange) {
/*     */         
/* 149 */         float superposition = 0.0F;
/* 150 */         if (distance > 10)
/* 151 */           superposition = 3.0F; 
/* 152 */         if (distance > 20) {
/* 153 */           superposition = 5.0F;
/*     */         }
/* 155 */         vec.add((new Vector3(player.getLookVec())).multiply(distance));
/* 156 */         vec.y += 0.5D;
/* 157 */         entities = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(vec.x - (3.0F + superposition), vec.y - (3.0F + superposition), vec.z - (3.0F + superposition), vec.x + (3.0F + superposition), vec.y + (3.0F + superposition), vec.z + (3.0F + superposition)));
/* 158 */         if (entities.contains(player)) {
/* 159 */           entities.remove(player);
/*     */         }
/* 161 */         distance++;
/*     */       } 
/*     */ 
/*     */       
/* 165 */       boolean notFound = false;
/*     */       
/* 167 */       if (entities.size() == 0) {
/* 168 */         notFound = true;
/* 169 */         entities = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(player.posX - gRange, player.posY - gRange, player.posZ - gRange, player.posX + gRange, player.posY + gRange, player.posZ + gRange));
/*     */       } 
/*     */       
/* 172 */       if ((((entities.size() > 0) ? 1 : 0) & notFound) != 0)
/*     */       {
/* 174 */         for (int counter = entities.size() - 1; counter >= 0; counter--) {
/* 175 */           if (!((EntityLivingBase)entities.get(counter)).canEntityBeSeen((Entity)player)) {
/* 176 */             entities.remove(counter);
/* 177 */             counter = entities.size();
/*     */           } 
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 183 */       if (entities.contains(player)) {
/* 184 */         entities.remove(player);
/*     */       }
/* 186 */       this; this; if (WandManager.consumeVisFromInventory(player, (new AspectList()).add(Aspect.FIRE, IgnisCost).add(Aspect.ENTROPY, PerditioCost))) {
/*     */         
/* 188 */         if (!world.isRemote) {
/* 189 */           Container inventory = player.inventoryContainer;
/* 190 */           ((EntityPlayerMP)player).sendContainerToPlayer(inventory);
/*     */         } 
/*     */         
/* 193 */         if (entities.size() > 0) {
/* 194 */           target = entities.get((int)((entities.size() - 1) * Math.random()));
/*     */         } else {
/* 196 */           target = null;
/*     */         } 
/* 198 */         spawnOrb(world, player, target);
/* 199 */         SuperpositionHandler.setCasted(player, 30, true);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 205 */     return stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFull3D() {
/* 210 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWarp(ItemStack arg0, EntityPlayer arg1) {
/* 215 */     return 3;
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\items\ItemCrimsonSpell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */