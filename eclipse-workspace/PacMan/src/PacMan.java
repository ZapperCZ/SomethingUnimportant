import java.net.URISyntaxException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PacMan extends BasicInterface{
	
	String state="";
	int life=3;
	int points=0;

	boolean isMoving=false;
	boolean canKill=false;
	
	public PacMan() {
		try {
			Image pClosed=new Image(getClass().getResource("/resources/pacman_close.png").toURI().toString(),50,50,false,false);
			Image pOpened=new Image(getClass().getResource("/resources/pacman_open.png").toURI().toString(),50,50,false,false);
			
			sprites.put("0", pClosed);
			sprites.put("1", pOpened);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		state = "0";
	}
	
	public void move(char direction) {
		
		switch(direction) {
			case 'd':
				//Right
				x=x+sprites.get(state).getWidth()/20;
				break;
			case 'a':
				//Left
				x=x-sprites.get(state).getWidth()/20;
				break;
			case 's':
				//Down
				y=y+sprites.get(state).getHeight()/20;
				break;
			case 'w':
				//Up
				y=y-sprites.get(state).getHeight()/20;
				break;
		}
		Integer iSprite = Integer.parseInt(state);
		iSprite = (iSprite+1)%state.length();
		state=iSprite.toString();
	}
	public void draw(GraphicsContext gcp){
		Image pci = sprites.get(state);
		gcp.drawImage(pci, x, y);
	}
	
	public void die() {
		
	}
	
}
