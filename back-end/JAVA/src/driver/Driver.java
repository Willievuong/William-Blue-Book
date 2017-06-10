package driver;

import java.io.*;
import java.util.LinkedHashMap;
import model.*;
import util.*;
import adapter.*;
import scale.*; 
import exception.*; 


/*
 	Instantiation of BuildAuto
  	Create two threads using EditOptions, which will modify the same models as LinkedHashmap
  	instance of Automobile in ProxyAutomotive class instance
  	Test your implementation to ensure that two threads altering same property do not cause data corruption
  	*/
public class Driver {
	/*public static void main(String[] args) {
		
		//"C:\\Users\\willievuong\\OneDrive\\Education\\2016 - 2017\\Spring 2017\\CIS35B\\Assignment 1\\automotiveModels3.txt"
		
		//Initialize Public Interface
		CreateAuto car = new BuildAuto();
		int iterator = 0; 

		//Two Threads
		car.BuildAuto("C:\\Users\\willievuong\\OneDrive\\Education\\2016 - 2017\\Spring 2017\\CIS35B\\Assignment 4\\automotiveModels1.txt", iterator);
		//car.printAuto("FocusWagonZTW");

		Editable edits = new BuildAuto(); 
		EditOptions editThread = new EditOptions("T1: ", edits); 
		EditOptions editThread2 = new EditOptions("T2: ", edits);
		
		//Use The Editor to Run The Threads 
		editThread.run();
		editThread2.run();
		
		car.printAuto("FocusWagonZTW");
	}*/
}