import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NaytosTest {

    LocalDateTime aika = LocalDateTime.now();
    Sali sali = new Sali(1, 10,10);
    Naytos naytos = new Naytos("Kummisetä", sali, aika);

    @Test
    void getElokuvanNimi() {
        assertEquals("Kummisetä", naytos.getElokuvanNimi());
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