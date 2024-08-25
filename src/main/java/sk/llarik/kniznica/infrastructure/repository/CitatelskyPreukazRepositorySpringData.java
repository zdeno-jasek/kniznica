package sk.llarik.kniznica.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sk.llarik.kniznica.domain.CitatelskyPreukaz;

@Repository
interface CitatelskyPreukazRepositorySpringData extends JpaRepository<CitatelskyPreukaz, Long> {

    @Query(value = "SELECT NEXTVAL('cislo_preukazu')", nativeQuery = true)
    Long getNextSequenceValue();
}
