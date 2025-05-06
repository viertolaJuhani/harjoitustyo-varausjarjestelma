import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Luokka mallintaa varausjärjestelmän käyttöliittymää.
 *
 */
public class VarausjarjestelmaUI {
    private Varausjarjestelma varausjarjestelma;
    private Scanner lukija;
    String asiakasSposti = "";
    String kirjautunutNimi = "";

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
                kirjaudu();
            } else if (valinta == 2) {
                uusiAsiakas();
            }
        }
        varausjarjestelma.kirjoitaTiedot();
    }

    public void aloitusmenu() {
            System.out.println();
            System.out.println("1. Kirjaudu sisään");
            System.out.println("2. Uusi asiakas?");
            System.out.println("0. Poistu");
    }

    static User kirjautunut = null;

    public void kirjaudu() {
        String kayttajanimi = lueMerkkijono("Käyttäjätunnus (sähköposti)");
        String salasana = lueMerkkijono("Salasana");
        for (User u : varausjarjestelma.getKayttajaLista()) {
            if (u.getEmail().equals(kayttajanimi) && u.getSalasana().equals(salasana)) {
                kirjautunut = u;
                asiakasSposti = u.getEmail();

                if (u instanceof Asiakas) {
                    System.out.println("\nTervetuloa " + u.getNimi());
                    System.out.println("\n*** ASIAKASMENU ***");
                    asiakasMenu();
                    return;
                } else if (u instanceof Admin) {
                    System.out.println("\n*** Tervetuloa ADMIN ***\n");
                    adminMenu();
                    return;
                }
            }
        }
        System.out.println("Väärä tunnus tai salasana");
    }

    public void uusiAsiakas() {
        System.out.println("Huom! Käyttäjän oltava vähintään 15-vuotias");
        String nimi = lueMerkkijono("Nimi");
        int ika = lueKokonaisluku(15, 150, "ikä vuosina");
        String email = lueMerkkijono("Sähköposti");
        String salasana = lueMerkkijono("Anna salasana");
        varausjarjestelma.lisaaAsiakas(new Asiakas(nimi, email, salasana, ika));
        varausjarjestelma.kirjoitaTiedot();
    }

    public void asiakasMenu() {
        int valinta = -1;
        while (valinta != 0) {
            System.out.println();
            System.out.println("1. Tarkastele omia varauksia");
            System.out.println("2. Selaa elokuvia / tee varaus");
            System.out.println("3. Peru varaus");
            System.out.println("0. Kirjaudu ulos");
            System.out.println();

            valinta = lueKokonaisluku(0, 3, "Anna valinta");
            if (valinta == 1) {
                for (Varaus v : varausjarjestelma.getVaraukset()) {
                    if (v.getAsiakasEmail().equals(asiakasSposti)) {
                        System.out.println(v);
                    } else {
                        System.out.println("Ei vielä varauksia");
                    }
                }
            }
            if (valinta == 2) {
                System.out.println(varausjarjestelma.listaaElokuvat());
                String valinta2 = lueMerkkijono("Tee uusi varaus? K/E");
                if (valinta2.equals("K")) {

                }
                if (valinta2.equals("E")) {
                    continue;
                }
                else {
                    System.out.println("K tai E");
                }
            }
            if (valinta == 3) {
                System.out.println();
                if (varausjarjestelma.listaaKayttajanVaraukset(asiakasSposti).isEmpty()) {
                    System.out.println("Ei varauksia");
                } else {
                    System.out.println("Valitse varaus, jonka tahdot perua");
                    System.out.println();
                    for (int i = 0; i < varausjarjestelma.listaaKayttajanVaraukset(asiakasSposti).size(); i++) {
                        System.out.println((i+1) + ". " + varausjarjestelma.listaaKayttajanVaraukset(asiakasSposti).get(i));
                    }
                    valinta = lueKokonaisluku(1, varausjarjestelma.listaaKayttajanVaraukset(asiakasSposti).size(), "Anna varauksen numero, jonka tahdot perua");
                    if (varausjarjestelma.poistaVaraus(varausjarjestelma.listaaKayttajanVaraukset(asiakasSposti).get(valinta-1))) {
                        System.out.println("Varaus poistettu");
                    }
                }
            }
        }
    }

    public void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        int valinta = -1;
        while (valinta != 0) {
            System.out.println("1. Tarkastele asiakkaiden varauksia");
            System.out.println("2. Hallitse elokuvia");
            System.out.println("3. Hallitse näytöksiä");
            System.out.println("0. Kirjaudu ulos");
            System.out.println();

            valinta = lueKokonaisluku(0, 3, "Anna valinta");
            if (valinta == 1) {
                System.out.println();
                System.out.println(varausjarjestelma.listaaVaraukset());
            }else if (valinta == 2) {
                adminElokuvaMenu();
            } else if (valinta == 3) {
                adminNaytosmenu();
            }
        }
    }

    public void adminElokuvaMenu() {
        int valinta = -1;
        while (valinta != 0) {
            System.out.println();
            System.out.println(varausjarjestelma.listaaElokuvat());
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
                String poistettava_elokuva = lueMerkkijono("Anna elokuvan nimi");
                varausjarjestelma.poistaElokuva(poistettava_elokuva);
            }
            System.out.println();
        }
    }

    public void adminNaytosmenu() {
        int valinta = -1;
        while (valinta != 0) {
            System.out.println("1. Näytä kaikki näytökset");
            System.out.println("2. Näytä tietyn elokuvan näytökset");
            System.out.println("3. Lisää näytös");
            System.out.println("4. Poista näytös");
            System.out.println("0. Poistu");

            valinta = lueKokonaisluku(0, 4, "Anna valinta");
            if (valinta == 1) {
                System.out.println();
                System.out.println(varausjarjestelma.listaaKaikkiNaytokset());
            } else if (valinta == 2) {
                System.out.println();
                System.out.println("Valitse elokuva, jonka näytökset haluat nähdä");




            } else if (valinta == 3) {
                System.out.println();
                System.out.println("Valitse elokuva, jolle haluat lisätä näytöksen:\n");
                System.out.println(varausjarjestelma.listaaElokuvat());
                while (true) {
                    String nimi = lueMerkkijono("Kirjoita elokuvan nimi tai poistu (0)");
                    if (varausjarjestelma.onkoElokuvaa(nimi)) {
                        System.out.println("\nValitse sali:\n");
                        System.out.println(varausjarjestelma.listaaSalit());
                        int salinumero = lueKokonaisluku(1, 3, "Salin numero");
                        Sali sali = varausjarjestelma.getSali(salinumero);
                        String aika = lueMerkkijono("Näytös pvm ja aika (pp.kk.hh.mm)");
                        Naytos naytos = new Naytos(nimi, sali, aika);
                        varausjarjestelma.lisaaNaytos(naytos);
                        varausjarjestelma.kirjoitaTiedot();
                        break;
                    } else if (nimi.equals("0")) {
                        break;
                    } else {
                        System.out.println("Elokuvaa ei löytynyt");
                        System.out.println("Tarkista nimen oikeinkirjoitus");
                    }
                }
            }
            if (valinta == 4) {
                for (int i = 0; i < varausjarjestelma.getNaytokset().size(); i++) {
                    System.out.println(i + 1 + ". " + varausjarjestelma.getNaytokset().get(i));
                }
                valinta = lueKokonaisluku(1, varausjarjestelma.getNaytokset().size(), "Valitse poistettavan näytöksen numero");
                if (varausjarjestelma.poistaNaytos(varausjarjestelma.getNaytokset().get(valinta-1))) {
                    System.out.println("Näytös poistettu");
                    varausjarjestelma.kirjoitaTiedot();
                } else {
                    System.out.println("Poisto epäonnistui");
                }
            }
        }
        System.out.println();
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