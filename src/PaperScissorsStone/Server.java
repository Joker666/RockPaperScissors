package PaperScissorsStone;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws Exception {
		String resClient_1 = "";
		String resClient_2 = "";
		String inputClient_1;
		String inputClient_2;

		ServerSocket serverSocket = new ServerSocket(9090);
		System.out.println("Server is running on port " + serverSocket.getLocalPort() + "\n");

		while (!serverSocket.isClosed()) {

			// Player one
			Socket client_1 = serverSocket.accept();
			DataOutputStream outClient_1 = new DataOutputStream(client_1.getOutputStream());
			BufferedReader inClient_1 = new BufferedReader(new InputStreamReader(client_1.getInputStream()));

			String playerOneName = inClient_1.readLine();
			if (client_1.isConnected()) {
				System.out.println("\nPlayer one - " + playerOneName + " has joined.");
			}

			// Player two
			Socket client_2 = serverSocket.accept();
			DataOutputStream outClient_2 = new DataOutputStream(client_2.getOutputStream());
			BufferedReader inClient_2 = new BufferedReader(new InputStreamReader(client_2.getInputStream()));

			String playerTwoName = inClient_2.readLine();
			if (client_2.isConnected()) {
				System.out.println("Player two - " + playerTwoName + " has joined. Lets start ...");
			}

			// Get client inputs
			inputClient_1 = inClient_1.readLine();
			inputClient_2 = inClient_2.readLine();

			if (inputClient_1.equals(inputClient_2)) {
				resClient_1 = "Draw";
				resClient_2 = "Draw";
				System.out.println("It'3 a draw.");
			} else if (inputClient_1.equals("1") && inputClient_2.equals("3")) {
				resClient_1 = "You win";
				resClient_2 = "You lose";
				System.out.println("Player one - " + playerOneName + " wins.");
			} else if (inputClient_1.equals("3") && inputClient_2.equals("1")) {
				resClient_1 = "You lose";
				resClient_2 = "You win";
				System.out.println("Player two - " + playerTwoName + " wins.");
			} else if (inputClient_1.equals("1") && inputClient_2.equals("2")) {
				resClient_1 = "You lose";
				resClient_2 = "You win";
				System.out.println("Player two - " + playerTwoName + " wins.");
			} else if (inputClient_1.equals("2") && inputClient_2.equals("1")) {
				resClient_1 = "You win";
				resClient_2 = "You lose";
				System.out.println("Player one - " + playerOneName + " wins.");
			} else if (inputClient_1.equals("3") && inputClient_2.equals("2")) {
				resClient_1 = "You win";
				resClient_2 = "You lose";
				System.out.println("Player one - " + playerOneName + " wins.");
			} else if (inputClient_1.equals("2") && inputClient_2.equals("3")) {
				resClient_1 = "You lose";
				resClient_2 = "You win";
				System.out.println("Player two - " + playerTwoName + " wins.");
			}

			outClient_1.writeBytes(resClient_1.toUpperCase());
			outClient_2.writeBytes(resClient_2.toUpperCase());
			client_1.close();
			client_2.close();

			System.out.println("\nWaiting for new players ...\n");
		}
	}
}