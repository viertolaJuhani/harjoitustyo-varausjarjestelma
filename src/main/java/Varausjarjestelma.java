import java.util.ArrayList;

/**
 * Luokka mallintaa varausjärjestelmän toimintaa.
 */
public class Varausjarjestelma {
    private ArrayList<Elokuva> elokuvat;
    private ArrayList<Naytos> naytokset;

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
}