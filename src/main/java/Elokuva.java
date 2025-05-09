import java.io.Serial;
import java.io.Serializable;

/**
 * Luokka mallintaa elokuvaa varausjärjestelmässä.
 */

public class Elokuva implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String nimi;
    private int kesto;
    private String genre;
    private Ikasuositus ikasuositus;
    private String kieli;

    public Elokuva(String nimi, int kesto, String kieli, String genre, Ikasuositus ikasuositus) {
        this.nimi = nimi;
        this.kesto = kesto;
        this.genre = genre;
        this.kieli = kieli;
        this.ikasuositus = ikasuositus;
    }

    public String getNimi() {
        return nimi;
    }

    public String getGenre() {
        return genre;
    }

    public Ikasuositus getIkasuositus() {
        return ikasuositus;
    }

    public String getKieli() {
        return kieli;
    }

    /**
     * Palauttaa elokuvan keston merkkijonona helposti
     * luettavassa muodossa tunteina ja minuutteina
     *
     * @return kesto merkkijonona
     */
    public String getKestoTunnitMinuutit() {
        return kesto / 60 + "h " + (kesto - kesto / 60 * 60) + "min";
    }

    /**
     * Muuttaa elokuvien tiedot tiedostoon kirjoitettavaan muotoon
     * @param erotinmerkki merkki, jolla elokuvan tiedot erotellaan
     * @return tiedostoon kirjoitettava muoto
     */
    public String getData(String erotinmerkki) {
        String data = nimi + erotinmerkki;
        data += kesto + erotinmerkki;
        data += genre + erotinmerkki;
        data += ikasuositus + erotinmerkki;
        data += kieli;

        return data;
    }

    @Override
    public String toString() {
        return nimi + " (" + getKestoTunnitMinuutit() + "), " + genre + ", ikäsuositus: " + ikasuositus + ", kieli: " + kieli;
    }
}