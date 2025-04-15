/**
 * Luokka mallintaa käyttäjää varausjärjestelmässä.
 */
public class User {
    private String nimi;
    private String email;
    private String salasana;
    private String kayttajanimi;

    public User(String nimi, String email, String kayttajanimi, String salasana) {
        this.nimi = nimi;
        this.email = email;
        this.kayttajanimi = kayttajanimi;
        this.salasana = salasana;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalasana() {
        return salasana;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    public String getKayttajanimi() {
        return kayttajanimi;
    }

    public void setKayttajanimi(String kayttajanimi) {
        this.kayttajanimi = kayttajanimi;
    }
}
