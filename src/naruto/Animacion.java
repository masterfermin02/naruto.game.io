

import acm.graphics.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Animacion implements NarutoGameContants{
	
	private ArrayList<GImage> scenes;
	private boolean running;
	private int frame;
	private Ninja propietario;
	private Iterator<GImage> it;
	
	public Animacion(Ninja propietario){
		running = false;
		scenes = new ArrayList<GImage>();
		frame = 0;
		this.propietario = propietario;
		
	}
	
	public void addScenes(GImage scenes){
		this.scenes.add(scenes);
		
	}
		
	public ArrayList getImages(){
		return scenes;
	}
	
	public GImage getImage(int index){
		return scenes.get(index);
	}
	
	public void update(){
		 
        if(frame < scenes.size()){
        	GImage frameImage = scenes.get(frame);
        	if(!propietario.facingRight())
        		frameImage = GImageUtils.flipHorizontal(frameImage);
        	propietario.setFrame(frameImage);
        	frame++;
        }else{
        	running = false;
        }
			
	}
	
	public void comenzar(){
		running = true;
		
		if(scenes.size() > 1){
				update();
		}
		
	}
	
	public void play(){
		running = true;
		frame = 0;
		update();
	}
	
	public void stop(){
		running = false;
	}
		
	public void loop(){
		//while(running){
			if(it != null && it.hasNext()){
				GImage frameImg = it.next();
	        	if(!propietario.facingRight())
	        		frameImg = GImageUtils.flipHorizontal(frameImg);
	        	propietario.setFrame(frameImg);
	        }else{
				it = scenes.iterator();
				GImage frameImg = it.next();
	        	if(!propietario.facingRight())
	        		frameImg = GImageUtils.flipHorizontal(frameImg);
	        	propietario.setFrame(frameImg);
			}
		//}//fin while
	}//fin loop
		
	public boolean isRunning(){
		return this.running;
	}
		
	
}
