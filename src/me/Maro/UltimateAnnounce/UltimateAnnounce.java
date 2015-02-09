package me.Maro.UltimateAnnounce;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.Maro.ActionAnnounce.ActionAPI;
import me.Maro.ActionAnnounce.aCommands;
import me.Maro.ActionAnnounce.aThread;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public final class UltimateAnnounce
  extends JavaPlugin
{
  @SuppressWarnings("unused")
private final String version = "1.3.4";
  private String prefix;
  private String header;
  private String footer;
  private String sound;
  private boolean space = true;
  private int interval;
  private boolean random = false;
  private int lastAnnounced = 0;
  private boolean active;
  @SuppressWarnings({ "unchecked", "rawtypes" })
private List<String> messages = new ArrayList();
  public int ainterval;
  public int aduration;
  public boolean arandom = false;
  private int alastAnnounced = 0;
  public boolean aactive;
  private UltimateAnnounce a;
  @SuppressWarnings({ "unchecked", "rawtypes" })
public List<String> amessages = new ArrayList();

  public void onEnable()
  {
	    if (!new File(getDataFolder(), "config.yml").exists()) {
	        saveDefaultConfig();
	      }
	      

	      load();
	      

    Commands an = new Commands(this);
    aCommands ac = new aCommands(this);
    getCommand("ultimateannounce").setExecutor(an);
    getCommand("uan").setExecutor(an);
    getCommand("actionannounce").setExecutor(ac);
    getCommand("uac").setExecutor(ac);
    enableuan();
  }
  




  public void onDisable()
  {
  }
  


  public String getPrefix()
  {
    return prefix;
  }
  


  public void setPrefix(String prefix)
  {
    this.prefix = prefix;
  }
  



  public String getHeader()
  {
    return header;
  }
  


  public void setHeader(String header)
  {
    this.header = header;
  }
  
  public String getFooter()
  {
    return footer;
  }
  


  public void setFooter(String footer)
  {
    this.footer = footer;
  }
  


  public String getSound()
  {
    return sound;
  }
  


  public void setSound(String sound)
  {
    this.sound = sound;
  }
  
  public List<String> getMessages()
  {
    return messages;
  }
  


  public void setMessages(List<String> messages)
  {
    this.messages = messages;
  }
  



  public void broadcastRandomMessage()
  {
    Random rand = new Random();
    int id = rand.nextInt(messages.size());
    broadcastMessage(id + 1);
  }
  



  public void broadcastNextMessage()
  {
    if (lastAnnounced >= messages.size()) {
      lastAnnounced = 0;
    }
    broadcastMessage(lastAnnounced + 1);
    lastAnnounced += 1;
  }
  



  @SuppressWarnings("deprecation")
public void enableuan()
  {
    getServer().getScheduler().scheduleSyncRepeatingTask(this, new Thread(this), 20 * getInterval(), 20 * getInterval());
    getServer().getScheduler().scheduleSyncRepeatingTask(this, new aThread(this), 20 * getaInterval(), 20 * getaInterval());
    active = true;
    save();
  }
  



  public void disableuan()
  {
    getServer().getScheduler().cancelTasks(this);
    active = false;
    save();
  }
  



  @SuppressWarnings("deprecation")
public void restartuan()
  {
    if (active) {
      getServer().getScheduler().cancelTasks(this);
      getServer().getScheduler().scheduleSyncRepeatingTask(this, new Thread(this), 20 * getInterval(), 20 * getInterval());
      getServer().getScheduler().scheduleSyncRepeatingTask(this, new aThread(this), 20 * getaInterval(), 20 * getaInterval());
    }
  }
  




  public boolean isRandom()
  {
    return random;
  }
  




  public int getInterval()
  {
    return interval;
  }
  




  public void setInterval(int interval)
  {
    this.interval = interval;
  }
  




  public void addMessage(String message)
  {
    messages.add(message);
  }
  





  public boolean removeMessage(int id)
  {
    if (isMessage(id)) {
      messages.remove(id - 1);
      return true;
    }
    return false;
  }
  





  public String getMessage(int id)
  {
    return (String)messages.get(id - 1);
  }
  




  public int getMessageCount()
  {
    return messages.size();
  }
  





  public boolean isMessage(int id)
  {
    if ((id <= messages.size()) && (id > 0)) {
      return true;
    }
    return false;
  }
  




  @SuppressWarnings("deprecation")
public void broadcastMessage(int id)
  {
    if (isMessage(id)) {
      String m = getMessage(id);
      if (m.startsWith("/"))
      {
        getServer().dispatchCommand(getServer().getConsoleSender(), m.substring(1));
      } else {
        for (Player p : Bukkit.getOnlinePlayers()) {
          if (p.hasPermission("uan.receive")) {
        	  if(isSpace())
        	  {
            p.sendMessage(" ");
            p.sendMessage(" ");
            p.sendMessage(" ");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', header));
            p.sendMessage(" ");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&r " + m));
            p.sendMessage(" ");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', footer));
            p.sendMessage(" ");
            p.playSound(p.getLocation(), Sound.valueOf(getConfig().getString("UltimateAnnounce.Sound")), 1.0F, 1.0F);
        	  }else{
                  p.sendMessage(ChatColor.translateAlternateColorCodes('&', header));
                  p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&r " + m));
                  p.sendMessage(ChatColor.translateAlternateColorCodes('&', footer));
                  p.playSound(p.getLocation(), Sound.valueOf(getConfig().getString("UltimateAnnounce.Sound")), 1.0F, 1.0F);
        	  }
          }
        }
      }
    }
  }
  
  public void load()
  {
	  reloadConfig();
	  prefix = getConfig().getString("UltimateAnnounce.Prefix", getPrefix());
	  header = getConfig().getString("UltimateAnnounce.Header", getHeader());
	  footer = getConfig().getString("UltimateAnnounce.Footer", getFooter());
	  space = getConfig().getBoolean("UltimateAnnounce.space-between-messages", isSpace());
	  sound = getConfig().getString("UltimateAnnounce.Sound", getSound());
	  messages = getConfig().getStringList("UltimateAnnounce.Messages");
	  interval = getConfig().getInt("UltimateAnnounce.Interval", getInterval());
	  active = getConfig().getBoolean("UltimateAnnounce.Enabled", isActive());
	  random = getConfig().getBoolean("UltimateAnnounce.Random", isRandom());
	  amessages = getConfig().getStringList("ActionAnnounce.Messages");
	  ainterval = getConfig().getInt("ActionAnnounce.Interval", getaInterval());
	  aduration = getConfig().getInt("ActionAnnounce.Duration", getaDuration());
	  arandom = getConfig().getBoolean("ActionAnnounce.Random", isRandom());
  }
  
  



  public void save()
  {
	  getConfig().set("UltimateAnnounce.Prefix", getPrefix());
	  getConfig().set("UltimateAnnounce.Header", getHeader());
	  getConfig().set("UltimateAnnounce.Footer", getFooter());
	  getConfig().set("UltimateAnnounce.space-between-messages", Boolean.valueOf(space));
	  getConfig().set("UltimateAnnounce.Sound", getSound());
	  getConfig().set("UltimateAnnounce.Messages", messages);
	  getConfig().set("UltimateAnnounce.Interval", Long.valueOf(interval));
	  getConfig().set("UltimateAnnounce.Enabled", Boolean.valueOf(active));
	  getConfig().set("UltimateAnnounce.Random", Boolean.valueOf(random));
	  getConfig().set("ActionAnnounce.Messages", amessages);
	  getConfig().set("ActionAnnounce.Interval", Long.valueOf(ainterval));
	  getConfig().set("ActionAnnounce.Duration", Long.valueOf(aduration));
	  getConfig().set("ActionAnnounce.Random", Boolean.valueOf(arandom));
	  saveConfig();
  }


  @SuppressWarnings("deprecation")
public void sayMessage(String m)
  {
    if (m.startsWith("/"))
    {
      getServer().dispatchCommand(getServer().getConsoleSender(), m.substring(1));
    } else {
      for (Player p : Bukkit.getOnlinePlayers()) {
        if (p.hasPermission("uan.receive")) {
          if (prefix.equals("")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
          } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&r " + m));
          }
        }
      }
    }
  }
  




  public boolean isActive()
  {
    return active;
  }
  




  public void setActive(boolean enabled)
  {
    if (enabled) {
      enableuan();
    }
  }
  




  public void setRandom(boolean random)
  {
    this.random = random;
  }
  

  public List<String> getaMessages()
  {
    return amessages;
  }
  


  public void setaMessages(List<String> messages)
  {
    this.amessages = messages;
  }
  

  public void setSpace(boolean space)
  {
	  this.space = space;
  }

  public void broadcastaRandomMessage()
  {
    Random rand = new Random();
    int id = rand.nextInt(amessages.size());
    broadcastaMessage(id + 1);
  }
  



  public void broadcastaNextMessage()
  {
    if (alastAnnounced >= amessages.size()) {
      alastAnnounced = 0;
    }
    broadcastaMessage(alastAnnounced + 1);
    alastAnnounced += 1;
  }
  


  public boolean isaRandom()
  {
    return arandom;
  }
  




  public int getaInterval()
  {
    return ainterval;
  }
  
  public int getaDuration()
  {
    return aduration;
  }

 public boolean isSpace()
 {
	 return space;
 }


  public void setaInterval(int interval)
  {
    this.ainterval = interval;
  }
  
  public void setaDuration(int duration)
  {
    this.aduration = duration;
  }



  public void addaMessage(String message)
  {
    amessages.add(message);
  }
  





  public boolean removeaMessage(int id)
  {
    if (isaMessage(id)) {
      amessages.remove(id - 1);
      return true;
    }
    return false;
  }
  





  public String getaMessage(int id)
  {
    return (String)amessages.get(id - 1);
  }
  




  public int getaMessageCount()
  {
    return amessages.size();
  }
  





  public boolean isaMessage(int id)
  {
    if ((id <= amessages.size()) && (id > 0)) {
      return true;
    }
    return false;
  }
  




  @SuppressWarnings("deprecation")
public void broadcastaMessage(int id)
  {
    if (isaMessage(id)) {
      final String m = getaMessage(id);
      if (m.startsWith("/"))
      {
        a.getServer().dispatchCommand(a.getServer().getConsoleSender(), m.substring(1));
      } else {
        for (final Player p : Bukkit.getOnlinePlayers()) {
          if (p.hasPermission("uan.receive")) {
    	    new BukkitRunnable(){
    	        private int num = getConfig().getInt("ActionAnnounce.Duration");
    	        @Override
    	        public void run() {
    	          if(num<=0){
    	              ActionAPI.sendAction(p, ChatColor.translateAlternateColorCodes('&', m));
    	            cancel();
    	            return;
    	          }
    	            ActionAPI.sendAction(p, ChatColor.translateAlternateColorCodes('&', m));
    	          num--;
    	        }
    	      }.runTaskTimer(this, 0L, 20L);
      }
    	 
          }
        }
      }
    }
  

  @SuppressWarnings("deprecation")
public void sayaMessage(final String m)
  {
    if (m.startsWith("/"))
    {
      a.getServer().dispatchCommand(a.getServer().getConsoleSender(), m.substring(1));
    } else {
      for (final Player p : Bukkit.getOnlinePlayers()) {
        if (p.hasPermission("uac.receive")) {
    	    new BukkitRunnable(){
    	        private int num = getConfig().getInt("ActionAnnounce.Duration");
    	        @Override
    	        public void run() {
    	          if(num<=0){
    	              ActionAPI.sendAction(p, ChatColor.translateAlternateColorCodes('&', m));
    	            cancel();
    	            return;
    	          }
    	            ActionAPI.sendAction(p, ChatColor.translateAlternateColorCodes('&', m));
    	          num--;
    	        }
    	      }.runTaskTimer(this, 0L, 20L);
        }
      }
    }
  }
  

  public void setaRandom(boolean random)
  {
    this.arandom = random;
  }

public String getVersion()
{
  return "1.3.4";
}
}
