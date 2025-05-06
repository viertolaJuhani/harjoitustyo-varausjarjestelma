import java.io.Serializable;

/**
 * Luokka mallintaa elokuvaa varausjärjestelmässä.
 */

public class Elokuva implements Serializable {

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

    public int getKesto() {
        return kesto;
    }

    public String getGenre() {
        return genre;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setKesto(int kesto) {
        this.kesto = kesto;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getIkaraja() {
        return ikaraja;
    }

    public void setIkaraja(int ikaraja) {
        this.ikaraja = ikaraja;
    }

    public String getKieli() {
        return kieli;
    }

    public void setKieli(String kieli) {
        this.kieli = kieli;
    }

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
        return nimi + " (" + kesto + " min), " + genre + ", ikäraja: " + ikaraja + ", kieli: " + kieli;
    }
}
