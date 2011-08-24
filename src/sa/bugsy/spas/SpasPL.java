package sa.bugsy.spas;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class SpasPL extends PlayerListener {
	public static spas plugin;
	
	public SpasPL(spas instance){
		plugin = instance;
	}
	public void onPlayerJoin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		player.sendMessage("§4Type \"/password <your_pass>\" to register or join.");
	}
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e){
		Player player = e.getPlayer();
		String[] str = e.getMessage().split(" ", 2);
		String start = "SimPlaAuthSyst MAIN Configuration";
		String start2 = "Do not Touch These";
		
		if(str[0].equalsIgnoreCase("/password")){
			String mcFile = spas.mDir+"MainConfig.properties";
			PluginProperties prop = new PluginProperties(mcFile);
			prop.load();
			prop.getString(player.getName(), str[1]);
			String pw = prop.getProperty(player.getName());
			prop.save(start);
			if(str[1].equals(pw)){
				String tpFile = spas.mDir+"donTouch.properties";
				PluginProperties prop2 = new PluginProperties(tpFile);
				prop2.load();
				prop2.put(player.getName(), "true");
				prop2.save(start2);
				e.setCancelled(false);
				player.sendMessage("§4Password set. Horray!");
			}else{
				e.setCancelled(true);
				player.sendMessage("§4Incorrect password");
				player.sendMessage("§4Type \"/password <your_password>\" again");
			}
			
		}else{
			if(str[0].charAt(0)=='/'){
				String tpFile = spas.mDir+"donTouch.properties";
				PluginProperties prop2 = new PluginProperties(tpFile);
				prop2.load();
				if(prop2.getProperty(player.getName()).equals("true")){
					e.setCancelled(false);
				}else{
					e.setCancelled(true);
					player.sendMessage("§4You are not Allowed to use commands");
					player.sendMessage("§4Type \"/password <your_password>\" to register or join");
				}
			}else{
				e.setCancelled(false);
			}
			
		}
	}
	public void onPlayerQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		String tpFile = spas.mDir+"donTouch.properties";
		PluginProperties prop2 = new PluginProperties(tpFile);
		prop2.load();
		prop2.setProperty(player.getName().toString(), "false");
		prop2.save("Do not Touch These");
	}

}
