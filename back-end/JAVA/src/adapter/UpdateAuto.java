package adapter;

import model.Automobile;

public interface UpdateAuto {
	public void updateOptionSetName(String Modelname, String OptionSetname, String newName);
	
	public void stageAuto(Automobile loadedCar); 
}
