package sk.llarik.kniznica.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.llarik.kniznica.domain.CitatelskyPreukaz;
import sk.llarik.kniznica.domain.CitatelskyPreukazRepository;

import java.util.Optional;

@Repository
public class CitatatelskyPreukazRepositoryDb implements CitatelskyPreukazRepository {

    @Autowired
    private CitatelskyPreukazRepositorySpringData citatelskyPreukazRepositorySpringData;
    @Override
    public Long getNextSequenceValue() {
        return citatelskyPreukazRepositorySpringData.getNextSequenceValue();
    }

    @Override
    public void save(CitatelskyPreukaz citatelskyPreukaz) {
        citatelskyPreukazRepositorySpringData.save(citatelskyPreukaz);
    }

    @Override
    public Optional<CitatelskyPreukaz> findById(long id) {
        return citatelskyPreukazRepositorySpringData.findById(id);
    }
}
