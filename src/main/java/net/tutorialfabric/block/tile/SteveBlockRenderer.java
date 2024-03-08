package net.tutorialfabric.block.tile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.Level;
import net.tutorialfabric.TutorialFabric;
import net.tutorialfabric.block.tile.steve_block.SteveBlockEntity;

public class SteveBlockRenderer<T extends SteveBlockEntity> implements BlockEntityRenderer<T> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(TutorialFabric.MOD_ID,"textures/block/steve.png");
    private final ItemRenderer itemRenderer;
    private static SteveBlockModel steveBlockModel;


    public SteveBlockRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
        steveBlockModel = new SteveBlockModel(context.bakeLayer(SteveBlockModel.LAYER_LOCATION));
    }

    @Override
    public void render(T entity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {
        Level world = entity.getLevel();
        assert world != null;
        poseStack.mulPose(Axis.XP.rotationDegrees(-180.0F));
        steveBlockModel.renderToBuffer(poseStack, multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(TEXTURE)), i, j, 1.0F, 1.0F, 1.0F, 1.0F);
        steveBlockModel.Root.setPos(8.0F, -24.0F, -8.0F);
        //Check if the block has item in the slot
        if (entity.hasLevel() && !entity.isEmpty()) {
            poseStack.pushPose();
            //set position for the item
            poseStack.translate(0.5, -0.2, -0.7);
            //set scale for the item
            poseStack.scale(0.45F, 0.45F, 0.45F);
            //rotate -180 because we dont want the item to be upside down
            poseStack.mulPose(Axis.XP.rotationDegrees(-180.0F));
            //Rendering the item
            this.itemRenderer.renderStatic(entity.getItem(0), ItemDisplayContext.FIXED, i, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, entity.getLevel(), j);
            poseStack.popPose();
        }
    }
}
