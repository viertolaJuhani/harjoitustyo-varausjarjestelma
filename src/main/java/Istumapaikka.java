import java.io.Serializable;

/**
 * Luokka mallintaa istumapaikkaa elokuvasalissa.
 */
public class Istumapaikka implements Serializable {

    private static final long serialVersionUID = 1L;

    private int rivi;
    private int paikkaRivilla;
    private boolean varattu = false;

    public Istumapaikka(int rivi, int paikkaRivilla) {
        this.rivi = rivi;
        this.paikkaRivilla = paikkaRivilla;
    }

    @Override
    public String toString() {
        return "Rivi: " + rivi + ", Paikka: " + paikkaRivilla;
    }
}