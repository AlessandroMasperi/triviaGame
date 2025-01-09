import java.io.IOException;
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

            mandaAvanti(receivedPacket.getAddress(), receivedPacket.getPort());

            return new String(receivedPacket.getData(), 0, receivedPacket.getLength());

        } catch (Exception e) {
            e.printStackTrace();
            return "Errore durante la gestione del messaggio: " + e.getMessage();
        }
    }

    public void mandaAvanti(InetAddress clientEscluso, int portaEsclusa) {
        try {
            for (int i = 0; i< clients.size(); i++) 
            {
                ClientInfo client = clients.get(i);
                InetAddress clientAddress = InetAddress.getByName(client.getAddress());
                int clientPort = client.getPort();
    
                if (!clientEscluso.equals(clientAddress) || clientPort != portaEsclusa) {
                    String messaggio = "avanti";
                    byte[] buffer = messaggio.getBytes();
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                    serverSocket.send(packet);
                }
            }
        } catch (Exception e) {
            System.err.println("Errore nell'invio del messaggio ai client: " + e.getMessage());
            e.printStackTrace();
        }
    }    

    public boolean genera() {
        try {
            String categoria = gestisciMessaggio();
            Domande domande = catDomande.getDomande(categoria);
            Domanda domandaSelezionata = domande.get((int) (Math.random()*domande.size()));
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

    public boolean gestisciRisposta() {
        try {
            DatagramPacket packet = attendiRisposta();
            String risultato = new String(packet.getData(), 0, packet.getLength()).trim();
    
            boolean risposta = risultato.equals("true");
    
            InetAddress clientAddress = packet.getAddress();
            int clientPort = packet.getPort();

            ClientInfo c = clients.get(clientAddress, clientPort);

            String messaggio;
            if (risposta) {
                messaggio = "indovinato";
                c.incrementa();
            } else {
                messaggio = "sbagliato";
                c.decrementa();
            }
            byte[] buffer = messaggio.getBytes();
            DatagramPacket rispostaPacket = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
            serverSocket.send(rispostaPacket);
    
            if (risposta) {
                for (int i = 0; i < clients.size(); i++) 
                {
                    ClientInfo client = clients.get(i);
                    if (!client.getAddress().equals(clientAddress.getHostAddress()) || client.getPort() != clientPort) 
                    {
                        String messaggioAltri = "rispostaIndovinata";
                        byte[] bufferAltri = messaggioAltri.getBytes();
                        DatagramPacket packetAltri = new DatagramPacket(bufferAltri, bufferAltri.length, InetAddress.getByName(client.getAddress()), client.getPort());
                        serverSocket.send(packetAltri);
                    }
                }
            }
    
            return risposta;
    
        } catch (Exception e) {
            System.err.println("Errore durante la gestione della risposta: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    

    public DatagramPacket attendiRisposta() {
        try {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(packet);
            return packet;

        } catch (IOException e) {
            System.err.println("Errore durante l'attesa di una risposta: " + e.getMessage());
            return null;
        }
    }

    public void mandaMessaggioATutti() {
        try {
            for (int i = 0; i < clients.size(); i++) 
            {
                ClientInfo client = clients.get(i);
                byte[] buffer = "tutti".getBytes();
                InetAddress clientAddress = InetAddress.getByName(client.getAddress());
                int clientPort = client.getPort();
    
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                serverSocket.send(packet);
            }

        } catch (Exception e) {
            System.err.println("Errore durante l'invio del messaggio a tutti i client: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
