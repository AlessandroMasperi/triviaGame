import javax.swing.*;

public class PaginaNonScelto {
    public PaginaNonScelto() {
        JFrame frame = new JFrame("Gioco Trivia");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Non sei stato scelto per il turno iniziale.\r\nAspetta che il giocatore scelga la categoria per giocare", SwingConstants.CENTER);
        frame.add(label);

        frame.setVisible(true);

        //manca consegna domanda anche a lui
    }
}