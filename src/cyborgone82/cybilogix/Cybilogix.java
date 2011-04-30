package cyborgone82.cybilogix;

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

public class Cybilogix extends JavaPlugin {
    private final CybilogixPlayerListener playerListener = new CybilogixPlayerListener(this);
    //private final CybilogixBlockListener blockListener = new CybilogixBlockListener(this);
    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
    
    private long sleepingTime = 0;
    
    
    
    
    
    
    
    public boolean isDebugging(final Player player) {
        if (debugees.containsKey(player)) {
            return debugees.get(player);
        } else {
            return false;
        }
    }

    public void setDebugging(final Player player, final boolean value) {
        debugees.put(player, value);
    }


    
    public void onDisable() {
        // NOTE: All registered events are automatically unregistered when a plugin is disabled
        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        // System.out.println("Goodbye world!");
    }

    
    public void onEnable() {
        // Register events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_BED_ENTER, (Listener) playerListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_BED_LEAVE, (Listener) playerListener, Priority.Normal, this);
        
        // Register commands
        //getCommand("pos").setExecutor(new SamplePosCommand(this));
        //getCommand("debug").setExecutor(new SampleDebugCommand(this));
        
        // Status-Message
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println( ">>>" + pdfFile.getName() + " Version: " + pdfFile.getVersion() + " is enabled!" );
    }



	public void setSleepingTime(long sleepingTime) {
		this.sleepingTime = sleepingTime;
	}


	public long getSleepingTime() {
		return sleepingTime;
	}

}
