import java.util.ArrayList;

public class Sali {
    private int salinumero;
    private int rivit;
    private int paikatRivilla;
    private boolean[][] paikat;

    public Sali(int salinumero, int rivit, int paikatRivilla) {
        this.salinumero = salinumero;
        this.rivit = rivit;
        this.paikatRivilla = paikatRivilla;
        this.paikat = new boolean[rivit][paikatRivilla];
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

    public void setRivit(int rivit) {
        this.rivit = rivit;
    }

    public int getPaikatRivilla() {
        return paikatRivilla;
    }

    public void setPaikatRivilla(int paikatRivilla) {
        this.paikatRivilla = paikatRivilla;
    }

    public int istumapaikkojaYhteensa() {
        return rivit * paikatRivilla;
    }

    public boolean[][] getPaikat() {
        return paikat;
    }

    public void setPaikat(boolean[][] paikat) {
        this.paikat = paikat;
    }


    public boolean onkoPaikkaVapaa(int rivi, int paikka) {
        return !paikat[rivi][paikka];
    }

    public void merkitseVaraus(int rivi, int paikka) {
        paikat[rivi][paikka] = true;
    }

    public void tulostaPaikat(Sali sali) {
        for (int i=0; i<sali.getRivit(); i++) {
            for (int j=0; j<sali.getPaikatRivilla(); j++) {
                System.out.print(sali.getPaikat()[i][j] ? "X " : "O ");
            }
        }
    }
}