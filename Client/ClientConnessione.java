import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientConnessione {

    public static DatagramSocket connessioneServer(String name, String serverIP, int port) throws Exception 
    {
        DatagramSocket clientSocket = new DatagramSocket();
        try {
            InetAddress serverAddress = InetAddress.getByName(serverIP);

            String message = "Richiesta connessione - " + name;
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, port);
            clientSocket.send(packet);

            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            clientSocket.receive(responsePacket);
            System.out.println(responsePacket.getData());

            return clientSocket;

        } catch (Exception ex) {
            clientSocket.close();
            throw new Exception("Errore durante la connessione: " + ex.getMessage());
        }
    }
}
