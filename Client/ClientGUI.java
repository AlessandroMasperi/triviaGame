import javax.swing.*;
import java.awt.*;

public class ClientGUI {
    private JTextField nameField;
    private JTextField ipField;
    private JTextField portField;
    private JTextArea logArea;

    public ClientGUI() {
        JFrame frame = new JFrame("Client Trivia");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Nome:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("IP del Server:"));
        ipField = new JTextField("127.0.0.1");
        inputPanel.add(ipField);

        inputPanel.add(new JLabel("Porta:"));
        portField = new JTextField("12345");
        inputPanel.add(portField);

        frame.add(inputPanel, BorderLayout.NORTH);

        logArea = new JTextArea();
        logArea.setEditable(false);
        frame.add(new JScrollPane(logArea), BorderLayout.CENTER);

        JButton connectButton = new JButton("Connetti");
        connectButton.addActionListener(new GestioneConnessione(nameField, ipField, portField, logArea, frame));
        frame.add(connectButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
