package scale;

import adapter.ProxyAutomobile;
import exception.AutoException;

import java.lang.Thread;
import java.util.Scanner;
import model.*;

/*	Used to edit Options for a given model in its own thread 
 	Be sure to use the LinkedHashMap instance of Automobile
 	Consider synchronizing releveant methods for object locking
 */

//Use Priority, Queue, Synchronization, and Avoiding DeadLocks 
//
public class EditOptions extends ProxyAutomobile implements Runnable {
	// private int threadNumber;
	// private static int threadCount = 0;
	private Thread t;
	private boolean running = true;
	Editable editing; 

	// Constructor
	// public EditOptions(){}

	public EditOptions(String threadName, Editable edits) {
		//this.car = hashKey;
		t = new Thread(threadName);
		editing = edits;
		t.start();
	}

	// Edit Options within an OptionSet
	public void run() {
		// A While Loop? I need to use it to edit options
		try {
			while (running) {
				String temp = " ";
				System.out.println("Executing " + t.getName());
				editing.edit();
				t.sleep(1000);
				stop();
			}

		} catch (InterruptedException e) {
		}
	}

	public void stop() {
		running = false;
	}


}
