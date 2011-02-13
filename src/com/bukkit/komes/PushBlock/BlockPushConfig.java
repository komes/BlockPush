package com.bukkit.komes.PushBlock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BlockPushConfig {
	public String BlockPushH;
	public String BlockPushV;
	public String BlockAmount;
	public int[] BlockID = new int[200];
	private File file;

	public void configFile(File file) {
		this.file = file;
	}
	public String getBlockH () {
		return BlockPushH;
	}
	public String getBlockV () {
		return BlockPushV;
	}
	public int[] getBlockID () {
		return BlockID;
	}
	public String getBlockAmount(){
		return BlockAmount;
	}
	public void LoadConfig() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(this.file));
		String line;
		line = reader.readLine();
		String[] split = line.split("=");

		BlockPushH = split[1];

		line = reader.readLine();
		split = line.split("=");

		BlockPushV = split[1];
		
		line = reader.readLine();
		split = line.split("=");
		
		BlockAmount = split[1];
		
		line = reader.readLine();
		split = line.split("=");

		split = split[1].split(",");
		
		
		for (int i = 0; i < BlockID.length; i++) {
			BlockID[i] = -1;
		}
		
		for (int i = 0; i < split.length; i++) {
			BlockID[i] = Integer.parseInt(split[i].trim());
		}
	}
}
