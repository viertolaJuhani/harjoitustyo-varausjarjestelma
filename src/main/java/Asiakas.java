import java.util.ArrayList;

/**
 * Luokka mallintaa asiakasta varausjärjestelmässä.
 */
public class Asiakas extends User {
    private int ika;

    public Asiakas(String nimi, String email, String salasana, int ika) {
        super(nimi, email, salasana);
        this.ika = ika;
    }

    public int getIka() {
        return ika;
    }

    public void setIka(int ika) {
        this.ika = ika;
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
        data += getIka();

        return data;
    }

    @Override
    public String toString() {
        return getNimi() + " (" + getEmail() + "), " + ika;
    }
}
