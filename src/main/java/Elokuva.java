import java.time.LocalDateTime;
import java.util.ArrayList;

public class Elokuva {
    private String nimi;
    private int kesto;
    private String genre;
    private int ikaraja;
    private String kieli;
    private ArrayList<Naytos> naytokset;

    public Elokuva(String nimi, int kesto, String kieli, String genre, int ikaraja) {
        this.nimi = nimi;
        this.kesto = kesto;
        this.genre = genre;
        this.kieli = kieli;
        this.ikaraja = ikaraja;
        naytokset = new ArrayList<>();
    }

    public String getNimi() {
        return nimi;
    }

    public int getKesto() {
        return kesto;
    }

    public String getGenre() {
        return genre;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setKesto(int kesto) {
        this.kesto = kesto;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getIkaraja() {
        return ikaraja;
    }

    public void setIkaraja(int ikaraja) {
        this.ikaraja = ikaraja;
    }

    public String getKieli() {
        return kieli;
    }

    public void setKieli(String kieli) {
        this.kieli = kieli;
    }
}
