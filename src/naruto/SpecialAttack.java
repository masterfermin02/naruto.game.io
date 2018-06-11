

import acm.graphics.GImage;
import java.util.ArrayList;
import java.util.Iterator;

public class SpecialAttack implements NarutoGameContants {
	
	private Ninja propietario;
	private ArrayList<GImage> imgs = new ArrayList<GImage>();
	private boolean attacking = false;
	private int nFrameToattck = 0;
	private int index = 0;
	private Iterator<GImage> it;
	private boolean tipofire;
	private FireAttack fire;
	private GImage frame;
	
	public SpecialAttack(Ninja propietario){
		this.propietario = propietario;
		fire = new FireAttack(propietario);
	}
	
	public void setPropietario(Ninja p){
		this.propietario = p;
	}
	
	public Ninja getPropietario(){
		return this.propietario;
	}
	
	public void setTipofire(boolean tipofire){
		this.tipofire = tipofire;
	}
	
	public boolean getTipofire(){
		return tipofire;
	}
	
	public FireAttack getFire(){
		return fire;
	}
	
	public void specialattack(GImage img){
		imgs.add(img);
		
	}
	
	public void setnFrameToAttack(int n){
		this.nFrameToattck = n;
	}
	
	public void comenzar(){
		attacking = true;
		it = imgs.iterator();
	}
	
	
	public boolean estado(){
		return attacking;
	}
	
	public void update(){
		if(it.hasNext()){
    		frame = it.next();
    		if(!propietario.facingRight()){
    			frame = GImageUtils.flipHorizontal(frame);
    		}
    		propietario.setFrame(frame);
    		propietario.setY(Game.screen.getHeight() - propietario.getH());
    		if(index == this.nFrameToattck && nFrameToattck > 0){
    			if(!propietario.facingRight())
    				propietario.setdx(-(propietario.getW()*2));
    			else
    				propietario.setdx(propietario.getW());
    		}else{
    			propietario.setdx(0);
    		}
    		 index++; 		
    	}else{
    		//se dipara el attack
    		//propietario.setEstado('f');
    		propietario.setdx(0);
    		if(tipofire && this.attacking){
    			if(fire.isAttacking()){
    				fire.update();
    			}else{
    				fire.comenzar();
    			
    			if(!propietario.facingRight()){
    				fire.setX(propietario.getX() - (propietario.getW() * 2));
    			}else{
    				fire.setX(propietario.getX() + propietario.getW() + 10);
    			}
    			fire.setY(propietario.getY());
    			if(fire.getTipo() == 'a')
    				fire.setdy(-130);
    			}
    		}else{
    			//se detiene este attack
    			//attacking = false;
    			index = 0;
    			propietario.setdx(0);
    			propietario.setEstado('s');
    			//propietario.setY(Game.screen.getHeight() - propietario.getH());
    		}
    		    		
    	} 	
		
	}
	
	public void stop(){
		attacking = false;
	}
	

}
