package model;

import java.io.Serializable;
import java.util.ArrayList;

import exception.AutoException;

public class OptionSet implements Serializable {
	private ArrayList<Option> optionList = new ArrayList<Option>();
	private String name = "empty";

	OptionSet() {
	}

	OptionSet(String n, int size) {
		optionList = new ArrayList<Option>(size);
		name = n;

	}

	public ArrayList<Option> getOpt() {
		return optionList;
	}

	public void addNewOpt(String newName, int newPrice) throws AutoException {

		Option temp = new Option();
		temp.setName(newName);
		temp.setPrice(newPrice);
		optionList.add(temp);
	}
	
	public void setOption(int location, String newName, int newPrice){
		optionList.get(location).setName(newName);
		optionList.get(location).setPrice(newPrice);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void initializeOptions(int optionSize) throws AutoException {
		// Not Necessary, already initializing when building the option
	}

	public void print() {
		for (int i = 0; i < optionList.size(); i++) {
			System.out.println("Opt Name: " + optionList.get(i).getName());
			System.out.println("Opt Price: " + optionList.get(i).getPrice());
		}
	}
}
