import java.util.ArrayList;
import java.util.HashMap;
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
        Sali sali2 = new Sali(2, 15, 20);
        Sali sali3 = new Sali(3, 15, 20);
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

    public ArrayList<Varaus> getVaraukset() {
        return varaukset;
    }

    public ArrayList<Naytos> getNaytokset() {
        return naytokset;
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
     * Palauttaa tietyn Naytos-olion elokuvan nimen, näytösajan ja salin mukaan
     * @param elokuvanNimi elokuvan nimi
     * @param naytosaika elokuvan näytösaika
     * @param sali sali, jossa elokuva näytetään
     * @return Naytos-olio
     */
    public Naytos annaNaytos(String elokuvanNimi, String naytosaika, Sali sali) {
        for (Naytos n : naytokset) {
            if (n.getElokuvanNimi().equals(elokuvanNimi) && n.getNaytosaika().equals(naytosaika) && n.getSali().equals(sali)) {
                return n;
            }
        }
        return null;
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
        StringBuilder e = new StringBuilder();
        for (Elokuva elokuva : elokuvat) {
            e.append(elokuva.toString()).append("\n");
        }
        return e.toString();
    }

    /**
     * Palauttaa kaikki tietyn elokuvan näytökset merkkijonona
     *
     * @return näytökset merkkijonona
     */
    public String listaaNaytokset(Elokuva elokuva) {
        StringBuilder n = new StringBuilder();
        for (Naytos naytos : naytokset) {
            if (naytos.getElokuvanNimi().equals(elokuva.getNimi())) {
                n.append(naytos.getElokuvanNimi() + "\t");
                n.append(naytos.getNaytosaika() + "\t\t");
                n.append(naytos.getSali() + "\t");
            }
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
        return "Varauslista:\n" + sb.toString();
    }

    /**
     * Palauttaa kaikki tietyn asiakkaan tekemät varaukset.
     * @param sposti asiakkaan sähköposti
     * @return Asiakkaan varaukset merkkijonona
     */
    public List<Varaus> listaaKayttajanVaraukset(String sposti) {
        List<Varaus> kayttajanVaraukset = new ArrayList<>();
        for (Varaus v : varaukset) {
            if (v.getAsiakasEmail().equals(sposti)) {
                kayttajanVaraukset.add(v);
            }
        }
        return kayttajanVaraukset;
    }
    /**
     * Palauttaa kaikkien elokuvien näytökset merkkijonona.
     *
     * @return näytökset merkkijonona
     */
    public String listaaKaikkiNaytokset() {
        StringBuilder n = new StringBuilder();
        for (Naytos naytos : naytokset) {
            n.append(naytos.toString()).append("\n");
        }
        return n.toString();
    }
}