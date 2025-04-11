import java.time.LocalDateTime;
import java.util.ArrayList;

public class Naytos {
    private Elokuva elokuva;
    private LocalDateTime naytosaika;
    private Sali sali;
    private ArrayList<Varaus> varaukset;

    public Naytos(Elokuva elokuva, LocalDateTime naytosaika, Sali sali) {
        this.elokuva = elokuva;
        this.naytosaika = naytosaika;
        this.sali = sali;
        varaukset = new ArrayList<>();
    }

    public Elokuva getElokuva() {
        return elokuva;
    }

    public void setElokuva(Elokuva elokuva) {
        this.elokuva = elokuva;
    }

    public LocalDateTime getNaytosaika() {
        return naytosaika;
    }

    public void setNaytosaika(LocalDateTime naytosaika) {
        this.naytosaika = naytosaika;
    }

    public Sali getSali() {
        return sali;
    }

    public void setSali(Sali sali) {
        this.sali = sali;
    }

    public ArrayList<Varaus> getVaraukset() {
        return varaukset;
    }

    public void setVaraukset(ArrayList<Varaus> varaukset) {
        this.varaukset = varaukset;
    }
}
