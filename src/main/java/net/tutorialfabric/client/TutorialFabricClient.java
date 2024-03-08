package net.tutorialfabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.tutorialfabric.block.tile.SteveBlockModel;
import net.tutorialfabric.block.tile.SteveBlockRenderer;
import net.tutorialfabric.registry.ExampleBlockEntities;
import net.tutorialfabric.registry.ExampleBlocks;

import java.util.function.Supplier;

public class TutorialFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(ExampleBlockEntities.STEVE_BLOCK_ENTITY, SteveBlockRenderer::new);
        registerModelLayer(SteveBlockModel.LAYER_LOCATION, SteveBlockModel::createBodyLayer);
        putRenderLayer(ExampleBlocks.STEVE_BLOCK, RenderType.cutout());
    }

    public static ModelLayerLocation registerModelLayer(ModelLayerLocation layerLocation, Supplier<LayerDefinition> layerDefinition) {
        EntityModelLayerRegistry.registerModelLayer(layerLocation, layerDefinition::get);
        return layerLocation;
    }

    public static <T extends Block> void putRenderLayer(T block, RenderType renderType) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, renderType);
    }
}
