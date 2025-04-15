/**
 * Luokka mallintaa istumapaikkaa elokuvasalissa.
 */
public class Istumapaikka {
    private int rivi;
    private int paikkaRivilla;
    private boolean varattu = false;

    public Istumapaikka(int rivi, int paikkaRivilla, boolean varattu) {
        this.rivi = rivi;
        this.paikkaRivilla = paikkaRivilla;
        this.varattu = varattu;
    }

    public Istumapaikka(int rivi, int paikkaRivilla) {
        this (rivi, paikkaRivilla, false);
    }
}