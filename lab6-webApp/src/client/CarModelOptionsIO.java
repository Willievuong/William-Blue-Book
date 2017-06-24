package client;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import adapter.*;
import model.Automobile;
import server.AutoServer;
import util.FileIO;

/*	a. Read data from the Properties file; create properties object, using the load 
 * 	method, which transfers the object from the client to server, using ObjectStream.
	b. Receive a response from the Server, verifying that the Car Model object is
	created successfully.
	c. Use CreateAuto interface to build Automobile and handle different type of files,
	passed in fileType.*/

public class CarModelOptionsIO {
	private static final int WAITING = 0;
	private static final int FILENAMEINPUT = 1;
	private static final int FILETYPEINPUT = 2;
	private static final int ITERATORINPUT = 3;
	private static final int ANOTHERINPUT = 4;

	private int state = WAITING;

	private Properties clientProp = new Properties();
	private FileIO clientFileIO = new FileIO();

	private String fileLocation = null;
	private String fileType = null;
	private FileIO reader = new FileIO();
	private int iterator = 0;

	public CarModelOptionsIO() {
	}

	public Properties clientProptoServer(String fileName, String fileType) {
		clientProp = clientFileIO.buildCarProperties(fileName, fileType);

		return clientProp;
	}

	public int processClient() {
		String clientOutput = null;
		String repeat = "Y";
		int numberofFiles = 0;
		Scanner input = new Scanner(System.in);

		while (repeat.equalsIgnoreCase("Y")) {
			if (state == WAITING) {
				System.out.println("ClientMod: Hello, welcome to Willam's Blue Book");
				state = FILENAMEINPUT;
			}
			if (state == FILENAMEINPUT) {
				System.out.println("ClientMod: Please enter the file location");
				fileLocation = input.next();
				state = FILETYPEINPUT;
			}
			if (state == FILETYPEINPUT) {
				System.out.println("ClientMod: Please enter the file type");
				fileType = input.next();
				clientProptoServer(fileLocation, fileType);
				state = ITERATORINPUT;
			}
			if (state == ITERATORINPUT) {
				iterator = numberofFiles;
				serialize();
				numberofFiles++;
				state = ANOTHERINPUT;
			}
			if (state == ANOTHERINPUT){
				System.out.println("ClientMod: Would you like to enter another file? (Y/N)");
				repeat = input.next();
				if (repeat.equalsIgnoreCase("Y")) {
					state = WAITING;
				} else if (repeat.equalsIgnoreCase("N")) {
					break;
				} else {
					System.out.println("ClientMod: Please enter Y or N");
				}
			}
		}

		return numberofFiles;
	}

	public void serialize() {
		clientFileIO.serializing(clientProp, iterator);
	}

	public ArrayList<String> getAutoList(){
		CreateAuto carMaker = new BuildAuto(); 
		carMaker.BuildAuto("C:\\Users\\willievuong\\OneDrive\\automotiveModels1.txt", 0);
		carMaker.BuildAuto("C:\\Users\\willievuong\\OneDrive\\automotiveModels2.txt", 1);
		
		ArrayList<String> list = carMaker.modelList();
		
		
		return list;
	}

}
