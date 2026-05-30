package com.vincenthuto.gcen;

import com.vincenthuto.gcen.client.model.ChihuahuaDogAdultModel;
import com.vincenthuto.gcen.client.model.ChihuahuaDogBabyModel;
import com.vincenthuto.gcen.client.model.LabradorDogAdultModel;
import com.vincenthuto.gcen.client.model.LabradorDogBabyModel;
import com.vincenthuto.gcen.client.renderer.ChihuahuaDogRenderer;
import com.vincenthuto.gcen.client.renderer.LabradorDogRenderer;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = GluttonousCaninesEatingNightshades.MODID, dist = Dist.CLIENT)
public class GluttonousCaninesEatingNightshadesClient {
    public GluttonousCaninesEatingNightshadesClient(IEventBus modEventBus, ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        modEventBus.addListener(this::onClientSetup);
        modEventBus.addListener(this::registerLayerDefinitions);
        modEventBus.addListener(this::registerRenderers);
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        GluttonousCaninesEatingNightshades.LOGGER.info("HELLO FROM CLIENT SETUP");
        GluttonousCaninesEatingNightshades.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(GluttonousCaninesEatingNightshades.LABRADOR_DOG.get(), LabradorDogRenderer::new);
        event.registerEntityRenderer(GluttonousCaninesEatingNightshades.CHIHUAHUA_DOG.get(), ChihuahuaDogRenderer::new);
    }

    private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LabradorDogAdultModel.LAYER_LOCATION, LabradorDogAdultModel::createBodyLayer);
        event.registerLayerDefinition(LabradorDogAdultModel.ARMOR_LAYER_LOCATION, LabradorDogAdultModel::createArmorLayer);
        event.registerLayerDefinition(LabradorDogBabyModel.LAYER_LOCATION, LabradorDogBabyModel::createBodyLayer);
        event.registerLayerDefinition(ChihuahuaDogAdultModel.LAYER_LOCATION, ChihuahuaDogAdultModel::createBodyLayer);
        event.registerLayerDefinition(ChihuahuaDogAdultModel.ARMOR_LAYER_LOCATION, ChihuahuaDogAdultModel::createArmorLayer);
        event.registerLayerDefinition(ChihuahuaDogBabyModel.LAYER_LOCATION, ChihuahuaDogBabyModel::createBodyLayer);
    }
}
