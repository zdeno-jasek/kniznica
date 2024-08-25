package sk.llarik.kniznica.infrastructure.dto;

import java.time.LocalDateTime;

public class PozickaReadDto {
    public long id;
    public String citatelMeno;
    public String citatelPriezvisko;
    public String cisloCitatelskhoPreukazu;
    public String knihaNazov;
    public String knihaAutor;
    public String serioveCislo;
    public LocalDateTime datumPozicky;
    public LocalDateTime datumVratenia;
}
