import java.net.DatagramPacket;
import java.net.DatagramSocket;
import javax.swing.*;

public class GestioneMessaggioInizioPartita extends Thread {
    private final JFrame frame;
    private final DatagramSocket clientSocket;
    private final String serverIp;
    private final int portServer;

    public GestioneMessaggioInizioPartita(JFrame frame, DatagramSocket clientSocket, String serverIp, int portServer) {
        this.frame = frame;
        this.clientSocket = clientSocket;
        this.serverIp = serverIp;
        this.portServer = portServer;
    }

    @Override
    public void run() {
        try {
            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);

            clientSocket.receive(responsePacket);
            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());

            frame.dispose();

            if (response.equals("si")) {
                new PaginaGioco(clientSocket, serverIp, portServer);
            } else {
                new PaginaNonScelto(clientSocket, serverIp, portServer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
