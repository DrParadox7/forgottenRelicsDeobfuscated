/*     */ package com.integral.forgottenrelics.research;
/*     */ 
/*     */ import com.integral.forgottenrelics.Main;
/*     */ import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
/*     */ import com.integral.forgottenrelics.handlers.SuperpositionHandler;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.crafting.InfusionRecipe;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.ResearchPage;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import vazkii.botania.common.item.ModItems;
/*     */ 
/*     */ 
/*     */ public class RelicsResearchRegistry
/*     */ {
/*  25 */   public static HashMap<String, InfusionRecipe> recipes = new HashMap<>();
/*     */   
/*  27 */   public static ItemStack superpositionRing = new ItemStack(Main.itemSuperpositionRing, 1, 0);
/*  28 */   public static ItemStack weatherStone = new ItemStack(Main.itemWeatherStone, 1, 0);
/*  29 */   public static ItemStack miningCharm = new ItemStack(Main.itemMiningCharm, 1, 0);
/*  30 */   public static ItemStack advancedMiningCharm = new ItemStack(Main.itemAdvancedMiningCharm, 1, 0);
/*  31 */   public static ItemStack ancientAegis = new ItemStack(Main.itemAncientAegis, 1, 0);
/*  32 */   public static ItemStack apotheosis = new ItemStack(Main.itemApotheosis, 1, 0);
/*  33 */   public static ItemStack nebulousCore = new ItemStack(Main.itemArcanum, 1, 0);
/*  34 */   public static ItemStack chaosCore = new ItemStack(Main.itemChaosCore, 1, 0);
/*  35 */   public static ItemStack chaosTome = new ItemStack(Main.itemChaosTome, 1, 0);
/*  36 */   public static ItemStack crimsonSpell = new ItemStack(Main.itemCrimsonSpell, 1, 0);
/*  37 */   public static ItemStack darkSunRing = new ItemStack(Main.itemDarkSunRing, 1, 0);
/*  38 */   public static ItemStack deificAmulet = new ItemStack(Main.itemDeificAmulet, 1, 0);
/*  39 */   public static ItemStack dimensionalMirror = new ItemStack(Main.itemDimensionalMirror, 1, 0);
/*  40 */   public static ItemStack eldritchSpell = new ItemStack(Main.itemEldritchSpell, 1, 0);
/*  41 */   public static ItemStack falseJustice = new ItemStack(Main.itemFalseJustice, 1, 0);
/*  42 */   public static ItemStack fateTome = new ItemStack(Main.itemFateTome, 1, 0);
/*  43 */   public static ItemStack lunarFlares = new ItemStack(Main.itemLunarFlares, 1, 0);
/*  44 */   public static ItemStack nuclearFury = new ItemStack(Main.itemMissileTome, 1, 0);
/*  45 */   public static ItemStack obeliskDrainer = new ItemStack(Main.itemObeliskDrainer, 1, 0);
/*  46 */   public static ItemStack theParadox = new ItemStack(Main.itemParadox, 1, 0);
/*  47 */   public static ItemStack shinyStone = new ItemStack(Main.itemShinyStone, 1, 0);
/*  48 */   public static ItemStack soulTome = new ItemStack(Main.itemSoulTome, 1, 0);
/*  49 */   public static ItemStack telekinesisTome = new ItemStack((Item)Main.itemTelekinesisTome, 1, 0);
/*  50 */   public static ItemStack discordTome = new ItemStack(Main.itemTeleportationTome, 1, 0);
/*  51 */   public static ItemStack XPTome = new ItemStack(Main.itemXPTome, 1, 0);
/*  52 */   public static ItemStack oblivionAmulet = new ItemStack(Main.itemOblivionAmulet, 1, 0);
/*  53 */   public static ItemStack terrorCrown = new ItemStack(Main.itemTerrorCrown, 1, 0);
/*  54 */   public static ItemStack thunderpeal = new ItemStack(Main.itemThunderpeal, 1, 0);
/*  55 */   public static ItemStack overthrower = new ItemStack(Main.itemOverthrower, 1, 0);
/*  56 */   public static ItemStack discordRing = new ItemStack(Main.itemDiscordRing, 1, 0);
/*  57 */   public static ItemStack voidGrimoire = new ItemStack(Main.itemVoidGrimoire, 1, 0);
/*  58 */   public static ItemStack oblivionStone = new ItemStack(Main.itemOblivionStone, 1, 0);
/*     */   
/*  60 */   public static ItemStack enderEye = new ItemStack(Items.ender_eye, 1, 0);
/*  61 */   public static ItemStack salisMundus = new ItemStack(ConfigItems.itemResource, 1, 14);
/*  62 */   public static ItemStack voidSeed = new ItemStack(ConfigItems.itemResource, 1, 17);
/*  63 */   public static ItemStack enderAir = new ItemStack(ModItems.manaResource, 1, 15);
/*  64 */   public static ItemStack primalCharm = new ItemStack(ConfigItems.itemResource, 1, 15);
/*  65 */   public static ItemStack blankRing = new ItemStack(ConfigItems.itemBaubleBlanks, 1, 1);
/*  66 */   public static ItemStack ghastTear = new ItemStack(Items.ghast_tear, 1, 0);
/*  67 */   public static ItemStack airRune = new ItemStack(ModItems.rune, 1, 3);
/*  68 */   public static ItemStack airShard = new ItemStack(ConfigItems.itemShard, 1, 0);
/*  69 */   public static ItemStack fireShard = new ItemStack(ConfigItems.itemShard, 1, 1);
/*  70 */   public static ItemStack waterShard = new ItemStack(ConfigItems.itemShard, 1, 2);
/*  71 */   public static ItemStack earthShard = new ItemStack(ConfigItems.itemShard, 1, 3);
/*  72 */   public static ItemStack orderShard = new ItemStack(ConfigItems.itemShard, 1, 4);
/*  73 */   public static ItemStack entropyShard = new ItemStack(ConfigItems.itemShard, 1, 5);
/*  74 */   public static ItemStack balancedShard = new ItemStack(ConfigItems.itemShard, 1, 6);
/*     */   
/*  76 */   public static ItemStack revealingGoggles = new ItemStack(ConfigItems.itemGoggles, 1, 0);
/*  77 */   public static ItemStack knowledgeFragment = new ItemStack(ConfigItems.itemResource, 1, 9);
/*  78 */   public static ItemStack inkwell = new ItemStack(ConfigItems.itemInkwell, 1, 0);
/*  79 */   public static ItemStack arcaneStone = new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6);
/*  80 */   public static ItemStack gaiaSpirit = new ItemStack(ModItems.manaResource, 1, 5);
/*  81 */   public static ItemStack terrasteelIngot = new ItemStack(ModItems.manaResource, 1, 4);
/*  82 */   public static ItemStack nitor = new ItemStack(ConfigItems.itemResource, 1, 1);
/*  83 */   public static ItemStack elementalPickaxe = new ItemStack(ConfigItems.itemPickElemental, 1, 0);
/*  84 */   public static ItemStack speedPotionII = new ItemStack((Item)Items.potionitem, 1, 8226);
/*  85 */   public static ItemStack reachRing = new ItemStack(ModItems.reachRing, 1, 0);
/*  86 */   public static ItemStack dragonStone = new ItemStack(ModItems.manaResource, 1, 9);
/*  87 */   public static ItemStack portableHole = new ItemStack(ConfigItems.itemFocusPortableHole, 1, 0);
/*  88 */   public static ItemStack handMirror = new ItemStack(ConfigItems.itemHandMirror, 1, 0);
/*  89 */   public static ItemStack glowstone = new ItemStack(Items.glowstone_dust, 1, 0);
/*  90 */   public static ItemStack etherealEssence = new ItemStack(ConfigItems.itemWispEssence, 1, 0);
/*  91 */   public static ItemStack elementiumIngot = new ItemStack(ModItems.manaResource, 1, 7);
/*  92 */   public static ItemStack voidPickaxe = new ItemStack(ConfigItems.itemPickVoid, 1, 0);
/*  93 */   public static ItemStack kineticRunicGirdle = new ItemStack(ConfigItems.itemGirdleRunic, 1, 1);
/*  94 */   public static ItemStack tectonicGirdle = new ItemStack(ModItems.knockbackBelt, 1, 0);
/*  95 */   public static ItemStack healPotionII = new ItemStack((Item)Items.potionitem, 1, 8229);
/*  96 */   public static ItemStack pixieDust = new ItemStack(ModItems.manaResource, 1, 8);
/*  97 */   public static ItemStack enchantedFabric = new ItemStack(ConfigItems.itemResource, 1, 7);
/*  98 */   public static ItemStack goldIngot = new ItemStack(Items.gold_ingot, 1, 0);
/*  99 */   public static ItemStack blazePowder = new ItemStack(Items.blaze_powder, 1, 0);
/* 100 */   public static ItemStack blazeRod = new ItemStack(Items.blaze_rod, 1, 0);
/* 101 */   public static ItemStack lavaBucket = new ItemStack(Items.lava_bucket, 1, 0);
/* 102 */   public static ItemStack runicRingCharged = new ItemStack(ConfigItems.itemRingRunic, 1, 2);
/* 103 */   public static ItemStack cinderPearl = new ItemStack(ConfigBlocks.blockCustomPlant, 1, 3);
/* 104 */   public static ItemStack superLavaPendant = new ItemStack(ModItems.superLavaPendant, 1, 0);
/* 105 */   public static ItemStack runicAmuletAdv = new ItemStack(ConfigItems.itemAmuletRunic, 1, 1);
/* 106 */   public static ItemStack lavaPendant = new ItemStack(ModItems.lavaPendant, 1, 0);
/* 107 */   public static ItemStack voidIngot = new ItemStack(ConfigItems.itemResource, 1, 16);
/* 108 */   public static ItemStack alumentum = new ItemStack(ConfigItems.itemResource, 1, 0);
/* 109 */   public static ItemStack voidSword = new ItemStack(ConfigItems.itemSwordVoid, 1, 0);
/* 110 */   public static ItemStack primordialPearl = new ItemStack(ConfigItems.itemEldritchObject, 1, 3);
/* 111 */   public static ItemStack superGoldenApple = new ItemStack(Items.golden_apple, 1, 1);
/* 112 */   public static ItemStack bloodPendant = new ItemStack(ModItems.bloodPendant, 1, 0);
/* 113 */   public static ItemStack netherStar = new ItemStack(Items.nether_star, 1, 0);
/* 114 */   public static ItemStack eldritchEye = new ItemStack(ConfigItems.itemEldritchObject, 1, 0);
/* 115 */   public static ItemStack writableBook = new ItemStack(Items.writable_book, 1, 0);
/* 116 */   public static ItemStack amber = new ItemStack(ConfigItems.itemResource, 1, 6);
/* 117 */   public static ItemStack greatVisAmulet = new ItemStack(ConfigItems.itemAmuletVis, 0, 1);
/* 118 */   public static ItemStack thaumiumIngot = new ItemStack(ConfigItems.itemResource, 1, 2);
/* 119 */   public static ItemStack enderPearl = new ItemStack(Items.ender_pearl, 1, 0);
/* 120 */   public static ItemStack shockFocus = new ItemStack(ConfigItems.itemFocusShock, 1, 0);
/* 121 */   public static ItemStack gravityRod = new ItemStack(ModItems.gravityRod, 1, 0);
/* 122 */   public static ItemStack pechFocus = new ItemStack(ConfigItems.itemFocusPech, 1, 0);
/* 123 */   public static ItemStack jarredBrain = new ItemStack(ConfigBlocks.blockJar, 1, 1);
/* 124 */   public static ItemStack goldLaurel = new ItemStack(ModItems.goldLaurel);
/* 125 */   public static ItemStack fireball = new ItemStack(Items.fire_charge, 1, 0);
/* 126 */   public static ItemStack bottledTaint = new ItemStack(ConfigItems.itemBottleTaint, 1, 0);
/* 127 */   public static ItemStack primalFocus = new ItemStack(ConfigItems.itemFocusPrimal, 1, 0);
/* 128 */   public static ItemStack redstone = new ItemStack(Items.redstone, 1, 0);
/* 129 */   public static ItemStack crimsonRites = new ItemStack(ConfigItems.itemEldritchObject, 1, 1);
/* 130 */   public static ItemStack starSword = new ItemStack(ModItems.starSword, 1, 0);
/* 131 */   public static ItemStack missileRod = new ItemStack(ModItems.missileRod, 1, 0);
/* 132 */   public static ItemStack kingKey = new ItemStack(ModItems.kingKey, 1, 0);
/* 133 */   public static ItemStack emerald = new ItemStack(Items.emerald, 1, 0);
/* 134 */   public static ItemStack wrathRune = new ItemStack(ModItems.rune, 1, 13);
/* 135 */   public static ItemStack prideRune = new ItemStack(ModItems.rune, 1, 15);
/* 136 */   public static ItemStack manaPearl = new ItemStack(ModItems.manaResource, 1, 1);
/* 137 */   public static ItemStack netherBrick = new ItemStack(Items.netherbrick, 1, 0);
/* 138 */   public static ItemStack netherWart = new ItemStack(Items.nether_wart, 1, 0);
/* 139 */   public static ItemStack sugar = new ItemStack(Items.sugar, 1, 0);
/* 140 */   public static ItemStack fermentedSpiderEye = new ItemStack(Items.fermented_spider_eye, 1, 0);
/* 141 */   public static ItemStack quartz = new ItemStack(Items.quartz, 1, 0);
/* 142 */   public static ItemStack hellFocus = new ItemStack(ConfigItems.itemFocusHellbat, 1, 0);
/* 143 */   public static ItemStack eldritchTablet = new ItemStack(ConfigItems.itemEldritchObject, 1, 2);
/* 144 */   public static ItemStack bedrock = new ItemStack(Blocks.bedrock, 1, 0);
/* 145 */   public static ItemStack sinisterStone = new ItemStack(ConfigItems.itemCompassStone, 1, 0);
/*     */ 
/*     */   
/*     */   public static void integrateResearch() {
/* 149 */     ResearchCategories.registerCategory("ForgottenRelics", new ResourceLocation("forgottenrelics:textures/items/Apotheosis.png"), new ResourceLocation("thaumcraft:textures/gui/gui_researchback.png"));
/*     */     
/* 151 */     (new ForgottenRelicsResearchItem("GenericTheory", "ForgottenRelics", (new AspectList()).add(Aspect.MIND, 4).add(Aspect.TOOL, 4).add(Aspect.MAGIC, 4).add(Aspect.CRAFT, 4), 0, 0, 1, eldritchTablet)).setSpecial().setRound().setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage("2"), new ResearchPage("3"), new ResearchPage("4"), new ResearchPage("5")
/* 152 */         }).setParentsHidden(new String[] { "INFUSION", "THAUMIUM"
/* 153 */         }).registerResearchItem();
/*     */ 
/*     */     
/* 156 */     (new ForgottenRelicsResearchItem("SuperpositionRing", "ForgottenRelics", (new AspectList())
/* 157 */         .add(Aspect.TRAVEL, 4).add(Aspect.DARKNESS, 3).add(Aspect.ELDRITCH, 6).add(Aspect.EXCHANGE, 4), 3, 1, 2, new ItemStack(Main.itemSuperpositionRing)))
/*     */ 
/*     */       
/* 160 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("ISuperpositionRing")), new ResearchPage("2")
/* 161 */         }).setParents(new String[] { "GenericTheory"
/* 162 */         }).setParentsHidden(new String[] { "ELDRITCHMINOR"
/* 163 */         }).registerResearchItem();
/*     */ 
/*     */     
/* 166 */     (new ForgottenRelicsResearchItem("WeatherStone", "ForgottenRelics", (new AspectList())
/* 167 */         .add(Aspect.WEATHER, 8).add(Aspect.AIR, 4).add(Aspect.WATER, 4).add(Aspect.EXCHANGE, 3), -3, -1, 1, new ItemStack(Main.itemWeatherStone)))
/*     */ 
/*     */       
/* 170 */       .setConcealed()
/* 171 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IWeatherStone")), new ResearchPage("2")
/* 172 */         }).setParents(new String[] { "GenericTheory"
/* 173 */         }).setParentsHidden(new String[] { "DeificAmulet"
/* 174 */         }).registerResearchItem();
/*     */ 
/*     */     
/* 177 */     (new ForgottenRelicsResearchItem("MiningCharm", "ForgottenRelics", (new AspectList())
/* 178 */         .add(Aspect.MINE, 5).add(Aspect.TOOL, 4).add(Aspect.MOTION, 4).add(Aspect.MAN, 3), 3, -1, 1, new ItemStack(Main.itemMiningCharm)))
/*     */ 
/*     */       
/* 181 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IMiningCharm"))
/* 182 */         }).setParents(new String[] { "GenericTheory"
/* 183 */         }).setParentsHidden(new String[] { "ELEMENTALPICK"
/* 184 */         }).setConcealed()
/* 185 */       .setRound()
/* 186 */       .registerResearchItem();
/*     */ 
/*     */     
/* 189 */     (new ForgottenRelicsResearchItem("AdvancedMiningCharm", "ForgottenRelics", (new AspectList())
/* 190 */         .add(Aspect.MINE, 8).add(Aspect.TOOL, 6).add(Aspect.MOTION, 4).add(Aspect.AURA, 3).add(Aspect.MAGIC, 4), 5, -3, 2, new ItemStack(Main.itemAdvancedMiningCharm)))
/*     */ 
/*     */       
/* 193 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IAdvancedMiningCharm"))
/* 194 */         }).setParents(new String[] { "MiningCharm"
/* 195 */         }).setHidden()
/* 196 */       .registerResearchItem();
/*     */     
/* 198 */     SuperpositionHandler.setupResearchTriggers("AdvancedMiningCharm", new ItemStack[] { gaiaSpirit, miningCharm });
/*     */ 
/*     */     
/* 201 */     (new ForgottenRelicsResearchItem("DimensionalMirror", "ForgottenRelics", (new AspectList())
/* 202 */         .add(Aspect.TRAVEL, 8).add(Aspect.DARKNESS, 6).add(Aspect.ELDRITCH, 6).add(Aspect.MAGIC, 5), 1, -3, 2, new ItemStack(Main.itemDimensionalMirror)))
/*     */ 
/*     */       
/* 205 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IDimensionalMirror")), new ResearchPage("2")
/* 206 */         }).setParents(new String[] { "GenericTheory"
/* 207 */         }).setParentsHidden(new String[] { "MIRRORHAND", "FOCUSPORTABLEHOLE", "DeificAmulet"
/* 208 */         }).setConcealed()
/* 209 */       .setSpecial()
/* 210 */       .registerResearchItem();
/*     */     
/* 212 */     ThaumcraftApi.addWarpToResearch("DimensionalMirror", 1);
/*     */ 
/*     */     
/* 215 */     (new ForgottenRelicsResearchItem("AncientAegis", "ForgottenRelics", (new AspectList())
/* 216 */         .add(Aspect.ARMOR, 8).add(Aspect.EXCHANGE, 6).add(Aspect.METAL, 6).add(Aspect.MAGIC, 4), 5, 3, 1, new ItemStack(Main.itemAncientAegis)))
/*     */ 
/*     */       
/* 219 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IAncientAegis")), new ResearchPage("2")
/* 220 */         }).setParents(new String[] { "SuperpositionRing"
/* 221 */         }).setParentsHidden(new String[] { "RUNICKINETIC", "ENCHFABRIC"
/* 222 */         }).setHidden()
/* 223 */       .registerResearchItem();
/*     */     
/* 225 */     SuperpositionHandler.setupResearchTriggers("AncientAegis", new ItemStack[] { dragonStone });
/*     */ 
/*     */     
/* 228 */     (new ForgottenRelicsResearchItem("XPTome", "ForgottenRelics", (new AspectList())
/* 229 */         .add(Aspect.MIND, 10).add(Aspect.VOID, 8).add(Aspect.EXCHANGE, 8).add(Aspect.MAGIC, 8), -5, -3, 2, new ItemStack(Main.itemXPTome)))
/*     */ 
/*     */       
/* 232 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IXPTome")), new ResearchPage("2")
/* 233 */         }).setParents(new String[] { "WeatherStone"
/* 234 */         }).setParentsHidden(new String[] { "JARBRAIN", "NITOR"
/* 235 */         }).setConcealed()
/* 236 */       .registerResearchItem();
/*     */     
/* 238 */     ThaumcraftApi.addWarpToResearch("XPTome", 2);
/*     */ 
/*     */     
/* 241 */     (new ForgottenRelicsResearchItem("SpellbookTheory", "ForgottenRelics", (new AspectList())
/* 242 */         .add(Aspect.MIND, 6).add(Aspect.MAGIC, 4).add(Aspect.CRAFT, 4).add(Aspect.ORDER, 3), -1, 2, 1, new ItemStack(Items.writable_book)))
/*     */ 
/*     */       
/* 245 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage("2"), new ResearchPage("3"), new ResearchPage("4")
/* 246 */         }).setParents(new String[] { "GenericTheory"
/* 247 */         }).setParentsHidden(new String[] { "CRIMSON"
/* 248 */         }).setConcealed()
/* 249 */       .setRound()
/* 250 */       .registerResearchItem();
/*     */ 
/*     */     
/* 253 */     (new ForgottenRelicsResearchItem("DiscordTome", "ForgottenRelics", (new AspectList())
/* 254 */         .add(Aspect.MAGIC, 8).add(Aspect.TRAVEL, 6).add(Aspect.DARKNESS, 4).add(Aspect.MIND, 4).add(Aspect.ELDRITCH, 3), -2, 5, 2, new ItemStack(Main.itemTeleportationTome)))
/*     */ 
/*     */       
/* 257 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IDiscordTome")), new ResearchPage("2")
/* 258 */         }).setParents(new String[] { "SpellbookTheory"
/* 259 */         }).setParentsHidden(new String[] { "FOCUSPORTABLEHOLE"
/* 260 */         }).setHidden()
/* 261 */       .registerResearchItem();
/*     */     
/* 263 */     SuperpositionHandler.setupResearchTriggers("DiscordTome", new ItemStack[] { elementiumIngot });
/* 264 */     ThaumcraftApi.addWarpToResearch("DiscordTome", 3);
/*     */ 
/*     */     
/* 267 */     (new ForgottenRelicsResearchItem("FateTome", "ForgottenRelics", (new AspectList())
/* 268 */         .add(Aspect.HEAL, 8).add(Aspect.LIFE, 8).add(Aspect.EXCHANGE, 5).add(Aspect.MIND, 4).add(Aspect.MAGIC, 4), -4, 2, 3, new ItemStack(Main.itemFateTome)))
/*     */ 
/*     */       
/* 271 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IFateTome")), new ResearchPage("2")
/* 272 */         }).setParents(new String[] { "SpellbookTheory"
/* 273 */         }).setParentsHidden(new String[] { "ELDRITCHMAJOR"
/* 274 */         }).setConcealed()
/* 275 */       .setSpecial()
/* 276 */       .setHidden()
/* 277 */       .registerResearchItem();
/*     */     
/* 279 */     SuperpositionHandler.setupResearchTriggers("FateTome", new ItemStack[] { goldLaurel, gaiaSpirit, dragonStone });
/* 280 */     ThaumcraftApi.addWarpToResearch("FateTome", 8);
/*     */ 
/*     */     
/* 283 */     (new ForgottenRelicsResearchItem("Thunderpeal", "ForgottenRelics", (new AspectList())
/* 284 */         .add(Aspect.ENERGY, 5).add(Aspect.AIR, 4).add(Aspect.MAGIC, 4).add(Aspect.MIND, 3), 0, 4, 2, new ItemStack(Main.itemThunderpeal)))
/*     */ 
/*     */       
/* 287 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IThunderpeal")), new ResearchPage("2"), new ResearchPage("3")
/* 288 */         }).setParents(new String[] { "SpellbookTheory"
/* 289 */         }).setParentsHidden(new String[] { "DiscordTome", "FOCUSSHOCK", "FOCALMANIPULATION"
/* 290 */         }).setConcealed()
/* 291 */       .registerResearchItem();
/*     */ 
/*     */     
/* 294 */     (new ForgottenRelicsResearchItem("TelekinesisTome", "ForgottenRelics", (new AspectList())
/* 295 */         .add(Aspect.MOTION, 8).add(Aspect.FLIGHT, 6).add(Aspect.TRAP, 6).add(Aspect.MIND, 4).add(Aspect.MAGIC, 4), -4, 7, 2, new ItemStack((Item)Main.itemTelekinesisTome)))
/*     */ 
/*     */       
/* 298 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("ITelekinesisTome")), new ResearchPage("2"), new ResearchPage("3")
/* 299 */         }).setParents(new String[] { "DiscordTome"
/* 300 */         }).setParentsHidden(new String[] { "ELDRITCHMINOR", "Thunderpeal"
/* 301 */         }).setHidden()
/* 302 */       .registerResearchItem();
/*     */     
/* 304 */     SuperpositionHandler.setupResearchTriggers("TelekinesisTome", new ItemStack[] { gaiaSpirit, gravityRod, voidSeed });
/*     */ 
/*     */     
/* 307 */     (new ForgottenRelicsResearchItem("ObeliskDrainer", "ForgottenRelics", (new AspectList())
/* 308 */         .add(Aspect.ELDRITCH, 10).add(Aspect.VOID, 8).add(Aspect.DARKNESS, 8).add(Aspect.EXCHANGE, 6).add(Aspect.MIND, 4).add(Aspect.MAGIC, 4), 0, 7, 3, new ItemStack(Main.itemObeliskDrainer)))
/*     */ 
/*     */       
/* 311 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IObeliskDrainer")), new ResearchPage("2")
/* 312 */         }).setParents(new String[] { "DiscordTome"
/* 313 */         }).setParentsHidden(new String[] { "ELDRITCHMAJOR", "VOIDMETAL", "ENCHFABRIC", "NITOR", "OCULUS"
/* 314 */         }).setConcealed()
/* 315 */       .registerResearchItem();
/*     */     
/* 317 */     ThaumcraftApi.addWarpToResearch("ObeliskDrainer", 4);
/*     */ 
/*     */     
/* 320 */     (new ForgottenRelicsResearchItem("EldritchSpell", "ForgottenRelics", (new AspectList())
/* 321 */         .add(Aspect.DARKNESS, 8).add(Aspect.WEAPON, 8).add(Aspect.ELDRITCH, 6).add(Aspect.MIND, 4).add(Aspect.MAGIC, 4), 2, 6, 2, new ItemStack(Main.itemEldritchSpell)))
/*     */ 
/*     */       
/* 324 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IEldritchSpell")), new ResearchPage("2")
/* 325 */         }).setParents(new String[] { "ObeliskDrainer"
/* 326 */         }).setConcealed()
/* 327 */       .setHidden()
/* 328 */       .setSpecial()
/* 329 */       .registerResearchItem();
/*     */     
/* 331 */     SuperpositionHandler.setupResearchTriggers("EldritchSpell", new ItemStack[] { eldritchEye, gaiaSpirit });
/* 332 */     ThaumcraftApi.addWarpToResearch("EldritchSpell", 6);
/*     */ 
/*     */     
/* 335 */     (new ForgottenRelicsResearchItem("CrimsonSpell", "ForgottenRelics", (new AspectList())
/* 336 */         .add(Aspect.FIRE, 8).add(Aspect.DARKNESS, 8).add(Aspect.ENTROPY, 6).add(Aspect.ELDRITCH, 4).add(Aspect.MIND, 4).add(Aspect.MAGIC, 4), 3, 8, 3, new ItemStack(Main.itemCrimsonSpell)))
/*     */ 
/*     */       
/* 339 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("ICrimsonSpell")), new ResearchPage("2"), new ResearchPage("3")
/* 340 */         }).setParents(new String[] { "EldritchSpell"
/* 341 */         }).setHidden()
/* 342 */       .setConcealed()
/* 343 */       .registerResearchItem();
/*     */     
/* 345 */     SuperpositionHandler.setupResearchTriggers("CrimsonSpell", new ItemStack[] { crimsonRites });
/* 346 */     ThaumcraftApi.addWarpToResearch("CrimsonSpell", 5);
/*     */ 
/*     */     
/* 349 */     (new ForgottenRelicsResearchItem("ChaosTome", "ForgottenRelics", (new AspectList())
/* 350 */         .add(Aspect.AIR, 8).add(Aspect.WATER, 8).add(Aspect.FIRE, 8).add(Aspect.EARTH, 8).add(Aspect.ORDER, 8).add(Aspect.ENTROPY, 8).add(Aspect.MIND, 4).add(Aspect.MAGIC, 4), 2, 3, 2, new ItemStack(Main.itemChaosTome)))
/*     */ 
/*     */       
/* 353 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IChaosTome")), new ResearchPage("2")
/* 354 */         }).setParents(new String[] { "EldritchSpell"
/* 355 */         }).setParentsHidden(new String[] { "ROD_primal_staff"
/* 356 */         }).setHidden()
/* 357 */       .registerResearchItem();
/*     */     
/* 359 */     SuperpositionHandler.setupResearchTriggers("ChaosTome", new ItemStack[] { gaiaSpirit, primalFocus });
/* 360 */     ThaumcraftApi.addWarpToResearch("ChaosTome", 7);
/*     */ 
/*     */     
/* 363 */     (new ForgottenRelicsResearchItem("NuclearFury", "ForgottenRelics", (new AspectList())
/* 364 */         .add(Aspect.MAGIC, 8).add(Aspect.ENERGY, 8).add(Aspect.LIGHT, 6).add(Aspect.FIRE, 5).add(Aspect.AURA, 5).add(Aspect.MIND, 4), 4, 5, 3, new ItemStack(Main.itemMissileTome)))
/*     */ 
/*     */       
/* 367 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("INuclearFury")), new ResearchPage("2"), new ResearchPage("3")
/* 368 */         }).setParents(new String[] { "ChaosTome"
/* 369 */         }).setParentsHidden(new String[] { "PRIMPEARL"
/* 370 */         }).setConcealed()
/* 371 */       .setHidden()
/* 372 */       .registerResearchItem();
/*     */     
/* 374 */     SuperpositionHandler.setupResearchTriggers("NuclearFury", new ItemStack[] { missileRod, terrasteelIngot, alumentum });
/* 375 */     ThaumcraftApi.addWarpToResearch("NuclearFury", 4);
/*     */ 
/*     */     
/* 378 */     (new ForgottenRelicsResearchItem("SoulTome", "ForgottenRelics", (new AspectList())
/* 379 */         .add(Aspect.SOUL, 8).add(Aspect.TRAP, 8).add(Aspect.DEATH, 6).add(Aspect.MIND, 4).add(Aspect.MAGIC, 4), -1, 9, 3, new ItemStack(Main.itemSoulTome)))
/*     */ 
/*     */       
/* 382 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("ISoulTome")), new ResearchPage("2")
/* 383 */         }).setParents(new String[] { "ObeliskDrainer"
/* 384 */         }).setParentsHidden(new String[] { "JARBRAIN"
/* 385 */         }).setHidden()
/* 386 */       .setConcealed()
/* 387 */       .registerResearchItem();
/*     */     
/* 389 */     SuperpositionHandler.setupResearchTriggers("SoulTome", new ItemStack[] { pechFocus, gaiaSpirit, jarredBrain, enderEye, alumentum });
/* 390 */     ThaumcraftApi.addWarpToResearch("SoulTome", 3);
/*     */ 
/*     */     
/* 393 */     (new ForgottenRelicsResearchItem("LunarFlares", "ForgottenRelics", (new AspectList())
/* 394 */         .add(Aspect.LIGHT, 8).add(Aspect.SENSES, 8).add(Aspect.AIR, 6).add(Aspect.ENERGY, 8).add(Aspect.MIND, 4).add(Aspect.MAGIC, 4), -5, 9, 3, new ItemStack(Main.itemLunarFlares)))
/*     */ 
/*     */       
/* 397 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("ILunarFlares")), new ResearchPage("2")
/* 398 */         }).setParents(new String[] { "TelekinesisTome"
/* 399 */         }).setParentsHidden(new String[] { "EldritchSpell", "PRIMPEARL"
/* 400 */         }).setHidden()
/* 401 */       .registerResearchItem();
/*     */     
/* 403 */     SuperpositionHandler.setupResearchTriggers("LunarFlares", new ItemStack[] { starSword, terrasteelIngot, enderAir });
/*     */ 
/*     */     
/* 406 */     (new ForgottenRelicsResearchItem("OblivionStone", "ForgottenRelics", (new AspectList())
/* 407 */         .add(Aspect.DARKNESS, 4).add(Aspect.VOID, 4).add(Aspect.EXCHANGE, 4), 7, -2, 1, new ItemStack(Main.itemOblivionStone)))
/*     */ 
/*     */       
/* 410 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IOblivionStone")), new ResearchPage("2"), new ResearchPage("3")
/* 411 */         }).setParents(new String[] { "SuperpositionRing"
/* 412 */         }).setParentsHidden(new String[] { "VOIDMETAL", "SINSTONE"
/* 413 */         }).setSecondary()
/* 414 */       .setSpecial()
/* 415 */       .setConcealed()
/* 416 */       .registerResearchItem();
/*     */     
/* 418 */     ThaumcraftApi.addWarpToResearch("OblivionStone", 3);
/*     */ 
/*     */     
/* 421 */     (new ForgottenRelicsResearchItem("ChaosCore", "ForgottenRelics", (new AspectList())
/* 422 */         .add(Aspect.ORDER, 8).add(Aspect.ENTROPY, 8).add(Aspect.EXCHANGE, 8).add(Aspect.ENERGY, 8).add(Aspect.VOID, 8), 8, -4, 2, new ItemStack(Main.itemChaosCore)))
/*     */ 
/*     */       
/* 425 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IChaosCore")), new ResearchPage("2")
/* 426 */         }).setParents(new String[] { "OblivionStone"
/* 427 */         }).setParentsHidden(new String[] { "ALUMENTUM", "AncientAegis"
/* 428 */         }).setConcealed()
/* 429 */       .setRound()
/* 430 */       .setHidden()
/* 431 */       .registerResearchItem();
/*     */     
/* 433 */     SuperpositionHandler.setupResearchTriggers("ChaosCore", new ItemStack[] { pixieDust, dragonStone, voidIngot });
/* 434 */     ThaumcraftApi.addWarpToResearch("ChaosCore", 2);
/*     */ 
/*     */     
/* 437 */     (new ForgottenRelicsResearchItem("TheParadox", "ForgottenRelics", (new AspectList())
/* 438 */         .add(Aspect.ENTROPY, 8).add(Aspect.EXCHANGE, 8).add(Aspect.ORDER, 8).add(Aspect.WEAPON, 6), 6, 0, 1, new ItemStack(Main.itemParadox)))
/*     */ 
/*     */       
/* 441 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("ITheParadox")), new ResearchPage("2")
/* 442 */         }).setParents(new String[] { "SuperpositionRing"
/* 443 */         }).setParentsHidden(new String[] { "ChaosCore", "PRIMPEARL"
/* 444 */         }).setConcealed()
/* 445 */       .setSecondary()
/* 446 */       .setHidden()
/* 447 */       .registerResearchItem();
/*     */     
/* 449 */     SuperpositionHandler.setupResearchTriggers("TheParadox", new ItemStack[] { chaosCore });
/* 450 */     ThaumcraftApi.addWarpToResearch("TheParadox", 8);
/*     */ 
/*     */     
/* 453 */     (new ForgottenRelicsResearchItem("DarkSunRing", "ForgottenRelics", (new AspectList())
/* 454 */         .add(Aspect.FIRE, 7).add(Aspect.DARKNESS, 6).add(Aspect.ENERGY, 5).add(Aspect.ARMOR, 4).add(Aspect.EXCHANGE, 3).add(Aspect.MAGIC, 2).add(Aspect.LIGHT, 1), 7, 2, 3, new ItemStack(Main.itemDarkSunRing)))
/*     */ 
/*     */       
/* 457 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IDarkSunRing")), new ResearchPage("2")
/* 458 */         }).setParents(new String[] { "SuperpositionRing"
/* 459 */         }).setParentsHidden(new String[] { "ELDRITCHMINOR", "NITOR", "RUNICCHARGED"
/* 460 */         }).setConcealed()
/* 461 */       .setHidden()
/* 462 */       .registerResearchItem();
/*     */     
/* 464 */     SuperpositionHandler.setupResearchTriggers("DarkSunRing", new ItemStack[] { superLavaPendant, blazeRod, gaiaSpirit });
/* 465 */     ThaumcraftApi.addWarpToResearch("DarkSunRing", 3);
/*     */ 
/*     */     
/* 468 */     (new ForgottenRelicsResearchItem("DeificAmulet", "ForgottenRelics", (new AspectList())
/* 469 */         .add(Aspect.MAN, 6).add(Aspect.HEAL, 4).add(Aspect.LIGHT, 4).add(Aspect.EXCHANGE, 4).add(Aspect.MAGIC, 4), 3, -4, 3, new ItemStack(Main.itemDeificAmulet)))
/*     */ 
/*     */       
/* 472 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IDeificAmulet")), new ResearchPage("2")
/* 473 */         }).setParents(new String[] { "MiningCharm"
/* 474 */         }).setParentsHidden(new String[] { "RUNICEMERGENCY"
/* 475 */         }).setHidden()
/* 476 */       .setConcealed()
/* 477 */       .registerResearchItem();
/*     */     
/* 479 */     SuperpositionHandler.setupResearchTriggers("DeificAmulet", new ItemStack[] { gaiaSpirit, pixieDust });
/*     */ 
/*     */     
/* 482 */     (new ForgottenRelicsResearchItem("ShinyStone", "ForgottenRelics", (new AspectList())
/* 483 */         .add(Aspect.HEAL, 8).add(Aspect.EXCHANGE, 6).add(Aspect.CRYSTAL, 4).add(Aspect.MAGIC, 4), 6, -5, 2, new ItemStack(Main.itemShinyStone)))
/*     */ 
/*     */       
/* 486 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IShinyStone")), new ResearchPage("2")
/* 487 */         }).setParents(new String[] { "DeificAmulet"
/* 488 */         }).setHidden()
/* 489 */       .registerResearchItem();
/*     */     
/* 491 */     SuperpositionHandler.setupResearchTriggers("ShinyStone", new ItemStack[] { superGoldenApple, dragonStone, gaiaSpirit });
/*     */ 
/*     */     
/* 494 */     (new ForgottenRelicsResearchItem("TerrorCrown", "ForgottenRelics", (new AspectList())
/* 495 */         .add(Aspect.ELDRITCH, 8).add(Aspect.WEAPON, 6).add(Aspect.ENTROPY, 6).add(Aspect.ARMOR, 4).add(Aspect.SENSES, 2), -6, -5, 3, new ItemStack(Main.itemTerrorCrown)))
/*     */ 
/*     */       
/* 498 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("ITerrorCrown")), new ResearchPage("2"), new ResearchPage("3")
/* 499 */         }).setParents(new String[] { "XPTome"
/* 500 */         }).setParentsHidden(new String[] { "ELDRITCHMAJOR", "OCULUS"
/* 501 */         }).setHidden()
/* 502 */       .registerResearchItem();
/*     */     
/* 504 */     SuperpositionHandler.setupResearchTriggers("TerrorCrown", new ItemStack[] { netherStar, eldritchEye });
/* 505 */     ThaumcraftApi.addWarpToResearch("TerrorCrown", 4);
/*     */ 
/*     */     
/* 508 */     (new ForgottenRelicsResearchItem("OblivionAmulet", "ForgottenRelics", (new AspectList())
/* 509 */         .add(Aspect.DARKNESS, 12).add(Aspect.DEATH, 10).add(Aspect.ARMOR, 8).add(Aspect.EXCHANGE, 8).add(Aspect.VOID, 8).add(Aspect.SOUL, 4), -3, -4, 3, new ItemStack(Main.itemOblivionAmulet)))
/*     */ 
/*     */       
/* 512 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IOblivionAmulet")), new ResearchPage("2"), new ResearchPage("3")
/* 513 */         }).setParents(new String[] { "XPTome"
/* 514 */         }).setParentsHidden(new String[] { "OblivionStone", "TerrorCrown", "PRIMPEARL"
/* 515 */         }).setConcealed()
/* 516 */       .setSpecial()
/* 517 */       .setHidden()
/* 518 */       .registerResearchItem();
/*     */     
/* 520 */     SuperpositionHandler.setupResearchTriggers("OblivionAmulet", new ItemStack[] { netherStar, eldritchEye, voidIngot });
/* 521 */     ThaumcraftApi.addWarpToResearch("OblivionAmulet", 8);
/*     */ 
/*     */     
/* 524 */     (new ForgottenRelicsResearchItem("NebulousCore", "ForgottenRelics", (new AspectList())
/* 525 */         .add(Aspect.AURA, 10).add(Aspect.MAGIC, 8).add(Aspect.VOID, 6).add(Aspect.ENERGY, 6).add(Aspect.EXCHANGE, 4), 0, -5, 3, new ItemStack(Main.itemArcanum)))
/*     */ 
/*     */       
/* 528 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("INebulousCore")), new ResearchPage("2"), new ResearchPage("3")
/* 529 */         }).setParents(new String[] { "DeificAmulet"
/* 530 */         }).setParentsHidden(new String[] { "PRIMPEARL", "VISAMULET", "SuperpositionRing", "THAUMIUM"
/* 531 */         }).setSpecial()
/* 532 */       .setHidden()
/* 533 */       .registerResearchItem();
/*     */     
/* 535 */     SuperpositionHandler.setupResearchTriggers("NebulousCore", new ItemStack[] { superpositionRing, thaumiumIngot });
/*     */ 
/*     */     
/* 538 */     if (RelicsConfigHandler.falseJusticeEnabled) {
/* 539 */       (new ForgottenRelicsResearchItem("FalseJustice", "ForgottenRelics", (new AspectList())
/* 540 */           .add(Aspect.LIGHT, 8).add(Aspect.DARKNESS, 8).add(Aspect.EXCHANGE, 8).add(Aspect.TOOL, 6).add(Aspect.MAN, 4), -7, 0, 3, new ItemStack(Main.itemFalseJustice)))
/*     */ 
/*     */         
/* 543 */         .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IFalseJustice")), new ResearchPage("2")
/* 544 */           }).setParentsHidden(new String[] { "Apotheosis"
/* 545 */           }).setRound()
/* 546 */         .setSpecial()
/* 547 */         .registerResearchItem();
/*     */       
/* 549 */       ThaumcraftApi.addWarpToResearch("FalseJustice", 4);
/*     */     } 
/*     */ 
/*     */     
/* 553 */     (new ForgottenRelicsResearchItem("Overthrower", "ForgottenRelics", (new AspectList())
/* 554 */         .add(Aspect.FIRE, 10).add(Aspect.TRAVEL, 8).add(Aspect.DARKNESS, 8).add(Aspect.MAGIC, 6).add(Aspect.MIND, 4).add(Aspect.SOUL, 4), -5, 5, 2, new ItemStack(Main.itemOverthrower)))
/*     */ 
/*     */       
/* 557 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IOverthrower")), new ResearchPage("2"), new ResearchPage("3")
/* 558 */         }).setParents(new String[] { "DiscordTome"
/* 559 */         }).setParentsHidden(new String[] { "FOCUSHELLBAT"
/* 560 */         }).setSecondary()
/* 561 */       .setSpecial()
/* 562 */       .setConcealed()
/* 563 */       .setHidden()
/* 564 */       .registerResearchItem();
/*     */     
/* 566 */     SuperpositionHandler.setupResearchTriggers("Overthrower", new ItemStack[] { hellFocus, netherWart });
/* 567 */     ThaumcraftApi.addWarpToResearch("Overthrower", 3);
/*     */     
/* 569 */     if (RelicsConfigHandler.voidGrimoireEnabled) {
/* 570 */       (new ForgottenRelicsResearchItem("VoidGrimoire", "ForgottenRelics", (new AspectList())
/* 571 */           .add(Aspect.VOID, 10).add(Aspect.DARKNESS, 8).add(Aspect.ELDRITCH, 8).add(Aspect.TRAVEL, 6).add(Aspect.MIND, 4).add(Aspect.ENTROPY, 4), -7, 7, 3, new ItemStack(Main.itemVoidGrimoire)))
/*     */ 
/*     */         
/* 574 */         .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IVoidGrimoire")), new ResearchPage("2"), new ResearchPage("3")
/* 575 */           }).setParents(new String[] { "Overthrower"
/* 576 */           }).setParentsHidden(new String[] { "OCULUS", "TerrorCrown", "ChaosCore"
/* 577 */           }).setLost()
/* 578 */         .setConcealed()
/* 579 */         .registerResearchItem();
/*     */       
/* 581 */       SuperpositionHandler.setupResearchTriggers("VoidGrimoire", new ItemStack[] { eldritchTablet, eldritchEye, voidSeed, bedrock, sinisterStone });
/* 582 */       ThaumcraftApi.addWarpToResearch("VoidGrimoire", 4);
/*     */     } 
/*     */     
/* 585 */     (new ForgottenRelicsResearchItem("PechFocus", "ForgottenRelics", (new AspectList())
/* 586 */         .add(Aspect.EXCHANGE, 8).add(Aspect.ENTROPY, 6).add(Aspect.MAGIC, 4).add(Aspect.SENSES, 4), -1, -2, 2, new ItemStack(ConfigItems.itemFocusPech)))
/*     */ 
/*     */       
/* 589 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IPechFocus")), new ResearchPage("2")
/* 590 */         }).setParents(new String[] { "WeatherStone"
/* 591 */         }).setParentsHidden(new String[] { "FOCUSFIRE", "FOCUSFROST"
/* 592 */         }).setHidden()
/* 593 */       .setSecondary()
/* 594 */       .registerResearchItem();
/*     */     
/* 596 */     SuperpositionHandler.setupResearchTriggers("PechFocus", new ItemStack[] { netherWart, ghastTear, fermentedSpiderEye, sugar, blazePowder });
/*     */ 
/*     */     
/* 599 */     (new ForgottenRelicsResearchItem("DiscordRing", "ForgottenRelics", (new AspectList())
/* 600 */         .add(Aspect.TOOL, 8).add(Aspect.TRAVEL, 8).add(Aspect.MAGIC, 8), 3, -6, 2, new ItemStack(Main.itemDiscordRing)))
/*     */ 
/*     */       
/* 603 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IDiscordRing")), new ResearchPage("2")
/* 604 */         }).setParents(new String[] { "DeificAmulet"
/* 605 */         }).setParentsHidden(new String[] { "DiscordTome"
/* 606 */         }).setSecondary()
/* 607 */       .setConcealed()
/* 608 */       .registerResearchItem();
/*     */ 
/*     */     
/* 611 */     (new ForgottenRelicsResearchItem("Apotheosis", "ForgottenRelics", (new AspectList())
/* 612 */         .add(Aspect.MAGIC, 16).add(Aspect.WEAPON, 12).add(Aspect.ENERGY, 12).add(Aspect.LIGHT, 12).add(Aspect.MIND, 8), -2, 7, 2, new ItemStack(Main.itemApotheosis)))
/*     */ 
/*     */       
/* 615 */       .setPages(new ResearchPage[] { new ResearchPage("1"), new ResearchPage(recipes.get("IApotheosis")), new ResearchPage("2"), new ResearchPage("3"), new ResearchPage("4")
/* 616 */         }).setParents(new String[] { "DiscordTome"
/* 617 */         }).setParentsHidden(new String[] { "LunarFlares", "SoulTome", "NuclearFury", "CrimsonSpell", "FateTome", "NebulousCore", "OblivionAmulet", "TheParadox", "Overthrower", "DarkSunRing", 
/* 618 */           "ShinyStone", "DimensionalMirror" }).setSpecial()
/* 619 */       .setRound()
/* 620 */       .setLost()
/* 621 */       .registerResearchItem();
/*     */ 
/*     */     
/* 624 */     ThaumcraftApi.addWarpToResearch("Apotheosis", 10);
/*     */     
/* 626 */     ThaumcraftApi.addWarpToItem(chaosTome, 4);
/* 627 */     ThaumcraftApi.addWarpToItem(fateTome, 6);
/* 628 */     ThaumcraftApi.addWarpToItem(theParadox, 8);
/* 629 */     ThaumcraftApi.addWarpToItem(oblivionAmulet, 4);
/* 630 */     ThaumcraftApi.addWarpToItem(falseJustice, 10);
/* 631 */     ThaumcraftApi.addWarpToItem(obeliskDrainer, 3);
/* 632 */     ThaumcraftApi.addWarpToItem(overthrower, 1);
/* 633 */     ThaumcraftApi.addWarpToItem(terrorCrown, 3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void integrateInfusion() {
/* 643 */     recipes.put("ISuperpositionRing", ThaumcraftApi.addInfusionCraftingRecipe("SuperpositionRing", superpositionRing, 4, (new AspectList())
/*     */           
/* 645 */           .add(Aspect.ELDRITCH, 32).add(Aspect.EXCHANGE, 30).add(Aspect.TRAVEL, 24).add(Aspect.DARKNESS, 20).add(Aspect.ARMOR, 8), blankRing, new ItemStack[] { enderEye, enderAir, voidSeed, salisMundus, emerald, salisMundus, voidSeed, enderAir }));
/*     */ 
/*     */ 
/*     */     
/* 649 */     recipes.put("IWeatherStone", ThaumcraftApi.addInfusionCraftingRecipe("WeatherStone", weatherStone, 4, (new AspectList())
/*     */           
/* 651 */           .add(Aspect.WEATHER, 16).add(Aspect.AIR, 24).add(Aspect.WATER, 10).add(Aspect.EXCHANGE, 12).add(Aspect.ENERGY, 20), arcaneStone, new ItemStack[] { gaiaSpirit, ghastTear, airRune, knowledgeFragment, nitor, knowledgeFragment, airRune, ghastTear }));
/*     */ 
/*     */ 
/*     */     
/* 655 */     recipes.put("IMiningCharm", ThaumcraftApi.addInfusionCraftingRecipe("MiningCharm", miningCharm, 1, (new AspectList())
/*     */           
/* 657 */           .add(Aspect.MINE, 24).add(Aspect.TOOL, 20).add(Aspect.MOTION, 16).add(Aspect.METAL, 12).add(Aspect.MAGIC, 16), reachRing, new ItemStack[] { elementalPickaxe, earthShard, salisMundus, goldIngot, speedPotionII, earthShard, salisMundus, goldIngot }));
/*     */ 
/*     */ 
/*     */     
/* 661 */     recipes.put("IDimensionalMirror", ThaumcraftApi.addInfusionCraftingRecipe("DimensionalMirror", dimensionalMirror, 6, (new AspectList())
/*     */           
/* 663 */           .add(Aspect.TRAVEL, 40).add(Aspect.DARKNESS, 20).add(Aspect.MAGIC, 24).add(Aspect.VOID, 16).add(Aspect.ELDRITCH, 10), handMirror, new ItemStack[] { gaiaSpirit, glowstone, dragonStone, enderAir, portableHole, enderAir, dragonStone, glowstone }));
/*     */ 
/*     */ 
/*     */     
/* 667 */     recipes.put("IAdvancedMiningCharm", ThaumcraftApi.addInfusionCraftingRecipe("AdvancedMiningCharm", advancedMiningCharm, 8, (new AspectList())
/*     */           
/* 669 */           .add(Aspect.MINE, 64).add(Aspect.AURA, 40).add(Aspect.CRYSTAL, 48).add(Aspect.EXCHANGE, 40).add(Aspect.MOTION, 32).add(Aspect.MAGIC, 36).add(Aspect.TOOL, 50), miningCharm, new ItemStack[] { primalCharm, etherealEssence, elementiumIngot, dragonStone, gaiaSpirit, etherealEssence, voidPickaxe, etherealEssence, gaiaSpirit, dragonStone, elementiumIngot, etherealEssence }));
/*     */ 
/*     */ 
/*     */     
/* 673 */     recipes.put("IAncientAegis", ThaumcraftApi.addInfusionCraftingRecipe("AncientAegis", ancientAegis, 5, (new AspectList())
/*     */           
/* 675 */           .add(Aspect.ARMOR, 45).add(Aspect.EXCHANGE, 30).add(Aspect.HEAL, 24).add(Aspect.MAGIC, 30).add(Aspect.METAL, 20), tectonicGirdle, new ItemStack[] { dragonStone, healPotionII, goldIngot, enchantedFabric, kineticRunicGirdle, enchantedFabric, goldIngot, healPotionII }));
/*     */ 
/*     */ 
/*     */     
/* 679 */     recipes.put("IDarkSunRing", ThaumcraftApi.addInfusionCraftingRecipe("DarkSunRing", darkSunRing, 8, (new AspectList())
/*     */           
/* 681 */           .add(Aspect.FIRE, 60).add(Aspect.ARMOR, 48).add(Aspect.EXCHANGE, 36).add(Aspect.DARKNESS, 40).add(Aspect.MAGIC, 25), runicRingCharged, new ItemStack[] { superLavaPendant, elementiumIngot, blazeRod, cinderPearl, voidSeed, nitor, voidSeed, cinderPearl, blazeRod, elementiumIngot }));
/*     */ 
/*     */ 
/*     */     
/* 685 */     recipes.put("IDeificAmulet", ThaumcraftApi.addInfusionCraftingRecipe("DeificAmulet", deificAmulet, 4, (new AspectList())
/*     */           
/* 687 */           .add(Aspect.MAN, 30).add(Aspect.LIGHT, 42).add(Aspect.AURA, 16).add(Aspect.MAGIC, 25).add(Aspect.HEAL, 8).add(Aspect.EXCHANGE, 20), runicAmuletAdv, new ItemStack[] { lavaPendant, gaiaSpirit, pixieDust, gaiaSpirit, primalCharm, gaiaSpirit, pixieDust, gaiaSpirit }));
/*     */ 
/*     */ 
/*     */     
/* 691 */     recipes.put("IChaosCore", ThaumcraftApi.addInfusionCraftingRecipe("ChaosCore", chaosCore, 10, (new AspectList())
/*     */           
/* 693 */           .add(Aspect.ENTROPY, 25).add(Aspect.ORDER, 25).add(Aspect.EXCHANGE, 16).add(Aspect.MAGIC, 32), primalCharm, new ItemStack[] { dragonStone, pixieDust, voidIngot, pixieDust, alumentum, pixieDust, voidIngot, pixieDust }));
/*     */ 
/*     */ 
/*     */     
/* 697 */     recipes.put("ITheParadox", ThaumcraftApi.addInfusionCraftingRecipe("TheParadox", theParadox, 32, (new AspectList())
/*     */           
/* 699 */           .add(Aspect.AIR, 64).add(Aspect.FIRE, 64).add(Aspect.WATER, 64).add(Aspect.EARTH, 64).add(Aspect.ORDER, 64).add(Aspect.ENTROPY, 64).add(Aspect.VOID, 48).add(Aspect.WEAPON, 32).add(Aspect.MAGIC, 24).add(Aspect.EXCHANGE, 36), voidSword, new ItemStack[] { chaosCore, airShard, fireShard, waterShard, primordialPearl, earthShard, orderShard, entropyShard }));
/*     */ 
/*     */ 
/*     */     
/* 703 */     recipes.put("IShinyStone", ThaumcraftApi.addInfusionCraftingRecipe("ShinyStone", shinyStone, 8, (new AspectList())
/*     */           
/* 705 */           .add(Aspect.HEAL, 40).add(Aspect.LIFE, 32).add(Aspect.TRAP, 24).add(Aspect.EXCHANGE, 24).add(Aspect.MAGIC, 24).add(Aspect.CRYSTAL, 16), dragonStone, new ItemStack[] { primalCharm, goldIngot, gaiaSpirit, nitor, superGoldenApple, nitor, gaiaSpirit, goldIngot }));
/*     */ 
/*     */ 
/*     */     
/* 709 */     recipes.put("IOblivionAmulet", ThaumcraftApi.addInfusionCraftingRecipe("OblivionAmulet", oblivionAmulet, 16, (new AspectList())
/*     */           
/* 711 */           .add(Aspect.DEATH, 64).add(Aspect.EXCHANGE, 50).add(Aspect.VOID, 72).add(Aspect.DARKNESS, 100).add(Aspect.ELDRITCH, 24).add(Aspect.MAGIC, 32).add(Aspect.FIRE, 16), bloodPendant, new ItemStack[] { netherStar, blazePowder, voidIngot, blazePowder, eldritchEye, blazePowder, voidIngot, blazePowder }));
/*     */ 
/*     */ 
/*     */     
/* 715 */     recipes.put("IXPTome", ThaumcraftApi.addInfusionCraftingRecipe("XPTome", XPTome, 4, (new AspectList())
/*     */           
/* 717 */           .add(Aspect.SOUL, 32).add(Aspect.MIND, 40).add(Aspect.EXCHANGE, 8).add(Aspect.MAGIC, 24), writableBook, new ItemStack[] { jarredBrain, salisMundus, amber, salisMundus, nitor, salisMundus, amber, salisMundus }));
/*     */ 
/*     */ 
/*     */     
/* 721 */     recipes.put("INebulousCore", ThaumcraftApi.addInfusionCraftingRecipe("NebulousCore", nebulousCore, 12, (new AspectList())
/*     */           
/* 723 */           .add(Aspect.AURA, 80).add(Aspect.MAGIC, 128).add(Aspect.VOID, 32).add(Aspect.ELDRITCH, 50).add(Aspect.DARKNESS, 40).add(Aspect.TRAVEL, 24).add(Aspect.EXCHANGE, 48), primordialPearl, new ItemStack[] { greatVisAmulet, etherealEssence, gaiaSpirit, thaumiumIngot, enderAir, etherealEssence, superpositionRing, etherealEssence, enderAir, thaumiumIngot, gaiaSpirit, etherealEssence }));
/*     */ 
/*     */ 
/*     */     
/* 727 */     recipes.put("IDiscordTome", ThaumcraftApi.addInfusionCraftingRecipe("DiscordTome", discordTome, 5, (new AspectList())
/*     */           
/* 729 */           .add(Aspect.TRAVEL, 50).add(Aspect.DARKNESS, 30).add(Aspect.VOID, 30).add(Aspect.MAGIC, 45).add(Aspect.MIND, 24), writableBook, new ItemStack[] { primalCharm, elementiumIngot, enderPearl, enderAir, salisMundus, portableHole, salisMundus, enderAir, enderPearl, elementiumIngot }));
/*     */ 
/*     */ 
/*     */     
/* 733 */     recipes.put("IObeliskDrainer", ThaumcraftApi.addInfusionCraftingRecipe("ObeliskDrainer", obeliskDrainer, 8, (new AspectList())
/*     */           
/* 735 */           .add(Aspect.ELDRITCH, 72).add(Aspect.VOID, 64).add(Aspect.DARKNESS, 64).add(Aspect.ENERGY, 40).add(Aspect.MAGIC, 52).add(Aspect.AURA, 25).add(Aspect.EXCHANGE, 32), writableBook, new ItemStack[] { nitor, knowledgeFragment, voidIngot, primalCharm, enchantedFabric, salisMundus, eldritchEye, salisMundus, enchantedFabric, primalCharm, voidIngot, knowledgeFragment }));
/*     */ 
/*     */ 
/*     */     
/* 739 */     recipes.put("ITelekinesisTome", ThaumcraftApi.addInfusionCraftingRecipe("TelekinesisTome", telekinesisTome, 8, (new AspectList())
/*     */           
/* 741 */           .add(Aspect.MOTION, 32).add(Aspect.TRAP, 48).add(Aspect.MAGIC, 64).add(Aspect.DARKNESS, 24).add(Aspect.AIR, 25).add(Aspect.ENERGY, 40).add(Aspect.TOOL, 16), writableBook, new ItemStack[] { primalCharm, voidSeed, shockFocus, gaiaSpirit, salisMundus, gravityRod, salisMundus, gaiaSpirit, shockFocus, voidSeed }));
/*     */ 
/*     */ 
/*     */     
/* 745 */     recipes.put("ISoulTome", ThaumcraftApi.addInfusionCraftingRecipe("SoulTome", soulTome, 10, (new AspectList())
/*     */           
/* 747 */           .add(Aspect.SOUL, 80).add(Aspect.VOID, 60).add(Aspect.MAGIC, 45).add(Aspect.TRAP, 60).add(Aspect.DEATH, 32).add(Aspect.DARKNESS, 48).add(Aspect.EXCHANGE, 52), writableBook, new ItemStack[] { pechFocus, alumentum, gaiaSpirit, enderEye, gaiaSpirit, salisMundus, jarredBrain, salisMundus, gaiaSpirit, enderEye, gaiaSpirit, alumentum }));
/*     */ 
/*     */ 
/*     */     
/* 751 */     recipes.put("IFateTome", ThaumcraftApi.addInfusionCraftingRecipe("FateTome", fateTome, 20, (new AspectList())
/*     */           
/* 753 */           .add(Aspect.HEAL, 40).add(Aspect.LIFE, 64).add(Aspect.EXCHANGE, 32).add(Aspect.MAN, 24).add(Aspect.MIND, 16).add(Aspect.SOUL, 36).add(Aspect.ORDER, 20), writableBook, new ItemStack[] { goldLaurel, dragonStone, gaiaSpirit, nitor, gaiaSpirit, salisMundus, primalCharm, salisMundus, gaiaSpirit, nitor, gaiaSpirit, dragonStone }));
/*     */ 
/*     */ 
/*     */     
/* 757 */     recipes.put("IEldritchSpell", ThaumcraftApi.addInfusionCraftingRecipe("EldritchSpell", eldritchSpell, 8, (new AspectList())
/*     */           
/* 759 */           .add(Aspect.ELDRITCH, 72).add(Aspect.DARKNESS, 64).add(Aspect.DEATH, 40).add(Aspect.MAGIC, 36).add(Aspect.MIND, 24).add(Aspect.WEAPON, 12).add(Aspect.TAINT, 24), writableBook, new ItemStack[] { primalCharm, voidIngot, fireball, eldritchEye, bottledTaint, salisMundus, gaiaSpirit, salisMundus, bottledTaint, eldritchEye, fireball, voidIngot }));
/*     */ 
/*     */ 
/*     */     
/* 763 */     recipes.put("IChaosTome", ThaumcraftApi.addInfusionCraftingRecipe("ChaosTome", chaosTome, 12, (new AspectList())
/*     */           
/* 765 */           .add(Aspect.AIR, 100).add(Aspect.WATER, 100).add(Aspect.EARTH, 100).add(Aspect.FIRE, 100).add(Aspect.ORDER, 100).add(Aspect.ENTROPY, 100).add(Aspect.MAGIC, 256).add(Aspect.EXCHANGE, 72).add(Aspect.ELDRITCH, 36).add(Aspect.DARKNESS, 40), writableBook, new ItemStack[] { primalFocus, salisMundus, elementiumIngot, primalFocus, gaiaSpirit, salisMundus, primalFocus, salisMundus, gaiaSpirit, primalFocus, elementiumIngot, salisMundus }));
/*     */ 
/*     */ 
/*     */     
/* 769 */     recipes.put("INuclearFury", ThaumcraftApi.addInfusionCraftingRecipe("NuclearFury", nuclearFury, 10, (new AspectList())
/*     */           
/* 771 */           .add(Aspect.LIGHT, 72).add(Aspect.FIRE, 80).add(Aspect.ENTROPY, 36).add(Aspect.MAGIC, 64).add(Aspect.AURA, 16).add(Aspect.ENERGY, 48).add(Aspect.SENSES, 32).add(Aspect.VOID, 40), chaosTome, new ItemStack[] { missileRod, alumentum, terrasteelIngot, primalCharm, gaiaSpirit, etherealEssence, primordialPearl, etherealEssence, gaiaSpirit, primalCharm, terrasteelIngot, alumentum }));
/*     */ 
/*     */ 
/*     */     
/* 775 */     recipes.put("ICrimsonSpell", ThaumcraftApi.addInfusionCraftingRecipe("CrimsonSpell", crimsonSpell, 8, (new AspectList())
/*     */           
/* 777 */           .add(Aspect.FIRE, 72).add(Aspect.ENTROPY, 64).add(Aspect.MAGIC, 54).add(Aspect.EXCHANGE, 24).add(Aspect.SENSES, 32).add(Aspect.MIND, 24).add(Aspect.ENERGY, 48), writableBook, new ItemStack[] { crimsonRites, blazePowder, enchantedFabric, primalCharm, redstone, salisMundus, eldritchEye, salisMundus, redstone, primalCharm, enchantedFabric, blazePowder }));
/*     */ 
/*     */ 
/*     */     
/* 781 */     recipes.put("ILunarFlares", ThaumcraftApi.addInfusionCraftingRecipe("LunarFlares", lunarFlares, 12, (new AspectList())
/*     */           
/* 783 */           .add(Aspect.AIR, 64).add(Aspect.ENERGY, 96).add(Aspect.MAGIC, 120).add(Aspect.MIND, 32).add(Aspect.ORDER, 40).add(Aspect.MOTION, 16).add(Aspect.WEAPON, 24).add(Aspect.LIGHT, 48), writableBook, new ItemStack[] { primalCharm, enderAir, terrasteelIngot, starSword, gaiaSpirit, salisMundus, primordialPearl, salisMundus, gaiaSpirit, starSword, terrasteelIngot, enderAir }));
/*     */ 
/*     */ 
/*     */     
/* 787 */     recipes.put("IApotheosis", ThaumcraftApi.addInfusionCraftingRecipe("Apotheosis", apotheosis, 48, (new AspectList())
/*     */           
/* 789 */           .add(Aspect.WEAPON, 128).add(Aspect.TOOL, 72).add(Aspect.EXCHANGE, 54).add(Aspect.MAGIC, 80).add(Aspect.LIGHT, 64).add(Aspect.METAL, 32).add(Aspect.MIND, 48).add(Aspect.ORDER, 40), writableBook, new ItemStack[] { kingKey, knowledgeFragment, goldIngot, primalCharm, gaiaSpirit, salisMundus, primordialPearl, salisMundus, gaiaSpirit, primalCharm, goldIngot, knowledgeFragment }));
/*     */ 
/*     */ 
/*     */     
/* 793 */     recipes.put("IFalseJustice", ThaumcraftApi.addInfusionCraftingRecipe("FalseJustice", falseJustice, 24, (new AspectList())
/*     */           
/* 795 */           .add(Aspect.LIGHT, 120).add(Aspect.GREED, 24).add(Aspect.WEAPON, 40).add(Aspect.TOOL, 36).add(Aspect.MAN, 52).add(Aspect.MAGIC, 36).add(Aspect.EXCHANGE, 72).add(Aspect.SOUL, 48).add(Aspect.DARKNESS, 45), writableBook, new ItemStack[] { nitor, goldIngot, netherStar, knowledgeFragment, primordialPearl, knowledgeFragment, netherStar, goldIngot }));
/*     */ 
/*     */ 
/*     */     
/* 799 */     recipes.put("ITerrorCrown", ThaumcraftApi.addInfusionCraftingRecipe("TerrorCrown", terrorCrown, 8, (new AspectList())
/*     */           
/* 801 */           .add(Aspect.ELDRITCH, 50).add(Aspect.WEAPON, 32).add(Aspect.SENSES, 24).add(Aspect.DEATH, 42).add(Aspect.SOUL, 16).add(Aspect.ENTROPY, 20).add(Aspect.MAGIC, 36).add(Aspect.ARMOR, 16), revealingGoggles, new ItemStack[] { netherStar, prideRune, goldIngot, gaiaSpirit, enderEye, gaiaSpirit, goldIngot, wrathRune }));
/*     */ 
/*     */ 
/*     */     
/* 805 */     recipes.put("IThunderpeal", ThaumcraftApi.addInfusionCraftingRecipe("Thunderpeal", thunderpeal, 4, (new AspectList())
/*     */           
/* 807 */           .add(Aspect.ENERGY, 32).add(Aspect.MAGIC, 24).add(Aspect.AIR, 24).add(Aspect.MIND, 10).add(Aspect.FIRE, 16), writableBook, new ItemStack[] { primalCharm, pixieDust, manaPearl, salisMundus, shockFocus, salisMundus, manaPearl, pixieDust }));
/*     */ 
/*     */ 
/*     */     
/* 811 */     recipes.put("IOverthrower", ThaumcraftApi.addInfusionCraftingRecipe("Overthrower", overthrower, 8, (new AspectList())
/*     */           
/* 813 */           .add(Aspect.FIRE, 70).add(Aspect.TRAVEL, 40).add(Aspect.MAGIC, 36).add(Aspect.MIND, 24).add(Aspect.DARKNESS, 48), writableBook, new ItemStack[] { primalCharm, blazePowder, netherBrick, salisMundus, netherWart, enderEye, netherWart, salisMundus, netherBrick, blazePowder }));
/*     */ 
/*     */ 
/*     */     
/* 817 */     recipes.put("IPechFocus", ThaumcraftApi.addInfusionCraftingRecipe("PechFocus", pechFocus, 4, (new AspectList())
/*     */           
/* 819 */           .add(Aspect.EXCHANGE, 25).add(Aspect.POISON, 20).add(Aspect.MAGIC, 16).add(Aspect.ENTROPY, 12).add(Aspect.SENSES, 8), primalCharm, new ItemStack[] { emerald, blazePowder, quartz, ghastTear, sugar, quartz, redstone, netherWart, quartz, fermentedSpiderEye }));
/*     */ 
/*     */ 
/*     */     
/* 823 */     recipes.put("IDiscordRing", ThaumcraftApi.addInfusionCraftingRecipe("DiscordRing", discordRing, 2, (new AspectList())
/*     */           
/* 825 */           .add(Aspect.TOOL, 16).add(Aspect.TRAVEL, 16).add(Aspect.MAGIC, 12).add(Aspect.EXCHANGE, 8).add(Aspect.VOID, 8), blankRing, new ItemStack[] { dragonStone, salisMundus, elementiumIngot, salisMundus, gaiaSpirit, salisMundus, elementiumIngot, salisMundus }));
/*     */ 
/*     */ 
/*     */     
/* 829 */     recipes.put("IVoidGrimoire", ThaumcraftApi.addInfusionCraftingRecipe("VoidGrimoire", voidGrimoire, 10, (new AspectList())
/*     */           
/* 831 */           .add(Aspect.VOID, 100).add(Aspect.DARKNESS, 52).add(Aspect.TRAVEL, 24).add(Aspect.MAGIC, 40).add(Aspect.MIND, 32).add(Aspect.ELDRITCH, 40).add(Aspect.EXCHANGE, 16).add(Aspect.ENTROPY, 20), overthrower, new ItemStack[] { gaiaSpirit, voidSeed, knowledgeFragment, voidSeed, eldritchEye, voidSeed, knowledgeFragment, voidSeed }));
/*     */ 
/*     */ 
/*     */     
/* 835 */     recipes.put("IOblivionStone", ThaumcraftApi.addInfusionCraftingRecipe("OblivionStone", oblivionStone, 4, (new AspectList())
/*     */           
/* 837 */           .add(Aspect.VOID, 16).add(Aspect.DARKNESS, 16).add(Aspect.ENTROPY, 12).add(Aspect.EXCHANGE, 8).add(Aspect.MAGIC, 8), sinisterStone, new ItemStack[] { enderEye, voidSeed, voidIngot, voidSeed, nitor, voidSeed, voidIngot, voidSeed }));
/*     */   }
/*     */ }


/* Location:              C:\Users\antro\Documents\forgottenRelicsDeobfuscated.jar!\com\integral\forgottenrelics\research\RelicsResearchRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */