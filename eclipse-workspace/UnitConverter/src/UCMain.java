import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UCMain extends Application {
	enum type{
		time,
		length,
		weight,
		n_a_d,
		capacity,
		content,
		p_a_e,
		speed,
		strenght,
		temperature,
		pressure,
		performance,
		nothing
		
	}
	/*enum enlarging{
		e1,
		e2,
		e3
	}
	*/
	static type units;
	//static enlarging height;
	
	public static void main (String[] args) {
		units=type.nothing;
		//height=enlarging.e1;
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		double rw=400;
		double rh=150;
		


		primaryStage.setTitle("Pøevodník Jednotek");
		VBox vb=new VBox();
		Scene scene=new Scene(vb,rw,rh);
		MenuButton test = new MenuButton();
		MenuItem mtime = new MenuItem();
		MenuItem mlength = new MenuItem();
		MenuItem mweight = new MenuItem();
		MenuItem mn_a_d = new MenuItem();
		MenuItem mcapacity = new MenuItem();
		MenuItem mcontent = new MenuItem();
		MenuItem mp_a_e = new MenuItem();
		MenuItem mspeed = new MenuItem();
		MenuItem mstrenght = new MenuItem();
		MenuItem mtemperature = new MenuItem();
		MenuItem mpressure = new MenuItem();
		MenuItem mperformance = new MenuItem();
		mtime.setText("Èas");
		mlength.setText("Délka");
		mweight.setText("Váha");
		mn_a_d.setText("Násobky a Díly");
		mcapacity.setText("Objem");
		mcontent.setText("Obsah");
		mp_a_e.setText("Práce a Energie");
		mspeed.setText("Rychlost");
		mstrenght.setText("Síla");
		mtemperature.setText("Teplota");
		mpressure.setText("Tlak");
		mperformance.setText("Výkon");
		
		test.getItems().addAll(mtime,mlength,mn_a_d,mcapacity,mcontent,mp_a_e,mspeed,mstrenght,mtemperature,mpressure,mweight,mperformance);
		
		mtime.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				units=type.time;
				test.setText("Èas");
				//height=enlarging.e2;
				primaryStage.setHeight(300);
				
				
			}
		});
		mlength.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				units=type.length;
				test.setText("Délka");
				primaryStage.setHeight(300);
			}	
		});
		mweight.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				units=type.weight;
				test.setText("Váha");
				primaryStage.setHeight(300);
				
			}
		});
		mn_a_d.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				units=type.n_a_d;
				test.setText("Násobky. a Díly");
				primaryStage.setHeight(300);
			}
		});
		mcapacity.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				units=type.capacity;
				test.setText("Objem");
				primaryStage.setHeight(300);
			}
		});
		mcontent.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				units=type.content;
				test.setText("Obsah");
				primaryStage.setHeight(300);
			}
		});
		mp_a_e.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				units=type.p_a_e;
				test.setText("Práce a Energie");
				primaryStage.setHeight(300);
			}
		});
		mspeed.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				units=type.speed;
				test.setText("Rychlost");
				primaryStage.setHeight(300);
			}
			
		});
		mstrenght.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				units=type.strenght;
				test.setText("Síla");
				primaryStage.setHeight(300);
			}
		});
		mtemperature.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				units=type.temperature;
				test.setText("Teplota");
				primaryStage.setHeight(300);
			}
		});
		mpressure.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				units=type.pressure;
				test.setText("Tlak");
				primaryStage.setHeight(300);
			}
		});
		mperformance.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				units=type.performance;
				test.setText("Výkon");
				primaryStage.setHeight(300);
			}
		});
		
		/*scene.addEventFilter(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
		 *
		 *	@Override
		 *	public void handle(MouseEvent arg0) {
		 *		switch(height) {
		 *		case e1:
		 *			primaryStage.setHeight(150);
		 *			break;
		 *		case e2:
		 *			primaryStage.setHeight(300);
		 *			break;
		 *		case e3:
		 *			break;
		 *			
		 *	}
		 *	
		 *	}			
		 *});
		 */
		
		StackPane sp=new StackPane();
		Label la1= new Label();


		test.setTranslateY(50);
		test.setText("     ");
		test.setMinWidth(150);
		System.out.println(test.getWidth());
		
		la1.setTranslateY(20);
		la1.setText("Vyberte typ jednotky");
		
		
		sp.getChildren().addAll(test,la1);
		sp.setAlignment(Pos.BASELINE_CENTER);
		((VBox)scene.getRoot()).getChildren().addAll(sp);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
