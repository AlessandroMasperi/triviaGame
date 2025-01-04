import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramSocket;

public class GestioneConnessione implements ActionListener {
    private JTextField nameField;
    private JTextField ipField;
    private JTextField portField;
    private JTextArea logArea;
    public JFrame frame;

    public GestioneConnessione(JTextField nameField, JTextField ipField, JTextField portField, JTextArea logArea, JFrame frame) {
        this.nameField = nameField;
        this.ipField = ipField;
        this.portField = portField;
        this.logArea = logArea;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText().trim();
        String serverIP = ipField.getText().trim();
        String portText = portField.getText().trim();

        if (name.isEmpty() || serverIP.isEmpty() || portText.isEmpty()) {
            logArea.append("Per favore, compila tutti i campi.\r\n");
            return;
        }

        try {
            int port = Integer.parseInt(portText);
            logArea.append("Connessione al server...\n");

            // Effettua la connessione e ottieni la socket
            DatagramSocket clientSocket = ClientConnessione.connessioneServer(name, serverIP, port);

            logArea.append("Connessione riuscita!\n");

            // Passa alla pagina di attesa
            frame.dispose();
            SwingUtilities.invokeLater(() -> new PaginaAttesa(clientSocket));

        } catch (NumberFormatException ex) {
            logArea.append("Il numero di porta deve essere un intero.\n");
        } catch (Exception ex) {
            logArea.append("Errore durante la connessione: " + ex.getMessage() + "\n");
        }
    }
}
