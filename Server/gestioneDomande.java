import java.net.DatagramSocket;

public class gestioneDomande {

    private DatagramSocket serverSocket;
    private ClientInGioco clients;
    private Categorie cat;

    public gestioneDomande(DatagramSocket serverSocket, ClientInGioco clients) {
        this.serverSocket = serverSocket;
        this.clients = clients;
    }

    public boolean genera() 
    {
        return false;
    }

}
