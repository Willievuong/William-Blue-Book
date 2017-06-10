package util;

import model.*;
import java.io.*;
import java.util.StringTokenizer;

import exception.AutoException;

public class FileIO {

	// Read File
	public Automobile BuildCar(String fname) {
		Automobile car = new Automobile();
		int counter = 0;
		int optionSize = 0;
		try {
			FileReader file = new FileReader(fname);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			while (!eof) {
				String line = buff.readLine();
				counter++;
				// System.out.println(counter);
				if (line == null) {
					eof = true;
					// System.out.println(eof);
				} else {
					if (counter == 1) {
						optionSize = Integer.parseInt(line);
					}
					if (counter == 2) {
						// Tokenize the string
						// Receive back the car object
						try {
							car = buildCar(line, car, optionSize);
						} catch (AutoException e) {
							e.fix(4);
							car = new Automobile("NO MAKER", "NO MODEL", 0, optionSize, optionSize); // Temporary
																										// Fix
						}
					}
					if (counter > 2) {
						// Tokenize the string
						// Receive back an array of options
						car = buildOpSet(line, counter, car, optionSize);
					}
				}
			}
			buff.close();
		} catch (IOException e) {
			System.out.println("Error нн " + e.toString());
		}
		// return automotive
		return car;
	}

	public Automobile buildCar(String temp, Automobile car, int optionSize) throws AutoException {

		String makeName = "";
		String modelName = "";
		int basePrice = 0;
		int optionSetSize = 0;

		String delim = "[ ]+";
		String[] st = temp.split(delim);

		for (int i = 0; i < 4; i++) {
			if (i == 0)
				makeName = st[i];
			if (i == 1)
				modelName = st[i];
			if (i == 2)
				basePrice = Integer.parseInt(st[i]);
			if (i == 3)
				optionSetSize = Integer.parseInt(st[i]);
		}
		Automobile tempCar = new Automobile(makeName, modelName, basePrice, optionSetSize, optionSize);
		return tempCar;
	}

	public Automobile buildOpSet(String temp, int optionIndex, Automobile car, int optionSize) {

		String delim = "[ ]+";
		String[] tokenz = temp.split(delim);
		int[] priceChecker = priceCheck(tokenz, optionSize);

	
		if (optionIndex - 3 < car.getOpSetSize()) {
			car.setOpSet(optionIndex - 3, Integer.toString(optionIndex - 3));
			for (int i = 0; i < tokenz.length; i++) {
				car.addNewOption(optionIndex - 3, tokenz[i], priceChecker[i]);
			}
		}

		return car;
	}

	public int[] priceCheck(String[] token, int optionSize) {
		int[] optPrice = new int[optionSize];
		// System.out.println("Token Length " + token.length);
		// System.out.println(token[2]);
		for (int i = 0; i < token.length; i++) {
			if (i == 0) {
				optPrice[i] = 0;
				// System.out.println("EXECUTED");
			} else if (i == 1) {
				if (token[i].equals("automatic")) {
					optPrice[i] = 0;
					// System.out.println("EXECUTED");
				} else if (token[i].equals("standard")) {
					optPrice[i] = -815;
				}
			} else if (i == 2) {

				if (token[i].equals("standard")) {
					optPrice[i] = 0;
				} else if (token[i].equals("abs")) {
					optPrice[i] = 400;
				} else if (token[i].equals("absAT")) {
					optPrice[i] = 1625;
				}
			} else if (i == 3) {
				if (token[i].equals("present")) {
					optPrice[i] = 350;
				} else if (token[i].equals("notpresent")) {
					optPrice[i] = 0;
				}
			} else if (i == 4) {
				if (token[i].equals("present")) {
					optPrice[i] = 595;
				} else if (token[i].equals("notpresent")) {
					optPrice[i] = 0;
				}
			}

		}

		// System.out.println("PRINTING OUT PRICE VALUES");
		// for(int x = 0; x < optPrice.length; x++){
		// System.out.println(optPrice[x]);
		// }

		return optPrice;
	}

	// Need to fix serializing code, especially deserializing
	// Serialize, copy from old code
	public void serializing(Automobile car, int index) {
		try {
			ObjectOutputStream p1 = new ObjectOutputStream(
					new FileOutputStream("car" + Integer.toString(index) + ".dat"));
			p1.writeObject(car);
			p1.close();

		} catch (Exception e) {
			System.out.println("Error -- " + e.toString());
		}
		;
	}

	// Deserialize, copy from old code
	public void deserializing(Automobile car, int index) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("car" + Integer.toString(index) + ".dat"));
			Automobile newCar = (Automobile) in.readObject();
			System.out.println("\n--AFTER SERIALIZATION--");
			newCar.print();
		} catch (Exception e) {
			System.out.println("Error -- " + e.toString());
		}
	}
}

// Create William Vuong
