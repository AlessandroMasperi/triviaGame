import javax.swing.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.UnknownHostException;

public class GestioneCorrezione extends Thread {

    private DatagramSocket clientSocket;
    private JFrame frame;
    private String serverIP;
    private int serverPort;

    public GestioneCorrezione(DatagramSocket clientSocket, String serverIP, int portServer) 
    {
        this.clientSocket = clientSocket;
        this.frame = new JFrame("Gioco Trivia - " + clientSocket.getLocalPort());
        this.serverIP= serverIP;
        this.serverPort = portServer;
    }

    public void gestisciRisposta() 
    {
        try {
            DatagramPacket packet = attendiRisposta();
            String risposta = new String(packet.getData(), 0, packet.getLength());

            if (risposta.equals("indovinato"))
                vaiAllaPaginaSceltaCategoria();
            else if (risposta.equals("sbagliato") || risposta.equals("rispostaIndovinata"))
                vaiAllaPaginaAttesa();

        } catch (Exception e) {
            System.err.println("Errore durante la gestione della risposta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private DatagramPacket attendiRisposta() 
    {
        try {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            clientSocket.receive(packet);
            return packet;
        } catch (Exception e) {
            System.err.println("Errore durante l'attesa di una risposta dal server: " + e.getMessage());
            return null;
        }
    }

    private void vaiAllaPaginaSceltaCategoria() throws UnknownHostException 
    {
        frame.dispose();
        new PaginaGioco(clientSocket, serverIP, serverPort);
    }

    private void vaiAllaPaginaAttesa() 
    {
        frame.dispose();
        new PaginaAttesa(clientSocket, serverIP, serverPort);
    }
}
