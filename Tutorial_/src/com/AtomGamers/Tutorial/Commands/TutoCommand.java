package com.AtomGamers.Tutorial.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.AtomGamers.Tutorial.Tutorial;

public class TutoCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		String command = cmd.getName();
		if(command.equalsIgnoreCase("tuto"))
		{
			try{
				if(!sender.hasPermission("Tutorial.cmd."+command+"."+args[0]))
				{
					sender.sendMessage(ChatColor.RED+"Voce nao tem permissao para usar: "+command+" "+args[0]);
					return true;
				}
				if(args[0].equalsIgnoreCase("tphere"))
				{
					if(!(sender instanceof Player)){
						sender.sendMessage(ChatColor.RED+"Voce precisa ser um jogador.");
						return true;
					}
					Player p = (Player) sender;
					Player p2 = Bukkit.getPlayer(args[1]);
					if(p2 == null || !p2.isOnline()){
						sender.sendMessage(ChatColor.RED+"O Jogador "+args[1]+" nao esta online.");
						return true;
					}
					p2.teleport(p);
					p2.sendMessage("Voce foi teleportado para "+p.getName());
					sender.sendMessage("Voce teleportou "+p2.getName()+" para voce.");						
				}else if(args[0].equalsIgnoreCase("pararanuncio")){
					if(Tutorial.stopSched())sender.sendMessage(ChatColor.GREEN+"Anuncio parado.");
					else sender.sendMessage(ChatColor.GREEN+"Voce nao pode parar um anuncio que nao foi iniciado.");
				}else if(args[0].equalsIgnoreCase("iniciaranuncio")){
					if(Tutorial.startSched())sender.sendMessage(ChatColor.GREEN+"Anuncio iniciado.");
					else sender.sendMessage(ChatColor.GREEN+"Voce nao pode iniciar mais de 1 anuncio.");
				}
				else{
					args[-1]=null;
				}
			}catch(Exception e)
			{
				this.showHelp(sender);
			}
			return true;
		}
		return false;
	}
	
	private void showHelp(CommandSender sender) {
		if(sender.hasPermission("Tutorial.viewHelp")){
			sender.sendMessage(ChatColor.RED+"[Tutorial] "+ChatColor.GREEN+"/tuto tphere [player]");
			sender.sendMessage(ChatColor.RED+"[Tutorial] "+ChatColor.GREEN+"/tuto tpto [player] [player]");			
		}else{
			sender.sendMessage(ChatColor.RED+"Voce nao tem permissao para isto.");
		}
	}
}
