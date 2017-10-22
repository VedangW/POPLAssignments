import java.
import java.io.*;
import java.net.*;
 
public class Client
{
    public static void main(String[] args)
    {
        System.out.println("Enter nick: ");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        scan.close();

        BufferedReader in = null;
        PrintWriter out = null;
        try {
           Socket socket = new Socket("localhost", 4444);
           in = new BufferedReader(
               new InputStreamReader(socket.getInputStream()));
           out = new PrintWriter(
               new OutputStreamWriter(socket.getOutputStream()));
           System.out.println("Connected to server.");
        } catch (IOException e) {
           System.err.println("Couldn't listen in to port 4444");
           e.printStackTrace();
           System.exit(-1);
        }
 
        // Create and start Sender thread
        Sender sender = new Sender(out, name);
        sender.setDaemon(true);

       sender.start();
 
        try {
           // Read messages from the server and print them
            String message;
           while ((message=in.readLine()) != null) {
               System.out.println(message);
           }
        } catch (IOException ioe) {
           System.err.println("Connection to server broken.");
           ioe.printStackTrace();
        }
 
    }
}
 
class Sender extends Thread
{
  private PrintWriter mOut;
   
  public Sender(PrintWriter aOut, String name)
  {
          mOut = aOut;
  }
   
  public void run()
  {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (!isInterrupted()) {
          String message = in.readLine();
          mOut.println(message);
          mOut.flush();
        }
      } catch (IOException ioe) {
            System.err.println("Connection to server broken.");
    }
  }
}

 