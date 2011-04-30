package cyborgone82.cybilogix;

import org.bukkit.event.block.BlockListener;

/**
 * PlugTest block listener
 * @author cyborgone82
 */

public class CybilogixBlockListener extends BlockListener{
    private final Cybilogix plugin;

    public CybilogixBlockListener(final Cybilogix plugin) {
        this.plugin = plugin;
    }

	public Cybilogix getPlugin() {
		return plugin;
	}

    //put all Block related code here
}
