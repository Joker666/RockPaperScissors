package PaperScissorsStone;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class Client {
    public static void main(String[] args) throws Exception {
		String input;
		String response;

		System.out.println("Enter name: ");
		Scanner name = new Scanner(System.in);
		String userName = name.nextLine();

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket = new Socket("localhost", 9090);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		outToServer.writeBytes(userName + "\n");
		do {
			System.out.println("\nSelect (1)Rock (2)Paper, (3)Scissors: ");
			input = inFromUser.readLine();
		} while (!input.equals("1") && !input.equals("2") && !input.equals("3"));

		outToServer.writeBytes(input + "\n");
		System.out.println("\nYou have entered (" + input + ")");

		response = inFromServer.readLine();
		System.out.println("Response from server: " + response);
		clientSocket.close();
    }
}
