package me.Maro.ActionAnnounce;

import java.util.ArrayList;
import java.util.List;

import me.Maro.UltimateAnnounce.UltimateAnnounce;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Player;





public class aCommands
  implements CommandExecutor, Conversable
{
  private UltimateAnnounce a;
  private String errorMessage = "You are not allowed to use that command.";
  




  public aCommands(UltimateAnnounce announce)
  {
    this.a = announce;
  }
  









  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if ((sender instanceof Player)) {
      @SuppressWarnings("unused")
	Player localPlayer = (Player)sender;
    }
    if (args.length > 0) {
      if (args[0].equalsIgnoreCase("help"))
        return onHelpCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("add"))
        return onAddCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("remove"))
        return onRemoveCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("list"))
        return onListCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("broadcast"))
        return onBroadcastCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("interval"))
        return onIntervalCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("duration"))
          return onDurationCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("say"))
        return onSayCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("random"))
        return onRandomCommand(sender, cmd, label, args);
      }
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAction&6] &7Use &a/uan help&7, &a/uac help &7for a list of commands."));
    return true;
  }
  







  @SuppressWarnings({ "unchecked", "rawtypes" })
public boolean onHelpCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    List<String> commands = new ArrayList();
    commands.add(ChatColor.translateAlternateColorCodes('&', "&6]&7========&6[ &cUAction &ev" + a.getVersion() + " &aHelp&6]&7========&6["));
    commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uan help&7 - &aDisplays help for UAnnounce commands"));
    commands.add(ChatColor.translateAlternateColorCodes('&', ""));
    if (sender.hasPermission("uac.add")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uac add &6<message>&7 - &aAdd a message to the UAction list."));
    }
    if (sender.hasPermission("uac.remove")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uac remove &6<id>&7 - &aRemove a message from the UAction list."));
    }
    if (sender.hasPermission("uac.list")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uac list&7 - &aList the current UAction messages."));
    }
    if (sender.hasPermission("uac.disable")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uac disable&7 - &aDisable UAction messages."));
    }
    if (sender.hasPermission("uac.broadcast")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uac broadcast &6<id>&7 - &aManually broadcast an UAction message."));
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uac say &6<message>&7 - &aBroadcast message with UAction prefix."));
    }
    if (sender.hasPermission("uac.interval")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uac interval &6<seconds>&7 - &aChange the interval time between UAction messages."));
    }
    if (sender.hasPermission("uac.duration")) {
        commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uac duration &6<seconds>&7 - &aChange how many seconds the message will be displayed"));
      }
    if (sender.hasPermission("uac.random")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uac random&7 - &aToggle random mode on and off."));
    }
    if (commands.size() > 0) {
      for (String s : commands) {
        sender.sendMessage(s);
      }
    } else {
      sender.sendMessage(ChatColor.RED + errorMessage);
    }
    return true;
  }
  







  public boolean onAddCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uac.add")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length > 1) {
      String message = buildMessage(args);
      a.addaMessage(message);
      a.save();
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAction&6] &eAction Message &r" + ChatColor.translateAlternateColorCodes('&', message) + " &eAdded!"));
      return true;
    }
    sender.sendMessage(ChatColor.RED + "Error: Please enter a message.");
    return true;
  }
  








  public boolean onRemoveCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uac.remove")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length == 2) {
      if (isInteger(args[1])) {
        int remove = Integer.parseInt(args[1]);
        if (a.removeaMessage(remove)) {
          sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAction&6]&e Message Removed!"));
          a.save();
          return true;
        }
        sender.sendMessage(ChatColor.RED + "Error: No message matching this ID.");
        return true;
      }
      
      sender.sendMessage(ChatColor.RED + "Error: Argument is not a number.");
      return true;
    }
    if (args.length > 2) {
      sender.sendMessage(ChatColor.RED + "Error: Too many arguments.");
      return true; }
    if (args.length == 1) {
      sender.sendMessage(ChatColor.RED + "Error: Enter message ID.");
      return true;
    }
    return false;
  }
  
  private boolean onListCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uac.list")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length > 1) {
      sender.sendMessage(ChatColor.RED + "Error: Too many arguments.");
      return true;
    }
    if (a.getaMessageCount() > 0) {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAction&6] &eUAction Messages:"));
      for (int i = 1; i <= a.getaMessageCount(); i++) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', i + ": " + ChatColor.translateAlternateColorCodes('&', a.getaMessage(i))));
      }
      return true;
    }
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAction&6] You don't have any messages yet."));
    return true;
  }
  







  private boolean onBroadcastCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uac.broadcast")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length == 2) {
      if (isInteger(args[1])) {
        int id = Integer.parseInt(args[1]);
        if (a.isaMessage(id)) {
        	a.broadcastaMessage(id);
          return true;
        }
        sender.sendMessage(ChatColor.RED + "Error: No message matching this ID.");
        return true;
      }
    }
    else if (args.length > 2) {
      sender.sendMessage(ChatColor.RED + "Error: Too many arguments.");
      return true;
    }
    return false;
  }
  








  private boolean onIntervalCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uac.interval")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length == 2) {
      if (isInteger(args[1])) {
        int interval = Integer.parseInt(args[1]);
        if (interval >= 1) {
        	a.setaInterval(interval);
        	a.save();
        	a.restartuan();
          sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAction&6]&e UAction Interval Updated!"));
          return true;
        }
        sender.sendMessage(ChatColor.RED + "Error: Interval must be 1 or greater.");
        return true;
      }
      
      sender.sendMessage(ChatColor.RED + "Error: Argument is not a valid number.");
      return true;
    }
    if (args.length > 2) {
      sender.sendMessage(ChatColor.RED + "Error: Too many arguments.");
      return true;
    }
    int seconds = a.getaInterval();
    if (seconds == 1) {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAction&6]&e UltimateAnnounce Interval&7 -&a 1 Second"));
      return true;
    }
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAction&6] &eUAction Interval&7 - &a" + seconds + "&a Seconds"));
    return true;
  }
  
  private boolean onDurationCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uac.duration")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length == 2) {
      if (isInteger(args[1])) {
        int duration = Integer.parseInt(args[1]);
        if (duration >= 1) {
        	a.setaDuration(duration);
        	a.save();
        	a.restartuan();
          sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAction&6]&e UAction Duration Updated!"));
          return true;
        }
        sender.sendMessage(ChatColor.RED + "Error: Duration must be 1 or greater.");
        return true;
      }
      
      sender.sendMessage(ChatColor.RED + "Error: Argument is not a valid number.");
      return true;
    }
    if (args.length > 2) {
      sender.sendMessage(ChatColor.RED + "Error: Too many arguments.");
      return true;
    }
    int seconds = a.getaDuration();
    if (seconds == 1) {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAction&6]&e UAction Duration&7 -&a 1 Second"));
      return true;
    }
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAction&6] &eUAction Duration&7 - &a" + seconds + "&a Seconds"));
    return true;
  }




  private boolean onSayCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uac.broadcast")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length > 1) {
    	a.sayMessage(buildMessage(args));
      return true;
    }
    sender.sendMessage(ChatColor.RED + "Error: Please enter a message.");
    return true;
  }
  









  private boolean onRandomCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uac.random")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length > 1) {
      sender.sendMessage(ChatColor.RED + "Error: Too many arguments.");
      return true;
    }
    if (a.isaRandom()) {
    	a.setaRandom(false);
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAN&6]&e UActionAnnounce Random Mode &cDisabled!"));
      a.save();
      return true;
    }
    a.setaRandom(true);
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAN&6]&e UAction Random Mode &aEnabled!"));
    a.save();
    return true;
  }
  




  private boolean isInteger(String s)
  {
    try
    {
      Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }
  





  private String buildMessage(String[] args)
  {
    String message = "";
    for (int i = 1; i < args.length; i++) {
      if (!message.equals("")) {
        message = message + " ";
      }
      message = message + args[i];
    }
    return message;
  }
  







  public void abandonConversation(Conversation arg0) {}
  







  public void abandonConversation(Conversation arg0, ConversationAbandonedEvent arg1) {}
  







  public void acceptConversationInput(String arg0) {}
  






  public boolean beginConversation(Conversation arg0)
  {
    return false;
  }
  




  public boolean isConversing()
  {
    return false;
  }
  
  public void sendRawMessage(String arg0) {}
}
