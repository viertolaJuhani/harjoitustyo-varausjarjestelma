import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Luokka mallintaa varausjärjestelmän käyttöliittymää.
 *
 */
public class VarausjarjestelmaUI {
    private Varausjarjestelma varausjarjestelma;
    private Scanner lukija;
    String kirjautunutEmail;
    int asiakasIka;

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

    public void kirjaudu() {
        String kayttajanimi = lueMerkkijono("Käyttäjätunnus (sähköposti)");
        String salasana = lueMerkkijono("Salasana");
        for (User u : varausjarjestelma.getKayttajaLista()) {
            if (u.getEmail().equals(kayttajanimi) && u.getSalasana().equals(salasana)) {
                kirjautunutEmail = u.getEmail();

                if (u instanceof Asiakas) {
                    asiakasIka = ((Asiakas) u).getIka();
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
        System.out.println("Rekisteröidy valitsemalla 2.");
        System.out.println();
    }

    public void uusiAsiakas() {
        System.out.println("Huom! Käyttäjän oltava vähintään 15-vuotias");
        String email;
        while (true) {
            email = lueMerkkijono("Sähköposti");
            if (varausjarjestelma.getKayttajienSpostit().contains(email)) {
                System.out.println("Kirjautuminen epäonnistui. Tämä sähköposti on jo käytössä.");
                System.out.println("Kokeile toista sähköpostia tai kirjaudu sisään");
            } else {
                break;
            }
        }
        String nimi = lueMerkkijono("Nimi");
        int ika = lueKokonaisluku(0, 150, "ikä vuosina");
        if (ika < 15) {
            System.out.println();
            System.out.println("Sinun on oltava vähintään 15 rekisteröityäksesi järjestelmään.");
            System.out.println();
            return;
        }
        String salasana = lueMerkkijono("Anna salasana");
        System.out.println();
        varausjarjestelma.lisaaAsiakas(new Asiakas(nimi, email, salasana, ika));
        varausjarjestelma.kirjoitaTiedot();
        System.out.println("Rekisteröinti valmis!");
        System.out.println("Voit nyt kirjautua sisään");
        System.out.println();
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
                if (!varausjarjestelma.getKayttajanVaraukset(kirjautunutEmail).isEmpty()) {
                    System.out.println(varausjarjestelma.listaaKayttajanVaraukset(kirjautunutEmail));
                } else {
                    System.out.println("Ei vielä varauksia");
                    System.out.println();
                }
            }
            if (valinta == 2) {
                System.out.println(varausjarjestelma.listaaElokuvat());
                System.out.println("1. Tee varaus");
                System.out.println("0. Poistu");
                valinta = lueKokonaisluku(0,1,"Valinta");
                if (valinta == 1) {
                paikanVaraus();
                }
                else if (valinta == 0) {
                    System.out.println();
                    continue;
                }
            }
            if (valinta == 3) {
                if (varausjarjestelma.getKayttajanVaraukset(kirjautunutEmail).isEmpty()) {
                    System.out.println("Ei varauksia");
                    System.out.println();
                } else {
                    System.out.println("Varaukset:");
                    System.out.println();
                    for (int i = 0; i < varausjarjestelma.getKayttajanVaraukset(kirjautunutEmail).size(); i++) {
                        System.out.println((i + 1) + ". " + varausjarjestelma.getKayttajanVaraukset(kirjautunutEmail).get(i));
                    }
                    System.out.println();
                    valinta = lueKokonaisluku(0, varausjarjestelma.getKayttajanVaraukset(kirjautunutEmail).size(), "Anna varauksen numero, jonka tahdot perua tai poistu (0)");
                    System.out.println();
                    if (valinta == 0) {
                        break;
                    }
                    //Poistetaan istumapaikkojen varaus salikartasta
                    Varaus varaus = varausjarjestelma.getKayttajanVaraukset(kirjautunutEmail).get(valinta-1);
                    Naytos naytos = varaus.getNaytos();

                    for (Istumapaikka istumapaikka : varaus.getIstumapaikat()) {
                        int rivi = istumapaikka.getRivi();
                        int paikka = istumapaikka.getPaikkaRivilla();
                        naytos.setVapaaksi(rivi, paikka);
                    }
                    if (varausjarjestelma.poistaVaraus(varaus)) {
                        System.out.println();
                        System.out.println("Varaus peruttu");
                        System.out.println();
                        varausjarjestelma.kirjoitaTiedot();
                    } else {
                        System.out.println("Varauksen poisto epäonnistui");
                    }
                }
            }
        }
    }

    public void paikanVaraus() {
        int valinta;
        int evalinta = lueKokonaisluku(1, varausjarjestelma.getElokuvat().size(), "Elokuvan numero");
        System.out.println();
        Elokuva elokuva = varausjarjestelma.getElokuvat().get(evalinta - 1);
        if (!varausjarjestelma.riittaakoIka(asiakasIka,elokuva)) {
            System.out.println("Tämän elokuvan ikäsuositus on " + elokuva.getIkasuositus() + ", Kokeile toista elokuvaa.");
            System.out.println("Palataan päävalikkoon");
            System.out.println();
            return;
        }
        if (varausjarjestelma.onkoNaytoksia(elokuva)) {
            System.out.println(varausjarjestelma.listaaElokuvanNaytokset(elokuva));
            int nvalinta = lueKokonaisluku(0, varausjarjestelma.getElokuvanNaytokset(elokuva).size(), "Näytöksen numero tai poistu (0)");
            System.out.println();
            if (nvalinta == 0) {
                return;
            }
            Naytos naytos = varausjarjestelma.getElokuvanNaytokset(elokuva).get(nvalinta - 1);
            System.out.println("Valitse istumapaikat (0 = vapaa 1 = varattu)");
            System.out.println("Rivit ja paikat alkaen vasemmalta oikealle ja ylhäältä alas nousevassa järjestyksessä");
            System.out.println();
            System.out.println("\t\tVALKOKANGAS");
            System.out.println(varausjarjestelma.tulostaSalikartta(naytos));
            ArrayList<Istumapaikka> istumapaikat = new ArrayList<>();
            while (true) {
                System.out.println("Valitse rivi tai poistu syöttämällä 0.");
                System.out.println();
                int rivi = lueKokonaisluku(0, naytos.getSali().getRivit(), "Valitse rivi");
                if (rivi == 0) {
                    if (!istumapaikat.isEmpty()) {
                        varausjarjestelma.lisaaVaraus(new Varaus(kirjautunutEmail, naytos, istumapaikat));
                        varausjarjestelma.kirjoitaTiedot();
                    }
                    System.out.println("Varaus keskeytetty");
                    System.out.println("Jos varasit jo paikkoja, niiden varaus tallennettu!");
                    System.out.println();
                    break;
                }
                int paikka = lueKokonaisluku(1, naytos.getSali().getPaikatRivilla(), "Valitse paikka");
                System.out.println();
                if (!naytos.onkoPaikkaVapaa(rivi, paikka)) {
                    istumapaikat.add(new Istumapaikka(rivi, paikka));
                    System.out.println("Paikan varaus onnistui!");
                    System.out.println();
                    System.out.println("1. Varaa toinen paikka");
                    System.out.println("0. Poistu ja tallenna varaus");
                    valinta = lueKokonaisluku(0, 1, "Valinta");
                    System.out.println();
                    naytos.setVaratuksi(rivi, paikka);
                    if (valinta == 0) {
                        Varaus varaus = new Varaus(kirjautunutEmail, naytos, istumapaikat);
                        varausjarjestelma.lisaaVaraus(varaus);
                        varausjarjestelma.kirjoitaTiedot();
                        System.out.println("Varaus tallennettu!");
                        System.out.println();
                        break;
                    }
                } else {
                    System.out.println("Tämä paikka on varattu!");
                    System.out.println();
                }
            }
        } else {
            System.out.println("Ei näytöksiä");
            System.out.println();
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
            System.out.println("2. Poista elokuva (Näytöksiä ei poisteta)");
            System.out.println("0. Poistu");

            valinta = lueKokonaisluku(0, 2, "Anna valinta");
            if (valinta == 1) {
                String nimi = lueMerkkijono("Nimi");
                int kesto = lueKokonaisluku(0, 1000, "Kesto minuutteina");
                String kieli = lueMerkkijono("Kieli");
                String genre = lueMerkkijono("Genre");
                while (true) {
                    String ikaraja = lueMerkkijono("Ikäsuositus (S, K7, K12, K16, K18)");
                    System.out.println();
                    ikaraja.toUpperCase();
                    try {
                        Ikasuositus ikasuositus = Ikasuositus.valueOf(ikaraja);
                        varausjarjestelma.lisaaElokuva(new Elokuva(nimi, kesto, kieli, genre, ikasuositus));
                        varausjarjestelma.kirjoitaTiedot();
                        System.out.println("Elokuva lisätty");
                        System.out.println();
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Virheellinen syöte");
                    }
                    System.out.println();
                }

            } else if (valinta == 2) {
                int evalinta = lueKokonaisluku(0,varausjarjestelma.getElokuvat().size(), "Anna elokuvan numero tai poistu (0)");
                System.out.println();
                if (evalinta == 0) {
                    break;
                }
                else if (varausjarjestelma.poistaElokuva(varausjarjestelma.getElokuvat().get(evalinta-1))) {
                    System.out.println("Elokuva poistettu!");
                    System.out.println();
                }
            }
        }
        System.out.println();
    }

    public void adminNaytosmenu() {
        int valinta = -1;
        while (valinta != 0) {
            System.out.println("1. Näytä kaikki näytökset");
            System.out.println("2. Näytä tietyn elokuvan näytökset");
            System.out.println("3. Näytä tietyn päivän näytökset");
            System.out.println("4. Lisää näytös");
            System.out.println("5. Poista näytös");
            System.out.println("6. Katsele salikarttoja");
            System.out.println("0. Poistu");

            valinta = lueKokonaisluku(0, 6, "Anna valinta");
            System.out.println();
            if (valinta == 1) {
                if (!varausjarjestelma.listaaNaytokset(varausjarjestelma.getNaytokset()).isEmpty()) {
                    System.out.println();
                    System.out.println(varausjarjestelma.listaaNaytokset(varausjarjestelma.getNaytokset()));
                } else {
                    System.out.println("\nEi näytöksiä");
                }
            } else if (valinta == 2) {
                System.out.println("Valitse elokuva, jonka näytökset haluat nähdä");
                System.out.println();
                //Tulostetaan lista, josta voi valita elokuvan sen järjestysnumerolla
                for (int i = 0; i < varausjarjestelma.getElokuvat().size(); i++) {
                    System.out.println(i + 1 + ". " + varausjarjestelma.getElokuvat().get(i));
                }
                int valinta2 = lueKokonaisluku(1, varausjarjestelma.getElokuvat().size(), "Anna valinta");
                Elokuva elokuva = varausjarjestelma.getElokuvat().get(valinta2 - 1);
                if (!varausjarjestelma.onkoNaytoksia(elokuva)) {
                    System.out.println("Tällä elokuvalla ei ole näytöksiä");
                } else {
                    System.out.println();
                    System.out.println(varausjarjestelma.listaaElokuvanNaytokset(elokuva));
                    System.out.println();
                }
            } else if (valinta == 3) {
                System.out.println("Anna päivä, jonka näytökset haluat nähdä tai poistu (0)");
                System.out.println();
                LocalDateTime aika = luePaiva("pp.kk");
                if (aika == null) {
                    break;
                }
                System.out.println();
                System.out.println(varausjarjestelma.listaaPaivanNaytokset(aika));

            } else if (valinta == 4) {
                System.out.println("Anna päivä, jolle haluat lisätä näytöksen tai poistu (0)");
                LocalDateTime aika = luePaiva("pp.kk");
                if (aika == null) {
                    break;
                }
                System.out.println(varausjarjestelma.listaaPaivanNaytokset(aika));
                String kellonaika = lueMerkkijono("Valitse kellonaika (hh.mm) tai poistu (0)");
                System.out.println();
                if (kellonaika.equals("0")) {
                    break;
                }
                String osat[] = kellonaika.split("\\.");
                int tunti = Integer.parseInt(osat[0]);
                int minuutit = Integer.parseInt(osat[1]);
                LocalDateTime naytosaika = LocalDateTime.of(aika.toLocalDate(), LocalTime.of(tunti, minuutit));
                System.out.println("Valitse elokuva, jolle haluat lisätä näytöksen:\n");
                System.out.println(varausjarjestelma.listaaElokuvat());
                valinta = lueKokonaisluku(1, varausjarjestelma.getElokuvat().size(), "Elokuvan numero");
                Elokuva elokuva = varausjarjestelma.getElokuvat().get(valinta - 1);
                System.out.println("\nValitse sali:\n");
                System.out.println(varausjarjestelma.listaaSalit());
                int salinumero = lueKokonaisluku(1, 3, "Salin numero");
                Sali sali = varausjarjestelma.getSali(salinumero);
                Naytos naytos = new Naytos(elokuva, sali, naytosaika);
                varausjarjestelma.lisaaNaytos(naytos);
                varausjarjestelma.kirjoitaTiedot();
                System.out.println("Näytös lisätty!");
                System.out.println();

            } else if (valinta == 5) {
                System.out.println(varausjarjestelma.listaaNaytokset(varausjarjestelma.getNaytokset()));
                valinta = lueKokonaisluku(0, varausjarjestelma.getNaytokset().size(), "Valitse poistettavan näytöksen numero tai poistu (0)");
                System.out.println();
                if (valinta == 0) {
                    break;
                }
                if (varausjarjestelma.poistaNaytos(varausjarjestelma.getNaytokset().get(valinta-1))) {
                    varausjarjestelma.kirjoitaTiedot();
                    System.out.println("Näytös poistettu!");
                    System.out.println();

                } else {
                    System.out.println("Poisto epäonnistui!");
                }
            } else if (valinta == 6) {
                if (!varausjarjestelma.getNaytokset().isEmpty()) {
                    System.out.println(varausjarjestelma.listaaNaytokset(varausjarjestelma.getNaytokset()));
                    System.out.println();
                    valinta = lueKokonaisluku(1, varausjarjestelma.getNaytokset().size(), "Valitse näytös, jonka salikartan haluat nähdä");
                    Naytos naytos = varausjarjestelma.getNaytokset().get(valinta - 1);
                    System.out.println();
                    System.out.println("1 = varattu ja 0 = vapaa");
                    System.out.println(varausjarjestelma.tulostaSalikartta(naytos));
                } else {
                    System.out.println("Ei näytöksiä");
                    System.out.println();
                }
            }
        }
    }

    /**
     * Apumetodi, joka näyttää käyttäjälle annetun kehotteen ja lukee
     * sitten tältä merkkijonon. Merkkijono muunnetaan LocalDateTime muotoon.
     *
     * @return käyttäjän syöttämä päivämäärä LocalDateTime muodossa
     */
    public LocalDateTime luePaiva(String kehote) {
        LocalDateTime paivamaara = null;
        while (paivamaara == null) {
            System.out.print(kehote + ": ");
            String syote = lukija.nextLine();
            String[] osat = syote.split("\\.");
            if (!syote.equals("") && osat.length == 2) {
                try {
                    int paiva = Integer.parseInt(osat[0]);
                    int kuukausi = Integer.parseInt(osat[1]);
                    int vuosi = LocalDate.now().getYear();
                    LocalDate aika = LocalDate.of(vuosi, kuukausi, paiva);
                    if (aika.isBefore(LocalDate.now())) {
                        aika = LocalDate.of(vuosi + 1, kuukausi, paiva);
                    }
                    paivamaara = LocalDateTime.of(aika, LocalTime.MIDNIGHT);
                } catch (DateTimeException | NumberFormatException e) {
                    System.out.println("Virheellinen päivämäärä. Yritä uudelleen");
                }
            } else if (syote.equals("0")) {
                break;
            } else {
                System.out.println("Virheellinen muoto");
            }
        }
        return paivamaara;
    }

    /**
     * Lukee käyttäjältä syötteen ja palauttaa sen kokonaislukuna.
     * Kokonaisluvun on oltava annetun minimin ja maksimin väliltä.
     *
     * @param minimi pienin sallittu arvo
     * @param maksimi suurin sallittu arvo
     * @param kehote käyttäjälle näytettävä kehote
     * @return käyttäjän syöte kokonaislukuna
     */
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

    /** Apumetodi, joka näyttää käyttäjälle annetun kehotteen ja lukee
     * sitten tältä merkkijonon. Annettu jono ei voi olla tyhjä.
     *
     * @param kehote käyttäjälle näytettävä kehote
     * @return käyttäjän syöttämä ei-tyhjä merkkijono
     */
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