package org.cyclops.integrateddynamics.block;

import net.minecraft.block.material.Material;
import org.cyclops.cyclopscore.config.configurable.ConfigurableBlockSapling;
import org.cyclops.cyclopscore.config.configurable.IConfigurable;
import org.cyclops.cyclopscore.config.extendedconfig.BlockConfig;
import org.cyclops.integrateddynamics.IntegratedDynamics;
import org.cyclops.integrateddynamics.Reference;
import org.cyclops.integrateddynamics.world.gen.WorldGeneratorMenrilTree;

/**
 * Config for the Menril Sapling.
 * @author rubensworks
 *
 */
public class BlockMenrilSaplingConfig extends BlockConfig {

    /**
     * The unique instance.
     */
    public static BlockMenrilSaplingConfig _instance;

    /**
     * Make a new instance.
     */
    public BlockMenrilSaplingConfig() {
        super(
                IntegratedDynamics._instance,
                true,
                "menrilSapling",
                null,
                null
        );
    }

    @Override
    protected IConfigurable initSubInstance() {
        return new ConfigurableBlockSapling(this, Material.PLANTS, new WorldGeneratorMenrilTree(false));
    }
    
    @Override
    public String getOreDictionaryId() {
        return Reference.DICT_SAPLINGTREE;
    }
    
}
