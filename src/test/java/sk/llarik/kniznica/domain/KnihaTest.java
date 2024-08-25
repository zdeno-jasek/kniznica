package sk.llarik.kniznica.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KnihaTest {

    private List<String> serioveCisla;
    private Kniha kniha;

    @BeforeEach
    void setUp() {
        serioveCisla = List.of("123", "456");
        kniha = new Kniha("Traja mušketieri", "Dumas", serioveCisla);
    }

    @Test
    void akPoslemDveSerioveCislaTakPocetKnihJeDva() {
        assertEquals(2, kniha.getCelkovyPocet());
    }

    @Test
    void knihaObsahujeZaslaneSerioveCislaAkoPlatne() {
        assertEquals(serioveCisla, kniha.getPlatneSerioveCisla());
    }

    @Test
    void akPridamNoveSerioveCisloTakSaMiZvysiCelkovyPocet() {
        kniha.addSerialNumbers(List.of("789"));
        assertEquals(3, kniha.getCelkovyPocet());
    }

    @Test
    void akPridamNoveSerioveCisloTakSaMiZvysiPocetDostupnych() {
        kniha.addSerialNumbers(List.of("789"));
        assertEquals(3, kniha.getPocetDostupnychVytlackov());
    }

    @Test
    void akPridamExistujuceSerioveCisloTakSaMiNicNezvysi() {
        kniha.addSerialNumbers(List.of(serioveCisla.getFirst()));
        assertEquals(2, kniha.getPocetDostupnychVytlackov());
        assertEquals(2, kniha.getCelkovyPocet());
        assertEquals(serioveCisla, kniha.getPlatneSerioveCisla());
    }
}