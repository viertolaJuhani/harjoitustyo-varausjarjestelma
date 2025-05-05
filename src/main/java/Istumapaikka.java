/**
 * Luokka mallintaa istumapaikkaa elokuvasalissa.
 */
public class Istumapaikka {
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