package net.tutorialfabric.block.tile.steve_block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SteveBlock extends BaseEntityBlock {
    public SteveBlock(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE = Block.box(1, 0, 1, 15, 8, 15);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public InteractionResult use(@NotNull BlockState state, Level worldIn, @NotNull BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        ItemStack heldItem = player.getItemInHand(handIn);
        if (worldIn.getBlockEntity(pos) instanceof SteveBlockEntity block && (!player.isShiftKeyDown()  && heldItem.getItem() != this.asItem())) {
            ItemStack stack = heldItem.copy();
            stack.setCount(1);
            if(block.getItem(0).isEmpty()){
                block.setItem(0, stack);
                if(!player.isCreative()){
                    heldItem.shrink(1);
                }
                return InteractionResult.SUCCESS;
            }else if(block.getItem(0).is(stack.getItem()) && block.getItem(0).getMaxStackSize() > block.getItem(0).getCount() + stack.getCount()){
                block.getItem(0).grow(1);
                if(!player.isCreative()){
                    heldItem.shrink(1);
                }
                return InteractionResult.SUCCESS;
            }else{
                popResource(worldIn, pos, block.getItem(0).copy());
                block.setItem(0, ItemStack.EMPTY);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SteveBlockEntity(pos, state);
    }

    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        BlockEntity tileentity = worldIn.getBlockEntity(pos);
        if (tileentity instanceof SteveBlockEntity) {
            Containers.dropContents(worldIn, pos, (SteveBlockEntity) tileentity);
            worldIn.updateNeighbourForOutputSignal(pos, this);
        }
        super.onRemove(state, worldIn, pos, newState, isMoving);
    }

    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}
