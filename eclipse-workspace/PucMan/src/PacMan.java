import java.net.URISyntaxException;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

public class PacMan extends BasicInterface{
	
	String state="";
	int life=3;
	int points=0;
	double curDeg=0;
	char movement='d';
	boolean isMoving=false;
	boolean canKill=false;
	MediaPlayer wakaPlay;
	Media waka;
	public PacMan() {
		
		try {
			/*
			Image pClosedRight=new Image(getClass().getResource("/resources/pacman_closed_right.png").toURI().toString(),50,50,false,false);
			Image pOpenedRight=new Image(getClass().getResource("/resources/pacman_open_right.png").toURI().toString(),50,50,false,false);
			*/
			/*
			Image pClosedLeft=new Image(getClass().getResource("/resources/pacman_closed_left.png").toURI().toString(),50,50,false,false);
			Image pOpenedLeft=new Image(getClass().getResource("/resources/pacman_open_left.png").toURI().toString(),50,50,false,false);
			Image pClosedUp=new Image(getClass().getResource("/resources/pacman_closed_up.png").toURI().toString(),50,50,false,false);
			Image pOpenedUp=new Image(getClass().getResource("/resources/pacman_open_up.png").toURI().toString(),50,50,false,false);
			Image pClosedDown=new Image(getClass().getResource("/resources/pacman_closed_down.png").toURI().toString(),50,50,false,false);
			Image pOpenedDown=new Image(getClass().getResource("/resources/pacman_open_down.png").toURI().toString(),50,50,false,false);
			*/
			Image pClosed=new Image(getClass().getResource("/resources/MineManClose.png").toURI().toString(),50,50,false,false);
			Image pOpened=new Image(getClass().getResource("/resources/MineManOpen.png").toURI().toString(),50,50,false,false);
			
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
				sprites.replace("0",imRotate(curDeg,0,sprites.get("0")));
				sprites.replace("1",imRotate(curDeg,0,sprites.get("1")));
				curDeg=0;
				break;
			case 'a':
				//Left
				
				x=x-sprites.get(state).getWidth();
				sprites.replace("0",imRotate(curDeg,180,sprites.get("0")));
				sprites.replace("1",imRotate(curDeg,180,sprites.get("1")));
				curDeg=180;
				break;
			case 's':
				//Down
				
				y=y+sprites.get(state).getHeight();
				sprites.replace("0",imRotate(curDeg,90,sprites.get("0")));
				sprites.replace("1",imRotate(curDeg,90,sprites.get("1")));
				curDeg=90;
				break;
			case 'w':
				//Up
				
				y=y-sprites.get(state).getHeight();
				sprites.replace("0",imRotate(curDeg,270,sprites.get("0")));
				sprites.replace("1",imRotate(curDeg,270,sprites.get("1")));
				curDeg=270;
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
	private Image imRotate(double curD,double deg,Image target) {
		
		deg=deg-curD;
		
		ImageView iv = new ImageView(target);
		iv.setRotate(deg);
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		Image rotatedImage=iv.snapshot(params, null);
		return rotatedImage;
	}
	
}
