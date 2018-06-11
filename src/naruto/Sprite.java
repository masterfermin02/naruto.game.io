
import java.awt.Graphics;

import acm.graphics.GImage;

public class Sprite implements NarutoGameContants{
	
	private int posx, posy, dx, dy;
	private GImage frame;
		
	public void setX(int x) {
		posx = x;
	}
	
	public void setY(int y) {
		posy=y;
	}
	
	public	int getX() {
		return posx;
	}
	
	public	int getY() {
		return posy;
	}
	
	public int getW() {
		return (int)frame.getWidth();
	}
	
	public int getH() {
		
			return (int)frame.getHeight();
		
	}
		
	public void setdx(int dx){
		this.dx = dx;
	}
	
	public int getdx(){
		return dx;
	}
	
	public void setdy(int dy){
		this.dy = dy;
	}
	
	public int getdy(){
		return dy;
	}
	public void setFrame(GImage frame){
		
		this.frame = frame;
		
	}
	
	public GImage getFrame(){
		return this.frame;
	}
	
	public void move(){
		posx += dx;
		posy += dy;
		if(posx < 0)
			posx = 0;
		else if(posx >= APPLICATION_WIDTH - this.getW())
			posx = APPLICATION_WIDTH - this.getW();
		
		if(posy > Game.screen.getHeight() - getH())
			posy = Game.screen.getHeight() - getH();
		
	}
	
	public void draw(Graphics g){
		g.drawImage(frame.getImage(), getX(), getY(), null);
	}
		
}
