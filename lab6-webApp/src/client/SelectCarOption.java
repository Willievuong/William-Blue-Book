package client;

import java.net.*;
import java.io.*;
import server.*;
import util.FileIO;
import adapter.*;
import model.Automobile;
import scale.Editable;

public class SelectCarOption {
	private static final int CONFIGWAIT = 0; 
	private static final int CONFIGSENT = 1;

	private int state = CONFIGWAIT;
	
	public SelectCarOption(){}
	
	public String processConfiguration(){
		String output = "process";
		if (state == CONFIGWAIT ){
			System.out.println("ClientMod: Please type in 'printAll' to display all available models");
			state = CONFIGSENT;
		} else if (state == CONFIGSENT){
			System.out.println("ClientMod: Please select a model to be modified by entering its respective index number");
		//	state = CONFIGRECEIVE;
		//} else if (state == CONFIGRECEIVE){
		//	System.out.println("Client Mod: Please select an option to be modified");
			output = "deserialize";
		} 
	
		return output; 
	}
	
	public String deSerializeReturnCar(int key){
		FileIO deserializer = new FileIO(); 
		String output = "userConfig";
		UpdateAuto loader = new BuildAuto();
		loader.stageAuto(deserializer.deserializingReturnCar(key));
	
		return output; 
	}
	
	public void Config(){
		Editable edits = new BuildAuto(); 
		CreateAuto printer = new BuildAuto();
		edits.edit();
		printer.printAuto();
		
	}
	
	public Automobile findCar(String carModel){
		Automobile lostCar = new Automobile();
		CreateAuto carFinder = new BuildAuto();
		
		lostCar = carFinder.carFinder(carModel);
		
		
		return lostCar;
	}
}
