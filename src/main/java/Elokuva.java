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
    private int ikaraja;
    private String kieli;

    public Elokuva(String nimi, int kesto, String kieli, String genre, int ikaraja) {
        this.nimi = nimi;
        this.kesto = kesto;
        this.genre = genre;
        this.kieli = kieli;
        this.ikaraja = ikaraja;
    }

    public String getNimi() {
        return nimi;
    }

    public String getGenre() {
        return genre;
    }

    public String getKestoTunnitMinuutit() {
        return kesto / 60 + "h " + (kesto - kesto / 60 * 60) + "min";
    }

    public int getIkaraja() {
        return ikaraja;
    }

    public String getKieli() {
        return kieli;
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
        data += ikaraja + erotinmerkki;
        data += kieli;

        return data;
    }

    @Override
    public String toString() {
        return nimi + " (" + getKestoTunnitMinuutit() + "), " + genre + ", ikäraja: " + ikaraja + ", kieli: " + kieli;
    }
}