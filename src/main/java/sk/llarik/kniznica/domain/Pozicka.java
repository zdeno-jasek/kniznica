package sk.llarik.kniznica.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "pozicka")
public class Pozicka {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "citatelsky_preukaz_id")
    private CitatelskyPreukaz citatelskyPreukaz;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kniha_id")
    private Kniha kniha;
    @Embedded
    private Interval platnost;
    @Column
    private LocalDateTime predpokladanyKoniec;
    @Column
    private String serioveCislo;

    public Pozicka(Kniha kniha, CitatelskyPreukaz citatelskyPreukaz, String serioveCislo) {
        this.kniha = Objects.requireNonNull(kniha);
        this.citatelskyPreukaz = Objects.requireNonNull(citatelskyPreukaz);
        this.serioveCislo = Objects.requireNonNull(serioveCislo);

        this.platnost = new Interval();
        this.predpokladanyKoniec = platnost.getZaciatok().plusDays(30);
        kniha.znizPocetKusov();
    }

    @Deprecated
    Pozicka() {
        // JPA
    }

    public long getId() {
        return id;
    }

    public CitatelskyPreukaz getCitatelskyPreukaz() {
        return citatelskyPreukaz;
    }

    public Kniha getKniha() {
        return kniha;
    }

    public Interval getPlatnost() {
        return platnost;
    }

    public LocalDateTime getPredpokladanyKoniec() {
        return predpokladanyKoniec;
    }

    public String getSerioveCislo() {
        return serioveCislo;
    }
}
