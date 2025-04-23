import java.util.ArrayList;

/**
 * Luokka mallintaa varausjärjestelmän toimintaa.
 * Sisältää ylläpitäjälle ja asiakkaalle oleelliset toiminnot.
 */
public class Varausjarjestelma {
    private ArrayList<Elokuva> elokuvat;
    private ArrayList<Naytos> naytokset;
    private ArrayList<Asiakas> asiakkaat;

    public Varausjarjestelma() {
        elokuvat = new ArrayList<>();
        naytokset = new ArrayList<>();
    }

    public void lisaaAsiakas(Asiakas asiakas) {
    }

    public void lisaaNaytos(Naytos naytos) {
    }

    public void lisaaElokuva(Elokuva elokuva) {
    }

    /**
     * Yrittää poistaa annetun elokuvan.
     *
     * @param elokuva elokuva, jonka poistoa yritetään
     * @return true, jos elokuva löytyi ja poistettiin, muuten false
     */
    public boolean poistaElokuva(Elokuva elokuva) {
        if (elokuvat.contains(elokuva)) {
            elokuvat.remove(elokuva);
            return true;
        }
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
    }

    /**
     * Palauttaa kaikki tietyn elokuvan näytökset merkkijonona
     *
     * @return näytökset merkkijonona
     */
    public String listaaNaytokset(Elokuva elokuva) {
    }

    /**
     * Palauttaa kaikkien elokuvien näytökset merkkijonona.
     *
     * @return näytökset merkkijonona
     */
    public String listaaKaikkiNaytokset() {
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