package sk.llarik.kniznica.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sk.llarik.kniznica.infrastructure.repository.KnihaRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KnihaFactoryTest {

    private List<String> serioveCisla;
    private Kniha kniha;
    private KnihaFactory knihaFactory;
    private KnihaRepository knihaRepository;

    @BeforeEach
    void setUp() {
        serioveCisla = List.of("123", "456");
        kniha = new Kniha("Traja mušketieri", "Dumas", serioveCisla);
        knihaRepository = Mockito.mock(KnihaRepository.class);
        knihaFactory = new KnihaFactory(knihaRepository);

        Mockito.when(knihaRepository.findByVytlackySerioveCisloIn(serioveCisla)).thenReturn(List.of(kniha));
    }

    @Test
    void akVytvorimKnihuSPouzitymiSeriovymiCislamiTakDostanemVynimku() {
        assertThrows(IllegalArgumentException.class, () -> knihaFactory.create("Traja mušketieri", "Dumas", serioveCisla));
    }

    @Test
    void knihaSNepouzivanymiSeriovymiCislamiSaVytvori() {
        Kniha kniha = knihaFactory.create("Traja mušketieri", "Dumas", List.of("789"));
        assertNotNull(kniha);
    }
}