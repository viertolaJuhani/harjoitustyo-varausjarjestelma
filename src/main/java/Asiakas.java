import java.util.ArrayList;

/**
 * Luokka mallintaa asiakasta varausjärjestelmässä.
 */
public class Asiakas extends User {
    private ArrayList<Varaus> varaukset;
    private int ika;

    public Asiakas(String nimi, String email, String salasana, int ika, ArrayList<Varaus> varaukset) {
        super(nimi, email, salasana);
        this.ika = ika;
        this.varaukset = varaukset;
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
        String data = getNimi() + erotinmerkki;
        data += getEmail() + erotinmerkki;
        data += getSalasana() + erotinmerkki;
        data += getIka() + erotinmerkki;
        data += varaukset;

        return data;
    }

    @Override
    public String toString() {
        return getNimi() + ", (" + getEmail() + "), " + ika + ", " + "varaukset: " + varaukset;
    }
}
