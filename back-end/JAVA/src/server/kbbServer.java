package server;

import java.net.*;
import java.util.Properties;

import adapter.BuildAuto;

import java.io.*;
import client.*;

public class kbbServer {

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = null;
		AutoServer server = new BuildAuto();

		try {
			serverSocket = new ServerSocket(5555);
		} catch (IOException e) {
			System.err.println("Could not listen on the indicated port");
			System.exit(1);
		}

		Socket clientSocket = null;
		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed");
			System.exit(1);
		}

		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String inputLine, outputLine;
		boolean outRequest = true;
		boolean outRequest2 = false;
		boolean serializeRequest = false; 

		// Instance of BuildCarModelOption using Properties File
		BuildCarModelOptions kbb = new BuildCarModelOptions();

		// outputLine = "Properties Accepted";
		// out.println(outputLine);

		// Initial Adding Files
		while ((inputLine = in.readLine()) != null) {

			System.out.println("User: " + inputLine);

			if(outRequest2 == true){
				out.println("You entered " + inputLine);
			}
			
			if (outRequest == true) {
				outputLine = kbb.processInput(inputLine);
				out.println(outputLine);
				outRequest = false;
			}

			// if(outputLine.equalsIgnoreCase("Successfully built automobile
			// objects"))
			// break;

			if (inputLine.equalsIgnoreCase("quit"))
				break;

			if (inputLine.equalsIgnoreCase("carConfig Stage")) {
				out.println("Now loading carConfig Operation");
			}

			if(serializeRequest == true){
				out.println(kbb.processConfiguration(inputLine));
				outRequest = true;
			}
			
			if (inputLine.equalsIgnoreCase("printAll")){
				out.println(server.printAllModel());
				serializeRequest = true; 
			}
			
		}

		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();
	}

}
