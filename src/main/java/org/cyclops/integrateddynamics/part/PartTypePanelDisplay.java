package org.cyclops.integrateddynamics.part;

import net.minecraft.block.Block;
import org.cyclops.cyclopscore.config.extendedconfig.BlockConfig;
import org.cyclops.integrateddynamics.core.block.IgnoredBlockStatus;
import org.cyclops.integrateddynamics.core.part.panel.PartTypePanelVariableDriven;

/**
 * A part that can display variables.
 * @author rubensworks
 */
public class PartTypePanelDisplay extends PartTypePanelVariableDriven<PartTypePanelDisplay, PartTypePanelDisplay.State> {

    public PartTypePanelDisplay(String name) {
        super(name);
    }

    @Override
    public Class<? super PartTypePanelDisplay> getPartTypeClass() {
        return PartTypePanelDisplay.class;
    }

    @Override
    protected Block createBlock(BlockConfig blockConfig) {
        return new IgnoredBlockStatus(blockConfig);
    }

    @Override
    public PartTypePanelDisplay.State constructDefaultState() {
        return new PartTypePanelDisplay.State();
    }

    @Override
    public int getConsumptionRate(State state) {
        return state.hasVariable() ? 2 : 1;
    }

    public static class State extends PartTypePanelVariableDriven.State<PartTypePanelDisplay, PartTypePanelDisplay.State> {

    }

}
