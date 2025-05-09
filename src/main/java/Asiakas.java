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

    /**
     * Muuttaa asiakkaan tiedot tiedostoon kirjoitettavaan muotoon
     * @param erotinmerkki merkki, jolla asiakkaan tiedot erotellaan
     * @return tiedostoon kirjoitettava muoto
     */
    @Override
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
