/*     */ package com.integral.forgottenrelics;
/*     */ 
/*     */ import com.integral.forgottenrelics.entities.EntityBabylonWeaponSS;
/*     */ import com.integral.forgottenrelics.entities.EntityChaoticOrb;
/*     */ import com.integral.forgottenrelics.entities.EntityCrimsonOrb;
/*     */ import com.integral.forgottenrelics.entities.EntityDarkMatterOrb;
/*     */ import com.integral.forgottenrelics.entities.EntityLunarFlare;
/*     */ import com.integral.forgottenrelics.entities.EntityRageousMissile;
/*     */ import com.integral.forgottenrelics.entities.EntityShinyEnergy;
/*     */ import com.integral.forgottenrelics.entities.EntitySoulEnergy;
/*     */ import com.integral.forgottenrelics.entities.EntityThunderpealOrb;
/*     */ import com.integral.forgottenrelics.handlers.RecipeOblivionStone;
/*     */ import com.integral.forgottenrelics.handlers.RelicsChunkLoadCallback;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import com.integral.forgottenrelics.handlers.RelicsEventHandler;
/*     */ import com.integral.forgottenrelics.handlers.RelicsKeybindHandler;
/*     */ import com.integral.forgottenrelics.handlers.RelicsMaterialHandler;
/*     */ import com.integral.forgottenrelics.handlers.RelicsUpdateHandler;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import com.integral.forgottenrelics.items.ItemAdvancedMiningCharm;
/*     */ import com.integral.forgottenrelics.items.ItemAncientAegis;
/*     */ import com.integral.forgottenrelics.items.ItemApotheosis;
/*     */ import com.integral.forgottenrelics.items.ItemArcanum;
/*     */ import com.integral.forgottenrelics.items.ItemChaosCore;
/*     */ import com.integral.forgottenrelics.items.ItemChaosTome;
/*     */ import com.integral.forgottenrelics.items.ItemCrimsonSpell;
/*     */ import com.integral.forgottenrelics.items.ItemDarkSunRing;
/*     */ import com.integral.forgottenrelics.items.ItemDeificAmulet;
/*     */ import com.integral.forgottenrelics.items.ItemDimensionalMirror;
/*     */ import com.integral.forgottenrelics.items.ItemDiscordRing;
/*     */ import com.integral.forgottenrelics.items.ItemDormantArcanum;
/*     */ import com.integral.forgottenrelics.items.ItemEldritchSpell;
/*     */ import com.integral.forgottenrelics.items.ItemFalseJustice;
/*     */ import com.integral.forgottenrelics.items.ItemFateTome;
/*     */ import com.integral.forgottenrelics.items.ItemGhastlySkull;
/*     */ import com.integral.forgottenrelics.items.ItemLunarFlares;
/*     */ import com.integral.forgottenrelics.items.ItemMiningCharm;
/*     */ import com.integral.forgottenrelics.items.ItemMissileTome;
/*     */ import com.integral.forgottenrelics.items.ItemObeliskDrainer;
/*     */ import com.integral.forgottenrelics.items.ItemOblivionAmulet;
/*     */ import com.integral.forgottenrelics.items.ItemOblivionStone;
/*     */ import com.integral.forgottenrelics.items.ItemOmegaCore;
/*     */ import com.integral.forgottenrelics.items.ItemOverthrower;
/*     */ import com.integral.forgottenrelics.items.ItemParadox;
/*     */ import com.integral.forgottenrelics.items.ItemShinyStone;
/*     */ import com.integral.forgottenrelics.items.ItemSoulTome;
/*     */ import com.integral.forgottenrelics.items.ItemSuperpositionRing;
/*     */ import com.integral.forgottenrelics.items.ItemTelekinesisTome;
/*     */ import com.integral.forgottenrelics.items.ItemTeleportationTome;
/*     */ import com.integral.forgottenrelics.items.ItemTerrorCrown;
/*     */ import com.integral.forgottenrelics.items.ItemThunderpeal;
/*     */ import com.integral.forgottenrelics.items.ItemVoidGrimoire;
/*     */ import com.integral.forgottenrelics.items.ItemWastelayer;
/*     */ import com.integral.forgottenrelics.items.ItemWeatherStone;
/*     */ import com.integral.forgottenrelics.items.ItemXPTome;
/*     */ import com.integral.forgottenrelics.minetweaker.MineTweakerIntegrator;
/*     */ import com.integral.forgottenrelics.packets.ApotheosisParticleMessage;
/*     */ import com.integral.forgottenrelics.packets.ArcLightningMessage;
/*     */ import com.integral.forgottenrelics.packets.BanishmentCastingMessage;
/*     */ import com.integral.forgottenrelics.packets.BurstMessage;
/*     */ import com.integral.forgottenrelics.packets.DiscordKeybindMessage;
/*     */ import com.integral.forgottenrelics.packets.EntityMotionMessage;
/*     */ import com.integral.forgottenrelics.packets.ForgottenResearchMessage;
/*     */ import com.integral.forgottenrelics.packets.GuardianVanishMessage;
/*     */ import com.integral.forgottenrelics.packets.ICanSwingMySwordMessage;
/*     */ import com.integral.forgottenrelics.packets.InfernalParticleMessage;
/*     */ import com.integral.forgottenrelics.packets.ItemUseMessage;
/*     */ import com.integral.forgottenrelics.packets.LightningBoltMessage;
/*     */ import com.integral.forgottenrelics.packets.LightningMessage;
/*     */ import com.integral.forgottenrelics.packets.LunarBurstMessage;
/*     */ import com.integral.forgottenrelics.packets.LunarFlaresParticleMessage;
/*     */ import com.integral.forgottenrelics.packets.NotificationMessage;
/*     */ import com.integral.forgottenrelics.packets.OverthrowChatMessage;
/*     */ import com.integral.forgottenrelics.packets.PacketVoidMessage;
/*     */ import com.integral.forgottenrelics.packets.PlayerMotionUpdateMessage;
/*     */ import com.integral.forgottenrelics.packets.PortalTraceMessage;
/*     */ import com.integral.forgottenrelics.packets.TelekinesisAttackMessage;
/*     */ import com.integral.forgottenrelics.packets.TelekinesisParticleMessage;
/*     */ import com.integral.forgottenrelics.packets.TelekinesisUseMessage;
/*     */ import com.integral.forgottenrelics.proxy.CommonProxy;
/*     */ import com.integral.forgottenrelics.research.RelicsAspectRegistry;
/*     */ import com.integral.forgottenrelics.research.RelicsResearchRegistry;
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.Loader;
/*     */ import cpw.mods.fml.common.Mod;
/*     */ import cpw.mods.fml.common.Mod.EventHandler;
/*     */ import cpw.mods.fml.common.Mod.Instance;
/*     */ import cpw.mods.fml.common.SidedProxy;
/*     */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLServerStartingEvent;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import cpw.mods.fml.common.registry.EntityRegistry;
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraftforge.common.ForgeChunkManager;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.oredict.RecipeSorter;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thaumcraft.common.config.Config;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mod(modid = "ForgottenRelics", version = "1.7.3", name = "Forgotten Relics", dependencies = "required-after:Thaumcraft@[4.2.3.5,);required-after:Botania@[r1.8-237,)")
/*     */ public class Main
/*     */ {
/*     */   public static final String MODID = "ForgottenRelics";
/*     */   public static final String VERSION = "1.7.3";
/*     */   public static final String NAME = "Forgotten Relics";
/*     */   public static final String RELEASE_TYPE = "Beta";
/*     */   public static SimpleNetworkWrapper packetInstance;
/*     */   @SidedProxy(clientSide = "com.integral.forgottenrelics.proxy.ClientProxy", serverSide = "com.integral.forgottenrelics.proxy.CommonProxy")
/*     */   public static CommonProxy proxy;
/*     */   @Instance("ForgottenRelics")
/*     */   public static Main instance;
/* 134 */   public static List<String> darkRingDamageNegations = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   public static HashMap<EntityPlayer, Integer> castingCooldowns = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public static HashMap<String, List<ItemStack>> forgottenKnowledge = new HashMap<>();
/*     */   
/*     */   public static Item itemFalseJustice;
/*     */   
/*     */   public static Item itemDeificAmulet;
/*     */   
/*     */   public static Item itemParadox;
/*     */   
/*     */   public static Item itemOblivionAmulet;
/*     */   
/*     */   public static Item itemArcanum;
/*     */   
/*     */   public static Item itemDormantArcanum;
/*     */   public static Item itemFateTome;
/*     */   public static Item itemDarkSunRing;
/*     */   public static Item itemChaosCore;
/*     */   public static Item itemMiningCharm;
/*     */   public static Item itemAdvancedMiningCharm;
/*     */   public static ItemTelekinesisTome itemTelekinesisTome;
/*     */   public static Item itemAncientAegis;
/*     */   public static Item itemMissileTome;
/*     */   public static Item itemCrimsonSpell;
/*     */   public static Item itemGhastlySkull;
/*     */   public static Item itemObeliskDrainer;
/*     */   public static Item itemEldritchSpell;
/*     */   public static Item itemLunarFlares;
/*     */   public static Item itemTeleportationTome;
/*     */   public static Item itemShinyStone;
/*     */   public static Item itemSuperpositionRing;
/*     */   public static Item itemSoulTome;
/*     */   public static Item itemDimensionalMirror;
/*     */   public static Item itemWeatherStone;
/*     */   public static Item itemApotheosis;
/*     */   public static Item itemChaosTome;
/*     */   public static Item itemOmegaCore;
/*     */   public static Item itemXPTome;
/*     */   public static Item itemThunderpeal;
/*     */   public static Item itemTerrorCrown;
/*     */   public static Item itemWastelayer;
/*     */   public static Item itemOverthrower;
/*     */   public static Item itemDiscordRing;
/*     */   public static Item itemVoidGrimoire;
/*     */   public static Item itemOblivionStone;
/* 191 */   public RelicsConfigHandler configHandler = new RelicsConfigHandler();
/* 192 */   public static final Logger log = LogManager.getLogger("ForgottenRelics");
/*     */   
/*     */   public static final int howCoolAmI = 2147483647;
/*     */   
/*     */   @EventHandler
/*     */   public void load(FMLInitializationEvent event) {
/* 198 */     proxy.registerDisplayInformation();
/*     */     
/* 200 */     RecipeSorter.register("forge:oblivionstone", RecipeOblivionStone.class, RecipeSorter.Category.SHAPELESS, "after:forge:shapelessorenbt");
/*     */ 
/*     */     
/* 203 */     darkRingDamageNegations.add(DamageSource.lava.damageType);
/* 204 */     darkRingDamageNegations.add(DamageSource.inFire.damageType);
/* 205 */     darkRingDamageNegations.add(DamageSource.onFire.damageType);
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void serverLoad(FMLServerStartingEvent event) {
/* 211 */     if (Loader.isModLoaded("MineTweaker3")) {
/* 212 */       MineTweakerIntegrator.registerCommands();
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void preInit(FMLPreInitializationEvent event) {
/* 218 */     this.configHandler.configDisposition(event);
/*     */     
/* 220 */     packetInstance = NetworkRegistry.INSTANCE.newSimpleChannel("RelicsChannel");
/* 221 */     packetInstance.registerMessage(PortalTraceMessage.Handler.class, PortalTraceMessage.class, 1, Side.CLIENT);
/* 222 */     packetInstance.registerMessage(BurstMessage.Handler.class, BurstMessage.class, 2, Side.CLIENT);
/* 223 */     packetInstance.registerMessage(ApotheosisParticleMessage.Handler.class, ApotheosisParticleMessage.class, 3, Side.CLIENT);
/* 224 */     packetInstance.registerMessage(LunarBurstMessage.Handler.class, LunarBurstMessage.class, 4, Side.CLIENT);
/* 225 */     packetInstance.registerMessage(LunarFlaresParticleMessage.Handler.class, LunarFlaresParticleMessage.class, 5, Side.CLIENT);
/* 226 */     packetInstance.registerMessage(LightningMessage.Handler.class, LightningMessage.class, 6, Side.CLIENT);
/* 227 */     packetInstance.registerMessage(ArcLightningMessage.Handler.class, ArcLightningMessage.class, 7, Side.CLIENT);
/* 228 */     packetInstance.registerMessage(ICanSwingMySwordMessage.Handler.class, ICanSwingMySwordMessage.class, 8, Side.CLIENT);
/* 229 */     packetInstance.registerMessage(EntityMotionMessage.Handler.class, EntityMotionMessage.class, 9, Side.CLIENT);
/* 230 */     packetInstance.registerMessage(LightningBoltMessage.Handler.class, LightningBoltMessage.class, 10, Side.CLIENT);
/* 231 */     packetInstance.registerMessage(InfernalParticleMessage.Handler.class, InfernalParticleMessage.class, 11, Side.CLIENT);
/* 232 */     packetInstance.registerMessage(ItemUseMessage.Handler.class, ItemUseMessage.class, 12, Side.CLIENT);
/* 233 */     packetInstance.registerMessage(BanishmentCastingMessage.Handler.class, BanishmentCastingMessage.class, 13, Side.CLIENT);
/* 234 */     packetInstance.registerMessage(PlayerMotionUpdateMessage.Handler.class, PlayerMotionUpdateMessage.class, 14, Side.CLIENT);
/* 235 */     packetInstance.registerMessage(NotificationMessage.Handler.class, NotificationMessage.class, 15, Side.CLIENT);
/* 236 */     packetInstance.registerMessage(OverthrowChatMessage.Handler.class, OverthrowChatMessage.class, 16, Side.CLIENT);
/* 237 */     packetInstance.registerMessage(DiscordKeybindMessage.Handler.class, DiscordKeybindMessage.class, 17, Side.SERVER);
/* 238 */     packetInstance.registerMessage(ForgottenResearchMessage.Handler.class, ForgottenResearchMessage.class, 18, Side.CLIENT);
/* 239 */     packetInstance.registerMessage(TelekinesisAttackMessage.Handler.class, TelekinesisAttackMessage.class, 19, Side.SERVER);
/* 240 */     packetInstance.registerMessage(TelekinesisUseMessage.Handler.class, TelekinesisUseMessage.class, 20, Side.SERVER);
/* 241 */     packetInstance.registerMessage(TelekinesisParticleMessage.Handler.class, TelekinesisParticleMessage.class, 21, Side.CLIENT);
/* 242 */     packetInstance.registerMessage(PacketVoidMessage.Handler.class, PacketVoidMessage.class, 22, Side.CLIENT);
/* 243 */     packetInstance.registerMessage(GuardianVanishMessage.Handler.class, GuardianVanishMessage.class, 23, Side.CLIENT);
/*     */     
/* 245 */     itemFalseJustice = (Item)new ItemFalseJustice();
/* 246 */     itemDeificAmulet = (Item)new ItemDeificAmulet();
/* 247 */     itemParadox = (Item)new ItemParadox(RelicsMaterialHandler.materialParadoxicalStuff);
/* 248 */     itemOblivionAmulet = (Item)new ItemOblivionAmulet();
/* 249 */     itemArcanum = (Item)new ItemArcanum();
/* 250 */     itemDormantArcanum = (Item)new ItemDormantArcanum();
/* 251 */     itemFateTome = (Item)new ItemFateTome();
/* 252 */     itemDarkSunRing = (Item)new ItemDarkSunRing();
/* 253 */     itemChaosCore = (Item)new ItemChaosCore();
/* 254 */     itemMiningCharm = (Item)new ItemMiningCharm();
/* 255 */     itemAdvancedMiningCharm = (Item)new ItemAdvancedMiningCharm();
/* 256 */     itemTelekinesisTome = new ItemTelekinesisTome();
/* 257 */     itemAncientAegis = (Item)new ItemAncientAegis();
/* 258 */     itemMissileTome = (Item)new ItemMissileTome();
/* 259 */     itemCrimsonSpell = (Item)new ItemCrimsonSpell();
/* 260 */     itemGhastlySkull = (Item)new ItemGhastlySkull();
/* 261 */     itemObeliskDrainer = (Item)new ItemObeliskDrainer();
/* 262 */     itemEldritchSpell = (Item)new ItemEldritchSpell();
/* 263 */     itemLunarFlares = (Item)new ItemLunarFlares();
/* 264 */     itemTeleportationTome = (Item)new ItemTeleportationTome();
/* 265 */     itemShinyStone = (Item)new ItemShinyStone();
/* 266 */     itemSuperpositionRing = (Item)new ItemSuperpositionRing();
/* 267 */     itemSoulTome = (Item)new ItemSoulTome();
/* 268 */     itemDimensionalMirror = (Item)new ItemDimensionalMirror();
/* 269 */     itemWeatherStone = (Item)new ItemWeatherStone();
/* 270 */     itemApotheosis = (Item)new ItemApotheosis();
/* 271 */     itemChaosTome = (Item)new ItemChaosTome();
/* 272 */     itemOmegaCore = (Item)new ItemOmegaCore();
/* 273 */     itemXPTome = (Item)new ItemXPTome();
/* 274 */     itemThunderpeal = (Item)new ItemThunderpeal();
/* 275 */     itemTerrorCrown = (Item)new ItemTerrorCrown(0, RelicsMaterialHandler.materialNobleGold);
/* 276 */     itemWastelayer = (Item)new ItemWastelayer(RelicsMaterialHandler.materialParadoxicalStuff);
/* 277 */     itemOverthrower = (Item)new ItemOverthrower();
/* 278 */     itemDiscordRing = (Item)new ItemDiscordRing();
/* 279 */     itemVoidGrimoire = (Item)new ItemVoidGrimoire();
/* 280 */     itemOblivionStone = (Item)new ItemOblivionStone();
/*     */     
/* 282 */     GameRegistry.registerItem(itemFalseJustice, "ItemFalseJustice");
/* 283 */     GameRegistry.registerItem(itemDeificAmulet, "ItemDeificAmulet");
/* 284 */     GameRegistry.registerItem(itemParadox, "ItemParadox");
/* 285 */     GameRegistry.registerItem(itemOblivionAmulet, "ItemOblivionAmulet");
/* 286 */     GameRegistry.registerItem(itemArcanum, "ItemArcanum");
/* 287 */     GameRegistry.registerItem(itemDormantArcanum, "ItemDormantArcanum");
/* 288 */     GameRegistry.registerItem(itemFateTome, "ItemFateTome");
/* 289 */     GameRegistry.registerItem(itemDarkSunRing, "ItemDarkSunRing");
/* 290 */     GameRegistry.registerItem(itemChaosCore, "ItemChaosCore");
/* 291 */     GameRegistry.registerItem(itemMiningCharm, "ItemMiningCharm");
/* 292 */     GameRegistry.registerItem(itemAdvancedMiningCharm, "ItemAdvancedMiningCharm");
/* 293 */     GameRegistry.registerItem((Item)itemTelekinesisTome, "ItemTelekinesisTome");
/* 294 */     GameRegistry.registerItem(itemAncientAegis, "ItemAncientAegis");
/* 295 */     GameRegistry.registerItem(itemMissileTome, "ItemMissileTome");
/* 296 */     GameRegistry.registerItem(itemCrimsonSpell, "ItemCrimsonSpell");
/* 297 */     GameRegistry.registerItem(itemGhastlySkull, "itemGhastlySkull");
/* 298 */     GameRegistry.registerItem(itemObeliskDrainer, "ItemObeliskDrainer");
/* 299 */     GameRegistry.registerItem(itemEldritchSpell, "ItemEldritchSpell");
/* 300 */     GameRegistry.registerItem(itemLunarFlares, "ItemLunarFlares");
/* 301 */     GameRegistry.registerItem(itemTeleportationTome, "ItemTeleportationTome");
/* 302 */     GameRegistry.registerItem(itemShinyStone, "ItemShinyStone");
/* 303 */     GameRegistry.registerItem(itemSuperpositionRing, "ItemSuperpositionRing");
/* 304 */     GameRegistry.registerItem(itemSoulTome, "ItemSoulTome");
/* 305 */     GameRegistry.registerItem(itemDimensionalMirror, "ItemDimensionalMirror");
/* 306 */     GameRegistry.registerItem(itemWeatherStone, "ItemWeatherStone");
/* 307 */     GameRegistry.registerItem(itemApotheosis, "ItemApotheosis");
/* 308 */     GameRegistry.registerItem(itemChaosTome, "ItemChaosTome");
/* 309 */     GameRegistry.registerItem(itemOmegaCore, "ItemOmegaCore");
/* 310 */     GameRegistry.registerItem(itemXPTome, "ItemXPTome");
/* 311 */     GameRegistry.registerItem(itemThunderpeal, "ItemThunderpeal");
/* 312 */     GameRegistry.registerItem(itemTerrorCrown, "ItemTerrorCrown");
/* 313 */     GameRegistry.registerItem(itemOverthrower, "ItemOverthrower");
/* 314 */     GameRegistry.registerItem(itemDiscordRing, "ItemDiscordRing");
/* 315 */     GameRegistry.registerItem(itemVoidGrimoire, "ItemVoidGrimoire");
/* 316 */     GameRegistry.registerItem(itemOblivionStone, "ItemOblivionStone");
/*     */     
/* 318 */     EntityRegistry.registerModEntity(EntityRageousMissile.class, "SomeVeryRageousStuff", 237, instance, 64, 20, true);
/* 319 */     EntityRegistry.registerModEntity(EntityCrimsonOrb.class, "EntityCrimsonOrb", 238, instance, 64, 20, true);
/* 320 */     EntityRegistry.registerModEntity(EntityDarkMatterOrb.class, "EntityDarkMatterOrb", 239, instance, 64, 20, true);
/* 321 */     EntityRegistry.registerModEntity(EntitySoulEnergy.class, "EntitySoulEnergy", 240, instance, 64, 20, true);
/* 322 */     EntityRegistry.registerModEntity(EntityLunarFlare.class, "EntityLunarFlare", 241, instance, 196, 20, true);
/* 323 */     EntityRegistry.registerModEntity(EntityShinyEnergy.class, "EntityShinyEnergy", 242, instance, 64, 20, true);
/* 324 */     EntityRegistry.registerModEntity(EntityBabylonWeaponSS.class, "EntityBabylonWeaponSS", 243, instance, 64, 20, true);
/* 325 */     EntityRegistry.registerModEntity(EntityChaoticOrb.class, "EntityChaoticOrb", 245, instance, 64, 20, true);
/* 326 */     EntityRegistry.registerModEntity(EntityThunderpealOrb.class, "EntityThunderpealOrb", 246, instance, 64, 20, true);
/*     */     
/* 328 */     RelicsAspectRegistry.registerItemAspectsFirst();
/*     */     
/* 330 */     proxy.registerKeybinds();
/*     */     
/* 332 */     MinecraftForge.EVENT_BUS.register(new RelicsUpdateHandler());
/*     */     
/* 334 */     FMLCommonHandler.instance().bus().register(new RelicsKeybindHandler());
/* 335 */     MinecraftForge.EVENT_BUS.register(new RelicsEventHandler());
/*     */     
/* 337 */     proxy.registerRenderers(this);
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public static void postLoad(FMLPostInitializationEvent event) {
/* 343 */     GameRegistry.addRecipe((IRecipe)new RecipeOblivionStone());
/*     */     
/* 345 */     RelicsResearchRegistry.integrateInfusion();
/* 346 */     RelicsAspectRegistry.registerItemAspectsLast();
/* 347 */     RelicsResearchRegistry.integrateResearch();
/*     */     
/* 349 */     log.info("Forgotten Knowledge list:");
/* 350 */     for (String key : forgottenKnowledge.keySet()) {
/* 351 */       log.info(" - " + key);
/*     */     }
/* 353 */     if (RelicsConfigHandler.forgottenKnowledgeOverridingEnabled) {
/* 354 */       SuperpositionHandler.setupOverrides();
/*     */     }
/* 356 */     Config.shieldRecharge = RelicsConfigHandler.runicRechargeSpeed;
/* 357 */     Config.shieldWait = RelicsConfigHandler.runicRechargeDelay;
/* 358 */     Config.shieldCost = RelicsConfigHandler.runicCost;
/* 359 */     Config.notificationDelay = RelicsConfigHandler.notificationDelay;
/*     */     
/* 361 */     if (Loader.isModLoaded("MineTweaker3")) {
/* 362 */       MineTweakerIntegrator.init();
/*     */     }
/*     */     
/* 365 */     ForgeChunkManager.setForcedChunkLoadingCallback(instance, (ForgeChunkManager.LoadingCallback)new RelicsChunkLoadCallback());
/*     */     
/* 367 */     RelicsUpdateHandler.init();
/*     */   }
/*     */ 
/*     */   
/* 371 */   public static CreativeTabs tabForgottenRelics = new CreativeTabs("tabForgottenRelics") {
/*     */       @SideOnly(Side.CLIENT)
/*     */       public Item getTabIconItem() {
/* 374 */         return Main.itemApotheosis;
/*     */       }
/*     */     };
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */