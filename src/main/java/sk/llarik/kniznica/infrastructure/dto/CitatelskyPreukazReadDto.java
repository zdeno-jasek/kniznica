package sk.llarik.kniznica.infrastructure.dto;

import java.time.LocalDateTime;

public class CitatelskyPreukazReadDto {
    public long id;
    public String citatelMeno;
    public String citatelPriezvisko;
    public String cisloCitatelskhoPreukazu;
    public LocalDateTime datumVydania;
    public LocalDateTime datumUkoncenia;
}
