package MultiThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public void run() throws UnknownHostException, IOException{
        int port = 8010;
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, port);

        //sending data to server
        PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
        toSocket.println("Hello World from socket "+socket.getLocalSocketAddress());


        //receiving data from the server
        BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = fromSocket.readLine();
        System.out.println("Data from server" + line);

        socket.close();
        toSocket.close();
        fromSocket.close();

    }

    public static void main(String[] args) {
        Client clientMachine = new Client();
        try{
            clientMachine.run();
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}