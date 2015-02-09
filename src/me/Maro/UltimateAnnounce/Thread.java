package me.Maro.UltimateAnnounce;

import org.bukkit.scheduler.BukkitRunnable;








public class Thread
  extends BukkitRunnable
{
  private UltimateAnnounce a;
  
  public Thread(UltimateAnnounce announce)
  {
    this.a = announce;
  }
  



  public void run()
  {
    if (a.isRandom()) {
    	a.broadcastRandomMessage();
    } else {
    	a.broadcastNextMessage();
    }
  }
}