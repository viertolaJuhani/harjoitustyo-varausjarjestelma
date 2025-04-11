import java.time.LocalDateTime;
import java.util.ArrayList;

public class Varausjarjestelma {
    private ArrayList<Elokuva> elokuvat;
    private ArrayList<Naytos> naytokset;

    public Varausjarjestelma() {
        elokuvat = new ArrayList<>();

    }

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

    }

    public boolean poistaNaytos(Naytos naytos) {

    }

    public boolean peruVaraus(Varaus varaus) {

    }

    public boolean poistaSali(Sali sali) {

    }



    public void tulostaPaikat(Sali sali) {
        for (int i=0; i<sali.getRivit(); i++) {
            for (int j=0; j<sali.getPaikatRivilla(); j++) {
                System.out.print(sali.getPaikat()[i][j] ? "X " : "O ");
            }
        }
    }
}