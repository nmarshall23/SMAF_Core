package smaf;


import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.xeoh.plugins.base.impl.PluginManagerFactory;
import net.xeoh.plugins.base.util.PluginManagerUtil;
import net.xeoh.plugins.base.*;
import net.xeoh.plugins.base.PluginInformation.Information;
import net.xeoh.plugins.base.util.uri.ClassURI;
import org.lwjgl.input.Keyboard;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SimplePluginManager {

	public PluginManager pm;
	public PluginManagerUtil pmUtil;
	
	public final static Logger logger = Logger.getLogger("SMAF");
    
    
	
	public SimplePluginManager() {
		InitializeLogger();
		LogMessage("...Initializing AddonManager...");
		pm = PluginManagerFactory.createPluginManager();
		
		
		
		pm.addPluginsFrom(ClassURI.CLASSPATH);
		pm.addPluginsFrom(new File(Minecraft.getMinecraftDir(), "plugins").toURI());
		
		pmUtil = new PluginManagerUtil(pm);
		LogPluginsLoaded();
		
		LogMessage("Done Loading AddOns...");
	}
	
	private void InitializeLogger(){
		try
        {
			File logFile = new File(Minecraft.getMinecraftDir(), "SMAFLog.txt");
			
            if ((logFile.exists() || logFile.createNewFile()) && logFile.canWrite())
            {
            	FileHandler logFileHandler = new FileHandler(logFile.getPath());
            	
            	logFileHandler.setFormatter(new SimpleFormatter());
            	logger.addHandler(logFileHandler);
            	logger.setLevel(Level.FINE);
            }
        }
        catch (Throwable var1)
        {
            throw new RuntimeException(var1);
        }
	}
	
    public void ActionDoKeybindingCallbacks() {
    	//Keyboard
    	ArrayList<SMAF_KeyBindingAddOn> addons = new ArrayList<SMAF_KeyBindingAddOn>( pmUtil.getPlugins(SMAF_KeyBindingAddOn.class));
    	for(SMAF_KeyBindingAddOn addon : addons){
    		addon.DoKeyBindingAction();
    	}
    }
	
	public static void LogMessage(String message) {
        System.out.println(message);

        if (MinecraftServer.getServer() != null) {
            MinecraftServer.getServer().getLogAgent().logInfo(message);
        }
        
        logger.fine(message);
    }

/***
 * ActionDoneUsingItem()
 *  Decoupling BTW from minecraft/client/Minecraft.java
 *  Currently only the BTW Addon implements this.
 */
    public boolean ActionDoneUsingItem() {
        SMAF_DoneUsingItem addon = pm.getPlugin(SMAF_DoneUsingItem.class);
        if(addon != null) {
                return addon.DoneUsingItem();
        }
        return true;
    }

	
	/**
	 * 
	 * 
	 */
	private void LogPluginsLoaded(){
		
		//PluginInformation pluginInfo = pm.getPlugin(PluginInformation.class);
		
		ArrayList<SMAF_GenericAddOn> addons = new ArrayList<SMAF_GenericAddOn>( pmUtil.getPlugins(SMAF_GenericAddOn.class));
		for (Iterator<SMAF_GenericAddOn> iterator = addons.iterator(); iterator.hasNext();) {
			SMAF_GenericAddOn addon = (SMAF_GenericAddOn) iterator.next();
						
			String PluginName = addon.GetAddOnName();
			
			LogMessage(" Loading Addon: " + PluginName);
			//Collection PluginVersion;
			//pluginInfo.getInformation(Information.AUTHORS,iSimplePlugin);
		}
	}
	

}
