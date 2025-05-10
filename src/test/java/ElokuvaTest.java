import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElokuvaTest {

    Elokuva elokuva = new Elokuva("The Truman Show", 103, "englanti", "komedia/sci-fi", Ikasuositus.K12);

    @Test
    void getNimi() {
        assertEquals("The Truman Show", elokuva.getNimi());
    }

    @Test
    void getGenre() {
        assertEquals("komedia/sci-fi", elokuva.getGenre());
    }

    @Test
    void getIkasuositus() {
        assertEquals(Ikasuositus.K12, elokuva.getIkasuositus());
    }

    @Test
    void getKieli() {
        assertEquals("englanti", elokuva.getKieli());
    }

    @Test
    void getKestoTunnitMinuutit() {
        assertEquals("1h 43min", elokuva.getKestoTunnitMinuutit());
    }

    @Test
    void getData() {
        assertEquals("The Truman Show;103;komedia/sci-fi;K12;englanti",elokuva.getData(";"));
    }

    @Test
    void testToString() {
        assertEquals("The Truman Show (1h 43min), komedia/sci-fi, ikäsuositus: K12, kieli: englanti", elokuva.toString());
    }
}