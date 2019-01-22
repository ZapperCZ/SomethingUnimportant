//package com.crunchify.tutorials;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
//import org.json.*;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class Main extends Application {
	

	
	
	static String loadJSON=null;
	static List <velicina>veliciny;
	
private static void extract() {
	veliciny=new ArrayList<velicina>();
	JSONObject obj;
	try {
		File file=new File("C:\\Users\\janfr\\eclipse-workspace\\UnitConverter2\\bin\\config.json");
		FileInputStream load=new FileInputStream(file);
		byte[]obsah=new byte[(int)file.length()];			
		load.read(obsah);									
		load.close();										
		loadJSON=new String(obsah);
		//System.out.println(loadJSON);
		
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	
	
	
	
	
//	System.out.println(loadJSON);
		try {
		obj = new JSONObject(loadJSON);
		Iterator<String> keys = obj.keys();
	
		
		while(keys.hasNext()) {
		    String key = keys.next();
		    //System.out.println(key);
		    if (obj.get(key) instanceof JSONObject) {
		    	JSONObject subObj= obj.getJSONObject(key);		 
	    		Iterator<String> deeper=subObj.keys();
	    		while(deeper.hasNext()) {
	    			String dKey=deeper.next();										//Nalezeni nove veliciny
	    			velicina v1=new velicina(dKey.toString(),"");
	    			//System.out.println(dKey.toString());
	    			JSONArray jArray = subObj.getJSONArray(dKey);
	    			for (int i = 0; i < jArray.length(); i++) {
	    				JSONObject subSubObj =(JSONObject) jArray.get(i);
	    				Unit u1=new Unit(subSubObj.get("name").toString(),subSubObj.get("shortcut").toString(),"",Double.parseDouble(subSubObj.get("conversion").toString()));
	    				if(Double.parseDouble(subSubObj.get("conversion").toString())==1.0) {
	    					v1.setBasicUnit(u1);
	    				}
	    				v1.addUnit(u1);
	    				//System.out.println(subSubObj.get("shortcut"));
	    				//System.out.println(subSubObj.get("name"));
	    				//System.out.println(subSubObj.get("basicUnit"));
	    				//System.out.println(subSubObj.get("conversion"));
	    				
	    				}
	    			
	    	
	    			
	    			veliciny.add(v1);
	    		}
		    		
		    }
		} 
		
		

		
		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
}
public static void dump() {
	int d1=0;
	for(d1=0;d1<veliciny.size();d1++) {
		System.out.println(veliciny.get(d1).name);
		System.out.println(veliciny.get(d1).basic.name);
		for(int d2=0;d2<veliciny.get(d1).units.size();d2++) {
			System.out.println(veliciny.get(d1).units.get(d2).name);
		}
	}
	
}
public static double conv(double input,String from, String to, String velicina) {
	double vysledek=0;
	for(int i1=0;i1<veliciny.size();i1++) {
		if(veliciny.get(i1).name.equals(velicina)) {
			for(int i2=0;i2<veliciny.get(i1).units.size();i2++) {
				if(veliciny.get(i1).units.get(i2).shortcut.equals(from)) {
					vysledek=veliciny.get(i1).units.get(i2).conversion*input;
				}
			}
			for(int i2=0;i2<veliciny.get(i1).units.size();i2++) {
				if(veliciny.get(i1).units.get(i2).shortcut.equals(to)) {
					vysledek=vysledek/veliciny.get(i1).units.get(i2).conversion;
				} 
			}
		}
	}
	
	return vysledek;
	
}
public static void main(String[] args){
	
	extract();
	launch(args);
	/*dump();
	double test=conv(1,"pc","m","Length");
	System.out.println(test);
	*/
}
@Override
public void start(Stage primaryStage) throws Exception {
	// TODO Auto-generated method stub
	double rw=400;
	double rh=150;
	


	primaryStage.setTitle("Pøevodník Jednotek");
	VBox vb=new VBox();
	Scene scene=new Scene(vb,rw,rh);
	
	ChoiceBox vel = new ChoiceBox();
	int d1=0;
	for(d1=0;d1<veliciny.size();d1++) {
		vel.getItems().add(veliciny.get(d1).name);
		}
	vel.getItems().add("brambora");
	vel.getSelectionModel().selectFirst();
	
	vel.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
		  @Override
		  public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
		    System.out.println(vel.getItems().get((Integer) number2));
		  }
		});
	StackPane sp=new StackPane();
	sp.getChildren().addAll(vel);
	sp.setAlignment(Pos.BASELINE_CENTER);
	((VBox)scene.getRoot()).getChildren().addAll(sp);
	primaryStage.setScene(scene);
	primaryStage.show();
	
}
}
