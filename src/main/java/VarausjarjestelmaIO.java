import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Luokkaa käytetään varausjärjestelmän tietojen tallentamiseen ja lukemiseen
 */
public class VarausjarjestelmaIO {

    public static void main(String[] args) {
        // Testikoodia
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

    public static ArrayList<Asiakas> lueAsiakkaat(String tiedostonNimi) {
        ArrayList<Asiakas> asiakkaat = new ArrayList<>();
        ArrayList<String> data = lueTiedosto(tiedostonNimi);
        for (String adata : data) {
            Asiakas as = parsiAsiakas(adata);
            asiakkaat.add(as);
        }
        return asiakkaat;
    }

    public static Asiakas parsiAsiakas(String data) {
        String[] tiedot = data.split(VarausjarjestelmaIO.EROTIN);
        String email = tiedot[0];
        String salasana = tiedot[1];
        String nimi = tiedot[2];
        int ika = Integer.valueOf(tiedot[3]);
        String varausStr = tiedot[4];
        varausStr = varausStr.replace("[", "").replace("]", "");
        ArrayList<Varaus> varaukset = new ArrayList<>();
        String[] vTiedot = varausStr.split(",");


        return new Asiakas(nimi, email, salasana, ika, varaukset);
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
}