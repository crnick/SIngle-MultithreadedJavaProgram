package MultiThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public Runnable getRunnable(){
        return new Runnable() {
            @Override
            public void run() {
                int port = 8010;
                try{
                    InetAddress address = InetAddress.getByName("localhost");
                    Socket socket = new Socket(address, port);

                    //sending data to server
                    try(
                            PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
                            BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            ){

                        toSocket.println("Hello World from socket "+socket.getLocalSocketAddress());

                        //receiving data from the server
                        String line = fromSocket.readLine();
                        System.out.println("Data from server" + line);
                        socket.close();
                        toSocket.close();
                        fromSocket.close();
                    }catch (Exception e){
                        e.getMessage();
                    }
                }catch (Exception e){
                    e.getMessage();
                }

            }
        };
    }
    public static void main(String args[]) throws IOException{
        Client client = new Client();
        for (int i=0;i<100;i++){
            try{
                Thread thread = new Thread(client.getRunnable());
                thread.start();
            }catch (Exception e){
                e.getMessage();
            }
        }
    }
}
