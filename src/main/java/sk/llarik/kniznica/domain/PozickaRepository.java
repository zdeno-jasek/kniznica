package sk.llarik.kniznica.domain;

import java.util.Collection;

public interface PozickaRepository {
    void save(Pozicka pozicka);

    Collection<Pozicka> findAll();
}
