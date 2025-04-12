/**
 * Luokka mallintaa istumapaikkaa elokuvasalissa.
 */
public class Istumapaikka {
    private int istuinId;

    public Istumapaikka(int istuinId) {
        this.istuinId = istuinId;
    }

    public int getIstuinId() {
        return istuinId;
    }

    public void setIstuinId(int istuinId) {
        this.istuinId = istuinId;
    }
}