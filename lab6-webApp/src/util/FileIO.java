package util;

import model.*;
import java.io.*;
import java.util.Properties;
import java.util.StringTokenizer;

import exception.AutoException;

public class FileIO {

	Properties file = new Properties();

	// Read File
	// Do One For Client
	public Properties buildCarProperties(String filename, String fileType) {
		Properties props = new Properties(); //
		Automobile newCar = new Automobile();

		try {
			FileInputStream in = new FileInputStream(filename);
			props.load(in); // This loads the entire file in memory.
		} catch (IOException e) {
			System.out.println("Error нн " + e.toString());
		}

		return props;
	}

	// Do One For Server

	public Automobile parseProperty(Properties props) {
		Automobile newCar = new Automobile();

		String CarMake = props.getProperty("CarMake"); // this is how you read
														// a property. It is
														// like gettting a
														// value from
														// HashTable.

		if (!CarMake.equals(null)) {
			String optionSetSize = props.getProperty("OptionSetSize");
			String CarModel = props.getProperty("CarModel");
			String price = props.getProperty("BasePrice");
			String Option0 = props.getProperty("Option0");
			String OptionValue0a = props.getProperty("OptionValue0a");
			String OptionValue0b = props.getProperty("OptionValue0b");
			// String OptionValue0c = props.getProperty("OptionValue0c");
			String Option1 = props.getProperty("Option1");
			String OptionValue1a = props.getProperty("OptionValue1a");
			String OptionValue1b = props.getProperty("OptionValue1b");
			String Option2 = props.getProperty("Option2");
			String OptionValue2a = props.getProperty("OptionValue2a");
			String OptionValue2b = props.getProperty("OptionValue2b");
			// String OptionValue2c = props.getProperty("OptionValue2c");
			String Option3 = props.getProperty("Option3");
			String OptionValue3a = props.getProperty("OptionValue3a");
			String OptionValue3b = props.getProperty("OptionValue3b");
			String Option4 = props.getProperty("Option4");
			String OptionValue4a = props.getProperty("OptionValue4a");
			String OptionValue4b = props.getProperty("OptionValue4b");

			int basePrice = Integer.parseInt(price);
			int opSetSize = Integer.parseInt(optionSetSize);
			newCar = new Automobile(CarMake, CarModel, basePrice, opSetSize);

			for (int i = 0; i < opSetSize; i++) {
				newCar.setOpSet(i, Integer.toString(i));
			}

			newCar.addNewOption(0, OptionValue0a, priceCheckIndividual(OptionValue0a, 0));
			newCar.addNewOption(0, OptionValue1a, priceCheckIndividual(OptionValue1a, 1));
			newCar.addNewOption(0, OptionValue2a, priceCheckIndividual(OptionValue2a, 2));
			newCar.addNewOption(0, OptionValue3a, priceCheckIndividual(OptionValue3a, 3));
			newCar.addNewOption(0, OptionValue4a, priceCheckIndividual(OptionValue4a, 4));

			newCar.addNewOption(1, OptionValue0b, priceCheckIndividual(OptionValue0b, 0));
			newCar.addNewOption(1, OptionValue1b, priceCheckIndividual(OptionValue1b, 1));
			newCar.addNewOption(1, OptionValue2b, priceCheckIndividual(OptionValue2b, 2));
			newCar.addNewOption(1, OptionValue3b, priceCheckIndividual(OptionValue3b, 3));
			newCar.addNewOption(1, OptionValue4b, priceCheckIndividual(OptionValue4b, 4));
		}
		return newCar;
	}

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
							car = new Automobile("NO MAKER", "NO MODEL", 0, optionSize); // Temporary
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
		Automobile tempCar = new Automobile(makeName, modelName, basePrice, optionSetSize);
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

	public int priceCheckIndividual(String name, int index) {
		int price = 0;
		if (index == 0) {
			price = 0;
		} else if (index == 1) {
			if (name.equalsIgnoreCase("automatic")) {
				price = 0;
			} else if (name.equalsIgnoreCase("standard")) {
				price = -815;
			}
		} else if (index == 2) {

			if (name.equalsIgnoreCase("standard")) {
				price = 0;
			} else if (name.equalsIgnoreCase("abs")) {
				price = 400;
			} else if (name.equalsIgnoreCase("absAT")) {
				price = 1625;
			}
		} else if (index == 3) {
			if (name.equalsIgnoreCase("present")) {
				price = 350;
			} else if (name.equalsIgnoreCase("notpresent")) {
				price = 0;
			}
		} else if (index == 4) {
			if (name.equalsIgnoreCase("present")) {
				price = 595;
			} else if (name.equalsIgnoreCase("notpresent")) {
				price = 0;
			}
		}

		System.out.println("Price Result " + price);
		return price;
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

		return optPrice;
	}

	// Need to fix serializing code, especially deserializing
	// Serialize, copy from old code
	public void serializing(Object car, int index) {
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

	public void serializeReturnClient(Object car, int index) {
		try {
			ObjectOutputStream p1 = new ObjectOutputStream(
					new FileOutputStream("returnCar" + Integer.toString(index) + ".dat"));
			p1.writeObject(car);
			p1.close();

		} catch (Exception e) {
			System.out.println("Error -- " + e.toString());
		}
		;
	}


	// Deserialize, copy from old code
	public Properties deserializing(int index) {
		Properties loadedFile = new Properties();

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("car" + Integer.toString(index) + ".dat"));
			Properties newCar = (Properties) in.readObject();
			loadedFile = newCar;
		} catch (Exception e) {
			System.out.println("Error -- " + e.toString());
		}
		return loadedFile;
	}
	
	public Automobile deserializingReturnCar(int index) {
		Automobile loadedFile = new Automobile();

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("returnCar" + Integer.toString(index) + ".dat"));
			Automobile newCar = (Automobile) in.readObject();
			loadedFile = newCar;
		} catch (Exception e) {
			System.out.println("Error -- " + e.toString());
		}
		return loadedFile;
	}
}

// Create William Vuong
