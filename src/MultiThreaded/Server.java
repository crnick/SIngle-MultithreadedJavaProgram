package MultiThreaded;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    public void run() throws IOException, UnknownHostException{
        int port = 8010;//server listening at this port
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(20000); // will wait till this time otherwise close it.
        while(true){
            System.out.println("Server is listening on port: "+port);
            Socket acceptedConnection = socket.accept(); // waits for client to connect
            System.out.println("Connected to "+acceptedConnection.getRemoteSocketAddress());

            //sends textual data, and automatically converts to bytes.
            PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
            toClient.println("Hello World from the server");
        }
    }

    public static void main(String[] args){
        Server server = new Server();
        try{
            server.run();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

}