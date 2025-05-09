import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NaytosTest {

    Sali sali = new Sali(1, 10,10);
    Naytos naytos = new Naytos("Kummisetä", sali, "9.5, 21:00");

    @Test
    void getElokuvanNimi() {
        assertEquals("Kummisetä", naytos.getElokuvanNimi());
    }

    @Test
    void getNaytosaika() {
        assertEquals("9.5, 21:00", naytos.getNaytosaika());
    }

    @Test
    void getSali() {
    }

    @Test
    void getVaraukset() {
    }

    @Test
    void setVaratuksi() {
    }

    @Test
    void setVapaaksi() {
    }

    @Test
    void onkoPaikkaVapaa() {
    }

    @Test
    void testToString() {
    }
}