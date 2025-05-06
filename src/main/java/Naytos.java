import java.io.Serializable;

/**
 * Luokka mallintaa elokuvanäytöstä ja varaustilannetta tietyssä salissa.
 * Varaukset tallennetaan 2D-taulukkoon, jossa jokainen paikka voi olla
 * varattu tai vapaa.
 */
public class Naytos implements Serializable {

    private static final long serialVersionUID = 1L;

    private Sali sali;
    private String elokuvanNimi;
    private String naytosaika;
    private boolean[][] varaukset;

    public Naytos(String elokuvanNimi, Sali sali, String naytosaika) {
        this.elokuvanNimi = elokuvanNimi;
        this.naytosaika = naytosaika;
        this.sali = sali;
        varaukset = new boolean[sali.getRivit()][sali.getPaikatRivilla()];
    }

    public String getElokuvanNimi() {
        return elokuvanNimi;
    }

    public void setElokuva(String elokuvanNimi) {
        this.elokuvanNimi = elokuvanNimi;
    }

    public String getNaytosaika() {
        return naytosaika;
    }

    public void setNaytosaika(String naytosaika) {
        this.naytosaika = naytosaika;
    }

    public Sali getSali() {
        return sali;
    }

    public boolean[][] getVaraukset() {
        return varaukset;
    }

    public void setVaraukset(int rivi, int paikkaRivilla) {
        varaukset[rivi][paikkaRivilla] = true;
    }

    public String getData(String erotinmerkki) {
        String data = sali + erotinmerkki;
        data += elokuvanNimi + erotinmerkki;
        data += naytosaika + erotinmerkki;
        data += varaukset;

        return data;
    }

    @Override
    public String toString() {
        return "Elokuva: " + elokuvanNimi + ", Näytösaika: " + naytosaika + ", Sali: " + sali.getSalinumero();
    }
}