import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class ChatServer {
	public static void main(String args[]){
		int portNumber = 3131;
		ServerSocket serverSocket = null;
		try{
			serverSocket = new ServerSocket(portNumber);
			System.out.println("Connected to server.");
			acceptClients(serverSocket);
			System.out.println("Initialised acceptClients");
		} catch(IOException e){
			System.err.println("Could not listen to port " + portNumber);
			System.exit(1);
		}
	}
	
	public static void acceptClients(ServerSocket serverSocket){
		ArrayList<ClientThread> clients = new ArrayList<>();
		while(true){
			try{
				Socket socket = serverSocket.accept();
				ClientThread client = new ClientThread(socket, clients);
				Thread t = new Thread(client);
				t.start();
				clients.add(client);
			} catch (IOException e){
				System.err.println("Couldn't join chat! Try Again.");
			}
		}
	}
}