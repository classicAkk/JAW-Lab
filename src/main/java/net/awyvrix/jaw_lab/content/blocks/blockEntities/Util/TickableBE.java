<<<<<<< Updated upstream:src/main/java/net/classicAkk/jaw_lab/Content/Blocks/BlockEntities/Util/TickableBE.java
package net.classicAkk.jaw_lab.Content.Blocks.BlockEntities.Util;
=======
package net.awyvrix.jaw_lab.content.blocks.blockEntities.util;
>>>>>>> Stashed changes:src/main/java/net/awyvrix/jaw_lab/content/blocks/blockEntities/Util/TickableBE.java

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;

public interface TickableBE {
    void tick();

    static <T extends BlockEntity>BlockEntityTicker<T> getTickerHelper(Level level) {
        return level.isClientSide() ? null : (level0, pos0, state0, blockEntity) -> ((TickableBE)blockEntity).tick();
    }
}