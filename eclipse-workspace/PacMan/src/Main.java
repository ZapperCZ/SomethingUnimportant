import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{
	public static int it=0;
	public static void main (String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("PacMan");
		VBox vb = new VBox();
		Scene scene = new Scene(vb,1800,900);
		Canvas canvas=new Canvas(vb.getWidth(),vb.getHeight());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		GameLevel MainLevel=new GameLevel();
		MainLevel.drawLevel(gc);
		clear(gc,canvas);
		/*
		MainLevel.gameLogic('d');
		MainLevel.drawLevel(gc);
		*/
		final long startNanoTime = System.nanoTime();
		
		ArrayList<String> input = new ArrayList<String>();
		
		scene.setOnKeyPressed(
				new EventHandler<KeyEvent>()
				{
					public void handle(KeyEvent e) {
						String code = e.getCode().toString();
						if(!input.contains(code)) {
							input.add(code);
						}
					}
				}
		);
		scene.setOnKeyReleased(
				new EventHandler<KeyEvent>()
				{
					public void handle(KeyEvent e) {
						String code = e.getCode().toString();
						input.remove(code);
					}
				}
		);
		
		new AnimationTimer() {
			
			public void handle(long currentNanoTime) {
				double t = (currentNanoTime-startNanoTime) / 1000000000.0;
				
				int curr=(int) t;
				if(curr>it) {
					it=curr;
					//System.out.println("i= "+curr+" ,it= "+it);
					clear(gc,canvas);
					//MainLevel.gameLogic('d');
					if(input.contains("W")) {
						MainLevel.gameLogic('w');
					}else if(input.contains("S")) {
						MainLevel.gameLogic('s');
					}else if(input.contains("A")) {
						MainLevel.gameLogic('a');
					}else if(input.contains("D")) {
						MainLevel.gameLogic('d');
					}
					MainLevel.drawLevel(gc);
				

				}
				//System.out.println(i);
			}
		}.start();
		
		((VBox)scene.getRoot()).getChildren().addAll(canvas);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	public void clear(GraphicsContext gcclear, Canvas cvs) {
		gcclear.setFill(Color.BLACK);
		gcclear.fillRect(0, 0, cvs.getWidth(), cvs.getHeight());
	}
}


