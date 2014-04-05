package com.AtomGamers.Tutorial;

import java.io.File;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.AtomGamers.Tutorial.Commands.TutoCommand;
import com.AtomGamers.Tutorial.Listeners.EventListener;

public class Tutorial extends JavaPlugin{
	
	private static String mensagens[] = {ChatColor.YELLOW+"Divulgue para seus familiares.",
		ChatColor.YELLOW+"Precisa de ajuda? Digite: /help",
		ChatColor.YELLOW+"Visite nosso fórum!"};
	private static Plugin pl = null;
	private static int schedID = -1;
	private File myConfig = new File(this.getDataFolder()+"/myConfig.yml");
	private Yaml myConfigYaml = new Yaml();
	
	@Override
	public void onEnable()
	{
		pl = this;
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			
			public void run() {
				Bukkit.broadcastMessage(ChatColor.RED+"Passou-se 2 minutos.");
				
			}
		}, 2*60*20L);			

		startSched();
		this.myConfigYaml.load(this.myConfig);
		this.myConfigYaml.addDefault("Teste.0", "O Plugin foi habilitado.");
		this.myConfigYaml.options().copyDefaults(true);
		this.myConfigYaml.save(this.myConfig);
		
		this.getConfig().addDefault("Mensagem.Plugin.Habilitado", "Plugin Enabled.");
		this.getConfig().addDefault("Mensagem.Plugin.Desabilitado", "Plugin Disable.");
		this.getConfig().options().copyDefaults(true);		
		this.saveConfig();
		
		this.getCommand("tuto").setExecutor(new TutoCommand());
		
		Bukkit.getServer().getPluginManager().registerEvents(new EventListener(), this);
		this.getLogger().info(this.getConfig().getString("Mensagem.Plugin.Habilitado"));
		this.getLogger().info(this.myConfigYaml.getString("Teste.0"));		
	}
	
	@Override
	public void onDisable()
	{
		pl = null;
		HandlerList.unregisterAll(this);
		this.getLogger().info(this.getConfig().getString("Mensagem.Plugin.Desabilitado"));
	}
	public static class Anuncio implements Runnable{

		public void run() {
			Random rnd = new Random();
			int i = rnd.nextInt(mensagens.length);
			Bukkit.broadcastMessage(mensagens[i]);			
		}
	}
	public static boolean stopSched(){
		if(schedID != -1){
			Bukkit.getScheduler().cancelTask(schedID);
			schedID = -1;
			return true;
		}
		return false;
	}
	public static boolean startSched(){
		if(schedID == -1 && pl != null){
			schedID = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Anuncio(), 5*60*20L, 5*60*20L);
			return true;
		}
		return false;
	}
}
