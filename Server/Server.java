import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    private static final int MAX_CLIENTS = 2;

    public static void connessione(DatagramSocket serverSocket, ClientInGioco clients) throws IOException
    {
        System.out.println("Server in ascolto");
        
        byte[] buffer = new byte[1024];

        int numHost = 0;

        while (numHost < MAX_CLIENTS) {
            DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(receivedPacket);

            String receivedMessage = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
            if(receivedMessage.contains("Richiesta connessione - "))
            {
                String responseMessage = "Connessione stabilita";
                byte[] responseData = responseMessage.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, receivedPacket.getAddress(), receivedPacket.getPort());
                serverSocket.send(responsePacket);
                numHost++;
                System.out.println("Client connesso");
                clients.add(new ClientInfo(receivedMessage.replace("Richiesta connessione - ",""), receivedPacket.getAddress().getHostAddress(), receivedPacket.getPort()));
            }
            buffer = new byte[1024];
        }
        System.out.println("Inizio gioco");
    }
}
