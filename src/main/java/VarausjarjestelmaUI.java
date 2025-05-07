import java.util.ArrayList;
import java.util.Scanner;

/**
 * Luokka mallintaa varausjärjestelmän käyttöliittymää.
 *
 */
public class VarausjarjestelmaUI {
    private Varausjarjestelma varausjarjestelma;
    private Scanner lukija;
    String asiakasSposti;

    public static void main(String[] args) {
        VarausjarjestelmaUI ui = new VarausjarjestelmaUI();
        ui.aloita();
    }

    public VarausjarjestelmaUI() {
        varausjarjestelma = new Varausjarjestelma();
        lukija = new Scanner(System.in);
    }

    public void aloita() {
        System.out.println("================================");
        System.out.println("Tervetuloa varausjärjestelmään!");
        System.out.println("================================");
        System.out.println();
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
        System.out.println();
        System.out.println("Väärä tunnus tai salasana");
        System.out.println();
    }

    public void uusiAsiakas() {
        System.out.println("Huom! Käyttäjän oltava vähintään 15-vuotias");
        String email;
        while (true) {
            email = lueMerkkijono("Sähköposti");
            if (varausjarjestelma.getKayttajienSpostit().contains(email)) {
                System.out.println("Tämä sähköposti on jo käytössä");
                System.out.println("Kokeile toista sähköpostia");
            } else {
                break;
            }
        }
        String nimi = lueMerkkijono("Nimi");
        int ika = lueKokonaisluku(0, 150, "ikä vuosina");
        if (ika < 15) {
            System.out.println("Sinun on oltava vähintään 15 rekisteröityäksesi järjestelmään.");
            return;
        }
        String salasana = lueMerkkijono("Anna salasana");
        varausjarjestelma.lisaaAsiakas(new Asiakas(nimi, email, salasana, ika));
        varausjarjestelma.kirjoitaTiedot();
    }

    public void asiakasMenu() {
        int valinta = -1;
        while (valinta != 0) {
            System.out.println("1. Tarkastele omia varauksia");
            System.out.println("2. Selaa elokuvia / tee varaus");
            System.out.println("3. Peru varaus");
            System.out.println("0. Kirjaudu ulos");

            valinta = lueKokonaisluku(0, 3, "Anna valinta");
            System.out.println();
            if (valinta == 1) {
                if (!varausjarjestelma.listaaKayttajanVaraukset(asiakasSposti).isEmpty()) {
                    for (Varaus v : varausjarjestelma.listaaKayttajanVaraukset(asiakasSposti)) {
                        System.out.println(v);
                        System.out.println();
                    }
                } else {
                    System.out.println("Ei vielä varauksia");
                    System.out.println();
                }
            }
            if (valinta == 2) {
                for (int i = 0; i < varausjarjestelma.getElokuvat().size(); i++) {
                    System.out.println(i + 1 + ". " + varausjarjestelma.getElokuvat().get(i));
                }
                System.out.println();
                String valinta2 = lueMerkkijono("Tee uusi varaus? K/E");
                if (valinta2.equalsIgnoreCase("K")) {
                paikanVaraus();
                }
                else if (valinta2.equalsIgnoreCase("E")) {
                    System.out.println();
                    continue;
                }
            }
            if (valinta == 3) {
                if (varausjarjestelma.listaaKayttajanVaraukset(asiakasSposti).isEmpty()) {
                    System.out.println("Ei varauksia");
                    System.out.println();
                } else {
                    System.out.println("Valitse varaus, jonka tahdot perua");
                    System.out.println();
                    for (int i = 0; i < varausjarjestelma.listaaKayttajanVaraukset(asiakasSposti).size(); i++) {
                        System.out.println((i + 1) + ". " + varausjarjestelma.listaaKayttajanVaraukset(asiakasSposti).get(i));
                    }
                    System.out.println();
                    valinta = lueKokonaisluku(1, varausjarjestelma.listaaKayttajanVaraukset(asiakasSposti).size(), "Anna varauksen numero, jonka tahdot perua");
                    //Poistetaan ensin istumapaikkojen varaus salikartasta
                    Varaus varaus = varausjarjestelma.listaaKayttajanVaraukset(asiakasSposti).get(valinta-1);
                    Naytos naytos = varaus.getNaytos();
                    boolean[][] salikartta = naytos.getVaraukset();

                    for (Istumapaikka istumapaikka : varaus.getIstumapaikat()) {
                        int rivi = istumapaikka.getRivi();
                        int paikka = istumapaikka.getPaikkaRivilla();
                        salikartta[rivi-1][paikka-1] = false;
                    }
                    if (varausjarjestelma.poistaVaraus(varaus)) {
                        System.out.println();
                        System.out.println("Varaus poistettu");
                        System.out.println();
                        varausjarjestelma.kirjoitaTiedot();
                    }
                }
            }
        }
    }

    public void paikanVaraus() {
        int valinta = -1;
        int evalinta = lueKokonaisluku(1, varausjarjestelma.getElokuvat().size(), "Elokuvan numero");
        Elokuva elokuva = varausjarjestelma.getElokuvat().get(evalinta - 1);
        System.out.println();
        if (varausjarjestelma.onkoNaytoksia(elokuva)) {
            System.out.println("Näytökset:");
            for (int i = 0; i < varausjarjestelma.getElokuvanNaytokset(elokuva).size(); i++) {
                System.out.println(i + 1 + ". " + varausjarjestelma.getElokuvanNaytokset(elokuva).get(i));
            }
            System.out.println();
            int nvalinta = lueKokonaisluku(1, varausjarjestelma.getElokuvanNaytokset(elokuva).size(), "Näytöksen numero");
            Naytos naytos = varausjarjestelma.getElokuvanNaytokset(elokuva).get(nvalinta - 1);
            System.out.println("Valitse istumapaikat (0 = vapaa 1 = varattu)");
            System.out.println("Rivit ja paikat alkaen vasemmalta oikealle ja ylhäältä alas nousevassa järjestyksessä");
            System.out.println();
            System.out.println("\t\tVALKOKANGAS");
            for (int i = 0; i < varausjarjestelma.getSalikartta(naytos).length; i++) {
                for (int j = 0; j < varausjarjestelma.getSalikartta(naytos)[i].length; j++) {
                    System.out.print(varausjarjestelma.getSalikartta(naytos)[i][j] ? "1 " : "0 ");
                }
                System.out.println();
            }
            ArrayList<Istumapaikka> istumapaikat = new ArrayList<>();
            while (valinta != 0) {
                int rivi = lueKokonaisluku(1, naytos.getSali().getRivit(), "Valitse rivi");
                int paikka = lueKokonaisluku(1, naytos.getSali().getPaikatRivilla(), "Valitse paikka");
                if (!naytos.getVaraukset()[rivi - 1][paikka - 1]) {
                    naytos.getVaraukset()[rivi - 1][paikka - 1] = true;
                    istumapaikat.add(new Istumapaikka(rivi, paikka));
                    System.out.println("Paikan varaus onnistui!");
                    System.out.println();
                    System.out.println("1. Varaa toinen paikka");
                    System.out.println("0. Poistu");
                    valinta = lueKokonaisluku(0, 1, "Valinta");
                    if (valinta == 0) {
                        Varaus varaus = new Varaus(asiakasSposti, naytos, istumapaikat);
                        varausjarjestelma.lisaaVaraus(varaus);
                        varausjarjestelma.kirjoitaTiedot();
                        System.out.println("Varaus tallennettu!");
                        System.out.println();
                    }
                } else if (naytos.getVaraukset()[rivi][paikka]) {
                    System.out.println("Tämä paikka on varattu!");
                    System.out.println();
                } else {
                    System.out.println("Varasit jo tämän paikan");
                    System.out.println();
                }
            }
        }
    }

    public void adminMenu() {
        int valinta = -1;
        while (valinta != 0) {
            System.out.println("1. Tarkastele asiakkaiden varauksia");
            System.out.println("2. Hallitse elokuvia");
            System.out.println("3. Hallitse näytöksiä");
            System.out.println("0. Kirjaudu ulos");

            valinta = lueKokonaisluku(0, 3, "Anna valinta");
            System.out.println();
            if (valinta == 1) {
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
        }
        System.out.println();
    }

    public void adminNaytosmenu() {
        int valinta = -1;
        while (valinta != 0) {
            System.out.println("1. Näytä kaikki näytökset");
            System.out.println("2. Näytä tietyn elokuvan näytökset");
            System.out.println("3. Lisää näytös");
            System.out.println("4. Poista näytös");
            System.out.println("5. Katsele salikarttoja");
            System.out.println("0. Poistu");

            valinta = lueKokonaisluku(0, 5, "Anna valinta");
            if (valinta == 1) {
                if (!varausjarjestelma.listaaKaikkiNaytokset().isEmpty()) {
                    System.out.println(varausjarjestelma.listaaKaikkiNaytokset());
                } else {
                    System.out.println("\nEi näytöksiä");
                }
            } else if (valinta == 2) {
                System.out.println("Valitse elokuva, jonka näytökset haluat nähdä");
                System.out.println();
                for (int i = 0; i < varausjarjestelma.getElokuvat().size(); i++) {
                    System.out.println(i + 1 + ". " + varausjarjestelma.getElokuvat().get(i));
                }
                int valinta2 = lueKokonaisluku(1, varausjarjestelma.getElokuvat().size(), "Anna valinta");
                Elokuva elokuva = varausjarjestelma.getElokuvat().get(valinta2 - 1);
                if (!varausjarjestelma.onkoNaytoksia(elokuva)) {
                    System.out.println("Tällä elokuvalla ei ole näytöksiä");
                } else {
                    System.out.println();
                    System.out.println("Nimi\t\t\taika\t\t\tSali");
                    System.out.println(varausjarjestelma.listaaNaytokset(elokuva));
                    System.out.println();
                }

            } else if (valinta == 3) {
                System.out.println("Valitse elokuva, jolle haluat lisätä näytöksen:\n");
                System.out.println(varausjarjestelma.listaaElokuvat());
                while (true) {
                    String nimi = lueMerkkijono("Kirjoita elokuvan nimi");
                    if (varausjarjestelma.onkoElokuvaa(nimi)) {
                        System.out.println("\nValitse sali:\n");
                        System.out.println(varausjarjestelma.listaaSalit());
                        int salinumero = lueKokonaisluku(1, 3, "Salin numero");
                        Sali sali = varausjarjestelma.getSali(salinumero);
                        String aika = lueMerkkijono("Näytös pvm ja aika (pp.kk.hh:mm)");
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
            }else if (valinta == 4) {
                for (int i = 0; i < varausjarjestelma.getNaytokset().size(); i++) {
                    System.out.println(i + 1 + ". " + varausjarjestelma.getNaytokset().get(i));
                }
                valinta = lueKokonaisluku(1, varausjarjestelma.getNaytokset().size(), "Valitse poistettavan näytöksen numero");
                if (varausjarjestelma.poistaNaytos(varausjarjestelma.getNaytokset().get(valinta-1))) {
                    System.out.println();
                    System.out.println("Näytös poistettu");
                    varausjarjestelma.kirjoitaTiedot();
                } else {
                    System.out.println("Poisto epäonnistui");
                }
            } else if (valinta == 5) {
                System.out.println();
                for (int i = 0; i < varausjarjestelma.getNaytokset().size(); i++) {
                    System.out.println(i + 1 + ". " + varausjarjestelma.getNaytokset().get(i));
                }
                System.out.println();
                valinta = lueKokonaisluku(1, varausjarjestelma.getNaytokset().size(), "Valitse näytös, jonka salikartan haluat nähdä");
                Naytos naytos = varausjarjestelma.getNaytokset().get(valinta-1);
                System.out.println();
                System.out.println("1 = varattu ja 0 = vapaa");
                for (int i = 0; i < varausjarjestelma.getSalikartta(naytos).length; i++) {
                    for (int j = 0; j < varausjarjestelma.getSalikartta(naytos)[i].length; j++) {
                        System.out.print(varausjarjestelma.getSalikartta(naytos)[i][j] ? "1 " : "0 ");
                    }
                    System.out.println();
                }
            }
            System.out.println();
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