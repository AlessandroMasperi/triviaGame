import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ClientInGioco 
{
    private ArrayList<ClientInfo> clients;
    private Classifica classifica;

    public ClientInGioco()
    {
        clients = new ArrayList<>();
        classifica = new Classifica();
    }

    public void add(ClientInfo Client)
    {
        clients.add(Client);
    }

    public void scegliPrimo(DatagramSocket serverSocket) 
    {
        ClientInfo scelto = clients.get((int) (Math.random() * clients.size()));

        for (ClientInfo clientInfo : clients) 
        {
            try {
                String message;
                if (scelto == clientInfo) {
                    message = "si";
                    System.out.println("Primo: " + clientInfo.getName());
                } else {
                    message = "no";
                }

                byte[] buffer = message.getBytes();
                InetAddress clientAddress = InetAddress.getByName(clientInfo.getAddress());
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, clientAddress, clientInfo.getPort());
                serverSocket.send(packet);
                
            } catch (Exception e) {
                System.err.println("Errore durante l'invio al client " + clientInfo.getName());
                e.printStackTrace();
            }
        }
    }

    public int size() {
        return this.clients.size();
    }

    public boolean controllaFine()
    {
        if(classifica.size() == clients.size() || classifica.size() == clients.size()-1)
            return false;

        for (ClientInfo clientInfo : clients) 
        {
            if(clientInfo.getPunteggio() == 10)
                return false;
        }

        return true;
    }

    public void controllaRound()
    {
        for (ClientInfo clientInfo : clients) 
        {
            if(clientInfo.getPunteggio() < -2)
            {
                classifica.add(clientInfo);
            }
        }
    }

    public ClientInfo get(int i) {
        return this.clients.get(i);
    }

    public ClientInfo get(InetAddress address, int port) throws UnknownHostException
    {
        for (ClientInfo clientInfo : clients) 
        {
            if(clientInfo.confronta(address, port) != null)
            {
                return clientInfo;
            }
        }
        return null;
    }

    public void StampaClassifica()
    {
        this.classifica.stampa();
    }
}
