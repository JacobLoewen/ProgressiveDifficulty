package net.starminer.ProgressiveDifficulty;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ProgressiveDifficulty.MOD_ID, name = ProgressiveDifficulty.NAME, version = ProgressiveDifficulty.VERSION)
public class ProgressiveDifficulty
{
    public static final String MOD_ID = "progressivedifficulty";
    //Important Data
    public static final String NAME = "Progressive Difficulty";
    public static final String VERSION = "1.12.2";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
