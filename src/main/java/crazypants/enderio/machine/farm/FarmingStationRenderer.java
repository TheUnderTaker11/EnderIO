package crazypants.enderio.machine.farm;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import com.enderio.core.client.render.BoundingBox;
import com.enderio.core.client.render.CubeRenderer;

import crazypants.enderio.EnderIO;
import crazypants.enderio.machine.TechneMachineRenderer;

public class FarmingStationRenderer extends TechneMachineRenderer<TileFarmStation> {

    public FarmingStationRenderer() {
        super(EnderIO.blockFarmStation, "models/farm");
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
            RenderBlocks renderer) {
        IIcon override = renderer.overrideBlockTexture;

        if (world != null) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te instanceof TileFarmStation && ((TileFarmStation) te).isActive()) {
                final Tessellator tessellator = Tessellator.instance;
                BoundingBox bb = BoundingBox.UNIT_CUBE.scale(10D / 16D, 0.25, 10D / 16D);
                bb = bb.scale(1.01, 1, 1.01);
                bb = bb.translate(0, 5f / 16f, 0);
                bb = bb.translate(x, y, z);
                tessellator.setColorOpaque_F(1, 1, 1);
                tessellator.setBrightness(0xF000F0);
                IIcon ico = override != null ? override : Blocks.portal.getBlockTextureFromSide(1);
                float x1 = ico.getInterpolatedU(3);
                float x2 = ico.getInterpolatedU(13);
                float y1 = ico.getInterpolatedV(6);
                float y2 = ico.getInterpolatedV(10);
                CubeRenderer.get().render(bb, x1, x2, y1, y2);
            }
        }

        return super.renderWorldBlock(world, x, y, z, block, modelId, renderer);
    }
}
