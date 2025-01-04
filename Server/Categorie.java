import java.io.IOException;
import java.util.HashMap;

public class Categorie 
{
    private HashMap<String, Domande> domandeDiviseCategoria;

    public Categorie() throws IOException
    {
        Domande lista = Domande.fromCSV("FileDomande.csv");
        this.domandeDiviseCategoria = new HashMap<String, Domande>();
        
        for (int i = 0; i < lista.size(); i++) 
        {
            Domanda domanda = lista.getDomanda(i);
            String categoria = domanda.getCategoria();

            if(domandeDiviseCategoria.containsKey(categoria))
            {
                Domande d = domandeDiviseCategoria.get(categoria);
                d.add(domanda);
                domandeDiviseCategoria.put(categoria, d);
            }
            else
            {
                Domande d = new Domande();
                d.add(domanda);
                domandeDiviseCategoria.put(categoria, d);
            }
        }
    }

    public Domande getDomande(String categoria) {
        return domandeDiviseCategoria.get(categoria);
    }
}
