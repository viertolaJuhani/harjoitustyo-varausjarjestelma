/**
 * Luokka mallintaa elokuvateatterin salia varausjärjestelmässä.
 */
public class Sali {
    private int salinumero;
    private int rivit;
    private int paikatRivilla;

    public Sali(int salinumero, int rivit, int paikatRivilla) {
        this.salinumero = salinumero;
        this.rivit = rivit;
        this.paikatRivilla = paikatRivilla;
    }

    public int getSalinumero() {
        return salinumero;
    }

    public void setSalinumero(int salinumero) {
        this.salinumero = salinumero;
    }

    public int getRivit() {
        return rivit;
    }

    public int getPaikatRivilla() {
        return paikatRivilla;
    }

    public int paikkojaYhteensa() {
        return rivit * paikatRivilla;
    }
}