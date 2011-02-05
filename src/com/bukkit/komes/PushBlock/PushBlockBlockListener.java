package com.bukkit.komes.PushBlock;

//All the imports

import com.bukkit.komes.PushBlock.PushBlockPlayerListener;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;


//Start the class BasicBlockListener
public class PushBlockBlockListener extends BlockListener{
		public int numBlock;
		public boolean collidy;
       public static PushBlock plugin;
       public PushBlockBlockListener(PushBlock instance) {
       plugin = instance;
       }
       
       public void groupMove (Vector dirBlock, Block[] blockArray, int blockTypeId){
    	   for (int i = 0; i <= numBlock; i++){
    		   blockArray[i].setTypeId(0);
    	   }
    	   for (int i = 0; i <= numBlock; i++){
    		   blockArray[i].getRelative((int)dirBlock.getX(), (int)dirBlock.getY(), (int)dirBlock.getZ()).setTypeId(blockTypeId); 
    	   }
    	   
       }
       
       
       public void groupCollision (Vector dirBlock, Block[] blockArray, int blockTypeId){
    	   for (int i = 0; i <= numBlock; i++) {
    		   Block blockNew = blockArray[i].getRelative((int)dirBlock.getX(),(int) dirBlock.getY(), (int)dirBlock.getZ()); 
    		   if (blockNew.getTypeId() != blockTypeId && blockNew.getTypeId() != 0 && blockNew.getTypeId() != 9 && blockNew.getTypeId() != 11 || (blockNew.getTypeId() == 9 && blockNew.getData() == 0) || (blockNew.getTypeId() == 11 && blockNew.getData() == 0)) {
    			  collidy = true;
    		   }
    		   
    	   }
    	   
       }
       
       
       
       public void groupCheck (Block blockPos, int blockTypeId, Block[] blockArray){
    	   if (blockPos.getRelative(0, 0, 1).getTypeId() == blockTypeId ){
    		   boolean blockAdded = true;
    		   Block blockNew = blockPos.getRelative(0, 0, 1);
    		   for (int i = 0; i <= numBlock; i++){
    			   if (blockNew == blockArray[i]){
    				   blockAdded = false;
    			   }
    		   }
    		   if (blockAdded == true){
    			   numBlock++;
    			   blockArray[numBlock] = blockNew;
    			   groupCheck(blockNew, blockTypeId, blockArray);
    		   }
    	   }
    	   if (blockPos.getRelative(0, 0, -1).getTypeId() == blockTypeId ){
    		   boolean blockAdded = true;
    		   Block blockNew = blockPos.getRelative(0, 0, -1);
    		   for (int i = 0; i <= numBlock; i++){
    			   if (blockNew == blockArray[i]){
    				   blockAdded = false;
    			   }
    		   }
    		   if (blockAdded == true){
    			   numBlock++;
    			   blockArray[numBlock] = blockNew;
    			   groupCheck(blockNew, blockTypeId, blockArray);
    		   }
    	   }
    	   if (blockPos.getRelative(0, 1, 0).getTypeId() == blockTypeId ){
    		   boolean blockAdded = true;
    		   Block blockNew = blockPos.getRelative(0, 1, 0);
    		   for (int i = 0; i <= numBlock; i++){
    			   if (blockNew == blockArray[i]){
    				   blockAdded = false;
    			   }
    		   }
    		   if (blockAdded == true){
    			   numBlock++;
    			   blockArray[numBlock] = blockNew;
    			   groupCheck(blockNew, blockTypeId, blockArray);
    		   }
    	   }
    	   if (blockPos.getRelative(0, -1, 0).getTypeId() == blockTypeId ){
    		   boolean blockAdded = true;
    		   Block blockNew = blockPos.getRelative(0, -1, 0);
    		   for (int i = 0; i <= numBlock; i++){
    			   if (blockNew == blockArray[i]){
    				   blockAdded = false;
    			   }
    		   }
    		   if (blockAdded == true){
    			   numBlock++;
    			   blockArray[numBlock] = blockNew;
    			   groupCheck(blockNew, blockTypeId, blockArray);
    		   }
    	   }
    	   if (blockPos.getRelative(1, 0, 0).getTypeId() == blockTypeId ){
    		   boolean blockAdded = true;
    		   Block blockNew = blockPos.getRelative(1, 0, 0);
    		   for (int i = 0; i <= numBlock; i++){
    			   if (blockNew == blockArray[i]){
    				   blockAdded = false;
    			   }
    		   }
    		   if (blockAdded == true){
    			   numBlock++;
    			   blockArray[numBlock] = blockNew;
    			   groupCheck(blockNew, blockTypeId, blockArray);
    		   }
    	   }
    	   if (blockPos.getRelative(-1, 0, 0).getTypeId() == blockTypeId ){
    		   boolean blockAdded = true;
    		   Block blockNew = blockPos.getRelative(-1, 0, 0);
    		   for (int i = 0; i <= numBlock; i++){
    			   if (blockNew == blockArray[i]){
    				   blockAdded = false;
    			   }
    		   }
    		   if (blockAdded == true){
    			   numBlock++;
    			   blockArray[numBlock] = blockNew;
    			   groupCheck(blockNew, blockTypeId, blockArray);
    		   }
    	   }
       }
       
       //This method is called when ever a block is placed.
	public void onBlockRightClick(BlockRightClickEvent event) {
    	   int pushPull = 0;
    	   numBlock = 0;
    	   collidy = false;  
    	   Block[] blockArray = new Block[512]; 
    	   Block block = event.getBlock();
    	   Player player = event.getPlayer();
    	   Vector blockLocation = block.getLocation().toVector();
    	   Vector playerLocation = player.getLocation().toVector();
    	   ItemStack stack = player.getItemInHand();
    	   int BlockType = block.getTypeId();
    	   double dx = playerLocation.getX() - blockLocation.getX();
    	   double dz = playerLocation.getZ() - blockLocation.getZ();
    	  if (stack.getTypeId() == 288){
    		  pushPull = 1;
    	  }else if (stack.getTypeId() == 280){
    		  pushPull = -1;
    	  }
    	  blockArray[0] = block;
    	  if (BlockType == 04 || BlockType == 05 || BlockType == 42  || BlockType == 35 || BlockType == 24 && PushBlockPlayerListener.plugin.enabled(player) && pushPull != 0){
    		   groupCheck(block, BlockType, blockArray);
    		   if (Math.abs(dx) > Math.abs(dz)) {
    	   			if (playerLocation.getX() > blockLocation.getX()) {
    	   				Vector dirBlock = new Vector(0, 0, 0);
    	   				dirBlock.setX(-1 * pushPull);
    	   				dirBlock.setY(0);
    	   				dirBlock.setZ(0);
    	   				groupCollision ( dirBlock, blockArray,BlockType);
    	   				if (collidy ==  false){
    	   					groupMove (dirBlock, blockArray, BlockType);
    	   				}
    	               
    	   			}else{
    	   				Vector dirBlock = new Vector(0, 0, 0);
    	   				dirBlock.setX(1 * pushPull);
    	   				dirBlock.setY(0);
    	   				dirBlock.setZ(0);
    	   				groupCollision ( dirBlock, blockArray,BlockType);
    	   				if (collidy ==  false){
    	   					groupMove (dirBlock, blockArray, BlockType);
    	   				}
    	   			} 
    	   			
    	   		}else if (Math.abs(dx) < Math.abs(dz)) {
    	   			if (playerLocation.getZ() > blockLocation.getZ()){
    	   				Vector dirBlock = new Vector(0, 0, 0);
    	   				dirBlock.setX(0);
    	   				dirBlock.setY(0);
    	   				dirBlock.setZ(-1 * pushPull);
    	   				groupCollision ( dirBlock, blockArray,BlockType);
    	   				if (collidy ==  false){
    	   					groupMove (dirBlock, blockArray, BlockType);	
    	   				}	
    	   			}else{
    	   				Vector dirBlock = new Vector(0, 0, 0);
    	   				dirBlock.setX(0);
    	   				dirBlock.setY(0);
    	   				dirBlock.setZ(1 * pushPull);
    	   				groupCollision ( dirBlock, blockArray,BlockType);
    	   				if (collidy ==  false){
    	   					groupMove (dirBlock, blockArray, BlockType);
    	   				}
    	   			
    	   			
    	   			}
        	   
    	   		}
    	   }
       }
}