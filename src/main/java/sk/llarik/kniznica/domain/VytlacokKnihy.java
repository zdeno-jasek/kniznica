package sk.llarik.kniznica.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "vytlacok_knihy")
public class VytlacokKnihy {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String serioveCislo;

    @Embedded
    private Interval platnost;

    VytlacokKnihy(String serioveCislo)  {
        this.serioveCislo = Objects.requireNonNull(serioveCislo);
        this.platnost = new Interval();
    }

    @Deprecated
    VytlacokKnihy() {
        // JPA
    }

    public boolean isPlatny() {
        return platnost.obsahujeDatum(LocalDateTime.now());
    }

    public String getSerioveCislo() {
        return serioveCislo;
    }
}
