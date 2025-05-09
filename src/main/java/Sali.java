import java.io.Serial;
import java.io.Serializable;

/**
 * Luokka mallintaa elokuvateatterin salia varausjärjestelmässä.
 */
public class Sali implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return "Sali: " + salinumero + ", paikkoja yhteensä: " + paikkojaYhteensa();
    }
}