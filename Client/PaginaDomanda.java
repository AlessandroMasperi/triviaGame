import javax.swing.*;
import java.awt.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class PaginaDomanda {

    public PaginaDomanda(DatagramSocket clientSocket) {
        JFrame frame = new JFrame("Domanda Trivia");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTextArea domandaArea = new JTextArea();
        domandaArea.setEditable(false);
        domandaArea.setLineWrap(true);
        domandaArea.setWrapStyleWord(true);
        domandaArea.setFont(new Font("Serif", Font.BOLD, 18));
        frame.add(new JScrollPane(domandaArea), BorderLayout.CENTER);

        JPanel rispostaPanel = new JPanel();
        rispostaPanel.setLayout(new GridLayout(2, 2, 10, 10));
        frame.add(rispostaPanel, BorderLayout.SOUTH);

        try {
            byte[] buffer = new byte[1024];
            DatagramPacket domandaPacket = new DatagramPacket(buffer, buffer.length);
            clientSocket.receive(domandaPacket);

            String domandaRicevuta = new String(domandaPacket.getData(), 0, domandaPacket.getLength());
            String[] parti = domandaRicevuta.split(";");

            domandaArea.setText(parti[1]); //domanda

            for (int i = 2; i < parti.length; i++) {
                JButton rispostaButton = new JButton(parti[i].trim());
                rispostaPanel.add(rispostaButton);

                rispostaButton.addActionListener(e -> {
                    String rispostaSelezionata = ((JButton) e.getSource()).getText();
                    inviaRisposta(clientSocket, rispostaSelezionata);
                    frame.dispose();
                    //manca passaggio pagina successiva
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Errore durante la ricezione della domanda.");
        }

        frame.setVisible(true);
    }

    private void inviaRisposta(DatagramSocket clientSocket, String risposta) {
        try {
            byte[] rispostaBytes = risposta.getBytes();
            DatagramPacket rispostaPacket = new DatagramPacket(rispostaBytes, rispostaBytes.length, clientSocket.getInetAddress(), clientSocket.getPort());
            clientSocket.send(rispostaPacket);
            //manca gestione della risposta lato server
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
