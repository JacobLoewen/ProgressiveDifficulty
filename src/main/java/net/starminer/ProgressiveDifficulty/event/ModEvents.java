package net.starminer.ProgressiveDifficulty.event;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;
import java.util.Timer;


@Mod.EventBusSubscriber
public class ModEvents {
    static Timer timer;
    public static int[] IDs = new int[50];
    public static int IDc = 0;
    //Make a system that takes the IDs of creepers and iterates through to see if their ID is in progress
    @SubscribeEvent
    //This MUST be static
    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            World world = player.getEntityWorld();
            List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().grow(15));
            // Check if any of the nearby entities are creepers
            //Iterate through only creepers and check distance every second
            for (Entity entity : entities) {
                if (entity.getDistance(player) <= 15 && entity instanceof EntityCreeper) {
                    EntityCreeper creeper = (EntityCreeper) entity;
                    // Do something when a creeper is nearby
                    //Minecraft.getMinecraft().player.sendMessage(new TextComponentString("A creeper is nearby!"));
                    int c = 0;

                    int creeperID = creeper.getEntityId();

                    for (int ID : IDs){
                        if (creeperID == ID) {
                            c = 1;
                            //Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Exists: " + creeperID));
                            break;
                        }
                    }
                    if (c == 0) {
                        if (IDc >= 25){
                            IDc = 0;
                        }
                        IDs[IDc] = creeperID;
                        //Minecraft.getMinecraft().player.sendMessage(new TextComponentString("IDc: " + IDc));
                        //Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Adding: " + creeperID));
                        IDc += 1;
                        timer = new Timer();
                        //15 seconds
                        timer.schedule(new DisplayCountdown(creeper, player), 0, 1000);
                    }
                }
            }
            //CountDown(creeper, player);
        }
    }

    public static boolean distTrue(EntityCreeper creeper, EntityPlayer player) {
        double distance = creeper.getDistance(player);
        //Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Distance: " + distance));
        return distance <= (15);
    }
//    public static void CountDown(EntityCreeper creeper, EntityPlayer player) {
//        //Below Works!
//        //Minecraft.getMinecraft().player.sendMessage(new TextComponentString("New Timer!"));
//        //Try out Matthew's Timer function
//        timer = new Timer();
//        //15 seconds
//        timer.schedule(new DisplayCountdown(creeper, player), 0, 1000);
//    }
}
