package ClienteServidor;

import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Cliente{
    private SocketCliente clientSocket;

    public Cliente(){
        try{
            clientSocket = new SocketCliente(new Socket(Servidor.ADDRESS, Servidor.PORT));
        }
        catch(IOException ex){
           JOptionPane.showMessageDialog(null, "Erro ao conectar com o servidor: " + ex.getMessage());
        }
    }

    public void mandar_recorrencia(String email) throws IOException{
        String recorrenciaString = email + "|" + java.time.LocalTime.now();
        clientSocket.sendMsg(recorrenciaString);
    }

    public static void main(String[] args)   {
        try {
            Cliente cl = new Cliente();
            cl.mandar_recorrencia("Rapha");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
