import java.util.ArrayList;

/**
 * Luokka
 */
public class Varausjarjestelma {
    private ArrayList<Elokuva> elokuvat;
    private ArrayList<Naytos> naytokset;
    private ArrayList<Varaus> varaukset;
    private ArrayList<Sali> salit;

    public Varausjarjestelma() {
        elokuvat = new ArrayList<>();
        naytokset = new ArrayList<>();
        varaukset = new ArrayList<>();
        salit = new ArrayList<>();
    }
    Sali sali1 = new Sali(1, 10, 20);
    Sali sali2 = new Sali(2, 8, 14);
    Sali sali3 = new Sali(3, 8, 10);

    public void lisaaAsiakas(Asiakas asiakas) {
    }

    public void lisaaNaytos(Naytos naytos) {
    }

    public void lisaaElokuva(Elokuva elokuva) {
    }

    public void lisaaSali(int salinumero, int rivit, int paikatRivilla) {
    }

    public void teeVaraus(Naytos naytos, ArrayList<Istumapaikka> istumapaikat) {
    }


    public boolean poistaElokuva(Elokuva elokuva) {
        if (elokuvat.contains(elokuva)) {
            elokuvat.remove(elokuva);
            return true;
        }
        return false;
    }

    public boolean poistaNaytos(Naytos naytos) {
        if (naytokset.contains(naytos)) {
            naytokset.remove(naytos);
            return true;
        }
        return false;
    }

    public boolean peruVaraus(Varaus varaus) {
        if (varaukset.contains(varaus)) {
            varaukset.remove(varaus);
            return true;
        }
        return false;
    }

    public boolean poistaSali(Sali sali) {
        if (salit.contains(sali)) {
            salit.remove(sali);
            return true;
        }
        return false;
    }

    public String listaaElokuvat() {
        for (Elokuva elokuva : elokuvat) {
            System.out.println(elokuva);
        }
        return "";
    }

    public String listaaNaytokset() {
        return "";
    }
}