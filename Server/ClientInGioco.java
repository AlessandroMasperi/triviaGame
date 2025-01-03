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
}
