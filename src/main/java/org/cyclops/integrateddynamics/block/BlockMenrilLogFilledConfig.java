package org.cyclops.integrateddynamics.block;

import net.minecraft.init.Blocks;
import org.cyclops.cyclopscore.config.ConfigurableProperty;
import org.cyclops.cyclopscore.config.ConfigurableTypeCategory;
import org.cyclops.cyclopscore.config.extendedconfig.BlockConfig;
import org.cyclops.integrateddynamics.IntegratedDynamics;
import org.cyclops.integrateddynamics.Reference;

/**
 * Config for the Menril Log.
 * @author rubensworks
 *
 */
public class BlockMenrilLogFilledConfig extends BlockConfig {

    /**
     * The unique instance.
     */
    public static BlockMenrilLogFilledConfig _instance;

    /**
     * The 1/x chance at which a Menril Log will be filled with Menril Resin when generated.
     */
    @ConfigurableProperty(category = ConfigurableTypeCategory.WORLDGENERATION, comment = "The 1/x chance at which a Menril Log will be filled with Menril Resin when generated.", isCommandable = true)
    public static int filledMenrilLogChance = 10;

    /**
     * Make a new instance.
     */
    public BlockMenrilLogFilledConfig() {
        super(
                IntegratedDynamics._instance,
                true,
                "menrilLogFilled",
                null,
                BlockMenrilLogFilled.class
        );
    }
    
    @Override
    public String getOreDictionaryId() {
        return Reference.DICT_WOODLOG;
    }
    
    @Override
    public void onRegistered() {
    	Blocks.FIRE.setFireInfo(getBlockInstance(), 5, 20);
    }

}
