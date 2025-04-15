import java.util.ArrayList;

/**
 * Luokka mallintaa asiakasta varausjärjestelmässä.
 */
public class Asiakas extends User {
    private ArrayList<Varaus> varaukset;
    private int ika;

    public Asiakas(String nimi, String email, String kayttajanimi, String salasana, int ika) {
        super(nimi, email, kayttajanimi, salasana);
        this.varaukset = new ArrayList<>();
        this.ika = ika;
    }

    public ArrayList<Varaus> getVaraukset() {
        return varaukset;
    }

    public int getIka() {
        return ika;
    }

    public void setIka(int ika) {
        this.ika = ika;
    }

    public void lisaaVaraus(Varaus varaus) {
        varaukset.add(varaus);
    }

    public String getTyyppi() {
        return "asiakas";
    }

    /**
     * Muuttaa asiakkaan tiedot tiedostoon kirjoitettavaan muotoon
     * @param erotinmerkki asiakkaat toisistaan erottava merkki
     * @return tiedostoon kirjoitettava muoto
     */
    public String getData(String erotinmerkki) {
        String data = "A " + getEmail() + getNimi() + " Varaukset: " + varaukset + erotinmerkki;
        return data;
    }
}
