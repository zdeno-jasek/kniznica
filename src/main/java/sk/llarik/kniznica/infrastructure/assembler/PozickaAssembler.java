package sk.llarik.kniznica.infrastructure.assembler;

import org.springframework.stereotype.Component;
import sk.llarik.kniznica.domain.Pozicka;
import sk.llarik.kniznica.infrastructure.dto.PozickaReadDto;

import java.util.function.Function;

@Component
public class PozickaAssembler implements Function<Pozicka, PozickaReadDto> {
    @Override
    public PozickaReadDto apply(Pozicka pozicka) {
        PozickaReadDto dto = new PozickaReadDto();
        dto.id = pozicka.getId();
        dto.citatelMeno = pozicka.getCitatelskyPreukaz().getCitatel().getMeno();
        dto.citatelPriezvisko = pozicka.getCitatelskyPreukaz().getCitatel().getPriezvisko();
        dto.cisloCitatelskhoPreukazu = pozicka.getCitatelskyPreukaz().getCisloCitatelskehoPreukazu();
        dto.knihaNazov = pozicka.getKniha().getNazov();
        dto.knihaAutor = pozicka.getKniha().getAutor();
        dto.serioveCislo = pozicka.getSerioveCislo();
        dto.datumPozicky = pozicka.getPlatnost().getZaciatok();
        dto.datumVratenia = pozicka.getPlatnost().getKoniec();
        return dto;
    }
}
