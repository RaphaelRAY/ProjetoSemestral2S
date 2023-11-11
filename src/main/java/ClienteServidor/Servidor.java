package ClienteServidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.LinkedList;

public class Servidor {
    public static final String ADDRESS = "127.0.0.1";
    public static final int PORT = 4000;
    private ServerSocket serverSocket;
    private final List<SocketCliente> clients = new LinkedList<>();

    public void start() throws IOException{
        serverSocket = new ServerSocket(PORT);
        System.out.printf("Servidor iniciado na porta: " + PORT + "\n");
        clientConnectionLoop();
    }

    private void clientConnectionLoop() throws IOException{
        System.out.println("Aguardando Recorrencias!");
        while(true){
            SocketCliente clientSocket = new SocketCliente(serverSocket.accept());
            clients.add(clientSocket);
            new Thread(() -> clientMessageLoop(clientSocket)).start();
        }
    }

    public void clientMessageLoop(SocketCliente clientSocket){
        String msg;
        msg = clientSocket.getMessage();
        System.out.println(msg);
    }

    public static void main(String[] args) {
        System.out.println("*v*v*v* CONSOLE DO SERVIDOR *v*v*v*");
        try{
            Servidor server = new Servidor();
            server.start();
        }
        catch(IOException ex){
            System.out.println("Erro ao iniciar o servidor: " + ex.getMessage());
        }
        System.out.println("Servidor finalizado!");
    }
}
