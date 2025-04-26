import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Luokkaa käytetään varausjärjestelmän tietojen tallentamiseen
 */
public class VarausjarjestelmaIO {

    private static final String EROTIN = ";";

    public static void kirjoitaTiedosto(String tiedostonNimi, String sisalto) {
        try (PrintWriter tiedosto = new PrintWriter(tiedostonNimi)) {
            tiedosto.write(sisalto);
        } catch (FileNotFoundException e) {
            System.out.println("Tapahtui virhe: " + e);
            System.out.println("Tämä johtuu todennäköisesti siitä, että tiedostoa " + tiedostonNimi + " ei ole olemassa.");
            System.out.println("Kokeile tallentaa tiedosto ennen sen latausta.");
        }
    }

    public static void kirjoitaAsiakkaat(ArrayList<Asiakas> asiakasLista, String tiedostonNimi) {
        String data = "";
        for (Asiakas asiakas : asiakasLista) {
            data += asiakas.getData(VarausjarjestelmaIO.EROTIN);
            data += "\n";
        }

        if (data.length() > 0) {
            data = data.substring(0, data.length() - 1);
        }
        kirjoitaTiedosto(tiedostonNimi, data);
    }

    public static void kirjoitaElokuvat(ArrayList<Elokuva> elokuvavalikoima, String tiedostonNimi) {

    }

    /**
     * Palauttaa uuden näytösolion annetun datarivin perusteella.
     * Rivillä tulee olla elokuvan nimi, näytösaika ja salin numero.
     *
     * @param data datarivi, josta tiedot parsitaan
     * @return uuden Naytos-olion, jolla tyhjä varauslista.
     */
    public static Naytos parsiNaytos(String data) {
        return null;
    }
}
