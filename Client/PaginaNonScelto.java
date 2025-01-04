import javax.swing.*;

public class PaginaNonScelto {
    public PaginaNonScelto() {
        JFrame frame = new JFrame("Non scelto");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Non sei stato scelto per il turno iniziale.", SwingConstants.CENTER);
        frame.add(label);

        frame.setVisible(true);
    }
}