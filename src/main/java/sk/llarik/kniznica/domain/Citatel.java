package sk.llarik.kniznica.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Citatel {
    @Column
    private String meno;
    @Column
    private String priezvisko;

    Citatel(String meno, String priezvisko) {
        this.meno = meno;
        this.priezvisko = priezvisko;
    }

    @Deprecated
    Citatel() {
        // JPA
    }

    public String getMeno() {
        return meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }
}
