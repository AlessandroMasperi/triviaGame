import javax.swing.*;

public class PaginaGioco {
    public PaginaGioco() {
        JFrame frame = new JFrame("Gioco Trivia");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Benvenuto nella pagina del gioco!", SwingConstants.CENTER);
        frame.add(label);

        frame.setVisible(true);
    }
}