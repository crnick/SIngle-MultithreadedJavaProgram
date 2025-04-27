package MultiThreaded;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.function.Consumer;

public class Server {
        public Consumer<Socket> getConsumer() {
            return (ClientSocket) -> {
                try{
                    PrintWriter toClient = new PrintWriter(ClientSocket.getOutputStream());
                    toClient.println("Hello from the server");
                    toClient.close();
                    ClientSocket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            };
    }
        public static void main(String args[]) throws IOException{
            int port = 8010;
            Server serve = new Server();
            try{
                ServerSocket server = new ServerSocket(port);
                server.setSoTimeout(10000);
                System.out.println("Server listening on port" + port);
                while(true){
                    Socket acceptSocket = server.accept();// creates a socket
                    Thread thread = new Thread(()->serve.getConsumer().accept(acceptSocket)); //within this thread will communicate with client
                    thread.start();

                }
            }catch (IOException e){
                e.getMessage();
            }
        }

}