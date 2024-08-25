package sk.llarik.kniznica.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.llarik.kniznica.domain.Kniha;

import java.util.Collection;

@Repository
public interface KnihaRepository extends JpaRepository<Kniha, Long>{

    Collection<Kniha> findByNazovIgnoreCase(String nazov);
    Collection<Kniha> findByAutorAndNazovIgnoreCase(String autor, String nazov);
    Collection<Kniha> findByVytlackySerioveCisloIn(Collection<String> serioveCisla);
}
