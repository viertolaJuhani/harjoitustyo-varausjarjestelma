import java.util.Scanner;

/**
 * Luokka mallintaa varausjärjestelmän käyttöliittymää.
 *
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
            tulostaAloitus();
            valinta = lueKokonaisluku(0, 2, "Anna valinta");
            if (valinta == 1) {
                tulostaKirjautumisSivu();
            } else if (valinta == 2) {

            }
        }
    }
    public void tulostaAloitus() {
        int valinta = -1;
        while (valinta != 0) {
            System.out.println("1. Kirjaudu sisään");
            System.out.println("2. Luo käyttäjä");
        }
    }

    public void tulostaKirjautumisSivu() {
        String kayttajanimi = lueMerkkijono("Käyttäjänimi");
        String salasana = lueMerkkijono("Salasana");
    }




    public void aloitaAdmin() {
        int valinta = -1;
        while (valinta != 0) {
            tulostaAdminMenu();

        }
    }

    public void aloitaAsiakas() {
        int valinta = -1;
        while (valinta != 0) {
            tulostaAsiakasMenu();

        }
    }

    public void adminElokuvaMenu() {
        int valinta = -1;
        while (valinta != 0) {
            varausjarjestelma.listaaElokuvat();
            System.out.println();
            System.out.println("1. Lisää elokuva");
            System.out.println("2. Poista elokuva");
            System.out.println("0. Poistu");

            valinta = lueKokonaisluku(0, 2, "Anna valinta");
            if (valinta == 1) {
                String nimi = lueMerkkijono("Nimi");
                int kesto = lueKokonaisluku(0, 1000, "Kesto minuutteina");
                String kieli = lueMerkkijono("Kieli");
                String genre = lueMerkkijono("Genre");
                int ikaraja = lueKokonaisluku(0, 18, "Ikäraja");

                varausjarjestelma.lisaaElokuva(new Elokuva(nimi, kesto, kieli, genre, ikaraja));
            } else if (valinta == 2) {

            }
        }
    }

    private int lueKokonaisluku(int minimi, int maksimi, String kehote) {
        while (true) {
            System.out.print(kehote + ": ");
            try {
                int arvo = Integer.parseInt(lukija.nextLine());
                if (arvo >= minimi && arvo <= maksimi) {
                    return arvo;
                }
                System.out.println("Syötä elokuvan kesto minuutteina " +
                        minimi + " - " + maksimi);
            } catch (NumberFormatException nfe) {
                System.out.println("Anna arvo numerona!");
            }
        }
    }
    private String lueMerkkijono(String kehote) {
        while (true) {
            System.out.print(kehote + ": ");
            String arvo = lukija.nextLine();
            if (!arvo.equals("")) {
                return arvo;
            }
        }
    }
}