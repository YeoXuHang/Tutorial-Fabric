package net.tutorialfabric;

import net.fabricmc.api.ModInitializer;
import net.tutorialfabric.registry.ExampleBlockEntities;
import net.tutorialfabric.registry.ExampleBlocks;
import net.tutorialfabric.registry.ExampleItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialFabric implements ModInitializer {
    public static final String MOD_ID = "tutorialfabric";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ExampleItems.registerModItems();
        ExampleBlocks.registerModBlocks();

        ExampleBlockEntities.registerBlockEntities();
    }
}
