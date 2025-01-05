import java.io.IOException;
import java.net.DatagramSocket;

public class MainServer {
    public static void main(String[] args) throws IOException 
    {
        Categorie cat = new Categorie();

        DatagramSocket serverSocket = new DatagramSocket(12345);
        
        ClientInGioco clients = new ClientInGioco();
        Server.connessione(serverSocket, clients);

        clients.scegliPrimo(serverSocket);

        gestioneDomande gestore = new gestioneDomande(serverSocket, clients, cat);

        boolean giocoInCorso = true;

        while (giocoInCorso) {
            gestore.genera();
            
            while(gestore.gestisciRisposta());

        }

        System.out.println("Gioco terminato.");
        serverSocket.close();
    }
}
