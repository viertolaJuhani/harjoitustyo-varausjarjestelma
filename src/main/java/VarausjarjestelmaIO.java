import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Luokkaa käytetään varausjärjestelmän tietojen tallentamiseen ja lukemiseen
 */
public class VarausjarjestelmaIO {

    public static void main(String[] args) {
        // Testikoodia
        System.out.print(lueKayttajat("kayttajat.txt"));
    }

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

    public static ArrayList<String> lueTiedosto(String tiedostonNimi) {
        ArrayList<String> data = new ArrayList<>();
        try (Scanner lukija = new Scanner(new File(tiedostonNimi))) {
            while (lukija.hasNextLine()) {
                data.add(lukija.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Tapahtui virhe: " + e);
        }
        return data;
    }

    public static void kirjoitaKayttajat(ArrayList<User> kayttajaLista, String tiedostonNimi) {
        String data = "";
        for (User u : kayttajaLista) {
            data += u.getData(VarausjarjestelmaIO.EROTIN);
            data += "\n";
        }

        if (data.length() > 0) {
            data = data.substring(0, data.length() - 1);
        }
        kirjoitaTiedosto(tiedostonNimi, data);
    }

    public static ArrayList<User> lueKayttajat(String tiedostonNimi) {
        ArrayList<User> asiakkaat = new ArrayList<>();
        ArrayList<String> data = lueTiedosto(tiedostonNimi);
        for (String adata : data) {
            User as = parsiKayttaja(adata);
            asiakkaat.add(as);
        }
        return asiakkaat;
    }

    public static User parsiKayttaja(String data) {
        String[] tiedot = data.split(VarausjarjestelmaIO.EROTIN);
        String nimi = tiedot[0];
        String email = tiedot[1];
        String salasana = tiedot[2];
        if (tiedot.length == 4) {
            int ika = Integer.valueOf(tiedot[3]);
            return new Asiakas(nimi, email, salasana, ika);
        } else
            return new Admin(nimi, email, salasana);
    }

    public static void kirjoitaElokuvat(ArrayList<Elokuva> elokuvalista, String tiedostonNimi) {
        String data = "";
        for (Elokuva elokuva : elokuvalista) {
            data += elokuva.getData(VarausjarjestelmaIO.EROTIN);
            data += "\n";
        }
        if (data.length() > 0) {
            data = data.substring(0, data.length() - 1);
        }
        kirjoitaTiedosto(tiedostonNimi, data);
    }

    public static ArrayList<Elokuva> lueElokuvat(String tiedostonNimi) {
        ArrayList<Elokuva> elokuvat = new ArrayList<>();
        ArrayList<String> data = lueTiedosto(tiedostonNimi);
        for (String edata : data) {
            Elokuva elokuva = parsiElokuva(edata);
            elokuvat.add(elokuva);
        }
        return elokuvat;
    }

    public static Elokuva parsiElokuva(String data) {
        String[] tiedot = data.split(VarausjarjestelmaIO.EROTIN);
        String nimi = tiedot[0];
        int kesto = Integer.valueOf(tiedot[1]);
        String genre = tiedot[2];
        int ikaraja = Integer.valueOf(tiedot[3]);
        String kieli = tiedot[4];

        return new Elokuva(nimi, kesto, kieli, genre, ikaraja);
    }

    public static void kirjoitaNaytokset(ArrayList<Naytos> naytokset, String tiedostonNimi) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tiedostonNimi))) {
            oos.writeObject(naytokset);
        } catch (IOException e) {
            System.out.println("Tapahtui virhe: " + e);
        }
    }

    public static ArrayList<Naytos> lueNaytokset(String tiedostonNimi) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tiedostonNimi))) {
            return (ArrayList<Naytos>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Tapahtui virhe: " + e);
            return new ArrayList<>();
        }
    }

    public static void kirjoitaVaraukset(ArrayList<Varaus> varaukset, String tiedostonNimi) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tiedostonNimi))) {
            oos.writeObject(varaukset);
        } catch (IOException e) {
            System.out.println("Tapahtui virhe: " + e);
        }
    }

    public static ArrayList<Varaus> lueVaraukset(String tiedostonNimi) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tiedostonNimi))) {
            return (ArrayList<Varaus>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Tapahtui virhe: " + e);
            return new ArrayList<>();
        }
    }
}