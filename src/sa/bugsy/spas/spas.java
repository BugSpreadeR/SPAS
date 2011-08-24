package sa.bugsy.spas;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class spas extends JavaPlugin {
	Logger log = Logger.getLogger("Minecraft");
	private String saPrefix = "[SA]";
	static String mDir = "plugins/SPAS/";
	private final SpasPL playerListener = new SpasPL(this);
	private final SpasBL blockListener = new SpasBL(this);
	
	public void onDisable() {	
		log.info(saPrefix+" SPAS plugin has been Disabled!");
	}
	public void onEnable() {
		new File(mDir).mkdir();
		loadSetting.loadMain();
		tmprrys.loadTemp();
		String propertiesFile = spas.mDir+"MainConfig.properties";
		PluginProperties properties = new PluginProperties(propertiesFile);
		properties.load();
		if(properties.getBoolean("enableSPAS", true)){
// in here...
		PluginManager pm = getServer().getPluginManager();		
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_COMMAND_PREPROCESS, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_PLACE, blockListener, Event.Priority.Normal, this);
		log.info(saPrefix+" SPAS plugin has been Enabled!");
// in here...
		}else{
			log.info("[SA] You disabled SPAS plugin.");
			log.info("[SA] To enable SPAS, please change \'enableSPAS\' to true");
		}
	}
}