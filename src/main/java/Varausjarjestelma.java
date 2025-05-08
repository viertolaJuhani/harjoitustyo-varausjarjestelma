import java.util.ArrayList;
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

    public Varausjarjestelma() {
        kayttajat = VarausjarjestelmaIO.lueKayttajat("kayttajat.txt");
        elokuvat = VarausjarjestelmaIO.lueElokuvat("elokuvat.txt");
        naytokset = VarausjarjestelmaIO.lueNaytokset("naytokset.csv");
        varaukset = VarausjarjestelmaIO.lueVaraukset("varaukset.csv");
        Sali sali1 = new Sali(1, 10, 14);
        Sali sali2 = new Sali(2, 10, 20);
        Sali sali3 = new Sali(3, 10, 20);
        salit = List.of(sali1, sali2, sali3);
    }

    public void kirjoitaTiedot() {
        VarausjarjestelmaIO.kirjoitaNaytokset(naytokset, "naytokset.csv");
        VarausjarjestelmaIO.kirjoitaKayttajat(kayttajat, "kayttajat.txt");
        VarausjarjestelmaIO.kirjoitaElokuvat(elokuvat, "elokuvat.txt");
        VarausjarjestelmaIO.kirjoitaVaraukset(varaukset, "varaukset.csv");
    }

    public Sali getSali(int salinumero) {
        for (Sali sali : salit) {
            if (salinumero == sali.getSalinumero()) {
                return sali;
            }
        }
        return null;
    }

    public String listaaSalit() {
        StringBuilder sb = new StringBuilder();
        for (Sali s : salit) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }

    public ArrayList<User> getKayttajaLista() {
        return kayttajat;
    }

    public ArrayList<Elokuva> getElokuvat() {
        return elokuvat;
    }

    public ArrayList<String> getKayttajienSpostit() {
        ArrayList<String > spostilista = new ArrayList<>();
        for (User u : kayttajat) {
            spostilista.add(u.getEmail());
        }
        return spostilista;
    }

    public ArrayList<Naytos> getNaytokset() {
        return naytokset;
    }

    public ArrayList<Naytos> getElokuvanNaytokset(Elokuva elokuva) {
        ArrayList<Naytos> elokuvanNaytokset = new ArrayList<>();
        for (Naytos n : naytokset) {
            if (n.getElokuvanNimi().equals(elokuva.getNimi())) {
                elokuvanNaytokset.add(n);
            }
        }
        return elokuvanNaytokset;
    }

    /**
     * Palauttaa tietyn näytöksen salikartan, jossa näkee kaikki varatut paikat
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
     * Yrittää poistaa annetun elokuvan.
     *
     * @param elokuva elokuva, jonka poistoa yritetään
     * @return true, jos elokuva löytyi ja poistettiin, muuten false
     */
    public boolean poistaElokuva(String elokuva) {
        for (Elokuva e : elokuvat) {
            if (e.getNimi().equals(elokuva)) {
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
            return ("Ei elokuvia listalla");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-3s %-20s %-15s %-25s %-15s %-15s\n", " ", "Nimi", "Kesto", "Genre", "Ikäsuositus", "kieli"));
        sb.append("--------------------------------------------------------------------------------------------\n");
        for (Elokuva e : elokuvat) {
            sb.append(String.format("%-3s %-20s %-15s %-25s %-15s %-15s\n", elokuvat.indexOf(e)+1, e.getNimi(),
                    e.getKestoTunnitMinuutit(), e.getGenre(),
                    e.getIkaraja(), e.getKieli()));
        }
        return sb.toString();
    }

    /**
     * Palauttaa kaikki tietyn elokuvan näytökset merkkijonona
     *
     * @return näytökset merkkijonona
     */
    public String listaaNaytokset(Elokuva elokuva) {
        StringBuilder n = new StringBuilder();
        List<Naytos> eNaytokset = new ArrayList<>();
        eNaytokset.addAll(getElokuvanNaytokset(elokuva));
        n.append("Näytökset:\n\n");
        n.append(String.format("%-3s %-20s %-15s %-15s\n", " ", "Nimi", "Näytösaika", "Sali"));
        n.append("----------------------------------------------\n");
        for (Naytos naytos : eNaytokset) {
            n.append(String.format("%-3s %-20s %-15s %-15s\n", eNaytokset.indexOf(naytos)+1,
                    naytos.getElokuvanNimi(), naytos.getNaytosaika(),
                    naytos.getSali().getSalinumero()));
        }
        return n.toString();
    }

    public boolean onkoElokuvaa(String nimi) {
        for (Elokuva e : elokuvat) {
            if (nimi.equals(e.getNimi())) {
                return true;
            }
        }
        return false;
    }

    public boolean onkoNaytoksia(Elokuva elokuva) {
        for (Naytos n : naytokset) {
            if (elokuva.getNimi().equals(n.getElokuvanNimi())) {
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
        sb.append(String.format("%-15s %-20s %-15s %-10s %s\n", "Varaaja", "Elokuva", "Aika", "Sali", "Istumapaikat"));
        sb.append("------------------------------------------------------------------------------------------------------------------------\n");
        for (Varaus v : getKayttajanVaraukset(email)) {
            sb.append(String.format("%-15s %-20s %-15s %-10s %s\n", v.getAsiakasEmail(),
                    v.getNaytos().getElokuvanNimi(), v.getNaytos().getNaytosaika(),
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
    public String listaaKaikkiNaytokset() {
        StringBuilder n = new StringBuilder();
        n.append(String.format("%-20s %-15s %-15s\n", "Nimi", "Näytösaika", "Sali"));
        n.append("--------------------------------------------\n");
        for (Naytos naytos : naytokset) {
            n.append(String.format("%-20s %-15s %-15s\n",
                    naytos.getElokuvanNimi(),
                    naytos.getNaytosaika(),
                    naytos.getSali().getSalinumero()));
        }
        return n.toString();
    }
}