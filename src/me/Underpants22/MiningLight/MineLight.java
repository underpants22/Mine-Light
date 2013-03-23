package me.Underpants22.MiningLight;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MineLight extends JavaPlugin implements Listener {
	
	@Override
	public void onEnable() {
		
		getServer().getPluginManager().registerEvents(this, this);
		
	}
	
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		
		Block block = event.getBlock();
		
		if(block.getType().toString().equals("STONE") || block.getType().toString().equals("IRON_ORE")
			||  block.getType().toString().equals("COAL_ORE") || block.getType().toString().equals("GOLD_ORE")
			|| block.getType().toString().equals("DIAMOND_ORE")) {
			
			Player player = event.getPlayer();
			if(!(player.hasPermission("MiningLight.Mine"))) {
				
				BlockState normalBlock = block.getState();
				block.setType(Material.AIR);
				
				if(block.getLightLevel() > 0) {
					
					block.setType(normalBlock.getType());
					block.breakNaturally();
					
				} else {
					
					player.sendMessage(ChatColor.RED + "You cannot break blocks, place a light source down!");
					event.setCancelled(true);
					block.setType(normalBlock.getType());
					
				}
				
			}
		}
	}		
}
