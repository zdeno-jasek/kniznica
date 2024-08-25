package sk.llarik.kniznica.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.llarik.kniznica.domain.Pozicka;
import sk.llarik.kniznica.domain.PozickaRepository;

import java.util.Collection;
import java.util.List;

@Repository
class PozickaRepositoryDb implements PozickaRepository {

    @Autowired
    private PozickaRepositorySpringData pozickaRepositorySpringData;
    @Override
    public void save(Pozicka pozicka) {
        pozickaRepositorySpringData.save(pozicka);
    }

    @Override
    public Collection<Pozicka> findAll() {
        return pozickaRepositorySpringData.findAll();
    }
}
