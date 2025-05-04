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
            aloitusmenu();
            valinta = lueKokonaisluku(0, 2, "Anna valinta");
            if (valinta == 1) {
                kirjautumissivu();
            } else if (valinta == 2) {
                uusiAsiakas();
            }
        }
    }
    public void aloitusmenu() {
        int valinta = -1;
        while (valinta != 0) {
            System.out.println();
            System.out.println("1. Kirjaudu sisään");
            System.out.println("2. Uusi asiakas?");
            System.out.println("0. Poistu");
        }
    }

    public void kirjautumissivu() {
        int valinta = -1;
        while (valinta != 0) {

            String kayttajanimi = lueMerkkijono("Käyttäjänimi");
            String salasana = lueMerkkijono("Salasana");
        }
    }

    public void uusiAsiakas() {
        int valinta = -1;
        while (valinta != 0) {
            System.out.println("Huom! Käyttäjän oltava vähintään 15-vuotias");
            String nimi = lueMerkkijono("Nimi");
            int ika = lueKokonaisluku(15, 150, "ikä vuosina");
            String email = lueMerkkijono("Sähköposti");
            String salasana = lueMerkkijono("Anna salasana");
        }
    }


    public void aloitaAdmin() {
        int valinta = -1;
        while (valinta != 0) {
            adminMenu();
        }
    }

    public void aloitaAsiakas() {
        int valinta = -1;
        while (valinta != 0) {
            asiakasMenu();

        }
    }

    public void asiakasMenu() {
        Scanner scanner = new Scanner(System.in);
        int valinta = -1;
        while (valinta != 0) {
            System.out.println("\n*** ASIAKASMENU ***");
            System.out.println("1. Tarkastele omia varauksia");
            System.out.println("2. Selaa elokuvia / tee varaus");
            System.out.println("0. Poistu");
        }
    }

    public void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        int valinta = -1;
        while (valinta != 0) {
            System.out.println("\n*** ADMIN MENU ***");
            System.out.println("1. Tarkastele varauksia");
            System.out.println("2. Hallitse elokuvia");
            System.out.println("3. Hallitse näytöksiä");
            System.out.println("0. Poistu");
        }
    }

    public void adminElokuvaMenu() {
        int valinta = -1;
        while (valinta != 0) {
            System.out.println(varausjarjestelma.listaaElokuvat());
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
                System.out.println(varausjarjestelma.listaaElokuvat());
                System.out.println();

                String poistettava_elokuva = lueMerkkijono("Anna elokuvan nimi");
                varausjarjestelma.poistaElokuva(poistettava_elokuva);
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
                System.out.println("Arvon tulee olla väliltä " +
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