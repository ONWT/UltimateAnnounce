package me.Maro.UltimateAnnounce;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Player;





public class Commands
  implements CommandExecutor, Conversable
{
  private UltimateAnnounce a;
  private String errorMessage = "You are not allowed to use that command.";
  




  public Commands(UltimateAnnounce announce)
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
      if (args[0].equalsIgnoreCase("enable"))
        return onEnableCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("disable"))
        return onDisableCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("prefix"))
        return onPrefixCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("header"))
        return onHeaderCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("footer"))
        return onFooterCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("sound"))
        return onSoundCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("broadcast"))
        return onBroadcastCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("interval"))
        return onIntervalCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("reload"))
        return onReloadCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("say"))
        return onSayCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("random"))
        return onRandomCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("space"))
          return onSpaceCommand(sender, cmd, label, args);
      if (args[0].equalsIgnoreCase("version")) {
        return onVersionCommand(sender, cmd, label, args);
      }
    }
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6] &7Use &a/uan help&7, &a/uac help &7for a list of commands."));
    return true;
  }
  







  @SuppressWarnings({ "unchecked", "rawtypes" })
public boolean onHelpCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    List<String> commands = new ArrayList();
    commands.add(ChatColor.translateAlternateColorCodes('&', "&6]&7========&6[ &cUAnnounce &ev" + a.getVersion() + " &aHelp&6]&7========&6["));
    commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uac help&7 - &aDisplays help for Action commands"));
    commands.add(ChatColor.translateAlternateColorCodes('&', ""));
    if (sender.hasPermission("uan.add")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uan add &6<message>&7 - &aAdd a message to the UAannounce list."));
    }
    if (sender.hasPermission("uan.remove")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uan remove &6<id>&7 - &aRemove a message from the UAannounce list."));
    }
    if (sender.hasPermission("uan.list")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uan list&7 - &aList the current UAannounce messages."));
    }
    if (sender.hasPermission("uan.enable")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uan enable&7 - &aEnable Messages."));
    }
    if (sender.hasPermission("uan.disable")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uan disable&7 - &aDisable Messages."));
    }
    if (sender.hasPermission("uan.prefix")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uan prefix &6<prefix:off>&7 - &aChange the Prefix."));
    }
    if (sender.hasPermission("uan.header")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uan header &6<header:off>&7 - &aChange the Header."));
    }
    if (sender.hasPermission("uan.footer")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uan footer &6<footer:off>&7 - &aChange the Footer."));
    }
    if (sender.hasPermission("uan.space")) {
        commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uan space&7 - &aToggles space between messages."));
      }
    if (sender.hasPermission("uan.sound")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uan sound &6<sound:off>&7 - &aChange the Sound."));
    }
    if (sender.hasPermission("uan.broadcast")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uan broadcast &6<id>&7 - &aManually broadcast an Message."));
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uan say &6<message>&7 - &aBroadcast message with Prefix."));
    }
    if (sender.hasPermission("uan.interval")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uan interval &6<seconds>&7 - &aChange the interval time between messages."));
    }
    if (sender.hasPermission("uan.reload")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uan reload&7 - &aReload Configuration."));
    }
    if (sender.hasPermission("uan.random")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uan random&7 - &aToggle random mode on and off."));
    }
    if (sender.hasPermission("uan.version")) {
      commands.add(ChatColor.translateAlternateColorCodes('&', "&e/uan version&7 - &aDisplay plugin version information."));
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
    if (!sender.hasPermission("uan.add")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length > 1) {
      String message = buildMessage(args);
      a.addMessage(message);
      a.save();
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6] &eMessage " + ChatColor.translateAlternateColorCodes('&', message) + " &eAdded!"));
      return true;
    }
    sender.sendMessage(ChatColor.RED + "Error: Please enter a message.");
    return true;
  }
  








  public boolean onRemoveCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uan.remove")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length == 2) {
      if (isInteger(args[1])) {
        int remove = Integer.parseInt(args[1]);
        if (a.removeMessage(remove)) {
          sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e Message Removed!"));
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
  








  private boolean onEnableCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uan.enable")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length > 1) {
      sender.sendMessage(ChatColor.RED + "Error: Too many arguments.");
      return true;
    }
    if (a.isActive()) {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6] &eUAannounce Already &a&aEnabled&e!"));
      return true;
    }
    a.enableuan();
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6] &eUAannounce &a&aEnabled&e!"));
    return true;
  }
  









  private boolean onDisableCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uan.disable")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length > 1) {
      sender.sendMessage(ChatColor.RED + "Error: Too many arguments.");
      return true;
    }
    if (!a.isActive()) {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6] &eUAannounce Already &c&cDisabled&e!"));
      return true;
    }
    a.disableuan();
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6] &eUAannounce &c&cDisabled&e!"));
    return true;
  }
  









  private boolean onListCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uan.list")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length > 1) {
      sender.sendMessage(ChatColor.RED + "Error: Too many arguments.");
      return true;
    }
    if (a.getMessageCount() > 0) {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6] &eUAannounce Messages:"));
      for (int i = 1; i <= a.getMessageCount(); i++) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', i + ": " + ChatColor.translateAlternateColorCodes('&', a.getMessage(i))));
      }
      return true;
    }
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAnnounce&6] You don't have any messages yet."));
    return true;
  }
  









  private boolean onPrefixCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uan.prefix")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length > 1) {
      if (args[1].equalsIgnoreCase("off")) {
    	  a.setPrefix("");
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e &eUAannounce Prefix &cDisabled!"));
      } else {
    	  a.setPrefix(buildMessage(args));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e &eUAannounce Prefix Updated!"));
      }
      a.save();
      return true;
    }
    if (a.getPrefix().equals("")) {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e UAannounce Prefix Currently &cDisabled!"));
    } else {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e UAannounce Prefix &7- " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', a.getPrefix())));
    }
    return true;
  }
  









  private boolean onHeaderCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uan.header")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length > 1) {
      if (args[1].equalsIgnoreCase("off")) {
    	  a.setHeader("");
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e UAannounce Header &cDisabled!"));
      } else {
    	  a.setHeader(buildMessage(args));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e UAannounce Header Updated!"));
      }
      a.save();
      return true;
    }
    if (a.getHeader().equals("")) {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e UAannounce Header Currently &cDisabled!"));
    } else {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e UAannounce Header &7- " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', a.getHeader())));
    }
    return true;
  }
  







  private boolean onFooterCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uan.footer")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length > 1) {
      if (args[1].equalsIgnoreCase("off")) {
    	  a.setFooter("");
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e UAannounce Footer &cDisabled!"));
      } else {
    	  a.setFooter(buildMessage(args));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e UAannounce Footer Updated!"));
      }
      a.save();
      return true;
    }
    if (a.getFooter().equals("")) {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e UAannounce Footer Currently &cDisabled!"));
    } else {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e UAannounce Footer &7- " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', a.getHeader())));
    }
    return true;
  }
  








  private boolean onSoundCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uan.sound")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length > 1) {
      if (args[1].equalsIgnoreCase("off")) {
    	  a.setSound("");
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e UAannounce Sound &cDisabled!"));
      } else {
    	  a.setSound(buildMessage(args));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e UAannounce Sound Updated!"));
      }
      a.save();
      return true;
    }
    if (a.getSound().equals("")) {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e UAannounce Sound Currently &cDisabled!"));
    } else {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e UAannounce Sound &7- " + ChatColor.GREEN + ChatColor.translateAlternateColorCodes('&', a.getSound())));
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e You can find all Minecraft Sounds here &7- &ahttp://goo.gl/XHpVpY"));
    }
    return true;
  }
  









  private boolean onBroadcastCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uan.broadcast")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length == 2) {
      if (isInteger(args[1])) {
        int id = Integer.parseInt(args[1]);
        if (a.isMessage(id)) {
        	a.broadcastMessage(id);
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
    if (!sender.hasPermission("uan.interval")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length == 2) {
      if (isInteger(args[1])) {
        int interval = Integer.parseInt(args[1]);
        if (interval >= 1) {
        	a.setInterval(interval);
        	a.save();
        	a.restartuan();
          sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e UAannounce Interval Updated!"));
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
    int seconds = a.getInterval();
    if (seconds == 1) {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e UAannounce Interval&7 -&a 1 Second"));
      return true;
    }
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e UAannounce Interval&7 - &a" + seconds + "&a Seconds"));
    return true;
  }
  









  private boolean onReloadCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uan.reload")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length > 1) {
      sender.sendMessage(ChatColor.RED + "Error: Too many arguments.");
      return true;
    }
    a.load();
    a.restartuan();
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&a UAannounce Configuration Reloaded!"));
    return true;
  }
  








  private boolean onSayCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uan.broadcast")) {
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
    if (!sender.hasPermission("uan.random")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length > 1) {
      sender.sendMessage(ChatColor.RED + "Error: Too many arguments.");
      return true;
    }
    if (a.isRandom()) {
    	a.setRandom(false);
    	a.save();
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e UAannounce Random Mode &cDisabled!"));
      return true;
    }
    a.setRandom(true);
    a.save();
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e UAannounce Random Mode &aEnabled!"));
    return true;
  }
  


  private boolean onSpaceCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uan.space")) {
      sender.sendMessage(ChatColor.RED + errorMessage);
      return true;
    }
    if (args.length > 1) {
      sender.sendMessage(ChatColor.RED + "Error: Too many arguments.");
      return true;
    }
    if (a.isSpace()) {
    	a.setSpace(false);
    	a.save();
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e Space between messages &cDisabled!"));
      return true;
    }
	a.setSpace(true);
	a.save();
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&cUAannounce&6]&e Space between messages &aEnabled!"));
    return true;
  }






  private boolean onVersionCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!sender.hasPermission("uan.version")) {
      sender.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + "You do not have access to that command.");
      return true;
    }
    if (args.length > 1) {
      sender.sendMessage(ChatColor.RED + "Error: Too many arguments.");
      return true;
    }
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6]&7========&6[ &cUltimateAnnounce &6]&7========&6["));
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eVersion &7- &av" + a.getVersion()));
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&ePlugin by &cMaro"));
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
