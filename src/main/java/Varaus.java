import java.util.ArrayList;

/**
 * Luokka mallintaa näytökseen tehtyä istumapaikan varausta salissa.
 */
public class Varaus {
    private Naytos naytos;
    private ArrayList<Istumapaikka> istumapaikat;

    public Varaus(Asiakas asiakas, Naytos naytos, ArrayList<Istumapaikka> istumapaikat) {
        this.naytos = naytos;
        this.istumapaikat = new ArrayList<>();
    }

    public Naytos getNaytos() {
        return naytos;
    }

    public void setNaytos(Naytos naytos) {
        this.naytos = naytos;
    }

    public ArrayList<Istumapaikka> getIstumapaikat() {
        return istumapaikat;
    }

    public void setIstumapaikat(ArrayList<Istumapaikka> istumapaikat) {
        this.istumapaikat = istumapaikat;
    }
}