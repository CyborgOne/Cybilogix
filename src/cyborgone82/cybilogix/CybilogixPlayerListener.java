package cyborgone82.cybilogix;

import org.bukkit.World;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Handle events for all Player related events
 * @author cyborgone82
 */

public class CybilogixPlayerListener extends PlayerListener {
    private final Cybilogix plugin;

    public Cybilogix getPlugin() {
		return plugin;
	}

	public CybilogixPlayerListener(Cybilogix instance) {
        plugin = instance;
    }

	
	
	/**
	 * onPlayerBedEnter(PlayerBedEnterEvent event)
	 * 
	 * - Merken wann Spieler ins Bett gegangen ist
	 * - Meldung ausgeben (Chat als User)
	 * 
	 */
	public void onPlayerBedEnter(PlayerBedEnterEvent event) {
		World w = event.getPlayer().getWorld();
		long t = w.getTime();
		
		super.onPlayerBedEnter(event);
		
		this.plugin.setSleepingTime(t);
		
		event.getPlayer().chat( "@Bed!  ZZZZZZZZZzzzzzzzzzzz" );
	}
	

	
	
	/**
	 * onPlayerBedLeave(PlayerBedLeaveEvent event)
	 * 
	 * - Merken wann Spieler aufgestanden ist
	 * - Energie entsprechend schlafdauer gutschreiben ( time / 250 )
	 * - Meldung ausgeben (Chat als User)
	 */	
	public void onPlayerBedLeave(PlayerBedLeaveEvent event) {
		World w = event.getPlayer().getWorld();
		
		// End-Zeit des Schlafs
		long t  = w.getTime()>18000?w.getTime():24000;
		
		// Start-Zeit des Schlafs
		long sleepingTime = this.plugin.getSleepingTime(); 
		
		// Gesundheit des Spielers vorher
		int playerHealth = event.getPlayer().getHealth();
		
		// durch Schlaf gewonnene Energie
		int addit = ( Integer.parseInt(""+t) - Integer.parseInt(""+sleepingTime) ) / 600 ;
		
		// neuer Gesundheits-Wert des Spielers
		int newHealth = playerHealth+addit;

		if (newHealth <= 0 ){
			newHealth = 1;
		} else if (newHealth > 20 ){
			newHealth = 20;
		}
		addit = (newHealth-playerHealth);
		
		// Standard-Logik
		super.onPlayerBedLeave(event);
		
		
		

		// Spieler heilen 
		event.getPlayer().setHealth(newHealth);
		
		
		
		// Meldung
		if(addit>0){
			event.getPlayer().sendMessage("Heilung: " + addit + "  *sponsored by CyborgOne"  );
		}
	}
	
	
	
	
	public void onPlayerJoin(PlayerJoinEvent event) {
		super.onPlayerJoin(event);
	}

	public void onPlayerQuit(PlayerQuitEvent event) {
		super.onPlayerQuit(event);
	}
	
	
	public void onPlayerChat(PlayerChatEvent event) {
		super.onPlayerChat(event);
	}

	
	
	
    
    
}
 