package sk.llarik.kniznica.domain;

import java.util.Optional;

public interface CitatelskyPreukazRepository {

    Long getNextSequenceValue();
    void save(CitatelskyPreukaz citatelskyPreukaz);
    Optional<CitatelskyPreukaz> findById(long id);
}
