package com.github.xaxys.consolesay;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor {
	
	Main PLUGIN = null;
	public static String RED_4 = "§4";
	public static String LAN_DAN_9 = "§9";
	public static String BOLD_L = "§l";
	public String Format;
	
	@Override
	public void onDisable() {
		saveConfig();
		super.onDisable();
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		saveDefaultConfig();
		loadConfig();
		getCommand("c").setExecutor(this);
		this.PLUGIN = this;
	}
	
	@Override
	public void onLoad() {
		super.onLoad();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player)sender;
			player.sendMessage(RED_4 + BOLD_L + "只能在控制台使用该命令！");
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage(LAN_DAN_9 + BOLD_L + "用法: c [word] 来说话");
			reloadConfig();
			loadConfig();
			sender.sendMessage(LAN_DAN_9 + BOLD_L + "配置重载成功");
		}
		else {
			StringBuilder sb = new StringBuilder(args[0]);
			for (int i = 1; i < args.length; i++) {
				sb.append(" " + args[i]);
			}
			String message = Format.replace("%WORD%", sb.toString());
			Bukkit.broadcastMessage(message);
		}
		return true;
	}
	
	public void loadConfig() {
		Format = getConfig().getString("Format").replace("&", "§");
	}

}
