import java.time.LocalDateTime;

/**
 * Luokka mallintaa elokuvanäytöstä ja varaustilannetta tietyssä salissa.
 *
 * Varaukset tallennetaan 2D-taulukkoon, jossa jokainen paikka voi olla
 * varattu tai vapaa.
 */
public class Naytos {
    private Elokuva elokuva;
    private LocalDateTime naytosaika;
    private Sali sali;
    private boolean[][] varaukset;

    public Naytos(Elokuva elokuva, LocalDateTime naytosaika, Sali sali) {
        this.elokuva = elokuva;
        this.naytosaika = naytosaika;
        this.sali = sali;
        varaukset = new boolean[sali.getRivit()][sali.getPaikatRivilla()];
    }

    public Elokuva getElokuva() {
        return elokuva;
    }

    public void setElokuva(Elokuva elokuva) {
        this.elokuva = elokuva;
    }

    public LocalDateTime getNaytosaika() {
        return naytosaika;
    }

    public void setNaytosaika(LocalDateTime naytosaika) {
        this.naytosaika = naytosaika;
    }

    public Sali getSali() {
        return sali;
    }

    public void setSali(Sali sali) {
        this.sali = sali;
    }

    public int getRivit() {
        return sali.getRivit();
    }

    public int getPaikatPerRivi() {
        return sali.getPaikatRivilla();
    }

    public boolean[][] getVaraukset() {
        return varaukset;
    }

    public void setVaraukset(boolean[][] varaukset) {
        this.varaukset = varaukset;
    }

    /**
     * Tarkistaa, onko tietty paikka varattu näytöksessä.
     *
     * @param rivi Paikan rivinumero
     * @param paikka Paikan numero rivillä
     * @return true, jos paikka on varattu; false, jos vapaa
     */
    public boolean onkoVarattu(int rivi, int paikka) {
        if (rivi >= 0 && rivi < sali.getRivit() && paikka >= 0 && paikka < sali.getPaikatRivilla()) {
            return varaukset[rivi][paikka];
        }
        return false;
    }

    /**
     * Varaa paikan tietyssä näytökksessä.
     * @param rivi Paikan rivinumero
     * @param paikka Paikan numero rivillä
     * @return true, jos varaus onnistui; false, jos epäonnistui
     */
    public boolean varaaPaikka(int rivi, int paikka) {
        if (rivi >= 0 && rivi < sali.getRivit() && paikka >= 0 && paikka < sali.getPaikatRivilla()) {
            if (!onkoVarattu(rivi, paikka)) {
                varaukset[rivi][paikka] = true;
                return true;
            }
        }
        return false;
    }
}