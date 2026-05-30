package com.vincenthuto.gcen;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.vincenthuto.gcen.entity.ChihuahuaDogEntity;
import com.vincenthuto.gcen.entity.LabradorDogEntity;
import com.vincenthuto.gcen.block.SpaghettiPileBlock;
import com.vincenthuto.gcen.block.TomatoCropBlock;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.TypedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(GluttonousCaninesEatingNightshades.MODID)
public class GluttonousCaninesEatingNightshades {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "gcen";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "gcen" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "gcen" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "gcen" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    // Create a Deferred Register to hold EntityTypes which will all be registered under the "gcen" namespace
    public static final DeferredRegister.Entities ENTITY_TYPES = DeferredRegister.createEntities(MODID);

    // Creates a wolf-like labrador entity with a larger body size
    public static final DeferredHolder<EntityType<?>, EntityType<LabradorDogEntity>> LABRADOR_DOG = ENTITY_TYPES.registerEntityType(
            "labrador_dog",
            LabradorDogEntity::new,
            MobCategory.CREATURE,
            builder -> builder.sized(0.9F, 1.25F));

    // Creates a smaller wolf-like chihuahua entity with a larger head silhouette
    public static final DeferredHolder<EntityType<?>, EntityType<ChihuahuaDogEntity>> CHIHUAHUA_DOG = ENTITY_TYPES.registerEntityType(
            "chihuahua_dog",
            ChihuahuaDogEntity::new,
            MobCategory.CREATURE,
            builder -> builder.sized(0.45F, 0.65F));

    // Creates a decorative ground block for placed spaghetti
    public static final DeferredBlock<SpaghettiPileBlock> SPAGHETTI_PILE = BLOCKS.registerBlock("spaghetti_pile",
            SpaghettiPileBlock::new,
            properties -> properties
                    .mapColor(MapColor.COLOR_ORANGE)
                    .instabreak()
                    .noOcclusion()
                    .sound(SoundType.WET_GRASS)
                    .pushReaction(PushReaction.DESTROY));

    // Creates a food block item that can be eaten or placed on the ground
    public static final DeferredItem<BlockItem> SPAGHETTI = ITEMS.registerSimpleBlockItem("spaghetti", SPAGHETTI_PILE,
            properties -> properties.food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationModifier(0.8f)
                    .build()));

    // Creates a new four-stage crop block with the id "gcen:tomato_crop"
    public static final DeferredBlock<TomatoCropBlock> TOMATO_CROP = BLOCKS.registerBlock("tomato_crop",
            TomatoCropBlock::new,
            properties -> properties
                    .mapColor(MapColor.PLANT)
                    .noCollision()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY));

    // Creates a food item with the id "gcen:tomato"
    public static final DeferredItem<Item> TOMATO = ITEMS.registerSimpleItem("tomato",
            properties -> properties.food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.3f)
                    .build()));

     // Creates a seed item that plants the tomato crop
     public static final DeferredItem<BlockItem> TOMATO_SEEDS = ITEMS.registerSimpleBlockItem("tomato_seeds", TOMATO_CROP);

      // Creates a spawn egg for the labrador dog entity
      public static final DeferredItem<SpawnEggItem> LABRADOR_DOG_SPAWN_EGG = ITEMS.registerItem("labrador_dog_spawn_egg",
              SpawnEggItem::new,
              properties -> properties.component(DataComponents.ENTITY_DATA, TypedEntityData.of(LABRADOR_DOG.get(), new CompoundTag())));

      // Creates a spawn egg for the chihuahua dog entity
      public static final DeferredItem<SpawnEggItem> CHIHUAHUA_DOG_SPAWN_EGG = ITEMS.registerItem("chihuahua_dog_spawn_egg",
              SpawnEggItem::new,
              properties -> properties.component(DataComponents.ENTITY_DATA, TypedEntityData.of(CHIHUAHUA_DOG.get(), new CompoundTag())));

     // Creates a creative tab with the id "gcen:gcen_tab" for the mod's items
     public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GCEN_TAB = CREATIVE_MODE_TABS.register("gcen_tab", () -> CreativeModeTab.builder()
             .title(Component.translatable("itemGroup.gcen")) //The language key for the title of your CreativeModeTab
             .icon(() -> SPAGHETTI.get().getDefaultInstance())
             .displayItems((parameters, output) -> {
                 output.accept(LABRADOR_DOG_SPAWN_EGG.get());
                 output.accept(CHIHUAHUA_DOG_SPAWN_EGG.get());
                 output.accept(SPAGHETTI.get());
                 output.accept(TOMATO.get());
                 output.accept(TOMATO_SEEDS.get());
             }).build());

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public GluttonousCaninesEatingNightshades(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so entities get registered
        ENTITY_TYPES.register(modEventBus);

        // Register entity attributes and spawn placement rules
        modEventBus.addListener(this::registerEntityAttributes);
        modEventBus.addListener(this::registerSpawnPlacements);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (GluttonousCaninesEatingNightshades) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(LABRADOR_DOG.get(), Wolf.createAttributes().build());
        event.put(CHIHUAHUA_DOG.get(), Wolf.createAttributes().build());
    }

    private void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(
                LABRADOR_DOG.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (type, level, spawnReason, pos, random) -> Wolf.checkWolfSpawnRules(EntityType.WOLF, level, spawnReason, pos, random),
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(
                CHIHUAHUA_DOG.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (type, level, spawnReason, pos, random) -> Wolf.checkWolfSpawnRules(EntityType.WOLF, level, spawnReason, pos, random),
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
