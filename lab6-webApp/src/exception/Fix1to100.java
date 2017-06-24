package exception;

import java.io.IOException;
import java.io.PrintWriter;

public class Fix1to100 {

	//Constructor
	public Fix1to100() {}

	//Fix Methods
	//Not Initializing Correctly (AUTOMOTIVE CLASS)
	void fix0(int errorno) {
		System.out.println("Need to Initialize Options With A Size");
	}

	//Error Occuring at Options (AUTOMOTIVE CLASS)
	void fix1(int errorno) {
		System.out.println("Error Occuring at Options in Automobile Class");
	}
	
	//Unable to Find Model (AUTOMOTIVE CLASS)
	void fix2(int errorno) {
		System.out.println("Unable to Find Value For Model");
	}

	//Unable to print (AUTOMOTIVE CLASS)
	void fix3(int errorno) {
		System.out.println("Unable to print all values");
	}

	//Building Car Error
	void fix4(int errorno) {
		System.out.println("Temporary Fix Has Occured to The Car");
	}
	
	void fix5(int errorno){
		System.out.println("Proof of Concept That The Error Management System Works");
	}
	
	public void log(int errorno){
		try{
		    PrintWriter log = new PrintWriter("ExceptionLog.txt", "UTF-8");
		    log.println("Exception No: " + errorno);
		    log.println("At: " + System.currentTimeMillis());
		    log.close();
		} catch (IOException e) {
		   System.out.println("ERROR FROM WRITING LOG");
		}
	}

}
