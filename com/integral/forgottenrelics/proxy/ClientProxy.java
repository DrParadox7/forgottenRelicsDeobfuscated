/*     */ package com.integral.forgottenrelics.proxy;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.entities.EntityBabylonWeaponSS;
/*     */ import com.integral.forgottenrelics.entities.EntityChaoticOrb;
/*     */ import com.integral.forgottenrelics.entities.EntityCrimsonOrb;
/*     */ import com.integral.forgottenrelics.entities.EntityDarkMatterOrb;
/*     */ import com.integral.forgottenrelics.entities.EntityRageousMissile;
/*     */ import com.integral.forgottenrelics.entities.EntityThunderpealOrb;
/*     */ import com.integral.forgottenrelics.entities.FXBurst;
/*     */ import com.integral.forgottenrelics.entities.FXWisp;
/*     */ import com.integral.forgottenrelics.handlers.RelicsKeybindHandler;
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import cpw.mods.fml.client.registry.RenderingRegistry;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.particle.EntityPortalFX;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.fx.bolt.FXLightningBolt;
/*     */ import thaumcraft.client.renderers.entity.RenderEldritchOrb;
/*     */ import thaumcraft.client.renderers.entity.RenderElectricOrb;
/*     */ import thaumcraft.client.renderers.entity.RenderPrimalOrb;
/*     */ 
/*     */ 
/*     */ public class ClientProxy
/*     */   extends CommonProxy
/*     */ {
/*     */   public void registerKeybinds() {
/*  32 */     RelicsKeybindHandler.registerKeybinds();
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerRenderers(Main ins) {}
/*     */ 
/*     */   
/*     */   public void registerDisplayInformation() {
/*  40 */     RenderingRegistry.registerEntityRenderingHandler(EntityRageousMissile.class, (Render)new RenderPrimalOrb());
/*  41 */     RenderingRegistry.registerEntityRenderingHandler(EntityChaoticOrb.class, (Render)new RenderPrimalOrb());
/*  42 */     RenderingRegistry.registerEntityRenderingHandler(EntityCrimsonOrb.class, new RenderCrimsonOrb());
/*  43 */     RenderingRegistry.registerEntityRenderingHandler(EntityDarkMatterOrb.class, (Render)new RenderEldritchOrb());
/*  44 */     RenderingRegistry.registerEntityRenderingHandler(EntityBabylonWeaponSS.class, new RenderBabylonWeaponSS());
/*  45 */     RenderingRegistry.registerEntityRenderingHandler(EntityThunderpealOrb.class, (Render)new RenderElectricOrb());
/*     */   }
/*     */ 
/*     */   
/*     */   public void lunarBurst(World world, double x, double y, double z, float size) {
/*  50 */     FXBurst ef = new FXBurst(world, x, y, z, size);
/*  51 */     (FMLClientHandler.instance().getClient()).effectRenderer.addEffect((EntityFX)ef);
/*     */   }
/*     */ 
/*     */   
/*     */   public void lightning(World world, double sx, double sy, double sz, double ex, double ey, double ez, int dur, float curve, int speed, int type, float width) {
/*  56 */     FXLightningBolt bolt = new FXLightningBolt(world, sx, sy, sz, ex, ey, ez, world.rand.nextLong(), dur, curve, speed);
/*     */     
/*  58 */     bolt.defaultFractal();
/*  59 */     bolt.setType(type);
/*  60 */     bolt.setWidth(width);
/*  61 */     bolt.finalizeBolt();
/*     */   }
/*     */   
/*     */   public void wispFX4(World worldObj, double posX, double posY, double posZ, Entity target, int type, boolean shrink, float gravity) {
/*  65 */     FXWisp ef = new FXWisp(worldObj, posX, posY, posZ, target, type);
/*  66 */     ef.setGravity(gravity);
/*  67 */     ef.shrink = shrink;
/*  68 */     ParticleEngine.instance.addEffect(worldObj, (EntityFX)ef);
/*     */   }
/*     */ 
/*     */   
/*     */   public void spawnSuperParticle(World world, String particleType, double x, double y, double z, double velX, double velY, double velZ, float particleSize, double renderDistance) {
/*  73 */     Minecraft mc = FMLClientHandler.instance().getClient();
/*  74 */     if (mc != null && mc.renderViewEntity != null && mc.effectRenderer != null && mc.theWorld == world) {
/*     */       
/*  76 */       double distX = mc.renderViewEntity.posX - x;
/*  77 */       double distY = mc.renderViewEntity.posY - y;
/*  78 */       double distZ = mc.renderViewEntity.posZ - z;
/*     */       
/*  80 */       EntityFX particle = null;
/*     */       
/*  82 */       double maxDist = renderDistance;
/*     */ 
/*     */       
/*  85 */       if (distX * distX + distY * distY + distZ * distZ < maxDist * maxDist) {
/*     */         EntityPortalFX entityPortalFX;
/*     */ 
/*     */         
/*  89 */         if (particleType.equals("portalstuff"))
/*     */         {
/*  91 */           entityPortalFX = new EntityPortalFX(world, x, y, z, velX, velY, velZ);
/*     */         }
/*     */ 
/*     */         
/*  95 */         if (entityPortalFX != null) {
/*     */           
/*  97 */           ((EntityFX)entityPortalFX).prevPosX = ((EntityFX)entityPortalFX).posX;
/*  98 */           ((EntityFX)entityPortalFX).prevPosY = ((EntityFX)entityPortalFX).posY;
/*  99 */           ((EntityFX)entityPortalFX).prevPosZ = ((EntityFX)entityPortalFX).posZ;
/*     */ 
/*     */ 
/*     */           
/* 103 */           mc.effectRenderer.addEffect((EntityFX)entityPortalFX);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\proxy\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */