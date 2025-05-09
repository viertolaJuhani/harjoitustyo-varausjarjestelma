import java.io.Serial;
import java.io.Serializable;

/**
 * Luokka mallintaa elokuvanäytöstä ja varaustilannetta tietyssä salissa.
 * Varaukset tallennetaan 2D-taulukkoon, jossa jokainen paikka voi olla
 * varattu tai vapaa.
 */
public class Naytos implements Serializable {

    @Serial
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

    /**
     * Asettaa näytöksen salikartassa paikan varatuksi
     * @param rivi paikan rivinumero
     * @param paikkaRivilla paikka rivillä
     * @return
     */
    public void setVaratuksi(int rivi, int paikkaRivilla) {
        varaukset[rivi-1][paikkaRivilla-1] = true;
    }

    /**
     * Asettaa näytöksen salikartassa paikan vapaaksi
     * @param rivi paikan rivinumero
     * @param paikkaRivilla paikka rivillä
     */
    public void setVapaaksi(int rivi, int paikkaRivilla) {
        varaukset[rivi-1][paikkaRivilla-1] = false;
    }

    /**
     * Palauttaa tiedon siitä, onko tietty istumapaikka näytöksessä varattu
     * @param rivi paikan rivinumero
     * @param paikkaRivilla paikka rivillä
     * @return true, jos varattu ja false, jos vapaa
     */
    public boolean onkoPaikkaVapaa(int rivi, int paikkaRivilla) {
        return varaukset[rivi - 1][paikkaRivilla - 1];
    }

    @Override
    public String toString() {
        return "Elokuva: " + elokuvanNimi + ", Näytösaika: " + naytosaika + ", Sali: " + sali.getSalinumero();
    }
}