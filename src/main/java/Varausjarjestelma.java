import java.util.ArrayList;
import java.util.HashMap;

/**
 * Luokka mallintaa varausjärjestelmän toimintaa.
 * Sisältää ylläpitäjälle ja asiakkaalle oleelliset toiminnot.
 */
public class Varausjarjestelma {
    private ArrayList<Elokuva> elokuvat;
    private ArrayList<Naytos> naytokset;
    private ArrayList<Asiakas> asiakkaat;

    public Varausjarjestelma() {
        asiakkaat = VarausjarjestelmaIO.lueAsiakkaat("asiakkaat.txt");
        elokuvat = VarausjarjestelmaIO.lueElokuvat("elokuvat.txt");
        naytokset = VarausjarjestelmaIO.lueNaytokset("naytokset.csv");
    }

    public ArrayList<Asiakas> getAsiakasLista() {
        return asiakkaat;
    }

    public ArrayList<Elokuva> getElokuvaLista() {
        return elokuvat;
    }
    public void kirjoitaTiedot() {
        VarausjarjestelmaIO.kirjoitaNaytokset(naytokset, "naytokset.csv");
        VarausjarjestelmaIO.kirjoitaAsiakkaat(asiakkaat, "asiakkaat.txt");
        VarausjarjestelmaIO.kirjoitaElokuvat(elokuvat, "elokuvat.txt");
    }

    public void lisaaNaytos(Naytos naytos) {
        naytokset.add(naytos);
    }

    public void lisaaAsiakas(Asiakas asiakas) {
        asiakkaat.add(asiakas);
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
     * Palauttaa kaikki elokuvat yhtenä merkkijonona.
     *
     * @return lista elokuvista merkkijonona
     */
    public String listaaElokuvat() {
        if (elokuvat.isEmpty()){
            return "Ei elokuvia listalla";
        }
        StringBuilder e = new StringBuilder();
        for (Elokuva elokuva : elokuvat) {
            e.append(elokuva.getNimi() + "\t");
            e.append(elokuva.getKesto() + "\t");
            e.append(elokuva.getKieli() + "\t");
            e.append(elokuva.getGenre() + "\t");
            e.append(elokuva.getIkaraja() + "\n");
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

    /**
     * Palauttaa kaikkien elokuvien näytökset merkkijonona.
     *
     * @return näytökset merkkijonona
     */
    public String listaaKaikkiNaytokset() {
        StringBuilder n = new StringBuilder();
        for (Naytos naytos : naytokset) {
            n.append(naytos.getElokuvanNimi() + "\t");
            n.append(naytos.getNaytosaika() + "\t\t");
            n.append(naytos.getSali() + "\t");
        }
        return n.toString();
    }

    /**
     * Varaa paikan tietyssä näytökksessä.
     * @param rivi Paikan rivinumero
     * @param paikka Paikan numero rivillä
     * @return true, jos varaus onnistui, muuten false
     */
    public boolean varaaPaikka(Naytos naytos, int rivi, int paikka, Sali sali) {
        if (rivi >= 0 && rivi < sali.getRivit() && paikka >= 0 && paikka < sali.getPaikatRivilla()) {
            if (!naytos.onkoVarattu(rivi, paikka)) {
                naytos.setVaraukset(rivi, paikka);
                return true;
            }
        }
        return false;
    }
}