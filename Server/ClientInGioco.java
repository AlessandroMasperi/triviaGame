import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class ClientInGioco 
{
    private ArrayList<ClientInfo> clients;

    public ClientInGioco()
    {
        clients = new ArrayList<>();
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
}
