package sa.bugsy.spas;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

public class SpasBL extends BlockListener{
	public static spas plugin;
	
	public SpasBL(spas instance){
		plugin = instance;
	}
	public void onBlockBreak(BlockBreakEvent event){
		Player player = event.getPlayer();
		String tpFile = spas.mDir+"donTouch.properties";
		PluginProperties properties = new PluginProperties(tpFile);
		properties.load();
		boolean tof = Boolean.parseBoolean(properties.getProperty(player.getName().toString()));
		if(!tof){
			event.setCancelled(true);
			player.sendMessage("§4You cant break block until your registered or joined.");
			player.sendMessage("§4Type \"/password <your_password>\" to join");
		}
	}
	public void onBlockPlace(BlockPlaceEvent event){
		Player player = event.getPlayer();
		String tpFile = spas.mDir+"donTouch.properties";
		PluginProperties properties = new PluginProperties(tpFile);
		properties.load();
		boolean tof = Boolean.parseBoolean(properties.getProperty(player.getName().toString()));
		if(!tof){
			event.setCancelled(true);
			player.sendMessage("§4You cant place block until your registered or joined.");
			player.sendMessage("§4Type \"/password <your_password>\" to join");
		}
		
	}
}
