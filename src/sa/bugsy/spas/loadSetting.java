package sa.bugsy.spas;

public class loadSetting {
	static boolean enableSPAS;
	
	public static void loadMain(){
		String propertiesFile = spas.mDir+"MainConfig.properties";
		PluginProperties properties = new PluginProperties(propertiesFile);
		properties.load();
		
		enableSPAS = properties.getBoolean("enableSPAS", true);
		properties.save("SimPlaAuthSyst MAIN Configuration");
	}
}
