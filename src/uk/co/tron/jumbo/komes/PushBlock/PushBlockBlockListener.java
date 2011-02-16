package uk.co.tron.jumbo.komes.PushBlock;

//All the imports

import java.io.File;
import java.io.IOException;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import uk.co.tron.jumbo.komes.PushBlock.BlockPushConfig;
import uk.co.tron.jumbo.komes.PushBlock.PushBlockPlayerListener;




//Start the class BasicBlockListener
public class PushBlockBlockListener extends BlockListener{
		public int numBlock;
		public boolean collidy;
		public int[] BlockID = new int[200];
		public int BlockHandH;
		public int BlockHandV;
		public int BlockA;
       public static PushBlock plugin;
       BlockPushConfig myConfig = new BlockPushConfig();

       
       public void load(File f){
    	   myConfig.configFile(f);
    	   try {
			myConfig.LoadConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BlockHandH = Integer.parseInt(myConfig.getBlockH().trim());
		BlockHandV = Integer.parseInt(myConfig.getBlockV().trim());
		BlockA = Integer.parseInt(myConfig.getBlockAmount().trim());
		BlockID = myConfig.getBlockID();
       }
       
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
    		   boolean selfcollide = true;
    		   
    		   for(int j = 0; j <= numBlock; j++){
    			   if (blockNew == blockArray[j]){
    				   selfcollide = false;
    			   }
    		   }
    		   
    		   if (blockNew.getTypeId() != blockTypeId && blockNew.getTypeId() != 0 && blockNew.getTypeId() != 9 && blockNew.getTypeId() != 11 || (blockNew.getTypeId() == 9 && blockNew.getData() == 0) || (blockNew.getTypeId() == 11 && blockNew.getData() == 0) || (selfcollide && blockNew.getTypeId() == blockTypeId)) {
    			   collidy = true; 
    		   }
    	   }
       }
       
       
       
       public void groupCheck (Block blockPos, int blockTypeId, Block[] blockArray){
    	   if (blockPos.getRelative(0, 0, 1).getTypeId() == blockTypeId && numBlock + 1 < blockArray.length){
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
    	   if (blockPos.getRelative(0, 0, -1).getTypeId() == blockTypeId && numBlock + 1 < blockArray.length){
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
    	   if (blockPos.getRelative(0, 1, 0).getTypeId() == blockTypeId && numBlock + 1 < blockArray.length){
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
    	   if (blockPos.getRelative(0, -1, 0).getTypeId() == blockTypeId && numBlock + 1 < blockArray.length){
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
    	   if (blockPos.getRelative(1, 0, 0).getTypeId() == blockTypeId && numBlock + 1 < blockArray.length){
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
    	   if (blockPos.getRelative(-1, 0, 0).getTypeId() == blockTypeId && numBlock + 1 < blockArray.length){
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
       
       
       public void onBlockDamage(BlockDamageEvent event){
    	   numBlock = 0;
	   collidy = false;  
	   Block[] blockArray = new Block[BlockA]; 
	   Block block = event.getBlock();
	   Player player = event.getPlayer();
	   Vector blockLocation = block.getLocation().toVector();
	   Vector playerLocation = player.getLocation().toVector();
	   ItemStack stack = player.getItemInHand();
	   int BlockType = block.getTypeId();
	   double dx = playerLocation.getX() - (blockLocation.getX() + 0.5);
	   double dz = playerLocation.getZ() - (blockLocation.getZ() + 0.5);
	   
	   boolean goodBlock = false;
	   int j = 0;
	   while(BlockID[j] != -1){
		   if(BlockType == BlockID[j]){
			   goodBlock = true;
		   }
		   j++;
	   }
	  blockArray[0] = block;
	  if (PushBlockPlayerListener.plugin.enabled(player)){
		  if (stack.getTypeId() == BlockHandH && goodBlock){
		   groupCheck(block, BlockType, blockArray);
		   if (Math.abs(dx) > Math.abs(dz)) {
	   			if (playerLocation.getX() > blockLocation.getX()) {
	   				Vector dirBlock = new Vector(0, 0, 0);
	   				dirBlock.setX(-1);
	   				dirBlock.setY(0);
	   				dirBlock.setZ(0);
	   				groupCollision ( dirBlock, blockArray,BlockType);
	   				if (collidy ==  false){
	   					groupMove (dirBlock, blockArray, BlockType);
	   				}
	               
	   			}else{
	   				Vector dirBlock = new Vector(0, 0, 0);
	   				dirBlock.setX(1);
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
	   				dirBlock.setZ(-1);
	   				groupCollision ( dirBlock, blockArray,BlockType);
	   				if (collidy ==  false){
	   					groupMove (dirBlock, blockArray, BlockType);	
	   				}	
	   			}else{
	   				Vector dirBlock = new Vector(0, 0, 0);
	   				dirBlock.setX(0);
	   				dirBlock.setY(0);
	   				dirBlock.setZ(1);
	   				groupCollision ( dirBlock, blockArray,BlockType);
	   				if (collidy ==  false){
	   					groupMove (dirBlock, blockArray, BlockType);
	   				}
	   			
	   			
	   			}
    	   
	   		}
		   
	   }
		  if (stack.getTypeId() == BlockHandV && goodBlock){
			  groupCheck(block, BlockType, blockArray);
			  Vector dirBlock = new Vector(0, 0, 0);
   				dirBlock.setX(0);
   				dirBlock.setY(1);
   				dirBlock.setZ(0);
   				groupCollision ( dirBlock, blockArray,BlockType);
   				if (collidy ==  false){
   					groupMove (dirBlock, blockArray, BlockType);
   				}
   				
		  }
   }
}

       //This method is called when ever a block is placed.
	public void onBlockRightClick(BlockRightClickEvent event) {
    	   numBlock = 0;
    	   collidy = false;  
    	   Block[] blockArray = new Block[BlockA]; 
    	   Block block = event.getBlock();
    	   Player player = event.getPlayer();
    	   Vector blockLocation = block.getLocation().toVector();
    	   Vector playerLocation = player.getLocation().toVector();
    	   ItemStack stack = player.getItemInHand();
    	   int BlockType = block.getTypeId();
    	   double dx = playerLocation.getX() - (blockLocation.getX() + 0.5);
    	   double dz = playerLocation.getZ() - (blockLocation.getZ() + 0.5);
    	   
    	   boolean goodBlock = false;
    	   int j = 0;
    	   while(BlockID[j] != -1){
    		   if(BlockType == BlockID[j]){
    			   goodBlock = true;
    		   }
    		   j++;
    	   }
    	  blockArray[0] = block;
    	  if (PushBlockPlayerListener.plugin.enabled(player)){
    		  if (stack.getTypeId() == BlockHandH && goodBlock){   
    		 groupCheck(block, BlockType, blockArray);
    		   if (Math.abs(dx) > Math.abs(dz)) {
    	   			if (playerLocation.getX() > blockLocation.getX()) {
    	   				Vector dirBlock = new Vector(0, 0, 0);
    	   				dirBlock.setX(1);
    	   				dirBlock.setY(0);
    	   				dirBlock.setZ(0);
    	   				groupCollision (dirBlock, blockArray,BlockType);
    	   				if (collidy ==  false){
    	   					groupMove (dirBlock, blockArray, BlockType);
    	   				}
    	               
    	   			}else{
    	   				Vector dirBlock = new Vector(0, 0, 0);
    	   				dirBlock.setX(-1);
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
    	   				dirBlock.setZ(1);
    	   				groupCollision ( dirBlock, blockArray,BlockType);
    	   				if (collidy ==  false){
    	   					groupMove (dirBlock, blockArray, BlockType);	
    	   				}	
    	   			}else{
    	   				Vector dirBlock = new Vector(0, 0, 0);
    	   				dirBlock.setX(0);
    	   				dirBlock.setY(0);
    	   				dirBlock.setZ(-1);
    	   				groupCollision ( dirBlock, blockArray,BlockType);
    	   				if (collidy ==  false){
    	   					groupMove (dirBlock, blockArray, BlockType);
    	   				}
    	   			
    	   			
    	   			}
        	   
    	   		}
    	   }
       }
    	  if (stack.getTypeId() == BlockHandV && goodBlock){
			  groupCheck(block, BlockType, blockArray);
			  Vector dirBlock = new Vector(0, 0, 0);
   				dirBlock.setX(0);
   				dirBlock.setY(-1);
   				dirBlock.setZ(0);
   				groupCollision ( dirBlock, blockArray,BlockType);
   				if (collidy ==  false){
   					groupMove (dirBlock, blockArray, BlockType);
   				}
   				
		  }
	}
}