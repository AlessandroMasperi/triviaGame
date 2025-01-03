import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) 
    {
        Server.connessione();
    }

    public static void connessione()
    {
        try (DatagramSocket serverSocket = new DatagramSocket(12345)) {
            System.out.println("Server in ascolto");
            
            byte[] buffer = new byte[1024];

            int numHost = 0;

            while (numHost < 2) {
                DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(receivedPacket);

                String receivedMessage = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                if(receivedMessage.equals("Richiesta connessione"))
                {
                    String responseMessage = "Connessione stabilita";
                    byte[] responseData = responseMessage.getBytes();
                    DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, receivedPacket.getAddress(), receivedPacket.getPort());
                    serverSocket.send(responsePacket);
                    numHost++;
                    System.out.println("Client connesso");
                }
                
            }
            System.out.println("Inizio gioco");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
