package sk.llarik.kniznica.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public final class Interval {
    private static final LocalDateTime DATE_MAX = LocalDateTime.of(3000, 1, 1, 0, 0);

    @Column
    private LocalDateTime zaciatok;
    @Column
    private LocalDateTime koniec;

    public Interval() {
        this(LocalDateTime.now(), DATE_MAX);
    }

    public Interval ukonci(LocalDateTime kedy ) {
        return new Interval( zaciatok, Objects.requireNonNull(kedy) );
    }

    private Interval(LocalDateTime zaciatok, LocalDateTime koniec ) {
        if (zaciatok.isAfter(koniec)) {
            throw new IllegalArgumentException("Kedy je skor ako zaciatkok");
        }
        this.zaciatok = zaciatok;
        this.koniec = koniec;
    }

    public LocalDateTime getZaciatok() {
        return zaciatok;
    }

    public LocalDateTime getKoniec() {
        return koniec;
    }

    public boolean obsahujeDatum(LocalDateTime datum) {
        if ( datum == null ) {
            return false;
        }
        return datum.isEqual(zaciatok) || datum.isEqual(koniec) || ( datum.isAfter(zaciatok) && datum.isBefore(koniec) );
    }
}
