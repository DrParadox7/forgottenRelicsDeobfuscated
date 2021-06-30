/*    */ package com.integral.forgottenrelics.proxy;
/*    */ 
/*    */ import com.integral.forgottenrelics.entities.EntityBabylonWeaponSS;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.ItemRenderer;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import vazkii.botania.client.core.helper.ShaderHelper;
/*    */ import vazkii.botania.common.item.relic.ItemKingKey;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderBabylonWeaponSS
/*    */   extends Render
/*    */ {
/* 24 */   private static final ResourceLocation babylon = new ResourceLocation("botania:textures/misc/babylon.png");
/*    */ 
/*    */   
/*    */   public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
/* 28 */     EntityBabylonWeaponSS weapon = (EntityBabylonWeaponSS)par1Entity;
/* 29 */     GL11.glPushMatrix();
/* 30 */     GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/* 31 */     GL11.glRotatef(weapon.getRotation(), 0.0F, 1.0F, 0.0F);
/*    */     
/* 33 */     int live = weapon.getLiveTicks();
/* 34 */     int delay = weapon.getDelay();
/* 35 */     float charge = Math.min(10.0F, Math.max(live, weapon.getChargeTicks()) + par9);
/* 36 */     float chargeMul = charge / 10.0F;
/*    */     
/* 38 */     GL11.glEnable(3042);
/* 39 */     GL11.glBlendFunc(770, 771);
/*    */     
/* 41 */     (Minecraft.getMinecraft()).renderEngine.bindTexture(TextureMap.locationItemsTexture);
/* 42 */     GL11.glPushMatrix();
/* 43 */     float s = 1.5F;
/* 44 */     GL11.glScalef(s, s, s);
/* 45 */     GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/* 46 */     GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
/* 47 */     IIcon icon = ItemKingKey.weaponIcons[weapon.getVariety()];
/* 48 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, chargeMul);
/* 49 */     float f = icon.getMinU();
/* 50 */     float f1 = icon.getMaxU();
/* 51 */     float f2 = icon.getMinV();
/* 52 */     float f3 = icon.getMaxV();
/*    */     
/* 54 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
/* 55 */     GL11.glDisable(2896);
/* 56 */     ItemRenderer.renderItemIn2D(Tessellator.instance, f1, f2, f, f3, icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
/* 57 */     GL11.glPopMatrix();
/*    */     
/* 59 */     GL11.glDisable(2884);
/* 60 */     GL11.glShadeModel(7425);
/* 61 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, chargeMul);
/*    */     
/* 63 */     (Minecraft.getMinecraft()).renderEngine.bindTexture(babylon);
/*    */     
/* 65 */     Tessellator tes = Tessellator.instance;
/* 66 */     ShaderHelper.useShader(ShaderHelper.halo);
/* 67 */     Random rand = new Random(weapon.getUniqueID().getMostSignificantBits());
/* 68 */     GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 69 */     GL11.glTranslatef(0.0F, -0.3F + rand.nextFloat() * 0.1F, 1.0F);
/*    */     
/* 71 */     s = chargeMul;
/* 72 */     if (live > delay)
/* 73 */       s -= Math.min(1.0F, ((live - delay) + par9) * 0.2F); 
/* 74 */     s *= 2.0F;
/* 75 */     GL11.glScalef(s, s, s);
/*    */     
/* 77 */     GL11.glRotatef(charge * 9.0F + (weapon.ticksExisted + par9) * 0.5F + rand.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/*    */     
/* 79 */     tes.startDrawingQuads();
/* 80 */     tes.addVertexWithUV(-1.0D, 0.0D, -1.0D, 0.0D, 0.0D);
/* 81 */     tes.addVertexWithUV(-1.0D, 0.0D, 1.0D, 0.0D, 1.0D);
/* 82 */     tes.addVertexWithUV(1.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 83 */     tes.addVertexWithUV(1.0D, 0.0D, -1.0D, 1.0D, 0.0D);
/* 84 */     tes.draw();
/*    */     
/* 86 */     ShaderHelper.releaseShader();
/*    */     
/* 88 */     GL11.glEnable(2896);
/* 89 */     GL11.glShadeModel(7424);
/* 90 */     GL11.glEnable(2884);
/* 91 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 96 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\proxy\RenderBabylonWeaponSS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */