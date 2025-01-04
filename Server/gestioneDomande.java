import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class gestioneDomande {

    private DatagramSocket serverSocket;
    private ClientInGioco clients;
    private Categorie catDomande;

    public gestioneDomande(DatagramSocket serverSocket, ClientInGioco clients, Categorie catDomande) {
        this.serverSocket = serverSocket;
        this.clients = clients;
        this.catDomande = catDomande;
    }

    public String gestisciMessaggio() {
        try {
            byte[] buffer = new byte[1024];
            DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(receivedPacket);

            return new String(receivedPacket.getData(), 0, receivedPacket.getLength());

        } catch (Exception e) {
            e.printStackTrace();
            return "Errore durante la gestione del messaggio: " + e.getMessage();
        }
    }

    public boolean genera() {
        try {
            String messaggio = gestisciMessaggio();
            Domande domande = catDomande.getDomande(messaggio);
            Domanda domandaSelezionata = domande.get((int) Math.random()*domande.size());
            String domanda = domandaSelezionata.toCSV();

            for (int i = 0; i < clients.size(); i++) 
            {
                ClientInfo client = clients.get(i);
                byte[] buffer = domanda.getBytes();
                InetAddress clientAddress = InetAddress.getByName(client.getAddress());
                int clientPort = client.getPort();

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                serverSocket.send(packet);
            }

            System.out.println("Domanda inviata ai client.");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
