import java.util.ArrayList;

/**
 * Luokka mallintaa asiakasta varausjärjestelmässä.
 *
 */
public class Asiakas {
    private ArrayList<Varaus> varaukset;
    private String nimi;
    private String email;
    private int ika;

    public Asiakas(String nimi, String email) {
        this.nimi = nimi;
        this.email = email;
        this.varaukset = new ArrayList<>();
    }

    public ArrayList<Varaus> getVaraukset() {
        return varaukset;
    }

    public void setVaraukset(ArrayList<Varaus> varaukset) {
        this.varaukset = varaukset;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int getIka() {
        return ika;
    }

    public void setIka(int ika) {
        this.ika = ika;
    }

    /**
     * Muuttaa asiakkaan tiedot tiedostoon kirjoitettavaan muotoon
     * @param erotinmerkki asiakkaat toisistaan erottava merkki
     * @return tiedostoon kirjoitettava muoto
     */
    public String getData(String erotinmerkki) {
        String data = "A " + email + nimi + " Varaukset: " + varaukset + erotinmerkki;
        return data;
    }
}
