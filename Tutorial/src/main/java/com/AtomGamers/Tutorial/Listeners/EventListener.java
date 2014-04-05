package com.AtomGamers.Tutorial.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener{
	@EventHandler	
	public void PlayerChat(AsyncPlayerChatEvent e)
	{
		Player p = e.getPlayer(); 
		String message = e.getMessage();
		message = message.replace("&", "§");
		if(p.hasPermission("Tutorial.ColoredChat"))
		{
			e.setMessage(message);
		}		
		if(p.isOp())
		{
			if(message.startsWith("!") && message.length() > 1)
			{		
				message = message.substring(1);
				for(Player p2 : Bukkit.getOnlinePlayers()){
					if(p2.isOp()){
						p2.sendMessage(ChatColor.RED+"[OP Chat] "+ChatColor.YELLOW+p.getName()+": "+message);
					}
				}
				e.setCancelled(true);
			}
		}			
	}
	@EventHandler
	public void DropItem(PlayerDropItemEvent e)
	{
		ItemStack is = e.getItemDrop().getItemStack();
		if(is.getType() == Material.BONE){
			Location loc = e.getItemDrop().getLocation();
			loc.setY(loc.getY() + 3.0);
			loc.getWorld().spawnEntity(loc, EntityType.SKELETON);
		}
	}
}
