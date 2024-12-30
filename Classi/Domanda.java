//classe che gestisce la domanda di un quiz
public class Domanda
{
    private String categoria; //categoria di una domanda
    private String domanda; //testo della domanda
    private String rispostaCorretta; //risposta corretta di una domanda
    private String risposta2; //risposta alternativa di una domanda
    private String risposta3; //risposta alternativa di una domanda
    private String risposta4; //risposta alternativa di una domanda

    /**
     * Costruttore di default
     */
    public Domanda()
    {
        this.categoria = "";
        this.domanda = "";
        this.rispostaCorretta = "";
        this.risposta2 = "";
        this.risposta3 = "";
        this.risposta4 = "";
    }

    /**
     * Costruttore con parametri
     * @param categoria categoria di una domanda
     * @param domanda testo della domanda
     * @param rispostaCorretta risposta corretta di una domanda
     * @param risposta2 risposta alternativa di una domanda
     * @param risposta3 risposta alternativa di una domanda
     * @param risposta4 risposta alternativa di una domanda
     */
    public Domanda(String categoria, String domanda, String rispostaCorretta, String risposta2, String risposta3, String risposta4) {
        this.categoria = categoria;
        this.domanda = domanda;
        this.rispostaCorretta = rispostaCorretta;
        this.risposta2 = risposta2;
        this.risposta3 = risposta3;
        this.risposta4 = risposta4;
    }

    //fai la conversione in csv
    public String toCSV()
    {
        return categoria + ";" + domanda + ";" + rispostaCorretta + ";" + risposta2 + ";" + risposta3 + ";" + risposta4;
    }

    //fai la conversione da csv
    public Domanda fromCSV(String contenuto)
    {
        String[] campi = contenuto.split(";");
        //controlla se la lunghezza dei campi è corretta
        if (campi.length == 6) {
            this.categoria = campi[0];
            this.domanda = campi[1];
            this.rispostaCorretta = campi[2];
            this.risposta2 = campi[3];
            this.risposta3 = campi[4];
            this.risposta4 = campi[5];
            return new Domanda(categoria, domanda, rispostaCorretta, risposta2, risposta3, risposta4);
        } else {
            throw new IllegalArgumentException("Il contenuto CSV non è valido: " + contenuto);
        }
    }

    //Getters and Setters
    /**
     * @return la categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria categoria di una domanda
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return il testo della domanda
     */
    public String getDomanda() {
        return domanda;
    }
    
    /**
     * @param domanda testo della domanda
     */
    public void setDomanda(String domanda) {
        this.domanda = domanda;
    }

    /**
     * @return la risposta corretta
     */
    public String getRispostaCorretta() {
        return rispostaCorretta;
    }

    /**
     * @param rispostaCorretta risposta corretta di una domanda
     */
    public void setRispostaCorretta(String rispostaCorretta) {
        this.rispostaCorretta = rispostaCorretta;
    }

    /**
     * @return la risposta sbagliata
     */
    public String getRisposta2() {
        return risposta2;
    }

    /**
     * @param risposta2 risposta sbagliata di una domanda
     */
    public void setRisposta2(String risposta2) {
        this.risposta2 = risposta2;
    }

    /**
     * @return la risposta sbagliata 2
     */
    public String getRisposta3() {
        return risposta3;
    }

    /**
     * @param risposta3 risposta sbagliata 2 di una domanda
     */
    public void setRisposta3(String risposta3) {
        this.risposta3 = risposta3;
    }

    /**
     * @return la risposta sbagliata 3
     */
    public String getRisposta4() {
        return risposta4;
    }

    /**
     * @param risposta4 risposta sbagliata 3 di una domanda
     */
    public void setRisposta4(String risposta4) {
        this.risposta4 = risposta4;
    }
}