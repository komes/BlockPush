package uk.co.tron.jumbo.komes.PushBlock;

import java.io.File;
import java.io.IOException;

import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.util.Vector;

public class PushBlockMech extends BlockListener {
	public static PushBlock plugin;
	BlockPushConfig myMech = new BlockPushConfig();
	public Vector[] signPos = new Vector[500];

	public void loadPos(File j) {
		myMech.posFile(j);

		try {
			myMech.loadBlockPos();
		} catch (IOException e) {
			e.printStackTrace();
		}
		signPos = myMech.getLocations();
	}

	public PushBlockMech(PushBlock instance) {
		plugin = instance;
	}

	public void RedStoneChange(BlockRedstoneEvent event) {
 
	}
	
	public void BlockPlace(SignChangeEvent event) {
			Sign sign = event.getAttachedFace
		}
		
		
	}


