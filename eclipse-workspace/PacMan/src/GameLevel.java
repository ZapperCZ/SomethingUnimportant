import java.util.List;

import javafx.scene.canvas.GraphicsContext;

public class GameLevel {
	PacMan PCPos;
	
	List <Ghost> GPos;
	List <Coin> CPos;
	List <Wall> WPos;
	
	public GameLevel() {
		PCPos = new PacMan();
	}
	
	public void drawLevel(GraphicsContext gcpm) {
		PCPos.draw(gcpm);
	}
	
	public void gameLogic(char mdirection) {
		PCPos.move(mdirection);
	}
	
	public void load() {
		
	}
	
}
