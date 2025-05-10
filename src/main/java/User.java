/**
 * Luokka mallintaa käyttäjää varausjärjestelmässä.
 */
public class User {
    private String nimi;
    private String email;
    private String salasana;

    public User(String nimi, String email, String salasana) {
        this.nimi = nimi;
        this.email = email;
        this.salasana = salasana;
    }

    public String getNimi() {
        return nimi;
    }

    public String getEmail() {
        return email;
    }

    public String getSalasana() {
        return salasana;
    }

    /**
     * Muuttaa käyttäjän tiedot tiedostoon kirjoitettavaan muotoon
     * @param erotinmerkki merkki, jolla käyttäjän tiedot erotellaan
     * @return tiedostoon kirjoitettava muoto
     */
    public String getData(String erotinmerkki) {
        return nimi + erotinmerkki + email + erotinmerkki + salasana;
    }

    @Override
    public String toString() {
        return "Nimi: " + nimi + ", email: " + email;
    }

}