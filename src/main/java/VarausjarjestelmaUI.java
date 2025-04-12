import java.util.Scanner;

/**
 * Luokka mallintaa asiakkaan käyttöliittymää varausjärjestelmässä.
 */
public class VarausjarjestelmaUI {
    private Varausjarjestelma varausjarjestelma;
    private Scanner lukija;

    public static void main(String[] args) {
        VarausjarjestelmaUI ui = new VarausjarjestelmaUI();

        ui.aloita();
    }

    public VarausjarjestelmaUI() {
        varausjarjestelma = new Varausjarjestelma();
        lukija = new Scanner(System.in);
    }

    public void aloita() {
        int valinta = -1;
        while (valinta != 0) {
            tulostaMenu();
        }
    }

    public void tulostaMenu() {

    }
}