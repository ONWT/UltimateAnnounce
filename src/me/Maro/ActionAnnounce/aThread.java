package me.Maro.ActionAnnounce;

import me.Maro.UltimateAnnounce.UltimateAnnounce;

import org.bukkit.scheduler.BukkitRunnable;








public class aThread
  extends BukkitRunnable
{
  private UltimateAnnounce a;
  
  public aThread(UltimateAnnounce ultimateAnnounce)
  {
    this.a = ultimateAnnounce;
  }
  



  public void run()
  {
    if (a.isaRandom()) {
    	a.broadcastaRandomMessage();
    } else {
    	a.broadcastaNextMessage();
    }
  }
}