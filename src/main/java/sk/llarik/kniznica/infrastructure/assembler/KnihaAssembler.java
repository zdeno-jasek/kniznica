package sk.llarik.kniznica.infrastructure.assembler;

import org.springframework.stereotype.Component;
import sk.llarik.kniznica.domain.Kniha;
import sk.llarik.kniznica.infrastructure.dto.KnihaReadDto;

import java.util.function.Function;

@Component
class KnihaAssembler implements Function<Kniha,KnihaReadDto> {

    public KnihaReadDto apply(Kniha kniha) {
        KnihaReadDto dto = new KnihaReadDto();
        dto.id = kniha.getId();
        dto.nazov = kniha.getNazov();
        dto.autor = kniha.getAutor();
        dto.celkovyPocet = kniha.getCelkovyPocet() ;
        dto.pocetDostupnychVytlackov = kniha.getPocetDostupnychVytlackov();
        return dto;
    }
}
