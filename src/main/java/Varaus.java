import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Luokka mallintaa näytökseen tehtyä istumapaikan varausta salissa.
 */
public class Varaus implements Serializable {

    @Serial
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

    public Naytos getNaytos() {
        return naytos;
    }

    /**
     * Palauttaa kaikki tiettyö varausta vastaavat istumapaikat merkkijonona
     * @return istumapaikat listana
     */
    public ArrayList<Istumapaikka> getIstumapaikat() {
        return istumapaikat;
    }

    /**
     * Palauttaa istumapaikat merkkijonona
     * @param istumapaikat lista istumapaikoista
     * @return istumapaikat merkkijonona
     */
    public String istumapaikatStr(ArrayList<Istumapaikka> istumapaikat) {
        StringBuilder sb = new StringBuilder();
        for (Istumapaikka istumapaikka : istumapaikat) {
            sb.append("(Rivi: " + istumapaikka.getRivi() + ", Paikka: " + istumapaikka.getPaikkaRivilla() + ") ");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return asiakasEmail + ", " + naytos + ", Istumapaikat: " + istumapaikatStr(istumapaikat);
    }
}