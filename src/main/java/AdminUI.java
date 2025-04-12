import java.util.Scanner;

/**
 * Ylläpitäjän käyttöliittymää kuvaava luokka
 *
 */
public class AdminUI {
    private Varausjarjestelma varausjarjestelma;
    private Scanner lukija;

    public static void main(String[] args) {
        AdminUI ui = new AdminUI();
        ui.aloita();
    }

    public AdminUI() {
        varausjarjestelma = new Varausjarjestelma();
        lukija = new Scanner(System.in);
    }

    public void aloita() {
        int valinta = -1;
        while (valinta != 0) {
            tulostaMenu();
            valinta = lueKokonaisluku(0, 4, "Anna valinta");
            if (valinta == 1) {
                elokuvaMenu();
            } else if (valinta == 2) {
                naytosMenu();
            } else if (valinta == 3) {
                saliMenu();
            } else if (valinta == 4) {
                asiakasMenu();
            }
        }
    }

    public void tulostaMenu() {
        System.out.println("1. Elokuvat");
        System.out.println("2. Näytökset");
        System.out.println("5. Asiakkaat");
        System.out.println("0. Poistu");
    }

    public void elokuvaMenu() {
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

    public void naytosMenu() {

    }

    public void saliMenu() {

    }

    public void asiakasMenu() {

    }
    private int lueKokonaisluku(int minimi, int maksimi, String kehote) {
        while (true) {
            System.out.print(kehote + ": ");
            try {
                int arvo = Integer.parseInt(lukija.nextLine());
                if (arvo >= minimi && arvo <= maksimi) {
                    return arvo;
                }
                System.out.println("Arvon pitää olla väliltä " +
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
