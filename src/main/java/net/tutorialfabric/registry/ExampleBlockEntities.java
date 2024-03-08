package net.tutorialfabric.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.tutorialfabric.TutorialFabric;
import net.tutorialfabric.block.tile.steve_block.SteveBlockEntity;


public class ExampleBlockEntities {
    public static final BlockEntityType<SteveBlockEntity> STEVE_BLOCK_ENTITY =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(TutorialFabric.MOD_ID, "steve_block_entity"),
                    FabricBlockEntityTypeBuilder.create(SteveBlockEntity::new,
                            ExampleBlocks.STEVE_BLOCK).build(null));

    public static void registerBlockEntities() {
        TutorialFabric.LOGGER.info("Registering Block Entities for " + TutorialFabric.MOD_ID);
    }
}
