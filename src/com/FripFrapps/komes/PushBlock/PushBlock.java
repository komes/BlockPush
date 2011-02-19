package com.FripFrapps.komes.PushBlock;

//All the imports
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import com.FripFrapps.komes.PushBlock.PushBlockBlockListener;


//Starts the class
public class PushBlock extends JavaPlugin {
	// Links the BasicPlayerListener
	private final PushBlockPlayerListener playerListener = new PushBlockPlayerListener(
			this);
	// Links the BasicBlockListener
	private final PushBlockBlockListener blockListener = new PushBlockBlockListener(
			this);
	// Create the hashmap "basicUsers"
	public final HashMap<Player, ArrayList<Block>> basicUsers = new HashMap<Player, ArrayList<Block>>();
	// Create the hashmap debugees
	private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();

	public PushBlock(PluginLoader pluginLoader, Server instance,
			PluginDescriptionFile desc, File folder, File plugin,
			ClassLoader cLoader) {
		super(pluginLoader, instance, desc, folder, plugin, cLoader);
	}

	@Override
	// When the plugin is disabled this method is called.
	public void onDisable() {
		// Print "Basic Disabled" on the log.
		System.out.println("Basic Disabled");

	}

	@Override
	// When the plugin is enabled this method is called.
	public void onEnable() {
		File f = new File(this.getDataFolder().getParentFile() + File.separator
				+ "PushBlock_Config.txt");
		blockListener.load(f);

		File j = new File(this.getDataFolder().getParentFile() + File.separator
				+ "Sign_Pos.txt");
		if (!j.exists()) {
			try {
				j.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			blockListener.loadPos(j);
		}

		// Create the pluginmanage pm.
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvent(Event.Type.PLAYER_COMMAND, this.playerListener,
				Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_DAMAGED, blockListener,
				Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_RIGHTCLICKED, blockListener,
				Event.Priority.Normal, this);
		// Get the infomation from the yml file.
		PluginDescriptionFile pdfFile = this.getDescription();
		// Print that the plugin has been enabled!
		System.out.println(pdfFile.getName() + " version "
				+ pdfFile.getVersion() + " is enabled!");

	}

	// Used when debugging
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

	// The method enabled which checks to see if the player is in the hashmap
	// basicUsers
	public boolean enabled(Player player) {
		return this.basicUsers.containsKey(player);
	}

	// The method toggleVision which if the player is on the hashmap will remove
	// the player else it will add the player.
	// Also sends user a message to notify them.
	public void toggleVision(Player player) {
		if (enabled(player)) {
			this.basicUsers.remove(player);
			player.sendMessage("BlockPush disabled");
		} else {
			this.basicUsers.put(player, null);
			player.sendMessage("BlockPush enabled");
		}
	}

}