import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import acm.graphics.GImage;



public class FireAttack extends Sprite implements NarutoGameContants{
	
	private Ninja owner;
	private boolean attacking;
	private ArrayList<GImage> frames;
	private char tipo;
	private Iterator<GImage> it;
	private GImage frame;
	private boolean aparecio;
	private int frameaparece;
	private int curFrame;
	private Ninja objectivo;
	private boolean terminar;
	private boolean falling;
	
	public FireAttack(Ninja owner){
		this.owner = owner;
		frames = new ArrayList<GImage>();
		aparecio = false;
		curFrame = 0;
	}
	
	public void setOwner(Ninja owner){
		this.owner = owner;
	}
	
	public Ninja getOwner(){
		return owner;
	}
	
	public void setTipo(char t){
		tipo = t;
	}
	
	public char getTipo(){
		return tipo;
	}
	
	public void selFrame(GImage frame){
		frames.add(frame);
	}
	
	public void setFrameApacre(int a){
		this.frameaparece = a;
	}
	public void setAttacking(boolean attacking){
		this.attacking = attacking;
	}
	
	public boolean isAttacking(){
		return attacking;
	}
	
	public void setObjectivo(Ninja ninja){
		this.objectivo = ninja;
	}
	
	public void comenzar(){
		
		it = frames.iterator();
		curFrame = 0;
		terminar = false;
		aparecio = false;
		falling = false;
		nextFrame();
		if(owner.facingRight())
			this.setdx(20);
		else
			this.setdx(-20);
		attacking = true;
	}
	
	public void update(){
		
			if(tipo == 'a'){
				jumpattack();
			}else{
				
			}
		
	}
	
	private void jumpattack(){
		if(it.hasNext()){
				
				if(!aparecio){
					 nextFrame();
					 if(curFrame > frameaparece){
						 aparecio = true;
					 }
					 
				}else{
					// si no a salido de la pantalla jump
					if(outScreen()){
						
						falling = true;
						setdy(130);
					}else if(falling == false){
						jump();
					}
					move();
				}
				if(terminar && falling){
						nextFrame();
						setdy(0);
				}
				
				this.setFrame(frame);
				checkColision();
				
	    	}else{
	    		//se detiene este attack
	    		attacking = false;
	    		//detenemos el attack del popietario
	    		owner.getCurAttack().stop();
	    		
	    	}
	}
	
	
	public void move(){
		setX(getX() + getdx());
		setY(getY() + getdy());
		
	}
	
	private void jump(){
		if(curFrame < 9){
			nextFrame();
		}
	}
	
	private boolean outScreen(){
		return (getY() < -90);
	}
	
	private void checkColision(){
		if(getBounds().intersects(objectivo.getBounds()) && falling){
			System.out.println("Me dio");
			terminar = true;
		}else if(getBounds().intersects(objectivo.getBounds())){
			System.out.println("comenzo a darme");
		}else if(getY() > APPLICATION_HEIGHT - getH() && falling && tipo == 'a'){
			terminar = true;
			setdy(0);
			
		}
	}
	
	public void nextFrame(){
		frame = it.next();
		curFrame++;
		 if(!owner.facingRight())
				frame = GImageUtils.flipHorizontal(frame);
		 
	}
	
	public Rectangle getBounds(){
		return new Rectangle(this.getX(), this.getY(), this.getW(), this.getH());
	}
}
