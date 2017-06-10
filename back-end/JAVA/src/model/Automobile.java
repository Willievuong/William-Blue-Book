package model;

import java.io.Serializable;
import java.util.ArrayList;
import exception.*;

//Make a LinkedHashMap static and then make calls to it from Auto

public class Automobile implements Serializable {

	// Name, BasePrice, OptionSet
	private String make = "";
	private String model = "";
	private int basePrice = 0;
	private ArrayList<OptionSet> opSetList = new ArrayList<OptionSet>();
	private ArrayList<Option> Choice = new ArrayList<Option>();
	private OptionSet opSet[]; // I won't need this anymore.

	// Constructor, Overloaded Constructor
	public Automobile() {
	}

	public Automobile(String newName, String newModel, int newPrice, int newOpSetSize, int optionSize) {
		this.make = newName;
		this.model = newModel;
		this.basePrice = newPrice;
		this.opSetList = new ArrayList<OptionSet>(newOpSetSize);

		// map.put(newModel,getClass());
		OptionSet x[] = new OptionSet[newOpSetSize];
		try {
			for (int i = 0; i < newOpSetSize; i++) {
				x[i] = new OptionSet();
				opSetList.add(x[i]);
				opSetList.get(i).initializeOptions(optionSize);
			}
		} catch (AutoException e) {
			e.fix(0);
		}
	}

	// Getter
	public String getName() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getBasePrice() {
		return basePrice;
	}

	// Return the OptionSet Size
	public int getOpSetSize() {
		return opSetList.size();
	}

	// Need Fixing?
	public OptionSet[] getOpSet() {
		return opSet;
	}

	// Setters
	public synchronized void setMakeName(String name) {
		this.make = name;
	}

	public synchronized void setModel(String model) {
		this.model = model;
	}

	public synchronized void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}

	public synchronized void setOpSet(int opSetPosition, String Optname) { 
		this.opSetList.get(opSetPosition).setName(Optname);
	}

	// Set values of option in context of opset
	public synchronized void addNewOption(int opSetPosition, String name, int price) {
		try {
			this.opSetList.get(opSetPosition).addNewOpt(name, price);
		} catch (AutoException e) {
			e.fix(1);
		}
	}

	public synchronized void setOption(int opSetPosition, int OptPosition, String name, int price) {
		this.opSetList.get(opSetPosition).setOption(OptPosition, name, price);
	}

	// Find: Look through opSet to find the model name
	public OptionSet findOptionSet(String OptionSetName) throws AutoException {
		int index = 0;

		for (int i = 0; i < opSetList.size(); i++) {
			if (opSetList.get(i).getName().equals(OptionSetName)) {
				index = i;
			} else {
				index = -1;
			}
		}

		return opSetList.get(index);
	}

	public OptionSet findOption(String OptionName) throws AutoException {
		int index = 0;
		int i = 0;
		// (int i = 0; i < opSetList.size(); i++)
		do {
			ArrayList<Option> temp = opSetList.get(i).getOpt();
			for (int x = 0; x < opSetList.get(i).getOpt().size(); i++) {
				if (temp.get(x).getName().equals(OptionName)) {
					index = x;
				} else {
					index = -1;
				}
			}
			i++;
		} while (!(index == 0));

		return opSetList.get(index);
	}

	// Set Option Choice
	public synchronized void setOptionChoice(String setName, String optionName) {
		OptionSet choiceSet = new OptionSet();
		Option userChoice = new Option();
		try {
			choiceSet = findOption(optionName);
		} catch (AutoException e) {
			System.out.println("Option is not found");
		}
		for (int i = 0; i < choiceSet.getOpt().size(); i++) {
			userChoice = choiceSet.getOpt().get(i);
		}
		Choice.add(userChoice);
	}

	public int getSetNameIndex(String setName) {
		int index = 0;
		if (setName.equals("PAINT"))
			index = 0;
		if (setName.equals("TRANSMISSION"))
			index = 1;
		if (setName.equals("BRAKES"))
			index = 2;
		if (setName.equals("AIRBAGS"))
			index = 3;
		if (setName.equals("POWERROOF"))
			index = 4;
		return index;
	}

	// Get Option Price
	public String GetOptionChoice(String setName) {
		OptionSet getChoice = new OptionSet();
		try {
			getChoice = findOptionSet(setName);
		} catch (AutoException e) {
			System.out.println("Choice Option Not Found");
		}
		String choiceString = "";
		String space = " ";

		for (int i = 0; i < getChoice.getOpt().size(); i++) {
			choiceString = choiceString + getChoice.getOpt().get(i).getName() + space;
		}

		return choiceString;
	}

	public int getOptionChoicePrice(String setName) {
		int price = 0;
		try {
			OptionSet getChoice = findOptionSet(setName);
			for (int i = 0; i < getChoice.getOpt().size(); i++) {
				price = price + getChoice.getOpt().get(i).getPrice();
			}

		} catch (AutoException e) {
			System.out.println("Choice Option Not Found");
		}
		return price;
	}

	public synchronized void updateOptionSet(OptionSet optionSet, String newName) {
		optionSet.setName(newName);
	}

	// Get Total Price
	// Run through all the options and add up all the prices
	public int getTotalPrice() {
		int total = 0;

		for (int i = 0; i < Choice.size(); i++) {
			total = total + Choice.get(i).getPrice();
		}

		return total;
	}

	// Print
	public void print() throws AutoException {
		System.out.println("Model Name:" + make);
		System.out.println("Maker Name:" + model);
		System.out.println("Base Price: " + basePrice);
		for (int i = 0; i < opSetList.size(); i++) {
			System.out.println("OptionSet # " + opSetList.get(i).getName());
			opSetList.get(i).print();
		}
	}

	public void shortPrint() {
		System.out.println("Model Name:" + make);
		System.out.println("Maker Name:" + model);
		System.out.println("Base Price: " + basePrice);
	}

	public void printOptionSetProperties(OptionSet model) {
		System.out.println("OptionSet # " + model.getName());
		for (int i = 0; i < model.getOpt().size(); i++) {
			model.print();
		}

	}
}
