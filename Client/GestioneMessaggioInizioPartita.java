import java.net.DatagramPacket;
import java.net.DatagramSocket;
import javax.swing.*;

public class GestioneMessaggioInizioPartita extends Thread {
    private final JFrame frame;
    private final DatagramSocket clientSocket;

    public GestioneMessaggioInizioPartita(JFrame frame, DatagramSocket clientSocket) {
        this.frame = frame;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);

            // Attende la risposta dal server
            clientSocket.receive(responsePacket);
            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());

            // Chiude la finestra di attesa
            SwingUtilities.invokeLater(() -> frame.dispose());

            // Mostra la finestra appropriata
            if ("si".equalsIgnoreCase(response)) {
                SwingUtilities.invokeLater(PaginaGioco::new);
            } else {
                SwingUtilities.invokeLater(PaginaNonScelto::new);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
