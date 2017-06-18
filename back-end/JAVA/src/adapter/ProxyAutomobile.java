package adapter;

import model.*;
import java.lang.Thread; 
import scale.EditOptions;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Scanner;

import exception.*;
import util.FileIO;

public abstract class ProxyAutomobile {
	private static Automobile a1;
	private AutoException exceptionHandler = new AutoException();
	public static LinkedHashMap<Integer, Automobile> map = new LinkedHashMap<Integer, Automobile>();
	
	public void BuildAuto(String fileName, int iterator) {
		FileIO input = new FileIO();
		Automobile car = new Automobile();
		car = input.BuildCar(fileName);
		a1 = car;
		map.put(iterator, a1);
	}
	
	public void BuildAutoProperties(Properties clientProp, int iterator){
		FileIO Input = new FileIO();
		Automobile car = new Automobile();
		car = Input.parseProperty(clientProp);
		//Use the properties to build a car
		a1 = car;
		map.put(iterator, a1);
	}
	
	public Automobile getKey(int iteratorPosition){
		return map.get(iteratorPosition);
	}
	
	public void stageAuto(Automobile loadedCar){
		a1 = loadedCar; 
	}
	
	public void updateOptionSetName(String ModelName, String OptionSetname, String newName) {
		// Searches for the model in a given OptionSet and sets the name of
		// OptionSet to newName
		if (a1.getName().equals(ModelName)) {
			try {
				a1.updateOptionSet(a1.findOptionSet(OptionSetname), newName);
			} catch (AutoException e) {
				e.fix(2);
			}
		}
	}
	
	public void edit(){
		String name = "";
		int price = 0;
		int opSetIndex = 0;
		int optionIndex = 0;
		opSetIndex = userInputOperation(0);
		optionIndex = userInputOperation(1);
		name = userInputOption(); 
		price = userInputOperation(2);
		update(opSetIndex, optionIndex, name, price);
	}

	public void update(int opSetIndex, int optionIndex, String name, int price) {
		a1.setOption(opSetIndex, optionIndex, name, price);
	}

	public String userInputOption() {
		Scanner userInput = new Scanner(System.in);
		String input = "";

		System.out.println("Enter a Name");
		input = userInput.next();

		return input;
	}

	public int userInputOperation(int index) {
		Scanner userInput = new Scanner(System.in);
		int selection = 0;
		int price = 0;

		if (index == 0)
			printOptionSetOperation();
		if (index == 1)
			printOptionOperation();
		if (index == 2)
			System.out.println("Enter a Price");
		selection = userInput.nextInt();

		return selection;
	}

	public void printOptionSetOperation() {
		System.out.println("Select an Operation");
		System.out.println("OptionSet");
		System.out.println("#0\n#1\n");
	}

	public void printOptionOperation() {
		System.out.println("Index \t\tOperation");
		System.out.println("0 \t\t  Edit Paint ");
		System.out.println("1 \t\t  Edit Transmission");
		System.out.println("2 \t\t  Edit Break");
		System.out.println("3 \t\t  Edit SideAirBag");
		System.out.println("4 \t\t  Edit SunRoof");
	}

	public void printAuto() {
			try {
				a1.print();
			} catch (AutoException e) {
				e.fix(3);
			}
	}

	public String printAllModel(){
		Automobile car = new Automobile(); 
		String modelName = ""; 
		String allModel = ""; 
		for(int i = 0; i < map.size(); i++){
			car = map.get(i);
			modelName = car.getModel();
			allModel = allModel + Integer.toString(i) + ". " + modelName + " "; 
			//System.out.println(modelName);
		}
		
		return allModel; 
	}
	
	public void serializeSelectedCar(int key){
		FileIO serializer = new FileIO(); 
		
		serializer.serializeReturnClient(map.get(key),key);
		
	}
	public void fix(int errorno) {
		exceptionHandler.fix(errorno);
	}

}
