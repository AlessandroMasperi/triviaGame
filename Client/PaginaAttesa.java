import java.net.DatagramSocket;
import javax.swing.*;

public class PaginaAttesa {

    private JTextArea logArea;

    public PaginaAttesa(DatagramSocket clientSocket, String serverIP, int portServer) {
        JFrame frame = new JFrame("Pagina di Attesa");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setText("Pagina di attesa: attendi che tutti i client si siano connessi.\nUna volta fatto, verrai mandato automaticamente alla pagina del gioco!");
        frame.add(new JScrollPane(logArea));

        frame.setVisible(true);

        new GestioneMessaggioInizioPartita(frame, clientSocket, serverIP, portServer).start();
    }
}
