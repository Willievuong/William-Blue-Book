package client;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class DefaultSocketClient {

	public static void main(String[] args) throws IOException {
		Socket kbbSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			kbbSocket = new Socket("192.168.1.86", 5555);
			out = new PrintWriter(kbbSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(kbbSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Dont know about host");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection: ");
			System.exit(1);
		}

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String fromServer = null;
		String fromUser = null;
		String output;
		int numOfFiles = 0;
		String stopRequest = "Y";
		boolean enterRequest = true;  
		SelectCarOption carConfig = new SelectCarOption();
		CarModelOptionsIO cts = new CarModelOptionsIO();

		numOfFiles = cts.processClient();
		fromUser = Integer.toString(numOfFiles);

		System.out.println("Client: I have sent " + fromUser + " Properties");
		out.println(fromUser);

		// Initial File Input
		while ((fromServer = in.readLine()) != null) {

			System.out.println("Server: " + fromServer);
			if (fromServer.equals("Bye."))
				break;

			if (!(fromServer.equalsIgnoreCase("Properties"))) {
				if (fromServer.equalsIgnoreCase("Successfully built automobile objects")){
					stopRequest = "process";
					//out.println("carConfig Stage");
					enterRequest = false; 
				}

				if(enterRequest == true)
				fromUser = stdIn.readLine();

				if (stopRequest.equalsIgnoreCase("process")) {
					stopRequest = carConfig.processConfiguration();
					fromUser = stdIn.readLine();
				}

				if (fromUser != null) {
					System.out.println("Client: " + fromUser);
					out.println(fromUser);
				}
				
				if (stopRequest.equalsIgnoreCase("deserialize")) {
					stopRequest = carConfig.deSerializeReturnCar(Integer.parseInt(fromUser));
				}

				if (stopRequest.equalsIgnoreCase("userConfig")) {
					carConfig.Config();
					break;
				}

			}
		}

			out.close();
			in.close();
			stdIn.close();
			kbbSocket.close();
		}

	}
