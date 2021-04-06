package PaperScissorsStone;

import java.io.*;
import java.net.*;

class Client {
    private static final String host = "localhost";
    private static final Integer port = 9090;

    private static final String msgWelcome = "--- Get Started\n";

    public static void main(String[] args) throws Exception {

		String input;
		String response;

		System.out.println(Client.msgWelcome);

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket = new Socket(Client.host, Client.port);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		do {
			// Prompt user for select rock, paper or scissors ...
			System.out.println("Select (1)Rock (2)Paper, (3)Scissors: ");
			input = inFromUser.readLine();
		} while (!input.equals("1") && !input.equals("2") && !input.equals("3"));

		// Transmit input to the server and provide some feedback for the user
		outToServer.writeBytes(input + "\n");
		System.out.println("\nYou have entered (" + input + ")");

		// Catch responses
		response = inFromServer.readLine();

		// Display responses
		System.out.println("Response from server: " + response);

		// Close socket
		clientSocket.close();
    }
}
