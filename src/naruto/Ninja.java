
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Ninja  extends Sprite implements Runnable {
	
	//private instance of variable
	private String name;
	//private char facing;
	//private String urlPersonaje = "\\image\\personaje\\";
	private char estado;
	private ArrayList<SpecialAttack> attackList;
	private SpecialAttack curAttack;
	//Animaciones
	private Animacion walk;
	private Animacion stance;
	private Animacion correr;
	private Animacion damage;
	private Animacion jump;
	//boolean variable
	private boolean facingRight;
	private boolean falling = false;
	
        public void run(){
            while(true){
                move();
                update();
                try {
                    Thread.sleep(150);                 //1000 milliseconds is one second.
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
	//Contrutor
	public Ninja(String name){
		this.name = name;
		facingRight = true;
		damage = new Animacion(this);
		curAttack = new SpecialAttack(this);
		attackList = new ArrayList<SpecialAttack>();
	}
	
	public void setWalk(Animacion a){
		walk = a;
	}
	
	public void setCorrer(Animacion a){
		correr = a;
		
	}
	
	public void setStance(Animacion a){
		stance = a;
	}
	
	public void setJump(Animacion a){
		jump = a;
	}
				
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
		
	}

	public char getEstado(){
		return estado;
	}
	
	public void setEstado(char estado){
		if(estado != this.estado){
			this.estado = estado;
		
			switch(estado){
				case 's':
					stance.loop();
					this.setdx(0);
					break;
				case 'w':
					walk.loop();
					break;
				case 'c':
					correr.loop();
					break;
				case 'a':
					curAttack.comenzar();
					break;
				case 'f':
					curAttack.getFire().comenzar();
					break;
				case 'j':
					falling = false;
					this.setdy(-20);
					jump.play();
					//jump
					break;
					default :
						break;
			}// fin de switch
		}//fin del if
	}

	//public boolean attacking(){
	//	return attacking;
	//}
	
	public void turn(){
		if(this.facingRight)
			facingRight = false;
		else
			facingRight = true;
	}
	
	public boolean facingRight(){
		return facingRight;
	}
		
	public void update(){
		switch(estado){
			case 's':
				stance.loop();
				break;
			case 'w':
				walk.loop();
				break;
			case 'c':
				correr.loop();
				break;
			case 'a':
				curAttack.update();
				break;
			case 'j':
				//estoy en el piso de puesde de subir
				// me para y me pongo en posicion stance
				if(getdy() == 0){
					this.setEstado('s');
					
				}
				// llegue al limite del el salto y comienzo a desender
				if(this.getY() <= jumpHeight){
					this.setdy(20);
					jump.update();
					falling = true;
				}
				// llegue al piso denuevo y cambio a la posicion cuando cargo
				if(falling && getY() >= Game.screen.getHeight() - getH()){
					this.setdy(0);
					jump.update();
					setY(Game.screen.getHeight() - getH());
				}
				//jump
				break;
				default :
					break;
		}
			
	}
	
	public void damage(){
		if(!damage.isRunning()){
			stance.stop();
			//damage.start();
			
		}
	}
	
	public void stopdamge(){
		damage.stop();
		//stance.start();
	}
	
	public Rectangle getBounds(){
		
		return new Rectangle(this.getX(), this.getY(), this.getW(), this.getH());
	}
		
	public void addSpecialAttack(SpecialAttack attack){
		attackList.add(attack);
	}
	
	public void iniSpecialAttack(int attack){
		curAttack = attackList.get(attack);
	}
	
	public SpecialAttack getCurAttack(){
		return curAttack;
	}
	
	public void draw(Graphics g) {
		super.draw(g);
		if(curAttack.getTipofire() && curAttack.getFire().isAttacking() && curAttack.getFire().getFrame() != null)
			curAttack.getFire().draw(g);
		//g.drawRect((int)this.getBounds().getX(), (int)this.getBounds().getY(), (int)this.getBounds().getWidth(), (int)this.getBounds().getHeight());
		//g.drawOval(getX(), getY(), getW(), getH());
	}
	
	
}
