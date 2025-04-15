/**
 * Luokka mallintaa elokuvanäytöstä ja varaustilannetta tietyssä salissa.
 * Varaukset tallennetaan 2D-taulukkoon, jossa jokainen paikka voi olla
 * varattu tai vapaa.
 */
public class Naytos {
    private Sali sali;
    private Elokuva elokuva;
    private String elokuvanNimi;
    private String naytosaika;
    private int salinumero;
    private boolean[][] varaukset;

    public Naytos(String elokuvanNimi, String naytosaika, Sali sali) {
        this.elokuvanNimi = elokuva.getNimi();
        this.naytosaika = naytosaika;
        this.salinumero = sali.getSalinumero();
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

    public int getSali() {
        return salinumero;
    }

    public void setSali(Sali sali) {
        this.salinumero = salinumero;
    }

    public boolean[][] getVaraukset() {
        return varaukset;
    }

    public void setVaraukset(int rivi, int paikkaRivilla) {
        varaukset[rivi][paikkaRivilla] = true;
    }

    /**
     * Tarkistaa, onko tietty paikka varattu näytöksessä.
     *
     * @param rivi Paikan rivinumero
     * @param paikka Paikan numero rivillä
     * @return true, jos paikka on varattu, muuten false
     */
    public boolean onkoVarattu(int rivi, int paikka) {
        if (rivi >= 0 && rivi <= sali.getRivit() && paikka >= 0 && paikka < sali.getPaikatRivilla()) {
            return !varaukset[rivi][paikka];
        }
        return false;
    }

    @Override
    public String toString() {
        return "Näytös: " + elokuvanNimi + ", " + ", sali: " + sali;
    }
}