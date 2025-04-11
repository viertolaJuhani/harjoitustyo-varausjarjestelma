public class Istumapaikka {
    private int istuinId;
    private boolean varattu;

    public Istumapaikka(int istuinId, boolean varattu) {
        this.istuinId = istuinId;
        this.varattu = false;
    }

    public int getIstuinId() {
        return istuinId;
    }

    public void setIstuinId(int istuinId) {
        this.istuinId = istuinId;
    }

    public boolean onkoVarattu() {
        return varattu;
    }

    public void setVarattu(boolean varattu) {
        this.varattu = varattu;
    }

    public void teeVaraus() {
        this.varattu = true;
    }

}