package sk.javakurz.kniznica.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Kniha2Repository extends JpaRepository<Kniha2, Long> {

}
