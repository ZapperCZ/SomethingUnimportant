
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;

public class Main  extends Application {
	
	enum shapes{
		pixel,
		rect,
		oval,
	}
	enum sWidth{
		thin,
		tnn,
		normal,
		tkn,
		thick,
	}
	enum fillColorModes{
		red,
		green,
		blue,
		black,
		white,
		gray,
		cyan,
		magenta,
		yellow,
		orange,
		lblue,
		lgray,
		lime,
	}
	enum strokeColorModes{
		red,
		green,
		blue,
		black,
		white,
		gray,
		cyan,
		magenta,
		yellow,
		orange,
		lblue,
		lgray,
		lime,
	}
	enum fillModes{
		yes,
		no,
	}
	
	
	
	double prevX,prevY;
	static shapes shapeModes;
	static fillColorModes Fcolors;
	static strokeColorModes Scolors;
	static sWidth line;
	static fillModes fill;
	public static void main (String[] args) {
		shapeModes=shapes.rect;
		Fcolors=fillColorModes.black;
		Scolors=strokeColorModes.black;
		line=sWidth.thin;
		fill=fillModes.no;
		launch(args);

	}
	
	private void saveF (Stage save,Canvas platno) {
		double xw,yh;
		xw=platno.getWidth();
		yh=platno.getHeight();
		FileChooser saving=new FileChooser();
		saving.setTitle("Vyberte cestu");
		saving.getExtensionFilters().add(new FileChooser.ExtensionFilter("png","*.png"));
		//saving.showSaveDialog(save);
		File savingF=saving.showSaveDialog(save);
		if(saving!=null) {
			WritableImage ui=new WritableImage((int)xw,(int)yh);
			try {
				ImageIO.write(SwingFXUtils.fromFXImage(platno.snapshot(null, ui), null), "png", savingF);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	private void loadF (Stage load,GraphicsContext gcLoad,double offset,Canvas canvas) {
		FileChooser loading=new FileChooser();
		loading.setTitle("Vyberte cestu");
		
		File loadingF=loading.showOpenDialog(load);
		Image loadI;
		loadI=new Image(loadingF.toURI().toString());
		double heightI=loadI.getHeight();
		double widthI=loadI.getWidth();
		canvas.setHeight(heightI);
		canvas.setWidth(widthI);
		gcLoad.drawImage(loadI, 0, 0);
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		//System.out.println(primaryScreenBounds.getWidth() +" "+ primaryScreenBounds.getHeight());
		double sW=primaryScreenBounds.getWidth();
		double sH=primaryScreenBounds.getHeight();
		
		/*if((heightI>sH-offset)||(widthI>sW)) {
			//canvas.setWidth(primaryScreenBounds.getWidth());
			//canvas.setHeight(primaryScreenBounds.getHeight());
			load.setFullScreen(true);
			//System.out.println("Do you work?");
			Image loadIB=new Image(loadingF.toURI().toString(),primaryScreenBounds.getWidth(),primaryScreenBounds.getHeight()-offset,false,false);
			gcLoad.drawImage(loadIB,0,0);
		}
		 */
		
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Paint");

		
		VBox vb = new VBox();
		Scene scene = new Scene(vb,1800,900);
		Canvas cmarani=new Canvas(1000,500);
		ScrollPane scrollP=new ScrollPane(cmarani);
		GraphicsContext gc = cmarani.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, cmarani.getWidth(), cmarani.getHeight());
		gc.setFill(Color.BLACK);
		//gc.fillRect(900,450,10,10);
		
		//Image imageRed=new Image(getClass().getResource("/images/redSquare.jpg").toURI().toString(),25,25,false,false);
		
		/*
		Image imageBrush=new Image(getClass().getResource("/images/brushImage.jpg").toURI().toString(),20,20,false,false);
		Image imageRect=new Image(getClass().getResource("/images/rectImage.jpg").toURI().toString(),30,21,false,false);
		Image imageFillYes=new Image(getClass().getResource("/images/rectImageFill.jpg").toURI().toString(),30,21,false,false);
		Image lineThin=new Image(getClass().getResource("/images/lineThin.jpg").toURI().toString(),30,21,false,false);
		Image lineTNN=new Image(getClass().getResource("/images/lineTNN.jpg").toURI().toString(),30,21,false,false);
		Image lineNormal=new Image(getClass().getResource("/images/lineNormal.jpg").toURI().toString(),30,21,false,false);
		Image lineTKN=new Image(getClass().getResource("/images/lineTKN.jpg").toURI().toString(),30,21,false,false);
		Image lineThick=new Image(getClass().getResource("/images/lineThick.jpg").toURI().toString(),30,20,false,false);
		Image imageOval=new Image(getClass().getResource("/images/ovalImage.jpg").toURI().toString(),30,21,false,false);
		*/
		
		MenuBar MenuTycinka=new MenuBar();
		Menu file=new Menu("File");

		
		MenuItem clear=new MenuItem("Clear");
		MenuItem saveAs=new MenuItem("Save As");
		MenuItem load=new MenuItem("Open");
		ToolBar tools=new ToolBar();
		
		Button brush =new Button("Štìtec");
		Button width=new Button();
		Button rectangle=new Button();
		Button fillN=new Button();
		Separator sep1=new Separator();
		Button red=new Button();
		Button orange=new Button();
		Button yellow=new Button();
		Button black=new Button();
		Button lime=new Button();
		Button green=new Button();
		Button blue=new Button();
		Button gray=new Button();
		Button white=new Button();
		
		//red.setGraphic(new ImageView(imageRed));
		//fillN.setGraphic(new ImageView(imageRect));
		//brush.setGraphic(new ImageView(imageBrush));
		brush.setStyle("-fx-background-color:#FFFFFF; -fx-border-color:#D3D3D3");
		//rectangle.setGraphic(new ImageView(imageRect));
		rectangle.setStyle("-fx-background-color:#FFFFFF; -fx-border-color:#D3D3D3");
		fillN.setStyle("-fx-background-color:#FFFFFF; -fx-border-color:#D3D3D3");
		//width.setGraphic(new ImageView(lineThin));
		width.setStyle("-fx-background-color:#FFFFFF; -fx-border-color:#D3D3D3");
		red.setStyle("-fx-background-color:#FF0000");
		orange.setStyle("-fx-background-color:#FFA500");
		yellow.setStyle("-fx-background-color:#FFFF00");
		black.setStyle("-fx-background-color:#000000");
		lime.setStyle("-fx-background-color:#00FF00");
		green.setStyle("-fx-background-color:#008000");
		blue.setStyle("-fx-background-color:#0000FF");
		gray.setStyle("-fx-background-color:#808080");
		white.setStyle("-fx-background-color:#FFFFFF");
		cmarani.setStyle("-fx-background-color:#FFFFFF");
		
		load.setAccelerator(new KeyCodeCombination(KeyCode.O,KeyCombination.CONTROL_DOWN));
		saveAs.setAccelerator(new KeyCodeCombination(KeyCode.S,KeyCombination.CONTROL_DOWN));
		clear.setAccelerator(new KeyCodeCombination(KeyCode.R,KeyCombination.CONTROL_DOWN));
		
		file.getItems().addAll(clear,saveAs,load);
		tools.getItems().addAll(brush,rectangle,fillN,width,sep1,black,gray,white,red,orange,yellow,lime,green,blue);
		MenuTycinka.getMenus().addAll(file);
	
		lime.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(fill==fillModes.yes){
					Fcolors=fillColorModes.lime;
				}else if(fill==fillModes.no) {
					Scolors=strokeColorModes.lime;
				}
			}
		});
		orange.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(fill==fillModes.yes){
					Fcolors=fillColorModes.orange;
				}else if(fill==fillModes.no) {
					Scolors=strokeColorModes.orange;
				}
			}
		});
		yellow.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(fill==fillModes.yes){
					Fcolors=fillColorModes.yellow;
				}else if(fill==fillModes.no) {
					Scolors=strokeColorModes.yellow;
				}
			}
		});
		black.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(fill==fillModes.yes){
					Fcolors=fillColorModes.black;
				}else if(fill==fillModes.no) {
					Scolors=strokeColorModes.black;
				}
			}
		});
		red.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if(fill==fillModes.yes){
					Fcolors=fillColorModes.red;
				}else if(fill==fillModes.no) {
					Scolors=strokeColorModes.red;
				}
			}
		});
		green.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(fill==fillModes.yes){
					Fcolors=fillColorModes.green;
				}else if(fill==fillModes.no) {
					Scolors=strokeColorModes.green;
				}
			}
				
				
		});
		blue.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if(fill==fillModes.yes){
					Fcolors=fillColorModes.blue;
				}else if(fill==fillModes.no) {
					Scolors=strokeColorModes.blue;
				}
			}
		});
		gray.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if(fill==fillModes.yes){
					Fcolors=fillColorModes.gray;
				}else if(fill==fillModes.no) {
					Scolors=strokeColorModes.gray;
				}
			}
		});
		white.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if(fill==fillModes.yes){
					Fcolors=fillColorModes.white;
				}else if(fill==fillModes.no) {
					Scolors=strokeColorModes.white;
				}
			}
		});
		clear.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gc.setFill(Color.WHITE);
				gc.fillRect(0, 0, cmarani.getWidth(), cmarani.getHeight());
				gc.setFill(Color.BLACK);
			}
			
			
			
		});
		saveAs.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				saveF(primaryStage,cmarani);
				
				
			}});
		load.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				loadF(primaryStage,gc,MenuTycinka.getHeight()+tools.getHeight(),cmarani);
			}
			
		});
		brush.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				brush.setStyle("-fx-background-color:#FFFFFF; -fx-border-color:#00BFFF");
				rectangle.setStyle("-fx-background-color:#FFFFFF; -fx-border-color:#D3D3D3");
				shapeModes=shapes.pixel;
				
			}
			
			
		});
		rectangle.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				brush.setStyle("-fx-background-color:#FFFFFF; -fx-border-color:#D3D3D3");
				rectangle.setStyle("-fx-background-color:#FFFFFF; -fx-border-color:#00BFFF");
				switch(shapeModes) {
					case oval:
						shapeModes=shapes.rect;
						//rectangle.setGraphic(new ImageView(imageRect));
						break;
					case rect:
						shapeModes=shapes.oval;
						//rectangle.setGraphic(new ImageView(imageOval));
						break;
					case pixel:
						shapeModes=shapes.rect;
						//rectangle.setGraphic(new ImageView(imageRect));
						break;
				}
			}
			
			
		});
		width.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				switch(line) {
					case thin:
						//width.setGraphic(new ImageView(lineThin));
						gc.setLineWidth(1);
						line=sWidth.tnn;
						break;
					case tnn:
						//width.setGraphic(new ImageView(lineTNN));
						gc.setLineWidth(3);
						line=sWidth.normal;
						break;
					case normal:
						//width.setGraphic(new ImageView(lineNormal));
						gc.setLineWidth(5);
						line=sWidth.tkn;
						break;
					case tkn:
						//width.setGraphic(new ImageView(lineTKN));
						gc.setLineWidth(7);
						line=sWidth.thick;
						break;
					case thick:
						//width.setGraphic(new ImageView(lineThick));
						gc.setLineWidth(10);
						line=sWidth.thin;
						break;
				}
				
			}
			
		});
		fillN.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
			
				if(fill==fillModes.yes) {
					//fillN.setGraphic(new ImageView(imageRect));
					fill=fillModes.no;
				}else if(fill==fillModes.no) {
					//fillN.setGraphic(new ImageView(imageFillYes));
					fill=fillModes.yes;
				}
			}
			
		});
		
		scene.addEventFilter(MouseEvent.MOUSE_PRESSED,new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent nazev) {
				switch(Fcolors) {
				case red:
					gc.setFill(Color.RED);
					break;
				case orange:
					gc.setFill(Color.ORANGE);
					break;
				case green:
					gc.setFill(Color.GREEN);
					break;
				case blue:
					gc.setFill(Color.BLUE);
					break;
				case black:
					gc.setFill(Color.BLACK);
					break;
				case white:
					gc.setFill(Color.WHITE);
					break;
				case gray:
					gc.setFill(Color.GRAY);
					break;
				case cyan:
					gc.setFill(Color.CYAN);
					break;
				case  magenta:
					gc.setFill(Color.MAGENTA);
					break;
				case yellow:
					gc.setFill(Color.YELLOW);
					break;
				case lblue:
					gc.setFill(Color.LIGHTBLUE);
					break;
				case lgray:
					gc.setFill(Color.LIGHTGRAY);
					break;
				case lime:
					gc.setFill(Color.LIME);
					break;
				default:
					break;
				}
				switch(Scolors) {
				case red:
					gc.setStroke(Color.RED);
					break;
				case orange:
					gc.setStroke(Color.ORANGE);
					break;
				case green:
					gc.setStroke(Color.GREEN);
					break;
				case blue:
					gc.setStroke(Color.BLUE);
					break;
				case black:
					gc.setStroke(Color.BLACK);
					break;
				case white:
					gc.setStroke(Color.WHITE);
					break;
				case gray:
					gc.setStroke(Color.GRAY);
					break;
				case cyan:
					gc.setStroke(Color.CYAN);
					break;
				case  magenta:
					gc.setStroke(Color.MAGENTA);
					break;
				case yellow:
					gc.setStroke(Color.YELLOW);
					break;
				case lblue:
					gc.setStroke(Color.LIGHTBLUE);
					break;
				case lgray:
					gc.setStroke(Color.LIGHTGRAY);
					break;
				case lime:
					gc.setStroke(Color.LIME);
					break;
				default:
					break;
				}
			
				if(shapeModes==shapes.pixel) {
				System.out.println(nazev.getSceneX()+" "+nazev.getSceneY());
				gc.fillRect(nazev.getSceneX(),nazev.getSceneY()-MenuTycinka.getHeight()-tools.getHeight(),5,5);
				}else if(shapeModes==shapes.rect) {
					prevX=nazev.getSceneX();
					prevY=nazev.getSceneY()-MenuTycinka.getHeight()-tools.getHeight();
				}else if(shapeModes==shapes.oval) {
					prevX=nazev.getSceneX();
					prevY=nazev.getSceneY()-MenuTycinka.getHeight()-tools.getHeight();
				}
				
				}
			
		});
		scene.addEventFilter(MouseEvent.MOUSE_RELEASED,new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
					
					if(shapeModes==shapes.pixel) {
					}
					if(fill==fillModes.yes) {
						if(shapeModes==shapes.rect) {	
							if((event.getSceneX()>=prevX)&&(event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight()>=prevY)) {										//Z leva nahore do prava dole
								gc.fillRect(prevX, prevY, event.getSceneX()-prevX, event.getSceneY()-prevY-MenuTycinka.getHeight()-tools.getHeight());

							}else if((event.getSceneX()<=prevX)&&(event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight()>=prevY)) {								//Z prava nahore do leva dole
								gc.fillRect(event.getSceneX(), prevY, prevX-event.getSceneX(), event.getSceneY()-prevY-MenuTycinka.getHeight()-tools.getHeight());
							
							}else if((event.getSceneX()<=prevX)&&(event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight()<=prevY)) {								//Z prava dole do leva nahore
								gc.fillRect(event.getSceneX(), event.getSceneY(), prevX-event.getSceneX(), prevY-event.getSceneY());
						
								/*	Debugging
						 	
									System.out.println(prevX+" "+prevY);
									System.out.println(event.getSceneX()+" "+event.getSceneY());
									double xx=prevX-event.getSceneX();
									double yy=prevY-event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight();
									System.out.println(xx+" "+yy+"\n");
								 */
							
							}else if((event.getSceneX()>=prevX)&&(event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight()<=prevY)) {								//Z leva dole do prava nahore
								gc.fillRect(prevX, event.getSceneY(), event.getSceneX()-prevX, prevY-event.getSceneY());
							}
						}
						if(shapeModes==shapes.oval) {
							if((event.getSceneX()>=prevX)&&(event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight()>=prevY)) {										//Z leva nahore do prava dole
								gc.fillOval(prevX, prevY, event.getSceneX()-prevX, event.getSceneY()-prevY-MenuTycinka.getHeight()-tools.getHeight());
							
							}else if((event.getSceneX()<=prevX)&&(event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight()>=prevY)) {								//Z prava nahore do leva dole
								gc.fillOval(event.getSceneX(), prevY, prevX-event.getSceneX(), event.getSceneY()-prevY-MenuTycinka.getHeight()-tools.getHeight());
							
							}else if((event.getSceneX()<=prevX)&&(event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight()<=prevY)) {								//Z prava dole do leva nahore
								gc.fillOval(event.getSceneX(), event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight(), prevX-event.getSceneX(), prevY-event.getSceneY()+MenuTycinka.getHeight()+tools.getHeight());
						
							}else if((event.getSceneX()>=prevX)&&(event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight()<=prevY)) {								//Z leva dole do prava nahore
								gc.fillOval(prevX, event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight(), event.getSceneX()-prevX, prevY-event.getSceneY()+MenuTycinka.getHeight()+tools.getHeight());
							}
						}
					}else if(fill==fillModes.no) {
						if(shapeModes==shapes.rect) {	
							if((event.getSceneX()>=prevX)&&(event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight()>=prevY)) {										//Z leva nahore do prava dole
								gc.strokeRect(prevX, prevY, event.getSceneX()-prevX, event.getSceneY()-prevY-MenuTycinka.getHeight()-tools.getHeight());

							}else if((event.getSceneX()<=prevX)&&(event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight()>=prevY)) {								//Z prava nahore do leva dole
								gc.strokeRect(event.getSceneX(), prevY, prevX-event.getSceneX(), event.getSceneY()-prevY-MenuTycinka.getHeight()-tools.getHeight());
							
							}else if((event.getSceneX()<=prevX)&&(event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight()<=prevY)) {								//Z prava dole do leva nahore
								gc.strokeRect(event.getSceneX(), event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight(), prevX-event.getSceneX(), prevY-event.getSceneY()+MenuTycinka.getHeight()+tools.getHeight());
						
							}else if((event.getSceneX()>=prevX)&&(event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight()<=prevY)) {								//Z leva dole do prava nahore
								gc.strokeRect(prevX, event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight(), event.getSceneX()-prevX, prevY-event.getSceneY()+MenuTycinka.getHeight()+tools.getHeight());
							}
						}
						if(shapeModes==shapes.oval) {
							if((event.getSceneX()>=prevX)&&(event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight()>=prevY)) {										//Z leva nahore do prava dole
								gc.strokeOval(prevX, prevY, event.getSceneX()-prevX, event.getSceneY()-prevY-MenuTycinka.getHeight()-tools.getHeight());
							
							}else if((event.getSceneX()<=prevX)&&(event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight()>=prevY)) {								//Z prava nahore do leva dole
								gc.strokeOval(event.getSceneX(), prevY, prevX-event.getSceneX(), event.getSceneY()-prevY-MenuTycinka.getHeight()-tools.getHeight());
							
							}else if((event.getSceneX()<=prevX)&&(event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight()<=prevY)) {								//Z prava dole do leva nahore
								gc.strokeOval(event.getSceneX(), event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight(), prevX-event.getSceneX(), prevY-event.getSceneY()+MenuTycinka.getHeight()+tools.getHeight());
						
							}else if((event.getSceneX()>=prevX)&&(event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight()<=prevY)) {								//Z leva dole do prava nahore
								gc.strokeOval(prevX, event.getSceneY()-MenuTycinka.getHeight()-tools.getHeight(), event.getSceneX()-prevX, prevY-event.getSceneY()+MenuTycinka.getHeight()+tools.getHeight());
							}
						}
					}
				}
			});
		((VBox)scene.getRoot()).getChildren().addAll(MenuTycinka,tools,scrollP);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
