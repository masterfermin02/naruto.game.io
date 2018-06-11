

import acm.program.*;
import acm.util.MediaTools;
import acm.graphics.*;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;



public class Game extends Program implements NarutoGameContants{
	
	
        
        @Override
	public void run(){
		screen = new SSCanvas();
		add(screen);
		naruto.setX(0);
		neji.setX(200);
		naruto.setY(this.getHeight() - naruto.getH());
		neji.setY(this.getHeight() - neji.getH());
		screen.addKeyListener(this);
		screen.setFocusable(true);
		cargarAttack();	
		
		bounceClip.loop();
                (new Thread(naruto)).start();
                (new Thread(neji)).start();
		while(true){
			
			//check for collide
			checkForCollide();
			if(naruto.facingRight() && (naruto.getX() > neji.getX())){
				naruto.turn();
				neji.turn();
			}
			
			if(neji.facingRight() && (neji.getX() > naruto.getX())){
				naruto.turn();
				neji.turn();
			}
			pause(150);
			
			
		}
			
	}
		
	private void checkForCollide(){
		
	}
	

//	 Called every time user clicks mouse
	//public void mouseClicked(MouseEvent e) {}
	//public void mouseEntered(MouseEvent e){}
	//public void mouseReleased(MouseEvent e){}
	//public void mousePressed(MouseEvent e){	}
	//public void mouseExited(MouseEvent e){}
	
	public void keyPressed(KeyEvent e){
		int action = e.getKeyCode();
		
		switch(action){
		case LEFT:
				//sin no esta atacando ni camindo entonce camina
				if(naruto.getEstado() != 'a' && naruto.getEstado() != 'w' && naruto.getEstado() != 'j'){
					naruto.setEstado('w');
					naruto.setdx(-10);
				}
								
				break;
		case RIGHT:
			//sin no esta atacando ni camindo entonce camina
			if(naruto.getEstado() != 'a' && naruto.getEstado() != 'w' && naruto.getEstado() != 'j'){
				naruto.setEstado('w');
				naruto.setdx(10);
			}			
				break;
		case KeyEvent.VK_UP:
//			sin no esta atacando ni saltando entonce brinca
			if(naruto.getEstado() != 'a' && naruto.getEstado() != 'j'){
				naruto.setEstado('j');
			}
			break;
		case KeyEvent.VK_G:
			   
			   	naruto.iniSpecialAttack(0);
			   	naruto.setEstado('a');
			   break;
		case KeyEvent.VK_T:
			
			//naruto.setdx(15);
			naruto.iniSpecialAttack(1);
			naruto.setEstado('a');
			break;
		case KeyEvent.VK_H:
			naruto.iniSpecialAttack(2);
			naruto.setEstado('a');
			break;
		case KeyEvent.VK_A:
			
			neji.setEstado('w');
			neji.setdx(-10);
							
			break;
		case KeyEvent.VK_S:
			
			neji.setEstado('w');
			neji.setdx(10);
							
			break;
		case KeyEvent.VK_F:
			neji.iniSpecialAttack(1);
			break;
		default :
			naruto.setdx(10);
			naruto.setEstado('c');
			break;
		}		
		
	}
		
	public void keyReleased(KeyEvent e){
		
		
		if(naruto.getEstado() == 'w' || naruto.getEstado() == 'c'){
			naruto.setEstado('s');
			
		}
		
		if(neji.getEstado() == 'w' || neji.getEstado() == 'c'){
			neji.setEstado('s');
			
		}
	
	}
		
	private void cargarAttack(){
		cargarNarutoAttack();
		cargarNejiAttack();
	}
	
	private void cargarNarutoAttack(){
		naruto.addSpecialAttack(GImageUtils.loadAttack(9, naruto, "combo", 0));
		naruto.addSpecialAttack(GImageUtils.loadAttack(41, naruto, "greatrasengan", 27));
		naruto.addSpecialAttack(GImageUtils.loadFireAttack(GImageUtils.loadAttack(6, naruto, "frogsummon", 0), 14, "firefrogsummon", neji, 7));
		
		
	}
	
	private void cargarNejiAttack(){
		SpecialAttack attack = new SpecialAttack(neji);
		GImage img = new GImage("image\\personaje\\"+ neji.getName() +"\\upattack1.png");
		loadImage(GImageUtils.splitImage(img, 1, 4), attack);
		img = new GImage("image\\personaje\\"+ neji.getName() +"\\upattack2.png");
		loadImage(GImageUtils.splitImage(img, 1, 4), attack);
		neji.addSpecialAttack(attack);
		attack = new SpecialAttack(neji);
		img = new GImage("image\\personaje\\"+ neji.getName() +"\\byakugan1.png");
		loadImage(GImageUtils.splitImage(img, 1, 8), attack);
		img = new GImage("image\\personaje\\"+ neji.getName() +"\\byakugan2.png");
		loadImage(GImageUtils.splitImage(img, 1, 4), attack);
		loadImage(GImageUtils.splitImage(img, 1, 4), attack);
		neji.addSpecialAttack(attack);
	}
	
	private void loadImage(GImage[] imgs, SpecialAttack sp){
		for(int i = 0; i < imgs.length; i++)
			sp.specialattack(imgs[i]);
	}
	
	
	@SuppressWarnings("serial")
	 class SSCanvas extends GCanvas implements NarutoGameContants{
		
		//Dibujar double buffer
		private Image dbImg;
		private Graphics dbg;
		public SSCanvas() {
//		 Cargamos los sprites
			
			bg = new GImage("image\\bg\\bg2.png");
			naruto = new Ninja("naruto");
			neji = new Ninja("neji");
			addAnimacionNaruto();
			addAnimacionNeji();
			neji.turn();					
			naruto.setEstado('s');
			neji.setEstado('s');
			
		}
		
		private void addAnimacionNaruto(){
			naruto.setCorrer(createAnimacion(naruto, 3, "run"));
			naruto.setStance(createAnimacion(naruto, 4, "stance"));
			naruto.setWalk(createAnimacion(naruto, 4, "walk"));
			naruto.setJump(createAnimacion(naruto, 3, "jump"));
		}
		
		private Animacion createAnimacion(Ninja ninja, int nFrames, String name){
			Animacion a = new Animacion(ninja);
			for(int i = 1; i <= nFrames; i++){
				GImage img = new GImage("image\\personaje\\"+ ninja.getName() +"\\"+name+i+".png");
				a.addScenes(img);
			}
			return a;
		}
		
		private void addAnimacionNeji(){
			neji.setCorrer(createAnimacion(neji, 6, "run"));
			neji.setStance(createAnimacion(neji, 6, "stance"));
			neji.setWalk(createAnimacion(neji, 6, "walk"));
		}
		public void paint(Graphics g){
			
			 dbImg = createImage(getWidth(), getHeight());
			 dbg = dbImg.getGraphics();
		     paintComponent(dbg);
		     g.drawImage(dbImg, 0, 0, this);
		}
										
		
		public void paintComponent(Graphics g) {
			//		 situar y dibujar sprite
			
			g.drawImage(bg.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
			//g.drawImage(bga.getImage(), 0, this.getHeight() - 54, this.getWidth(), this.getHeight(), null);
			naruto.setX(naruto.getX());
			naruto.setY(naruto.getY());
			neji.setX(neji.getX());
			neji.setY(neji.getY());
			naruto.draw(g);
			neji.draw(g);
			repaint();	
		}
	}
	
	
	
	static SSCanvas screen;
	private Ninja naruto;
	private Ninja neji;
	private GImage bg;
	private AudioClip bounceClip = MediaTools.loadAudioClip("naruto.au");
	
	
		
}
