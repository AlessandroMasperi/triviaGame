public class MainServer {
    public static void main(String[] args) {
        ClientInGioco clients = new ClientInGioco();
        Server.connessione(12345, clients);
        int a = 0;
    }
}
