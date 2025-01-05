import javax.swing.*;
import java.awt.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;

public class PaginaDomanda {

    public PaginaDomanda(DatagramSocket clientSocket,  String serverIP, int serverPort) {
        JFrame frame = new JFrame("Domanda Trivia - " + clientSocket.getLocalPort());
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

            String rispostaCorretta = parti[2];

            ArrayList<String> risposte = new ArrayList<String>();
            for (int i = 2; i < parti.length; i++)
                risposte.add(parti[i].trim());
            Collections.shuffle(risposte);

            for (String risposta : risposte) {
                JButton rispostaButton = new JButton(risposta);
                rispostaPanel.add(rispostaButton);

                rispostaButton.addActionListener(e -> {
                    String rispostaSelezionata = ((JButton) e.getSource()).getText();
                    inviaRisposta(clientSocket, rispostaSelezionata, rispostaCorretta, serverIP, serverPort);
                    frame.dispose();
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Errore durante la ricezione della domanda.");
        }

        new Thread(() -> {
            new GestioneCorrezione(clientSocket, serverIP, serverPort).gestisciRisposta();
        }).start();

        frame.setVisible(true);
    }

    private void inviaRisposta(DatagramSocket clientSocket, String risposta, String rispostaCorretta, String serverIP, int serverPort) {
        try {
            String messaggio;
            if (risposta.equals(rispostaCorretta))
                messaggio = "true";
            else
                messaggio = "false";

            byte[] rispostaBytes = messaggio.getBytes();
            
            DatagramPacket rispostaPacket = new DatagramPacket(rispostaBytes, rispostaBytes.length, InetAddress.getByName(serverIP), serverPort);
            clientSocket.send(rispostaPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
