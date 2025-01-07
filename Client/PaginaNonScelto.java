import javax.swing.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class PaginaNonScelto {

    public PaginaNonScelto(DatagramSocket clientSocket, String serverIP, int serverPort) {

        JFrame frame = new JFrame("Gioco Trivia - " + clientSocket.getLocalPort());
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Non sei stato scelto per il turno iniziale.<br>Attendi che il giocatore scelga la categoria per giocare.",SwingConstants.CENTER);
        frame.add(label);

        frame.setVisible(true);

        new Thread(() -> {
            try {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                clientSocket.receive(packet);

                String messaggio = new String(packet.getData(), 0, packet.getLength());
                
                if (messaggio.equals("avanti")) {
                    frame.dispose();
                    new PaginaDomanda(clientSocket, serverIP, serverPort);
                }
                else if(messaggio.equals("tutti"))
                {
                    frame.dispose();
                    new PaginaAttesa(clientSocket, serverIP, serverPort);
                }
                else if(messaggio.equals("rispostaIndovinata"))
                {
                    frame.dispose();
                    new PaginaNonScelto(clientSocket, serverIP, serverPort);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
