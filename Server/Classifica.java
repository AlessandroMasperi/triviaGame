import java.util.ArrayList;

public class Classifica 
{
    private ArrayList<ClientInfo> giocatori;

    public Classifica()
    {
        this.giocatori = new ArrayList<ClientInfo>();
    }

    public void add(ClientInfo client)
    {
        this.giocatori.add(client);
    }

    public void stampa() {
        for (int i = giocatori.size()-1; i > -1; i--) 
        {
            System.out.println(giocatori.get(i).getName() + " Posizione:" + (i+1) + " Punteggio: " + giocatori.get(i).getPunteggio());
        }
    }

    public int size() {
        return this.giocatori.size();
    }
}
