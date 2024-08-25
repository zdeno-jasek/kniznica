package sk.llarik.kniznica.domain;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity(name = "kniha")
public class Kniha {
    private static final Logger LOG = LoggerFactory.getLogger(Kniha.class);

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String nazov;

    @Column
    private String autor;
    @Column
    private Integer celkovyPocet;
    @Column
    private Integer pocetDostupnychVytlackov;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "kniha_id")
    private Collection<VytlacokKnihy> vytlacky;

    Kniha(String nazov, String autor, Collection<String> serioveCisla) {
        this.nazov = Objects.requireNonNull(nazov);
        this.autor = Objects.requireNonNull(autor);
        this.vytlacky = serioveCisla.stream()
                .distinct()
                .filter(Objects::nonNull)
                .map(VytlacokKnihy::new)
                .toList();
        this.celkovyPocet = vytlacky.size();
        this.pocetDostupnychVytlackov = this.celkovyPocet;
    }

    @Deprecated
    Kniha() {
        // JPA
    }

    public Collection<String> getPlatneSerioveCisla() {
        return vytlacky.stream()
                .filter(vytlacok -> vytlacok.isPlatny())
                .map(VytlacokKnihy::getSerioveCislo)
                .toList();
    }

    public long getId() {
        return id;
    }

    public String getNazov() {
        return nazov;
    }

    public String getAutor() {
        return autor;
    }

    public int getCelkovyPocet() {
        return celkovyPocet == null ? 0 : celkovyPocet;
    }

    public int getPocetDostupnychVytlackov() {
        return pocetDostupnychVytlackov == null ? 0 : pocetDostupnychVytlackov;
    }

    public void addSerialNumbers(Collection<String> serioveCisla) {
        Collection<String> pouziteSerioveCisla = vytlacky.stream()
                .map(VytlacokKnihy::getSerioveCislo)
                .toList();
        vytlacky = new ArrayList<>(vytlacky);
        vytlacky.addAll( serioveCisla.stream()
                        .filter(serioveCislo -> !pouziteSerioveCisla.contains(serioveCislo))
                        .map(VytlacokKnihy::new)
                        .toList() );
        int pozicanych = celkovyPocet - pocetDostupnychVytlackov;
        this.celkovyPocet = vytlacky.size();
        this.pocetDostupnychVytlackov = this.celkovyPocet - pozicanych;
    }

    public void znizPocetKusov() {
        if (pocetDostupnychVytlackov == 0) {
            LOG.warn("Kniha {} uz nema dostupne vytlacky, napriek tomu bola pozicana", nazov);
        }
        pocetDostupnychVytlackov--;
    }
}
