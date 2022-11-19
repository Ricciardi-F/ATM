package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import code.Businnes_Logic.Euro;

public class EuroTest {
    @Test
    void testGetValore() {
        Euro importo = new Euro(10);
        assertEquals(10*100, importo.getValore());

        Euro importoNegativo = new Euro(-10);
        assertEquals(-10*100, importoNegativo.getValore());

        Euro importoConCentesimi = new Euro(10, 35);
        assertEquals(1035, importoConCentesimi.getValore());  
           
        Euro importoNegativoConCentesimi = new Euro(-10, 35);
        assertEquals(-1035, importoNegativoConCentesimi.getValore());
    }

    @Test
    void testMinoreDi() {
        Euro importoMaj = new Euro(20);
        Euro importoMin = new Euro(10);
        assertTrue(importoMin.minoreDi(importoMaj));
    }

    @Test
    void testSomma() {
        var val1 = 10;
        var val2 = 20;
        var totale = 30*100;
        Euro importo1 = new Euro(val1);
        Euro importo2 = new Euro(val2);
        importo1.somma(importo2);
        assertEquals(totale ,importo1.getValore());

    }

    @Test
    void testSottrai() {
        var val1 = 20;
        var val2 = 10;
        var totale = 10*100;
        Euro importo1 = new Euro(val1);
        Euro importo2 = new Euro(val2);
        importo1.sottrai(importo2);
        assertEquals(totale ,importo1.getValore());
    }

    @Test
    void testStampa() {
        Euro importoIntero = new Euro(100);
        assertEquals("100.0 euro" ,importoIntero.stampa());

        Euro importoConVirgola = new Euro(100, 35);
        assertEquals("100.35 euro" ,importoConVirgola.stampa());
        
        Euro importoConVirgolaNegativo = new Euro(-100, 35);
        assertEquals("-100.35 euro" ,importoConVirgolaNegativo.stampa());
    }

    @Test
    void testUgualeA() {
        Euro e = new Euro(10);
        Euro f = new Euro(10);
        assertTrue(e.ugualeA(f));
    }

    @Test
    void testUgualeANegativo() {
        Euro e = new Euro(-10);
        Euro f = new Euro(-10);
        assertTrue(e.ugualeA(f));
    }
}
