import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NaytosTest {

    Elokuva elokuva = new Elokuva("Kummisetä", 175, "englanti", "rikos", Ikasuositus.K18);
    LocalDateTime aika = LocalDateTime.now();
    Sali sali = new Sali(1, 10,10);
    Naytos naytos = new Naytos(elokuva, sali, aika);

    @Test
    void getElokuva() {
        assertEquals(elokuva, naytos.getElokuva());
        assertEquals(elokuva.getNimi(), naytos.getElokuva().getNimi());
    }

    @Test
    void getNaytosaika() {
        assertEquals(aika, naytos.getNaytosaika());
    }

    @Test
    void getSali() {
        assertSame(sali, naytos.getSali());
        assertEquals(1, sali.getSalinumero());
    }

    @Test
    void getVaraukset() {
        boolean[][] matriisi = new boolean[10][10];
        assertArrayEquals(matriisi[1], naytos.getVaraukset()[1]);
    }

    @Test
    void setVaratuksi() {
        assertFalse(naytos.getVaraukset()[1][1]);
        assertTrue(naytos.setVaratuksi(1, 1));
    }

    @Test
    void setVapaaksi() {
        assertFalse(naytos.setVapaaksi(1,2));
    }

    @Test
    void onkoPaikkaVapaa() {
        assertFalse(naytos.getVaraukset()[1][3]);
        naytos.setVaratuksi(1,5);
        assertTrue(naytos.onkoPaikkaVapaa(1,5));
    }

    @Test
    void testToString() {
        assertEquals(naytos.toString(), "Elokuva: Kummisetä, Näytösaika: " +
                naytos.getNaytosaika() + ", Sali: 1");
    }
}