package com.FripFrapps.komes.PushBlock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.bukkit.util.Vector;

public class BlockPushConfig {
	public String BlockPushH;
	public String BlockPushV;
	public String BlockAmount;
	Vector[] signPos = new Vector[500];
	public int[] BlockID = new int[200];
	private File file;
	private File fileMech;

	public void configFile(File file) {
		this.file = file;
	}
	public String getBlockH () {
		return BlockPushH;
	}
	public Vector[] getLocations () {
		return signPos;
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
	
	public void posFile(File file) {
		this.fileMech = file;
	} 
	
	public void loadBlockPos()throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(this.fileMech));
		String line;
		line = reader.readLine();
		String[] split = line.split(":");
	
		
		for (int i = 0; i < split.length; i++){
			String[] splitLoc = split[i].split(",");
			signPos[i].setX(Integer.parseInt(splitLoc[1].trim()));
			signPos[i].setY(Integer.parseInt(splitLoc[2].trim()));
			signPos[i].setZ(Integer.parseInt(splitLoc[3].trim()));
		}
	}
}
