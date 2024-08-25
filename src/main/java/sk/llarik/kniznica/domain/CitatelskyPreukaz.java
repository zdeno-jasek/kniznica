package sk.llarik.kniznica.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "citatelsky_preukaz")
public class CitatelskyPreukaz {
    private transient final String formatCisla = "%06d";

    @Id
    @GeneratedValue
    private long id;

    @Embedded
    private Citatel citatel;
    @Column
    private String cisloCitatelskehoPreukazu;
    @Embedded
    private Interval platnost;

    public CitatelskyPreukaz(String meno, String priezvisko, long cisloCitatelskehoPreukazu) {
        this.citatel = new Citatel(meno, priezvisko);
        this.cisloCitatelskehoPreukazu = String.format(formatCisla, cisloCitatelskehoPreukazu);
        this.platnost = new Interval();
    }

    @Deprecated
    CitatelskyPreukaz() {
        // JPA
    }

    public long getId() {
        return id;
    }

    public Citatel getCitatel() {
        return citatel;
    }

    public String getCisloCitatelskehoPreukazu() {
        return cisloCitatelskehoPreukazu;
    }

    public LocalDateTime getDatumVydania() {
        return platnost.getZaciatok();
    }

    public LocalDateTime getDatumUkoncenia() {
        return platnost.getKoniec();
    }
}
