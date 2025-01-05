import java.io.IOException;
import java.net.DatagramSocket;

public class MainServer {
    private static final int MAX_CLIENTS = 2;
    public static void main(String[] args) throws IOException 
    {
        Categorie cat = new Categorie();

        DatagramSocket serverSocket = new DatagramSocket(12345);
        
        ClientInGioco clients = new ClientInGioco();
        Server.connessione(serverSocket, clients, MAX_CLIENTS);

        clients.scegliPrimo(serverSocket);

        gestioneDomande gestore = new gestioneDomande(serverSocket, clients, cat);

        boolean giocoInCorso = true;

        while (giocoInCorso) {
            gestore.genera();
            int i = 0;

            for (; i < MAX_CLIENTS; i++) 
            {
                if(gestore.gestisciRisposta())
                    break;
            }

            if(i== MAX_CLIENTS)
            {
                gestore.mandaMessaggioATutti();
                clients.scegliPrimo(serverSocket);
            }
        }

        System.out.println("Gioco terminato.");
        serverSocket.close();
    }
}
