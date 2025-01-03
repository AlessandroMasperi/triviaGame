import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientConnessione {
    public static String connessioneServer(String name, String serverIP, int port) {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(serverIP);

            String message = "Richiesta connessione";
            byte[] buffer = message.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, port);
            clientSocket.send(packet);

            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            clientSocket.receive(responsePacket);

            return new String(responsePacket.getData(), 0, responsePacket.getLength());
        } catch (Exception ex) {
            return "Errore durante la connessione: " + ex.getMessage();
        }
    }
}
