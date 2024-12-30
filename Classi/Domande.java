import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Domande 
{
    private ArrayList<Domanda> domande; // lista di domande


    /**
     * Costruttore di default
     */
    public Domande()
    {
        this.domande = new ArrayList<Domanda>();
    }

    //aggiungi
    /**
     * Aggiunge una domanda alla lista di domande
     * @param domanda domanda da aggiungere
     */
    public void add(Domanda domanda)
    {
        this.domande.add(domanda);
    }

    //fai la conversione in csv
    public void toCSV(String filePath) throws IOException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Domanda domanda : domande) {
                writer.write(domanda.toCSV());
                writer.newLine();
            }
        }
    }

    //fai la conversione da csv
    public void fromCSV(String filePath) throws IOException
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) { // Evita righe vuote
                    Domanda domanda = Domanda.fromCSV(line);
                    this.add(domanda);
                }
            }
        }
    }
}