package adapter;

import java.util.ArrayList;

import model.Automobile;

public interface CreateAuto {
	public void BuildAuto(String fileName, int iterator); 
	
	public ArrayList<String>modelList();
	
	public Automobile getKey(int key);
	
	public Automobile carFinder(String model);
	
	public void printAuto(); 
}
