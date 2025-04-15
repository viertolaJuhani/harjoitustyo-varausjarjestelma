/**
 * Luokka mallintaa ylläpitäjää.
 */
public class Admin extends User{

    public Admin(String nimi, String email, String kayttajanimi, String salasana) {
        super(nimi, email, kayttajanimi, salasana);
    }

    public String getTyyppi() {
        return "admin";
    }
}
