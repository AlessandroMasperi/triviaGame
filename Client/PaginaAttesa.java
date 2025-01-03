import javax.swing.*;

public class PaginaAttesa {

    private JTextArea logArea;

    public PaginaAttesa() {

        JFrame frame = new JFrame("Pagina successiva");
        frame.setSize(400, 300);

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setText("Pagina di attesa: attendi che tutti i client si siano connessi.\r\nUna volta fatto verrrai mandato automaticamente alla pagina del gioco!");
        frame.add(new JScrollPane(logArea));

        // Mostra la finestra
        frame.setVisible(true);
    }
}
