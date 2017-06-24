package server;

import java.util.Properties;

public interface AutoServer {

	public void BuildAutoProperties(Properties clientProp, int iterator);

	public String printAllModel(); 
	
	public void printAuto();
	
	public void serializeSelectedCar(int key); 
}
