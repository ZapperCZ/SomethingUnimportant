import java.net.URISyntaxException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PacMan extends BasicInterface{
	
	String state="";
	int life=3;
	int points=0;
	//string wak=""
	char movement='d';
	boolean isMoving=false;
	boolean canKill=false;
	MediaPlayer wakaPlay;
	Media waka;
	public PacMan() {
		
		try {
			Image pClosed=new Image(getClass().getResource("/resources/pacman_close.png").toURI().toString(),50,50,false,false);
			Image pOpened=new Image(getClass().getResource("/resources/pacman_open.png").toURI().toString(),50,50,false,false);
			waka = new Media(getClass().getResource("/resources/pac-man-waka-waka.mp3").toURI().toString());
			
			
			sprites.put("0", pClosed);
			sprites.put("1", pOpened);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		state = "0";
		System.out.println("Zavolan konstruktor");
	}
	
	public void move(char direction) {
		
		if(direction==movement) {
			wakaPlay = new MediaPlayer(waka);
			wakaPlay.play();
			wakaPlay.setAutoPlay(true);
			if(state.equals("0")) {
				state="1";
				System.out.println(state);
				System.out.println("Prvni if");
			}else{
				state="0";
				System.out.println(state);
				System.out.println("Druhej if");
			}
		}
		switch(direction) {
			case 'd':
				//Right
				
				x=x+sprites.get(state).getWidth();
				
				break;
			case 'a':
				//Left
				
				x=x-sprites.get(state).getWidth();
				
				break;
			case 's':
				//Down
				
				y=y+sprites.get(state).getHeight();
				
				break;
			case 'w':
				//Up
				
				y=y-sprites.get(state).getHeight();
				
				break;
		}
		/*
		Integer iSprite = Integer.parseInt(state);
		iSprite = (iSprite+1)%state.length();
		state=iSprite.toString();
		System.out.println("Final state > "+state);
		*/
		movement=direction;
	}
	public void draw(GraphicsContext gcp){
		Image pci = sprites.get(state);
		gcp.drawImage(pci, x, y);
	}
	
	public void die() {
		
	}
	
}
