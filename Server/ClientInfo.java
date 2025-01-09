import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientInfo {
    private String name;
    private String address;
    private int port;
    private int punteggio;

    public ClientInfo(String name, String address, int port) {
        this.name = name;
        this.address = address;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void incrementa()
    {
        this.punteggio++;
    }

    public void decrementa()
    {
        this.punteggio--;
    }

    public ClientInfo confronta(InetAddress indirizzo, int porta) throws UnknownHostException
    {
        InetAddress corrente = InetAddress.getByName(this.address);

        if(corrente.equals(indirizzo) && porta == this.port)
            return this;
        else
            return null;
    }
    
}
