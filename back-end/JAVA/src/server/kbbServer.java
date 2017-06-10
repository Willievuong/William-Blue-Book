package server;

import java.net.*;
import java.io.*;
import client.*; 

public class kbbServer {

	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = null;
		
		try{
			serverSocket = new ServerSocket(4444);
		}catch(IOException e){
			System.err.println("Could not listen on the indicated port");
			System.exit(1);
		}

		Socket clientSocket = null;
		try{
			clientSocket = serverSocket.accept();
		}catch (IOException e){
			System.err.println("Accept failed");
			System.exit(1);
		}
		
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String inputLine, outputLine; 
		kbbProtocol kbb = new kbbProtocol(); 
		
		outputLine = kbb.processInput(null);
		out.println(outputLine);
		
		while((inputLine = in.readLine()) != null){
			outputLine = kbb.processInput(inputLine);
			out.println(outputLine); 
			if(outputLine.equals("quit"))
				break; 
		}
		out.close();
		in.close();
		clientSocket.close(); 
		serverSocket.close();
	}

}
