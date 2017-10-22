import java.net.*;
import java.io.*;


public class ServerThread implements Runnable{
	
	private Socket socket;
	private String name;
	private BufferedReader serverIn;
	private BufferedReader userIn;
	private PrintWriter out;
	
	public ServerThread(Socket socket, String name){
		this.socket = socket;
		this.name = name;
	}
	
	@Override
	public void run(){
		try{
			out = new PrintWriter(socket.getOutputStream(), true);
			userIn = new BufferedReader(new InputStreamReader(System.in));
			serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			while(!socket.isClosed()){
				if(serverIn.ready()){
					System.out.println("<" + name + ">: ");
					String input = serverIn.readLine();
					if (input != null){
						System.out.println(input);
					}
				}
				if(userIn.ready()){
					out.println("<" + name + ">" + userIn.readLine());
				}
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
