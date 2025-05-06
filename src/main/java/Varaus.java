import java.io.Serializable;
import java.util.ArrayList;

/**
 * Luokka mallintaa näytökseen tehtyä istumapaikan varausta salissa.
 */
public class Varaus implements Serializable {

    private static final long serialVersionUID = 1L;

    private String asiakasEmail;
    private Naytos naytos;
    private ArrayList<Istumapaikka> istumapaikat;

    public Varaus(String asiakasEmail, Naytos naytos, ArrayList<Istumapaikka> istumapaikat) {
        this.asiakasEmail = asiakasEmail;
        this.naytos = naytos;
        this.istumapaikat = istumapaikat;
    }

    public String getAsiakasEmail() {
        return asiakasEmail;
    }

    public void setAsiakasEmail(String asiakasEmail) {
        this.asiakasEmail = asiakasEmail;
    }

    public Naytos getNaytos() {
        return naytos;
    }

    public void setNaytos(Naytos naytos) {
        this.naytos = naytos;
    }

    /**
     * Palauttaa kaikki tiettyö varausta vastaavat istumapaikat merkkijonona
     * @return istumapaikat merkkijonona
     */
    public String getIstumapaikat() {
        StringBuilder sb = new StringBuilder();
        for (Istumapaikka i : istumapaikat) {
            sb.append(i.toString());
        }
        return sb.toString();
    }

    public void setIstumapaikat(ArrayList<Istumapaikka> istumapaikat) {
        this.istumapaikat = istumapaikat;
    }

    @Override
    public String toString() {
        return asiakasEmail + ", " + naytos + ", Istumapaikat: " + getIstumapaikat();
    }
}