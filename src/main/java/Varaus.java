import java.util.ArrayList;

/**
 * Luokka mallintaa näytökseen tehtyä istumapaikan varausta salissa.
 */
public class Varaus {
    private Asiakas asiakas;
    private Naytos naytos;
    private ArrayList<Istumapaikka> istumapaikat;

    public Varaus(Asiakas asiakas, Naytos naytos, ArrayList<Istumapaikka> istumapaikat) {
        this.asiakas = asiakas;
        this.naytos = naytos;
        this.istumapaikat = new ArrayList<>();
    }

    public Asiakas getAsiakas() {
        return asiakas;
    }

    public void setAsiakas(Asiakas asiakas) {
        this.asiakas = asiakas;
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