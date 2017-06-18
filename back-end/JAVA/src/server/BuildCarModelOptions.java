package server;

import java.util.Properties;
import adapter.*;
import model.Automobile;
import util.FileIO;

/* 
 	a. Accept properties object from client socket over an ObjectStream and create an Automobile.
	b. Then add that created Automobile to the LinkedHashMap. This method will be declared in the AutoServer interface.
	c. AutoServer interface should be implemented in BuildAuto and BuildCarModelOptions classes.
	d. Based on the current structure, this method will be implemented in proxyAutomobile class and called in a method of BuildCarModelOptions.*/

public class BuildCarModelOptions extends ProxyAutomobile implements AutoServer {
	private static final int WAITING = 0;
	private static final int PROPFILEINPUT = 1;
	private static final int BUILDSUCCESS = 2;

	private int state = WAITING;

	private FileIO reader = new FileIO();
	private int iterator = 0;

	BuildCarModelOptions() {
	}

	public String processInput(String input) {
		String output = " ";
		int numOfFiles = 0;
		Properties clientProp = new Properties();
		AutoServer serverProcessor = new BuildAuto();
		if (input != null)
			if (state == WAITING) {
				numOfFiles = Integer.parseInt(input);
				System.out.println("We detected that there is " + numOfFiles + " files");
				state = PROPFILEINPUT;
			}
		if (state == PROPFILEINPUT) {
			for (int i = 0; i < numOfFiles; i++) {
				clientProp = deserializeBuild(i);
				serverProcessor.BuildAutoProperties(clientProp, i);
			}
			System.out.println("Building...");
			state = BUILDSUCCESS;
		}

		if (state == BUILDSUCCESS) {
			output = "Successfully built automobile objects";
			state = CONFIGWAIT;
		}

		return output;
	}
	
	public Properties deserializeBuild(int index) {
		Properties clientFile = new Properties();

		clientFile = reader.deserializing(index);

		return clientFile;
	}
	
	private static final int CONFIGWAIT = 5; 


	//TO BE CHANGED
	public String processConfiguration(String input){
		String output = null; 
		AutoServer server = new BuildAuto(); 
		
		if (state == CONFIGWAIT ){
			output = "We received your request, serializing model " + input;
			server.serializeSelectedCar(Integer.parseInt(input));
		}
	
		return output; 
	}
}
