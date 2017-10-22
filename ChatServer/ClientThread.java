import java.util.*;
import java.net.*;
import java.io.*;

public class ClientThread extends ChatServer implements Runnable{
	
	private Socket socket;
	private ArrayList<ClientThread> clients;
	private BufferedReader in;
	private PrintWriter out;
	
	public ClientThread(Socket socket, ArrayList<ClientThread> clients){
		this.socket = socket;
		this.clients = clients;
 	}
	
	public PrintWriter getWriter(){
		return out;
	}
	
	@Override
	public void run(){
		try{
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			while(!socket.isClosed()){
				String input = in.readLine();
				if (input != null){
					for (ClientThread client : clients){
						client.getWriter().write(input);
					}
				}
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
