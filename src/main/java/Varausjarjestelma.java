import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Luokka mallintaa varausjärjestelmän toimintaa.
 * Sisältää ylläpitäjälle ja asiakkaalle oleelliset toiminnot.
 */
public class Varausjarjestelma {
    private ArrayList<Elokuva> elokuvat;
    private ArrayList<Naytos> naytokset;
    private ArrayList<User> kayttajat;
    private ArrayList<Varaus> varaukset;
    private List<Sali> salit;

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");

    public Varausjarjestelma() {
        kayttajat = VarausjarjestelmaIO.lueKayttajat("kayttajat.txt");
        elokuvat = VarausjarjestelmaIO.lueElokuvat("elokuvat.txt");
        naytokset = VarausjarjestelmaIO.lueNaytokset("naytokset.csv");
        varaukset = VarausjarjestelmaIO.lueVaraukset("varaukset.csv");
        // Alustetaan salit.
        Sali sali1 = new Sali(1, 10, 14);
        Sali sali2 = new Sali(2, 8, 16);
        Sali sali3 = new Sali(3, 10, 20);
        salit = List.of(sali1, sali2, sali3);
        // Lisätään ylläpitäjä, jos sellaista ei ole
        Admin admin = new Admin("admin", "admin", "admin");
        if (kayttajat.isEmpty()) {
            kayttajat.add(admin);
        }
    }

    public void kirjoitaTiedot() {
        VarausjarjestelmaIO.kirjoitaNaytokset(naytokset, "naytokset.csv");
        VarausjarjestelmaIO.kirjoitaKayttajat(kayttajat, "kayttajat.txt");
        VarausjarjestelmaIO.kirjoitaElokuvat(elokuvat, "elokuvat.txt");
        VarausjarjestelmaIO.kirjoitaVaraukset(varaukset, "varaukset.csv");
        //Järjestetään näytökset näytösajan mukaan
        try {
            naytokset.sort(Comparator.comparing(Naytos::getNaytosaika));
        } catch (NullPointerException e) {
            System.out.println();
        }
    }

    public void lisaaNaytos(Naytos naytos) {
        naytokset.add(naytos);
    }

    public void lisaaAsiakas(Asiakas asiakas) {
        kayttajat.add(asiakas);
    }

    public void lisaaElokuva(Elokuva elokuva) {
        elokuvat.add(elokuva);
    }

    public void lisaaVaraus(Varaus varaus) {
        varaukset.add(varaus);
    }

    /**
     * Palauttaa numeroa vastaavan sali-olion
     * @param salinumero jokaiselle salille uniikki numero
     * @return sali-olio jos löytyi, muuten null
     */
    public Sali getSali(int salinumero) {
        for (Sali sali : salit) {
            if (salinumero == sali.getSalinumero()) {
                return sali;
            }
        }
        return null;
    }

    /**
     * Palauttaa salit merkkijonona
     * @return salit merkkijonona
     */
    public String listaaSalit() {
        StringBuilder sb = new StringBuilder();
        for (Sali s : salit) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Palauttaa käyttäjälistan
     * @return lista käyttäjistä
     */
    public ArrayList<User> getKayttajat() {
        return kayttajat;
    }

    /**
     * Palauttaa elokuvalistan
     * @return lista elokuvista
     */
    public ArrayList<Elokuva> getElokuvat() {
        return elokuvat;
    }

    /**
     * Palauttaa listan näytöksistä
     * @return lista näytöksistä
     */
    public ArrayList<Naytos> getNaytokset() {
        return naytokset;
    }

    public ArrayList<Varaus> getVaraukset() {
        return varaukset;
    }

    /**
     * Palauttaa listan käyttäjien sähköpostiosoitteista
     * @return lista sähköpostiosoitteista
     */
    public ArrayList<String> getKayttajienSpostit() {
        ArrayList<String > spostilista = new ArrayList<>();
        for (User u : kayttajat) {
            spostilista.add(u.getEmail());
        }
        return spostilista;
    }

    /**
     * Tarkistaa riittääkö tietty ikä varaamaan paikan tietyn elokuvan näytökseen
     * @param ika asiakkaan ikä
     * @param elokuva elokuva, jonka ikäsuositukseen ikää verrataan
     * @return true, jos ikää on riittävästi, muuten false
     */
    public boolean riittaakoIka(int ika, Elokuva elokuva) {
        if (ika >= 18) {
            return true;
        } else if (ika >= 16 && elokuva.getIkasuositus() != Ikasuositus.K18) {
            return true;
        } else if (ika >= 12 && elokuva.getIkasuositus() != Ikasuositus.K16
                && elokuva.getIkasuositus() != Ikasuositus.K18) {
            return true;
        }
        return false;
    }

    /**
     * Palauttaa listan tietyn elokuvan näyöksistä
     * @param elokuva elokuva, jonka näytöksistä lista muodostuu
     * @return lista elokuvan näytöksistä
     */
    public ArrayList<Naytos> getElokuvanNaytokset(Elokuva elokuva) {
        ArrayList<Naytos> elokuvanNaytokset = new ArrayList<>();
        for (Naytos n : naytokset) {
            if (n.getElokuva().getNimi().equals(elokuva.getNimi())) {
                elokuvanNaytokset.add(n);
            }
        }
        return elokuvanNaytokset;
    }

    /**
     * Palauttaa näytöksen salikartan, jossa näkee kaikki varatut paikat
     * @param naytos näytös, jonka salikartta haetaan
     * @return salikartta matriisina, jos paikka varattu: arvo on true, muuten false
     */
    public boolean[][] getSalikartta(Naytos naytos) {
        boolean[][] kartta = naytos.getVaraukset();
        for (Varaus varaus : varaukset) {
            for (Istumapaikka i : varaus.getIstumapaikat()) {
                int paikka = i.getPaikkaRivilla()-1;
                int rivi = i.getRivi()-1;
                kartta[rivi][paikka] = true;
            }
        }
        return kartta;
    }

    /**
     * Palauttaa salikartan merkkijonona, jossa varattua paikkaa merkitään numerolla 1
     * ja vapaata paikkaa numerolla 0.
     * @param naytos näytös, jonka salikartta haetaan
     * @return salikartta merkkijonona
     */
    public String tulostaSalikartta(Naytos naytos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getSalikartta(naytos).length; i++) {
            for (int j = 0; j < getSalikartta(naytos)[i].length; j++) {
                sb.append(getSalikartta(naytos)[i][j] ? "1 " : "0 ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Yrittää poistaa annetun elokuvan.
     *
     * @param elokuva elokuva, jonka poistoa yritetään
     * @return true, jos elokuva löytyi ja poistettiin, muuten false
     */
    public boolean poistaElokuva(Elokuva elokuva) {
        for (Elokuva e : elokuvat) {
            if (elokuvat.contains(elokuva)) {
                elokuvat.remove(e);
                return true;
            }
        }
        System.out.println("Elokuvaa ei löytynyt");
        return false;
    }

    /**
     * Yrittää poistaa annetun näytöksen.
     *
     * @param naytos naytös, jonka poistoa yritetään
     * @return true, jos näytös löytyi ja poistettiin, muuten false
     */
    public boolean poistaNaytos(Naytos naytos) {
        if (naytokset.contains(naytos)) {
            naytokset.remove(naytos);
            return true;
        }
        return false;
    }

    /**
     * Yrittää poistaa annetun varauksen.
     *
     * @param varaus varas, jonka poistoa yritetään
     * @return true, jos varaus löytyi ja poistettiin, muutten false
     */
    public boolean poistaVaraus(Varaus varaus) {
        if (varaukset.contains(varaus)) {
            varaukset.remove(varaus);
            return true;
        }
        return false;
    }

    /**
     * Palauttaa kaikki elokuvat yhtenä merkkijonona.
     *
     * @return lista elokuvista merkkijonona
     */
    public String listaaElokuvat() {
        if (elokuvat.isEmpty()) {
            return ("Ei elokuvia listalla\n");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-3s %-30s %-15s %-25s %-15s %-15s\n", " ",
                "Nimi", "Kesto", "Genre", "Ikäsuositus", "kieli"));
        sb.append("-------------------------------------------------------------------------------------------------------------\n");
        for (Elokuva e : elokuvat) {
            sb.append(String.format("%-3s %-30s %-15s %-25s %-15s %-15s\n", elokuvat.indexOf(e)+1+".", e.getNimi(),
                    e.getKestoTunnitMinuutit(), e.getGenre(),
                    e.getIkasuositus(), e.getKieli()));
        }
        return sb.toString();
    }

    /**
     * Tarkistaa löytyykö tietyllä elokuvalla näytöksiä
     * Verrataan elokuvan nimeen, sillä näytöksiä voi olla myös
     * poistetuilla elokuvilla.
     *
     * @param elokuva elokuva, jonka näytöksiä etsitään
     * @return true, jos näytöksiä löytyy, muuten false
     */
    public boolean onkoNaytoksia(Elokuva elokuva) {
        for (Naytos n : naytokset) {
            if (elokuva.getNimi().equals(n.getElokuva().getNimi())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Palauttaa kaikki varaukset merkkijonona
     * @return varaukset merkkijonona
     */
    public String listaaVaraukset() {
        if (varaukset.isEmpty()) {
            return "Ei varauksia\n";
        }
        StringBuilder sb = new StringBuilder();
        for (Varaus varaus : varaukset) {
            sb.append(varaus.toString()).append("\n");
        }
        return "Varauslista:\n" + sb;
    }

    /**
     * Palauttaa tietyn asiakkaan varaukset listana
     * @param email asiakkaan sähköposti
     * @return varaukset listana
     */
    public List<Varaus> getKayttajanVaraukset(String email) {
        List<Varaus> kayttajanVaraukset = new ArrayList<>();
        for (Varaus v : varaukset) {
            if (v.getAsiakasEmail().equals(email)) {
                kayttajanVaraukset.add(v);
            }
        }
        return kayttajanVaraukset;
    }

    /**
     * Palauttaa kaikki tietyn asiakkaan tekemät varaukset.
     * @param email asiakkaan sähköposti
     * @return Asiakkaan varaukset merkkijonona
     */
    public String listaaKayttajanVaraukset(String email) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s %-20s %-10s %s\n", "Elokuva", "Aika", "Sali", "Istumapaikat"));
        sb.append("------------------------------------------------------------------------------------------------------------------------\n");
        for (Varaus v : getKayttajanVaraukset(email)) {
            sb.append(String.format("%-20s %-20s %-10s %s\n",
                    v.getNaytos().getElokuva().getNimi(), v.getNaytos().getNaytosaika().format(FORMATTER),
                    v.getNaytos().getSali().getSalinumero(),
                    v.istumapaikatStr(v.getIstumapaikat())));
        }
        return sb.toString();
    }

    /**
     * Palauttaa kaikkien elokuvien näytökset merkkijonona.
     *
     * @return näytökset merkkijonona
     */
    public String listaaNaytokset(ArrayList<Naytos> naytokset) {
        StringBuilder n = new StringBuilder();
        if (!naytokset.isEmpty()) {
            n.append(String.format("%-3s %-40s %-20s %-15s\n", " ", "Nimi ja kesto", "Näytösaika", "Sali"));
            n.append("-------------------------------------------------------------------------------\n");
            for (Naytos naytos : naytokset) {
                n.append(String.format("%-3s %-40s %-20s %-15s\n",
                        naytokset.indexOf(naytos) + 1 + ".", naytos.getElokuva().getNimi() + " (" + naytos.getElokuva().getKestoTunnitMinuutit() + ")",
                        naytos.getNaytosaika().format(FORMATTER),
                        naytos.getSali().getSalinumero()));
            }
            return n.toString();
        }
        return "\nEi näytöksiä\n";
    }

    /**
     * Palauttaa kaikki tietyn elokuvan näytökset merkkijonona
     *
     * @return näytökset merkkijonona
     */
    public String listaaElokuvanNaytokset(Elokuva elokuva) {
        ArrayList<Naytos> eNaytokset = new ArrayList<>(getElokuvanNaytokset(elokuva));

        return listaaNaytokset(eNaytokset);
    }

    /**
     * Palauttaa tietyn päivän näytökset merkkijonona
     *
     * @param aika aika, jonka päivämäärään verrataan
     * @return näytökset merkkijonona tai "Ei näytöksiä", jos päivällä ei ole näytöksiä
     */
    public String listaaPaivanNaytokset(LocalDateTime aika) {
        ArrayList<Naytos> paivanNaytokset = new ArrayList<>();
        for (Naytos naytos : naytokset) {
            if (naytos.getNaytosaika().getMonthValue() == (aika.getMonthValue())
                    && naytos.getNaytosaika().getDayOfMonth() == aika.getDayOfMonth()) {
                paivanNaytokset.add(naytos);

            }
        }
        return listaaNaytokset(paivanNaytokset);
    }
}