import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PaginaGioco {

    private DatagramSocket clientSocket;
    private InetAddress serverAddress;
    private int serverPort;

    public PaginaGioco(DatagramSocket socket, String serverAddress, int serverPort) throws UnknownHostException 
    {
        this.clientSocket = socket;
        this.serverAddress = InetAddress.getByName(serverAddress);
        this.serverPort = serverPort;

        JFrame frame = new JFrame("Gioco Trivia");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel label = new JLabel("Benvenuto nella pagina del gioco! Scegli la categoria per iniziare", SwingConstants.CENTER);
        frame.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10));

        //prendere le categorrie direttamente dal file
        String[] categorie = {"Scienze", "Geografia", "Storia", "Arte", "Sport", "Intrattenimento"};
        for (String categoria : categorie) {
            JButton button = new JButton(categoria);
            buttonPanel.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    button.setEnabled(false);
                    inviaCategoria(categoria);
                    frame.dispose();
                    new PaginaDomanda(clientSocket);
                }
            });
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void inviaCategoria(String categoria) {
        try {
            String message = categoria;
            byte[] buffer = message.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, serverPort);
            clientSocket.send(packet);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Errore nell'invio della categoria: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}
