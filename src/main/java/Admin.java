/**
 * Luokka mallintaa ylläpitäjää.
 */
public class Admin extends User{

    public Admin(String nimi, String email, String salasana) {
        super(nimi, email, salasana);
    }

    public String getTyyppi() {
        return "admin";
    }
}
