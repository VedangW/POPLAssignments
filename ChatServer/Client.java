import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
	public static void main(String args[]){
		int portNumber = 3131;
		Socket socket = null;
		System.out.println("Enter nick: ");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		scan.close();
		System.out.println("Welcome " + name);
		try{
			socket = new Socket("localhost", portNumber);
			System.out.println("Connected to server at " + portNumber);
			Thread.sleep(10);
			ServerThread st = new ServerThread(socket, name);
			System.out.println("Initialised ServerThread");
			Thread server = new Thread(st);
			System.out.println("Created Thread");
			server.start();
			System.out.println("Started thread.");
		} catch (IOException e){
			System.err.println("Fatal connection error on " + portNumber);
			e.printStackTrace();
		} catch (InterruptedException e){
			System.err.println("Fatal connection error on " + portNumber);
			e.printStackTrace();
		}
	}
}
