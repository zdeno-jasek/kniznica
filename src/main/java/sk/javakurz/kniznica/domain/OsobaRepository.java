package sk.javakurz.kniznica.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Pojmom "Repository" označujeme interface-y, ktoré umožňujú prístup k objektom
 * uloženým v úložisku dát (databáza, súbor, XML, služba).
 * JpaRepository ukazuje na spôsob ukladania do databázy.
 * Tento interface získa veľa metód práve z JpaRepository.
 * Sú to metódy určené na zápis objektov ako aj na ich čítanie.
 */
@Repository
public interface OsobaRepository extends JpaRepository<Osoba, Long> {

	Optional<Osoba> findByRodneCislo( String rodneCislo );
}
