package sa.bugsy.spas;

public class tmprrys {
	
	public static void loadTemp(){
		String tpFile = spas.mDir+"donTouch.properties";
		PluginProperties properties = new PluginProperties(tpFile);
		properties.load();
		properties.getString("u-name", "password");
		properties.save("Do not Touch These");
	}

}
