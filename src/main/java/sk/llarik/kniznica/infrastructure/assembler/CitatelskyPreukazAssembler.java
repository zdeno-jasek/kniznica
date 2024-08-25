package sk.llarik.kniznica.infrastructure.assembler;

import org.springframework.stereotype.Component;
import sk.llarik.kniznica.domain.CitatelskyPreukaz;
import sk.llarik.kniznica.infrastructure.dto.CitatelskyPreukazReadDto;

import java.util.function.Function;

@Component
class CitatelskyPreukazAssembler implements Function<CitatelskyPreukaz, CitatelskyPreukazReadDto>{

    public CitatelskyPreukazReadDto apply(CitatelskyPreukaz citatelskyPreukaz ) {
        CitatelskyPreukazReadDto dto = new CitatelskyPreukazReadDto();
        dto.id = citatelskyPreukaz.getId();
        dto.citatelMeno = citatelskyPreukaz.getCitatel().getMeno();
        dto.citatelPriezvisko = citatelskyPreukaz.getCitatel().getPriezvisko();
        dto.cisloCitatelskhoPreukazu = citatelskyPreukaz.getCisloCitatelskehoPreukazu();
        dto.datumVydania = citatelskyPreukaz.getDatumVydania();
        dto.datumUkoncenia = citatelskyPreukaz.getDatumUkoncenia();
        return dto;
    }
}
