package net.starminer.ProgressiveDifficulty.event;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

import java.util.TimerTask;

import static net.starminer.ProgressiveDifficulty.event.ModEvents.IDc;
import static net.starminer.ProgressiveDifficulty.event.ModEvents.IDs;

public class DisplayCountdown extends TimerTask {

    private final EntityPlayer player;
    private final EntityCreeper creeper;
    int seconds = 15;
    public DisplayCountdown(EntityCreeper creeper, EntityPlayer player) {
        this.creeper = creeper;
        this.player = player;
    }

    public void run() {
        int creeperID = creeper.getEntityId();
        if (seconds > 0) {
            //Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Seconds: " + seconds));
            seconds--;

            if(!ModEvents.distTrue(creeper, player)){
                //Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Countdown Interrupted"));
                //Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Creeper IDs (removal)"));
                int c2 = 0;
                for(int ID : IDs){
                    if (ID == creeperID){
                        //Minecraft.getMinecraft().player.sendMessage(new TextComponentString("" + creeperID));
                        IDs[c2] = 0;
                        break;
                    }
                    c2 += 1;
                }
                System.exit(0);
            }
        } else {
            //Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Countdown Finished"));
            creeper.ignite();
            //Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Creeper IDs (removal): "));
            int c2 = 0;
            for(int ID : IDs){
                //Minecraft.getMinecraft().player.sendMessage(new TextComponentString("" + creeperID));
                if (ID == creeperID){
                    IDs[c2] = 0;
                    break;
                }
                c2 += 1;
            }
            System.exit(0);
        }
    }
}
