package sk.llarik.kniznica.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.llarik.kniznica.domain.Pozicka;

public interface PozickaRepositorySpringData extends JpaRepository<Pozicka, Long> {
}
