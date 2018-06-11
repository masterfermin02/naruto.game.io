


import acm.graphics.GImage;

public class GImageUtils {
	
	public static GImage[] splitImage(GImage gimage, int rows, int cols) {
		int w = (int)gimage.getWidth() / cols;
		int h = (int)gimage.getHeight() / rows;
		int num = 0;
		GImage[] imgs = new GImage[cols * rows];
		int[][] array = gimage.getPixelArray();
		for (int y = 0; y < rows; y++) {  
            for (int x = 0; x < cols; x++) {
            	
                imgs[num] = drawImage(array, 0, 0, w, h, w * x, h * y, w * x + w, h * y + h); 
                num++;  
            }  
        }  
        return imgs;
		
	}
	
	private static GImage drawImage(int[][] array, int x, int y, int width, int height, int iniciox, int inicioy, int hastax, int hastay){
			int[][] newArray = new int[height][width];
			for(int j = inicioy; j < hastay; j++){
				for(int i = iniciox; i < hastax - 1; i++){
					newArray[y][x] = array[j][i];
					x++;
				}
				x = 0;
				y++;
			}
			return new GImage(newArray);
	}
	
	public static GImage flipHorizontal(GImage image){
		int array[][] = image.getPixelArray();
		int height = array.length;
		int width = array[0].length;
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width / 2; j++){
				int pixel = width - j - 1;
				int temp = array[i][j];
				array[i][j] = array[i][pixel];
				array[i][pixel] = temp;
			}
			
		}
		return new GImage(array);
	}
		
	public static SpecialAttack loadAttack(int cantFrame, Ninja ninja, String nameAttack, int nFrameToAttack){
		SpecialAttack attack  = new SpecialAttack(ninja);
		attack.setnFrameToAttack(nFrameToAttack);
		for(int i = 1; i <= cantFrame; i++){
			String url = "image\\personaje\\"+ ninja.getName() +"\\"+nameAttack+i+".png";
			GImage img = new GImage(url);
			attack.specialattack(img);
		}
		return attack;
	}
	
	public static SpecialAttack loadFireAttack(SpecialAttack sp, int cantFrame, String nameAttack, Ninja enemigo, int aparece){
		sp.getFire().setTipo('a');
		sp.setTipofire(true);
		sp.getFire().setObjectivo(enemigo);
		sp.getFire().setFrameApacre(aparece);
		for(int i = 1; i <= cantFrame; i++){
			String url = "image\\personaje\\"+ sp.getPropietario().getName() +"\\"+nameAttack+i+".png";
			GImage img = new GImage(url);
			sp.getFire().selFrame(img);
		}
		return sp;
	}
}
